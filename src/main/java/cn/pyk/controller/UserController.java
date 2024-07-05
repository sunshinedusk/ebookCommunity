package cn.pyk.controller;

import cn.pyk.entity.User;
import cn.pyk.mapper.UserMapper;
import cn.pyk.service.impl.UserService;
import cn.pyk.util.Result;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
