package cn.itcast.core.service;

import cn.itcast.core.pojo.address.Address;

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
}
