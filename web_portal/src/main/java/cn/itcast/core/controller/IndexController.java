package cn.itcast.core.controller;

import cn.itcast.core.pojo.item.ItemCat;
import cn.itcast.core.service.IndexService;
import cn.itcast.core.service.ItemCatService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/index")
public class IndexController {

    @Reference
    private IndexService indexService;

    /**
     * 查询商品分类信息
     *
     * @return
     */
    @RequestMapping("/findItemCatList")
    public List<ItemCat> findItemCatList() {
        return indexService.findItemCatList();
    }
}
