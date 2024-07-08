package cn.pyk.controller;

import cn.pyk.entity.Note;
import cn.pyk.service.impl.NoteService;
import cn.pyk.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/note")
public class NoteController {
    @Autowired
    private NoteService noteService;

    @GetMapping("/{id}")
    public Result<Note> getNoteById(@PathVariable int id) {
        Note note = noteService.getNoteById(id);
        if (note == null) {
            return Result.error("404", "笔记未找到");
        }
        return Result.success(note, "获取笔记成功");
    }

    @GetMapping("/user/{uid}")
    public Result<List<Note>> getNotesByUserId(@PathVariable int uid) {
        List<Note> notes = noteService.findByUserId(uid);
        if (notes.isEmpty()) {
            return Result.error("400", "无笔记记录");
        }
        return Result.success(notes, "获取笔记记录成功");
    }

    @PostMapping("/add")
    public Result<Note> addNote(@RequestBody Note note) {
        if (noteService.addNote(note)) {
            return Result.success(note, "添加笔记成功");
        } else {
            return Result.error("500", "添加笔记失败");
        }
    }

    @PutMapping("/update")
    public Result<Note> updateNote(@RequestBody Note note) {
        if (noteService.updateNote(note)) {
            return Result.success(note, "更新笔记成功");
        } else {
            return Result.error("500", "更新笔记失败");
        }
    }

    @DeleteMapping("/delete/{id}/{uid}")
    public Result<String> deleteNote(@PathVariable int id, @PathVariable int uid) {
        int result = noteService.delete(id, uid);
        if (result > 0) {
            return Result.success("删除笔记成功");
        } else {
            return Result.error("500", "删除笔记失败");
        }
    }
}
