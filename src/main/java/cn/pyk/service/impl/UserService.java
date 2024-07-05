package cn.pyk.service.impl;

import cn.pyk.entity.User;
import cn.pyk.mapper.UserMapper;
import cn.pyk.service.IUserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService extends ServiceImpl<UserMapper, User> implements IUserService {
    @Autowired
    public UserMapper userMapper;


    public Object getUser(){
        QueryWrapper<User> qw = new QueryWrapper<>();
        qw.eq("id",1);
        return userMapper.selectOne(qw);
    }

    //插入一条用户信息到数据库mysql中
    public boolean insert(User user) {
        if(userMapper.insert(user)>0){
            return true;
        }
        return false;
    }

    //根据用户名和密码查询用户
    public User findUser(String username, String password) {
        QueryWrapper<User> qw = new QueryWrapper<>();
        qw.eq("username",username);
        qw.eq("password",password);
        User user = userMapper.selectOne(qw);
        return user;
    }

    //修改用户密码
    public boolean changePwd(User user) {
        return userMapper.updateById(user) > 0;
    }
}
