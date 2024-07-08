package cn.pyk.service.impl;

import cn.pyk.entity.Note;
import cn.pyk.mapper.NoteMapper;
import cn.pyk.service.IBookService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService extends ServiceImpl<NoteMapper, Note> implements IBookService {
    @Autowired
    private NoteMapper noteMapper;

    public Note getNoteById(int id) {
        return noteMapper.findById(id);
    }

    public List<Note> findByUserId(int uid) {
        return noteMapper.findByUserId(uid);
    }

    public boolean addNote(Note note) {
        return noteMapper.insert(note) > 0;
    }

    public boolean updateNote(Note note) {
        return noteMapper.update(note) > 0;
    }

    public int delete(int id, int uid) {
        return noteMapper.delete(id, uid);
    }
}
