package cn.pyk.mapper;

import cn.pyk.entity.Note;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NoteMapper extends BaseMapper<Note> {
    @Select("SELECT * FROM note WHERE id = #{id}")
    Note findById(int id);

    @Select("SELECT * FROM note WHERE uid = #{uid}")
    List<Note> findByUserId(int uid);

    @Insert("INSERT INTO note(title, content, uid) VALUES(#{title}, #{content}, #{uid})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Note note);

    @Update("UPDATE note SET title = #{title}, date = #{date}, content = #{content} WHERE id = #{id} AND uid = #{uid}")
    int update(Note note);

    @Delete("DELETE FROM note WHERE id = #{id} AND uid = #{uid}")
    int delete(@Param("id") int id, @Param("uid") int uid);

}
