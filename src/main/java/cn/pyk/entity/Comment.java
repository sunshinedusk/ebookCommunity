package cn.pyk.entity;

import lombok.Data;

@Data
public class Comment {
    private int id;             //评论号
    private int uid;            //发布评论的用户号
    private int bid;            //评论的书号
    private String content;     //评论内容
    private int like;           //点赞数

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
