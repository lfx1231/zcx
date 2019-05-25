package cn.itcast.core.controller;

import cn.itcast.core.pojo.item.ItemCat;
import cn.itcast.core.service.ItemCatService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 商品分类查询
 */
@RestController
@RequestMapping("")
public class GoodsController {
    @Reference
    private ItemCatService  itemCatService;


    //商品分类实现，三级联动查询
    @RequestMapping("")
    public List<ItemCat>findByItemCat (Long parentId){
        List<ItemCat> fuId = itemCatService.findByParentId(parentId);
        return fuId;
    }


}
