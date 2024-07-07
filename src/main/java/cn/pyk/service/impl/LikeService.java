package cn.pyk.service.impl;

import cn.pyk.entity.Comment;
import cn.pyk.entity.Like;
import cn.pyk.mapper.CommentMapper;
import cn.pyk.mapper.LikeMapper;
import cn.pyk.service.ILikeService;
import cn.pyk.util.Result;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LikeService extends ServiceImpl<LikeMapper, Like> implements ILikeService {
    @Autowired
    private LikeMapper likeMapper;

    @Autowired
    private CommentMapper commentMapper;

    // 点赞评论
    public Result<Boolean> likeComment(int uid, int cid) {
        // 查询是否已经点赞过
        Like existLike = likeMapper.selectByUidAndCid(uid, cid);
        if (existLike != null) {
            return Result.error("500", "您已经点赞过该评论");
        }

        // 执行点赞操作
        Comment comment = commentMapper.selectById(cid);
        if (comment != null) {
            comment.setLike(comment.getLike() + 1);
            int result = commentMapper.updateById(comment);
            if (result > 0) {
                // 记录点赞信息
                Like newLike = new Like();
                newLike.setUid(uid);
                newLike.setCid(cid);
                likeMapper.insert(newLike);
                return Result.success(true, "点赞成功");
            }
        }

        return Result.error("500", "点赞失败");
    }

    public Result<Boolean> unlikeComment(int uid, int cid) {
        Like existingLike = likeMapper.selectByUidAndCid(uid, cid);
        if (existingLike == null) {
            return Result.error("400", "用户没有点赞过该评论");
        }
        int rows = likeMapper.deleteById(existingLike.getId());
        if (rows > 0) {
            Comment comment = commentMapper.selectById(cid);
            comment.setLike(comment.getLike() - 1);
            commentMapper.updateById(comment);
            return Result.success(true, "取消点赞成功");
        }
        return Result.error("500", "取消点赞失败");
    }
}
