package cn.pyk.entity;

import lombok.Data;

@Data
public class Like {
    private int id;     //点赞id
    private int uid;    //点赞者用户id
    private int cid;    //点赞的评论id

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

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }
}
