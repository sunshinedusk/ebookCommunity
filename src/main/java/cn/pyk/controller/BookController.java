package cn.pyk.controller;

import cn.pyk.entity.Book;
import cn.pyk.service.impl.BookService;
import cn.pyk.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookService bookService;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /*
    普通用户功能:
        获取所有书籍信息
        按名查找
        按作者查找
        按类别查找
        按书籍名模糊查找
     */

    //获取数据库所有书籍信息
    @GetMapping("/getAllBook")
    public Result<List<Book>> getAllBook() {
        List<Book> books = bookService.getAllBook();
        if(books.isEmpty()){
            return Result.error("400","无书籍信息!");
        }
        return Result.success(books,"查询成功!");
    }

    //按书籍名查询
    @GetMapping("/getBooksByTitle")
    public Result<List<Book>> getBooksByTitle(@RequestParam String title) {
        List<Book> books = bookService.getBooksByTitle(title);
        if(books.isEmpty()){
            return Result.error("400","无书籍信息!");
        }
        return Result.success(books,"查询成功!");
    }

    //按作者查询
    @GetMapping("/getBooksByAuthor")
    public Result<List<Book>> getBooksByAuthor(@RequestParam String author) {
        List<Book> books = bookService.getBooksByAuthor(author);
        if(books.isEmpty()){
            return Result.error("400","无书籍信息!");
        }
        return Result.success(books,"查询成功!");
    }

    //按类别查询
    @GetMapping("/getBooksByCategory")
    public Result<List<Book>> getBooksByCategory(@RequestParam int cid) {
        List<Book> books = bookService.getBooksByCategory(cid);
        if(books.isEmpty()){
            return Result.error("400","无书籍信息!");
        }
        return Result.success(books,"查询成功!");
    }

    //按书籍名模糊查询
    @GetMapping("/searchBooksByTitle")
    public Result<List<Book>> searchBooksByTitle(@RequestParam String title) {
        List<Book> books = bookService.searchBooksByTitle(title);
        if(books.isEmpty()){
            return Result.error("400","无书籍信息!");
        }
        return Result.success(books,"查询成功!");
    }

    @GetMapping("/top10-collect")
    public Result<List<Book>> getTop10BooksByCollect() {
        List<Book> books = bookService.getTop10BooksByCollect();
        if(books.isEmpty()){
            return Result.error("400","无书籍信息!");
        }
        return Result.success(books, "按收藏数排序查询前十书籍成功");
    }

    @GetMapping("/top10-collect/{cid}")
    public Result<List<Book>> getTop10BooksByCategory(@PathVariable("cid") int cid) {
        List<Book> books = bookService.getTop10BooksByCategory(cid);
        if (books.isEmpty()) {
            return Result.error("400", "无书籍信息!");
        }
        return Result.success(books, "按类别和收藏数排序查询前十书籍成功");
    }



    /*
    管理员操作:
        新增书籍
        删除书籍
        修改书籍
     */

    //新增书籍
    @PostMapping("/insertBook")
    public Result<Boolean> insertBook(@RequestParam Book book){
        if(bookService.insertBook(book)) {
            return Result.success(true,"新增书籍成功!");
        }
        return Result.error("500","新增书籍失败!");
    }

    // 删除书籍
    @DeleteMapping("/deleteBook/{id}")
    public Result<Boolean> deleteBook(@PathVariable int id) {
        if (bookService.deleteBook(id)) {
            return Result.success(true, "删除书籍成功!");
        }
        return Result.error("500", "删除书籍失败!");
    }

    // 修改书籍
    @PutMapping("/updateBook")
    public Result<Boolean> updateBook(@RequestBody Book book) {
        if (bookService.updateBook(book)) {
            return Result.success(true, "修改书籍成功!");
        }
        return Result.error("500", "修改书籍失败!");
    }
}
