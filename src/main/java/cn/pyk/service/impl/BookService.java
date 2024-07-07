package cn.pyk.service.impl;

import cn.pyk.entity.Book;
import cn.pyk.mapper.BookMapper;
import cn.pyk.service.IBookService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService extends ServiceImpl<BookMapper, Book> implements IBookService {
    @Autowired
    public BookMapper bookMapper;

    //获取数据库所有书籍信息
    public List<Book> getAllBook() {
        // 使用 selectList 并传递一个空的 QueryWrapper
        List<Book> books = bookMapper.selectList(null);
        return books;
    }

    // 按书籍名查询
    public List<Book> getBooksByTitle(String title) {
        QueryWrapper<Book> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("title", title);
        List<Book> books = bookMapper.selectList(queryWrapper);
        return books;
    }

    // 按作者查询
    public List<Book> getBooksByAuthor(String author) {
        QueryWrapper<Book> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("author", author);
        List<Book> books = bookMapper.selectList(queryWrapper);
        return books;
    }

    // 按类别查询
    public List<Book> getBooksByCategory(int cid) {
        QueryWrapper<Book> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("cid", cid);
        List<Book> books = bookMapper.selectList(queryWrapper);
        return books;
    }

    // 按书籍名模糊查询
    public List<Book> searchBooksByTitle(String title) {
        QueryWrapper<Book> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("title", title);                  //模糊查询
        List<Book> books = bookMapper.selectList(queryWrapper);
        return books;
    }
}
