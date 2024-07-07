package cn.pyk.mapper;

import cn.pyk.entity.Book;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BookMapper extends BaseMapper<Book> {
    @Select("SELECT * FROM book WHERE cid = #{cid} ORDER BY collect DESC LIMIT 10")
    List<Book> selectTop10BooksByCategory(@Param("cid") int cid);
}
