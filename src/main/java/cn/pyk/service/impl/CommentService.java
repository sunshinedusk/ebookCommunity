package cn.pyk.service.impl;

import cn.pyk.entity.Comment;
import cn.pyk.mapper.CommentMapper;
import cn.pyk.service.ICommentService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService extends ServiceImpl<CommentMapper, Comment> implements ICommentService {
    @Autowired
    private CommentMapper commentMapper;

    // 发布评论
    public boolean addComment(Comment comment) {
        int result = commentMapper.insert(comment);
        if (result > 0) {
            return true;
        } else {
            return false;
        }
    }

    // 删除评论
    public boolean deleteComment(int id) {
        int result = commentMapper.deleteById(id);
        if (result > 0) {
            return true;
        } else {
            return false;
        }
    }

    // 点赞评论
    public boolean likeComment(int id) {
        Comment comment = commentMapper.selectById(id);
        if (comment != null) {
            comment.setLike(comment.getLike() + 1);
            int result = commentMapper.updateById(comment);
            if (result > 0) {
                return true;
            }
        }
        return false;
    }

    // 查询某个书籍的评论（按点赞数排序）
    public List<Comment> getCommentsByBookId(int bid) {
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("bid", bid).orderByDesc("like");
        List<Comment> comments = commentMapper.selectList(queryWrapper);
        return comments;
    }


}
