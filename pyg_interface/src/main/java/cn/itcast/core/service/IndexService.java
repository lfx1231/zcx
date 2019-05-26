package cn.itcast.core.service;

import cn.itcast.core.pojo.item.ItemCat;

import java.util.List;

public interface IndexService {
    //查询商品分类信息
    public List<ItemCat> findItemCatList();
}
