package cn.itcast.core.service;

import cn.itcast.core.dao.address.AddressDao;
import cn.itcast.core.pojo.address.Address;
import cn.itcast.core.pojo.address.AddressQuery;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressDao addressDao;

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
}
