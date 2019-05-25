package cn.itcast.core.service;

import cn.itcast.core.dao.address.AddressDao;
import cn.itcast.core.dao.address.AreasDao;
import cn.itcast.core.dao.address.CitiesDao;
import cn.itcast.core.dao.address.ProvincesDao;
import cn.itcast.core.pojo.address.*;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressDao addressDao;

    @Autowired
    private ProvincesDao provincesDao;

    @Autowired
    private CitiesDao citiesDao;

    @Autowired
    private AreasDao areasDao;

    @Override
    public List<Address> findListByUserId(String userId) {
        AddressQuery example=new AddressQuery();
        AddressQuery.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(userId);
        return addressDao.selectByExample(example);

    }

    /**
     * 添加收件地址
     * @param address
     */
    @Override
    public void add(Address address) {
        addressDao.insertSelective(address);
    }

    /**
     * 通过id查询一个
     * @param id
     * @return
     */
    @Override
    public Address findOne(Long id) {
        return addressDao.selectByPrimaryKey(id);
    }

    /**
     * 修改
     * @param address
     */
    @Override
    public void update(Address address) {
        addressDao.updateByPrimaryKeySelective(address);
    }

    /**
     * 根据主键id删除
     * @param id
     */
    @Override
    public void delete(Long id) {
        addressDao.deleteByPrimaryKey(id);
    }

    /**
     * 获取所有省份集合
     * @return
     */
    @Override
    public List<Provinces> selectProvincesList() {
        return provincesDao.selectByExample(null);
    }

    /**
     * 根据省份id，查询所有的城市列表
     * @param provincesid
     * @return
     */
    @Override
    public List<Cities> findByprovinceid(String provincesid) {
        CitiesQuery query = new CitiesQuery();
        CitiesQuery.Criteria criteria = query.createCriteria();
        criteria.andProvinceidEqualTo(provincesid);
        return citiesDao.selectByExample(query);
    }

    /**
     * 根据城市id，查询所有的区域列表
     * @param citiesid
     * @return
     */
    @Override
    public List<Areas> findBycityid(String citiesid) {
        AreasQuery query = new AreasQuery();
        AreasQuery.Criteria criteria = query.createCriteria();
        criteria.andCityidEqualTo(citiesid);
        return areasDao.selectByExample(query);
    }
}
