package cn.pyk.mapper;

import cn.pyk.entity.Like;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface LikeMapper extends BaseMapper<Like> {
    @Select("SELECT * FROM like WHERE uid = #{uid} AND cid = #{cid}")
    Like selectByUidAndCid(@Param("uid") int uid, @Param("cid") int cid);

}
