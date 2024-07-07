package cn.pyk.controller;

import cn.pyk.entity.Comment;
import cn.pyk.service.impl.CommentService;
import cn.pyk.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    // 发布评论
    @PostMapping("/add")
    public Result<Boolean> addComment(@RequestBody Comment comment) {
        if(commentService.addComment(comment)){
            return Result.success(true,"评论发布成功");
        }
        return Result.error("500", "评论发布失败");
    }

    // 删除评论
    @DeleteMapping("/delete/{id}")
    public Result<Boolean> deleteComment(@PathVariable int id) {
        if(commentService.deleteComment(id)){
            return Result.success(true, "评论删除成功");
        }
        return Result.error("500", "评论删除失败");
    }

    // 查询某个书籍的评论（按点赞数排序）
    @GetMapping("/book/{bid}")
    public Result<List<Comment>> getCommentsByBookId(@PathVariable int bid) {
        List<Comment> comments = commentService.getCommentsByBookId(bid);
        return Result.success(comments, "查询评论成功");
    }
}
