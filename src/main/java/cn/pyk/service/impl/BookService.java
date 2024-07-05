package cn.pyk.service.impl;

import cn.pyk.entity.Book;
import cn.pyk.mapper.BookMapper;
import cn.pyk.service.IBookService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService extends ServiceImpl<BookMapper, Book> implements IBookService {
    @Autowired
    public BookMapper bookMapper;

    
}
