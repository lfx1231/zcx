package cn.itcast.core.service;

import cn.itcast.core.pojo.entity.Item_Cat;
import cn.itcast.core.pojo.item.ItemCat;

import java.util.List;

public interface ItemCatService {

    public List<ItemCat> findByParentId(Long parentId);

    public ItemCat findOne(Long id);

    public List<ItemCat> findAll();
    //查询商品分类信息
    public  List<Item_Cat>findItemCatList();


}
