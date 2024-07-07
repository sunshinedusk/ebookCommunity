package cn.pyk.entity;

import lombok.Data;

@Data
public class Comment {
    private int id;             //评论号
    private int uid;            //发布评论的用户号
    private int bid;            //评论的书号
    private String content;     //评论内容
    private int like;           //点赞数
    private Data time;          //发布评论的日期

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

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public Data getTime() {
        return time;
    }

    public void setTime(Data time) {
        this.time = time;
    }
}
