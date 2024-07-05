package cn.pyk.entity;

import lombok.Data;

@Data
public class category {
    private int id;         //类别号
    private String name;    //类别名

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
