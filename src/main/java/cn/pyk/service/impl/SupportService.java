package cn.pyk.service.impl;

import cn.pyk.entity.Comment;
import cn.pyk.entity.Support;
import cn.pyk.mapper.CommentMapper;
import cn.pyk.mapper.SupportMapper;
import cn.pyk.service.ISupportService;
import cn.pyk.util.Result;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class SupportService extends ServiceImpl<SupportMapper, Support> implements ISupportService {
    @Autowired
    private SupportMapper supportMapper;

    @Autowired
    private CommentMapper commentMapper;

    // 点赞评论
    public Result<Boolean> likeComment(int uid, int cid) {
        // 查询是否已经点赞过
        Support existSupport = supportMapper.selectByUidAndCid(uid, cid);
        if (existSupport != null) {
            return Result.error("500", "您已经点赞过该评论");
        }

        // 执行点赞操作
        Comment comment = commentMapper.selectById(cid);    //查询是否存在评论
        if (comment != null) {
            //评论的点赞数+1
            comment.setSupport(comment.getSupport() + 1);
            int result = commentMapper.updateById(comment);
            if (result > 0) {
                // 记录点赞信息
                Support newSupport = new Support();
                newSupport.setUid(uid);
                newSupport.setCid(cid);
                newSupport.setTime(new Date()); // 设置当前时间
                supportMapper.insert(newSupport);
                return Result.success(true, "点赞成功");
            }
        }

        return Result.error("500", "点赞失败");
    }

    public Result<Boolean> unlikeComment(int uid, int cid) {
        Support existingSupport = supportMapper.selectByUidAndCid(uid, cid);
        if (existingSupport == null) {
            return Result.error("400", "用户没有点赞过该评论");
        }
        int rows = supportMapper.deleteById(existingSupport.getId());
        if (rows > 0) {
            //评论的点赞数-1
            Comment comment = commentMapper.selectById(cid);
            comment.setSupport(comment.getSupport() - 1);
            commentMapper.updateById(comment);
            return Result.success(true, "取消点赞成功");
        }
        return Result.error("500", "取消点赞失败");
    }
}
