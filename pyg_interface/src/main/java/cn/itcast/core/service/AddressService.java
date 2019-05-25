package cn.itcast.core.service;

import cn.itcast.core.pojo.address.Address;
import cn.itcast.core.pojo.address.Areas;
import cn.itcast.core.pojo.address.Cities;
import cn.itcast.core.pojo.address.Provinces;

import java.util.List;

public interface AddressService {

    /**
     * 根据用户查询地址
     * @param userId
     * @return
     */
    public List<Address> findListByUserId(String userId );

    /**
     * 添加收件信息
     * @param address
     */
    void add(Address address);

    /**
     * 查询一个
     * @param id
     * @return
     */
    Address findOne(Long id);

    /**
     * 修改
     * @param address
     */
    void update(Address address);

    /**
     * 删除
     * @param id
     */
    void delete(Long id);

    /**
     * 设置默认地址
     * @param id
     * @param userName
     */
    void isDefault(Long id, String userName);


    //获取所有省份集合
    List<Provinces> selectProvincesList();

    //根据省份id，查询所有的城市列表
    List<Cities> findByprovinceid(String provincesid);

    //根据城市id，查询所有的区域列表
    List<Areas> findBycityid(String citiesid);

}
