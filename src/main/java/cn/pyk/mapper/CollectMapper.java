package cn.pyk.mapper;

import cn.pyk.entity.Collect;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CollectMapper extends BaseMapper<Collect> {
    @Insert("INSERT INTO collect (uid, bid) VALUES (#{uid}, #{bid})")
    int insertCollect(Collect collect);

    @Delete("DELETE FROM collect WHERE uid = #{uid} AND bid = #{bid}")
    int deleteCollect(@Param("uid") int uid, @Param("bid") int bid);

    @Select("SELECT * FROM collect WHERE uid = #{uid}")
    List<Collect> selectCollectsByUserId(int uid);

    @Select("SELECT COUNT(*) FROM collect WHERE uid = #{uid} AND bid = #{bid}")
    int countByUidAndBid(@Param("uid") int uid, @Param("bid") int bid);


}
