package cn.pyk.controller;

import cn.pyk.entity.Book;
import cn.pyk.service.impl.BookService;
import cn.pyk.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookService bookService;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

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
}
