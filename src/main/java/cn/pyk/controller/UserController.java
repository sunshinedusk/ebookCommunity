package cn.pyk.controller;

import cn.pyk.entity.User;
import cn.pyk.service.impl.UserService;
import cn.pyk.util.JwtUtil;
import cn.pyk.util.Result;
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
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

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
    @PostMapping("/login")
    public Result<User> doLogin(@RequestParam String username,
                                @RequestParam String password,
                                @RequestParam int type,
                                HttpServletResponse response) {
        User user = userService.findUser(username, password);
        if (user == null || user.getType() != type) {
            return Result.error("400", "用户名或密码错误或权限不匹配");
        }

        String token = jwtUtil.generateToken(username);
        redisTemplate.opsForValue().set("token:" + token, user, 7, TimeUnit.DAYS);

        response.setHeader("Authorization", "Bearer " + token);
        return Result.success(user, "登录成功");
    }

    //用户注册
    @PostMapping("/register")
    public Result<Boolean> doRegister(@RequestBody User user,
                                      HttpServletResponse response){
        boolean isRegistered = userService.insert(user);
        if (!isRegistered) {
            return Result.error("500", "注册失败");
        }

        redisTemplate.opsForValue().set("user:" + user.getId(), user, 7, TimeUnit.DAYS);
        String token = jwtUtil.generateToken(user.getUsername());
        redisTemplate.opsForValue().set("token:" + token, user, 7, TimeUnit.DAYS);

        response.setHeader("Authorization", "Bearer " + token);
        return Result.success(true, "注册成功");
    }

    @PostMapping("/changePwd")
    public Result<Boolean> changePwd(@RequestParam String username,
                                     @RequestParam String oldPwd,
                                     @RequestParam String newPwd,
                                     @RequestHeader("Authorization") String token) {
        User user = userService.findUser(username, oldPwd);
        if (user == null) {
            return Result.error("400", "用户未注册或密码错误");
        }

        user.setPassword(newPwd);
        if (userService.changePwd(user)) {
            redisTemplate.opsForValue().set("user:" + user.getId(), user, 7, TimeUnit.DAYS);
            return Result.success(true, "密码修改成功");
        }

        return Result.error("500", "修改密码失败");
    }

    //用户登出
    @PostMapping("/logout")
    public Result logout(@RequestHeader("Authorization") String token) {
        String cleanedToken = token.substring(7);
        redisTemplate.delete("token:" + cleanedToken);
        return Result.success(true, "登出成功");
    }
}
