package cn.pyk.controller;

import cn.pyk.entity.User;
import cn.pyk.mapper.UserMapper;
import cn.pyk.service.impl.UserService;
import cn.pyk.util.Result;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;



    @GetMapping("/getUser")
    public Object getUser(){

        return userService.getUser();
    }

    @GetMapping("insertUser")
    public boolean insertUser(){
        return true;
    }

    //上传头像功能
    private String filePath = "D:\\idea\\ideaProject\\springboot-init\\WebFile\\\\file\\\\img";

    @PostMapping("/uploadimg")
    @ResponseBody
    public String upload(@RequestPart(value="file") MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();  //获取文件原名
        String visibleUri="/"+fileName;     //拼接访问图片的地址
        String saveUri=filePath+"/"+fileName;        //拼接保存图片的真实地址


        File saveFile = new File(saveUri);
        //判断是否存在文件夹，不存在就创建，但其实可以直接手动确定创建好，这样不用每次保存都检测
        if (!saveFile.exists()){
            saveFile.mkdirs();
        }
        try {
            file.transferTo(saveFile);  //保存文件到真实存储路径下
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(saveUri);

        return visibleUri;
    }

    @Value("${upload.path}")
    private String uploadDir;

    @PostMapping("/uploadAvatar")
    public ResponseEntity<?> uploadAvatar(@RequestParam("file") MultipartFile file,
                                          @CookieValue(value = "token", defaultValue = "") String token) {
        if (file.isEmpty()) {
            return new ResponseEntity<>("Please select a file!", HttpStatus.BAD_REQUEST);
        }

        try {
            // 保存文件到指定目录
            byte[] bytes = file.getBytes();
            String fileName = file.getOriginalFilename();
            Path path = Paths.get(uploadDir + File.separator + fileName);
            Files.write(path, bytes);

            // 获取用户信息
            User user = (User) redisTemplate.opsForValue().get("token:" + token);
            if (user == null) {
                return new ResponseEntity<>("User not found!", HttpStatus.BAD_REQUEST);
            }

            // 更新用户头像
            user.setAvatar("/uploads/" + fileName);
            userService.updateById(user);
            redisTemplate.opsForValue().set("user:" + user.getId(), user, 7, TimeUnit.DAYS);
            redisTemplate.opsForValue().set("token:" + token, user, 7, TimeUnit.DAYS);

            // 返回文件的URL
            String fileUrl = "/uploads/" + file.getOriginalFilename();
            return ResponseEntity.ok().body("{\"imageUrl\": \"" + fileUrl + "\"}");
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //用户登录
    @GetMapping("login")
    public Result doLogin(@RequestParam String username,
                           @RequestParam String password,
                           HttpServletResponse response){
        User user = userService.findUser(username,password);
        if(user != null && !user.getUsername().isEmpty()){
            //生成唯一会话ID
            String token = UUID.randomUUID().toString();
            //将用户信息缓存到redis
            redisTemplate.opsForValue().set("token:" + token,user,7, TimeUnit.DAYS);
            //创建一个cookie对象,存储会话ID
            Cookie cookie = new Cookie("token", token);
            cookie.setMaxAge(7*24*60*60); //七天过期
            cookie.setPath("/");
            response.addCookie(cookie);
            return Result.success(true, "登录成功");
        }else {
            return Result.error("400", "用户名或密码错误");
        }
    }

    //用户注册
    @PostMapping("/register")
    public Result<Boolean> doRegister(@RequestBody User user,
                                      HttpServletResponse response){
        boolean type = false;
        if (user != null){
            if(userService.insert(user)){   //将用户信息插入到数据库中
                // 将用户信息缓存到 Redis 中
                redisTemplate.opsForValue().set("user:" + user.getId(), user, 7, TimeUnit.DAYS);
                // 生成唯一会话ID (Token)
                String token = UUID.randomUUID().toString();
                // 将Token缓存到Redis
                redisTemplate.opsForValue().set("token:" + token, user, 7, TimeUnit.DAYS);
                // 创建一个Cookie对象,存储会话ID
                Cookie cookie = new Cookie("token", token);
                cookie.setMaxAge(7 * 24 * 60 * 60); // 七天过期
                cookie.setPath("/");
                response.addCookie(cookie);
                type = true;
            }
        }
        if (type){

            return Result.success(type,"注册成功");
        }
        return Result.error("500","注册失败");
    }

    //用户修改密码
    public Result<Boolean> changePwd(@RequestParam String username,
                                     @RequestParam String oldPwd,
                                     @RequestParam String newPwd,
                                     HttpServletResponse response,
                                     @CookieValue(value = "token", defaultValue = "") String token){
        //查询用户信息是否存在
        User user = userService.findUser(username,oldPwd);
        if(user == null){
            return Result.error("400","用户未注册!");
        }
        //若已存在该用户，则修改用户密码
        user.setPassword(newPwd);   //将用户密码修改为新密码
        if(userService.changePwd(user)){
            // 更新 Redis 中的用户信息
            redisTemplate.opsForValue().set("user:" + user.getId(), user, 7, TimeUnit.DAYS);
            // 更新Token对应的用户信息
            if (!token.isEmpty()) {
                redisTemplate.opsForValue().set("token:" + token, user, 7, TimeUnit.DAYS);
            }
            return Result.success(true,"密码修改成功!");
        }

        return Result.error("500","修改密码失败!");
    }

    //用户登出
    @PostMapping("/logout")
    public Result logout(HttpServletRequest request, HttpServletResponse response) {
        // 获取 token Cookie
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    // 获取 token
                    String token = cookie.getValue();
                    // 删除 Redis 中的会话信息
                    redisTemplate.delete(token);

                    // 使 Cookie 失效
                    cookie.setMaxAge(0);
                    cookie.setPath("/");
                    response.addCookie(cookie);
                    return Result.success(true, "登出成功");
                }
            }
        }
        return Result.error("400", "未登录");
    }
}
