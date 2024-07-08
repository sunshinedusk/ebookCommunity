package cn.pyk.service.impl;

import cn.pyk.entity.Book;
import cn.pyk.entity.Collect;
import cn.pyk.mapper.BookMapper;
import cn.pyk.mapper.CollectMapper;
import cn.pyk.service.ICollectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CollectService extends ServiceImpl<CollectMapper, Collect> implements ICollectService {
    @Autowired
    private CollectMapper collectMapper;

    @Autowired
    private BookMapper bookMapper;

    public boolean addCollect(Collect collect) {
        if (hasCollected(collect.getUid(), collect.getBid())) {
            return false; // 已收藏，不重复收藏
        }

        if (collectMapper.insertCollect(collect) > 0) {
            Book book = bookMapper.selectBookById(collect.getBid());
            book.setCollect(book.getCollect() + 1);
            bookMapper.updateBook(book);
            return true;
        }
        return false;
    }

    public boolean removeCollect(int uid, int bid) {
        if (collectMapper.deleteCollect(uid, bid) > 0) {
            Book book = bookMapper.selectBookById(bid);
            book.setCollect(book.getCollect() - 1);
            bookMapper.updateBook(book);
            return true;
        }
        return false;
    }

    public List<Collect> getCollectsByUserId(int uid) {
        return collectMapper.selectCollectsByUserId(uid);
    }

    public boolean hasCollected(int uid, int bid) {
        return collectMapper.countByUidAndBid(uid, bid) > 0;
    }

    public List<Book> getCollectedBooksByUserId(int uid) {
        List<Collect> collects = collectMapper.selectCollectsByUserId(uid);
        return collects.stream()
                .map(collect -> bookMapper.selectBookById(collect.getBid()))
                .collect(Collectors.toList());
    }
}
