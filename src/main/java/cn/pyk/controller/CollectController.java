package cn.pyk.controller;

import cn.pyk.entity.Book;
import cn.pyk.entity.Collect;
import cn.pyk.service.impl.CollectService;
import cn.pyk.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/collect")
public class CollectController {
    @Autowired
    private CollectService collectService;

    //用户收藏书籍
    @PostMapping("/add")
    public Result<String> addCollect(@RequestBody Collect collect) {
        if (collectService.addCollect(collect)) {
            return Result.success("收藏成功");
        }
        return Result.error("400", "收藏失败或已收藏");
    }

    //用户取消收藏
    @DeleteMapping("/remove")
    public Result<String> removeCollect(@RequestParam int uid, @RequestParam int bid) {
        if (collectService.removeCollect(uid, bid)) {
            return Result.success("取消收藏成功");
        }
        return Result.error("400", "取消收藏失败");
    }

    //获取用户收藏的书籍列表
    @GetMapping("/user/{uid}")
    public Result<List<Book>> getCollectedBooksByUserId(@PathVariable int uid) {
        List<Book> books = collectService.getCollectedBooksByUserId(uid);
        if (books.isEmpty()) {
            return Result.error("400", "无收藏记录");
        }
        return Result.success(books, "获取收藏记录成功");
    }
}
