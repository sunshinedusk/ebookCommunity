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
        QueryWrapper<Book> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("collect");                //按收藏数排序
        List<Book> books = bookMapper.selectList(queryWrapper);
        return books;
    }

    // 按书籍名查询
    public List<Book> getBooksByTitle(String title) {
        QueryWrapper<Book> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("title", title).orderByDesc("collect");
        List<Book> books = bookMapper.selectList(queryWrapper);
        return books;
    }

    // 按作者查询
    public List<Book> getBooksByAuthor(String author) {
        QueryWrapper<Book> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("author", author).orderByDesc("collect");
        List<Book> books = bookMapper.selectList(queryWrapper);
        return books;
    }

    // 按类别查询
    public List<Book> getBooksByCategory(int cid) {
        QueryWrapper<Book> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("cid", cid).orderByDesc("collect");
        List<Book> books = bookMapper.selectList(queryWrapper);
        return books;
    }

    // 按书籍名模糊查询
    public List<Book> searchBooksByTitle(String title) {
        QueryWrapper<Book> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("title", title).orderByDesc("collect");                  //模糊查询
        List<Book> books = bookMapper.selectList(queryWrapper);
        return books;
    }

    // 按收藏数排序查询前十书籍
    public List<Book> getTop10BooksByCollect() {
        QueryWrapper<Book> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("collect").last("LIMIT 10");
        List<Book> books = bookMapper.selectList(queryWrapper);
        return books;
    }

    //按书籍种类排前十榜单
    public List<Book> getTop10BooksByCategory(int cid) {
        return bookMapper.selectTop10BooksByCategory(cid);
    }

    /*
    管理员功能
     */

    //新增书籍
    public boolean insertBook(Book book) {
        return bookMapper.insert(book) > 0;
    }

    public boolean deleteBook(int id) {
        return bookMapper.deleteById(id) > 0;
    }

    public boolean updateBook(Book book) {
        return bookMapper.updateById(book) > 0;
    }
}
