package cn.itcast.core.pojo.entity;

import java.util.List;

public class Item_Cat {
    private Long id;
    private  String name;
    private Long parentId;
    private List<Item_Cat> fuList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public List<Item_Cat> getFuList() {
        return fuList;
    }

    public void setFuList(List<Item_Cat> fuList) {
        this.fuList = fuList;
    }
}
