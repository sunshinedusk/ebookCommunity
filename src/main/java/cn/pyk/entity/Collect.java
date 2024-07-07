package cn.pyk.entity;

import lombok.Data;

@Data
public class Collect {
    private int id;     //收藏id
    private int uid;    //收藏者用户id
    private int bid;    //收藏的书籍id

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }
}
