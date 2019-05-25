package cn.itcast.core.service;

import cn.itcast.core.dao.address.AddressDao;
import cn.itcast.core.dao.address.AreasDao;
import cn.itcast.core.dao.address.CitiesDao;
import cn.itcast.core.dao.address.ProvincesDao;
import cn.itcast.core.pojo.address.*;
import cn.itcast.core.util.Constants;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;

import static cn.itcast.core.util.Constants.REDIS_ADDRESSLIST;

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

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public List<Address> findListByUserId(String userId) {
        List<Address> addressList = (List<Address>)redisTemplate.boundHashOps(REDIS_ADDRESSLIST).get(userId);

        if (addressList == null) {
            AddressQuery example = new AddressQuery();
            AddressQuery.Criteria criteria = example.createCriteria();
            criteria.andUserIdEqualTo(userId);
            addressList = addressDao.selectByExample(example);
            //存入redis中一份
            redisTemplate.boundHashOps(REDIS_ADDRESSLIST).put(userId, addressList);
        }
        return addressList;
    }

    /**
     * 添加收件地址
     * @param address
     */
    @Override
    public void add(Address address,String userId) {
        addressDao.insertSelective(address);
        redisTemplate.boundHashOps(REDIS_ADDRESSLIST).delete(userId);
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
    public void update(Address address,String userId) {
        addressDao.updateByPrimaryKeySelective(address);
        redisTemplate.boundHashOps(REDIS_ADDRESSLIST).delete(userId);
    }

    /**
     * 根据主键id删除
     * @param id
     */
    @Override
    public void delete(Long id,String userId) {
        addressDao.deleteByPrimaryKey(id);
        redisTemplate.boundHashOps(REDIS_ADDRESSLIST).delete(userId);
    }

    /**
     * 设置默认收件地址
     * @param id
     */
    @Override
    public void isDefault(Long id,String userName) {
        //先将该用户默认地址状态全部改为0

        Address address = new Address();
        address.setIsDefault("0");

        AddressQuery query = new AddressQuery();
        AddressQuery.Criteria criteria = query.createCriteria();
        criteria.andUserIdEqualTo(userName);

        addressDao.updateByExampleSelective(address,query);

        //通过设置新的默认地址
        address.setId(id);
        address.setIsDefault("1");
        addressDao.updateByPrimaryKeySelective(address);

        redisTemplate.boundHashOps(REDIS_ADDRESSLIST).delete(userName);
    }

    /**
     * 获取所有省份集合
     * @return
     */
    @Override
    public List<Provinces> selectProvincesList() {
        List<Provinces> provincesList = (List<Provinces>)redisTemplate.boundValueOps(Constants.REDIS_PROVINCESLIST).get();
        if (provincesList == null) {
            provincesList = provincesDao.selectByExample(null);
        }
        return provincesList;
    }

    /**
     * 根据省份id，查询所有的城市列表
     * @param provincesid
     * @return
     */
    @Override
    public List<Cities> findByprovinceid(String provincesid) {
        List<Cities> citiesList = (List<Cities>)redisTemplate.boundValueOps(Constants.REDIS_CITIESLIST).get();
        if (citiesList == null) {
            CitiesQuery query = new CitiesQuery();
            CitiesQuery.Criteria criteria = query.createCriteria();
            criteria.andProvinceidEqualTo(provincesid);
            citiesList = citiesDao.selectByExample(query);
        }
        return citiesList;
    }

    /**
     * 根据城市id，查询所有的区域列表
     * @param citiesid
     * @return
     */
    @Override
    public List<Areas> findBycityid(String citiesid) {
        List<Areas> areasList = (List<Areas>)redisTemplate.boundValueOps(Constants.REDIS_AREASLIST).get();
        if (areasList == null) {
            AreasQuery query = new AreasQuery();
            AreasQuery.Criteria criteria = query.createCriteria();
            criteria.andCityidEqualTo(citiesid);
            areasList = areasDao.selectByExample(query);
        }
        return areasList;
    }

    /**
     * 根据别名查询地址信息
     * @param alias
     * @param userName
     * @return
     */
    @Override
    public Address findByAlias(String alias, String userName) {
        AddressQuery query = new AddressQuery();
        AddressQuery.Criteria criteria = query.createCriteria();
        if (userName != null) {
            criteria.andUserIdEqualTo(userName);
            if (alias != null){
                criteria.andAliasEqualTo(alias);
                List<Address> addressList = addressDao.selectByExample(query);
                //默认返回第一个
                if (addressList != null && addressList.size()>0) {
                    return addressList.get(0);
                }
            }
        }
        return null;
    }
}
