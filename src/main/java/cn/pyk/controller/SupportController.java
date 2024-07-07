package cn.pyk.controller;

import cn.pyk.service.impl.SupportService;
import cn.pyk.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/like")
public class SupportController {
    @Autowired
    private SupportService supportService;

    // 点赞评论
    @PostMapping("/likeComment")
    public Result<Boolean> likeComment(@RequestParam int uid, @RequestParam int cid) {
        return supportService.likeComment(uid, cid);
    }

    // 取消点赞评论
    @PostMapping("/unlikeComment")
    public Result<Boolean> unlikeComment(@RequestParam int uid, @RequestParam int cid) {
        return supportService.unlikeComment(uid, cid);
    }
    
}
