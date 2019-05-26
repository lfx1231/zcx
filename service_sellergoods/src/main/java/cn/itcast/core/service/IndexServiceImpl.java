package cn.itcast.core.service;

import cn.itcast.core.dao.item.ItemCatDao;
import cn.itcast.core.pojo.item.ItemCat;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;


@Service
public class IndexServiceImpl implements IndexService {

    @Autowired
    private ItemCatDao itemCatDao;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public List<ItemCat> findItemCatList() {
        //从缓存中查询首页商品分类
        List<ItemCat> itemCatList = (List<ItemCat>) redisTemplate.boundHashOps("itemCat").get("indexItemCat");

        //如果缓存中没有数据，则从数据库查询再存入缓存
        if(itemCatList==null){
            //查询出1级商品分类的集合
            List<ItemCat> itemCatList1 = itemCatDao.findItemCatListByParentId(0L);
            //遍历1级商品分类的集合
            for(ItemCat itemCat1:itemCatList1){
                //查询2级商品分类的集合(将1级商品分类的id作为条件)
                List<ItemCat> itemCatList2 = itemCatDao.findItemCatListByParentId(itemCat1.getId());
                //遍历2级商品分类的集合
                for(ItemCat itemCat2:itemCatList2){
                    //查询3级商品分类的集合(将2级商品分类的父id作为条件)
                    List<ItemCat> itemCatList3 = itemCatDao.findItemCatListByParentId(itemCat2.getId());
                    //将2级商品分类的集合封装到2级商品分类实体中
                    itemCat2.setItemCatList(itemCatList3);
                }
                /*到这一步的时候，3级商品分类已经封装到2级分类中*/
                //将2级商品分类的集合封装到1级商品分类实体中
                itemCat1.setItemCatList(itemCatList2);
            }
            //存入缓存
            redisTemplate.boundHashOps("itemCat").put("indexItemCat",itemCatList1);
            return itemCatList1;
        }
        //到这一步，说明缓存中有数据，直接返回
        return itemCatList;
    }
}