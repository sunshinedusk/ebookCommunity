package cn.pyk.mapper;

import cn.pyk.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    @Select("select * from tb_user where username=#{username} and password=#{password}")
    boolean doLogins(String username,String password);
}
