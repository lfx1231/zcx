package cn.itcast.core.controller;

import cn.itcast.core.pojo.address.Address;
import cn.itcast.core.pojo.address.Areas;
import cn.itcast.core.pojo.address.Cities;
import cn.itcast.core.pojo.address.Provinces;
import cn.itcast.core.pojo.entity.Result;
import cn.itcast.core.service.AddressService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {

    @Reference
    private AddressService addressService;


    /**
     * 查询所有
     *
     * @return
     */
    @RequestMapping("/findAll")
    public List<Address> findAll() {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        return addressService.findListByUserId(userName);
    }

    /**
     * 获取所有省份的集合
     * @return
     */
    @RequestMapping("/selectProvincesList")
    public List<Provinces> selectProvincesList(){
        return  addressService.selectProvincesList();
    }

    /**
     * 根据省份id，查询所有的城市列表
     * @param provincesid
     * @return
     */
    @RequestMapping("/findByprovinceid")
    public List<Cities> findByprovinceId(String provincesid){
        return addressService.findByprovinceid(provincesid);
    }

    /**
     * 根据城市id，查询所有的区域列表
     * @param citiesid
     * @return
     */
    @RequestMapping("/findBycityid")
    public List<Areas> findBycityId(String citiesid){
        return addressService.findBycityid(citiesid);
    }


    /**
     * 添加
     *
     * @param address
     * @return
     */
    @RequestMapping("/add")
    public Result add(@RequestBody Address address) {
        try {
            String userName = SecurityContextHolder.getContext().getAuthentication().getName();
            address.setUserId(userName);
            address.setIsDefault("0");
            address.setCreateDate(new Date());
            addressService.add(address,userName);
            return new Result(true, "添加成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "添加失败！");
        }
    }

    /**
     * 查询一个
     *
     * @param id
     * @return
     */
    @RequestMapping("/findOne")
    public Address findOne(Long id) {
        return addressService.findOne(id);
    }

    /**
     * 修改
     * @param address
     * @return
     */
    @RequestMapping("/update")
    public Result update(@RequestBody Address address){
        try {
            String userName = SecurityContextHolder.getContext().getAuthentication().getName();
            address.setCreateDate(new Date());
            addressService.update(address,userName);
            return new Result(true, "修改成功!");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "修改失败！");
        }
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @RequestMapping("/delete")
    public Result delete(Long id){
        try {
            String userName = SecurityContextHolder.getContext().getAuthentication().getName();
            addressService.delete(id,userName);
            return new Result(true, "删除成功!");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "删除失败！");
        }
    }

    /**
     * 设置默认地址
     * @param id
     * @return
     */
    @RequestMapping("/isDefault")
    public Result isDefault(Long id){
        try {
            String userName = SecurityContextHolder.getContext().getAuthentication().getName();
            addressService.isDefault(id,userName);
            return new Result(true, "设置默认地址成功!");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "设置默认地址失败！");
        }
    }

    /**
     * 根据别名查询地址信息
     * @param address
     * @return
     */
    @RequestMapping("/findByAlias")
    public Address findByAlias(@RequestBody Address address){
        String alias = address.getAlias();
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        return addressService.findByAlias(alias,userName);
    }

}
