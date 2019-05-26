package cn.itcast.core.controller;

import cn.itcast.core.pojo.entity.Item_Cat;
import cn.itcast.core.service.ItemCatService;

import com.alibaba.dubbo.config.annotation.Reference;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 商品分类查询
 */
@RestController
@RequestMapping("index")
public class ItemController {
//    @Reference
//    private ItemCatService itemCatService;
//
//    @RequestMapping("findItemCatList")
//    public List<Item_Cat> find() {
//        return itemCatService.findItemCatList();
//    }
//}



    @Reference
    private ItemCatService itemCatService;

    /**
     * 查询商品分类信息
     *
     * @return
     */
    @RequestMapping("/findItemCatList")
    public List<Item_Cat> findItemCatList() {
        return itemCatService.findItemCatList();
    }
}