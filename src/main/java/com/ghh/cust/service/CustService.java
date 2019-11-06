package com.ghh.cust.service;
import com.ghh.cust.bean.Customer;
import com.ghh.cust.bean.CustomerExample;
import com.ghh.cust.mapper.CustomerMapper;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
@Service
public class CustService implements  ICustService{
    @Resource
    private CustomerMapper customerMapper;
    @Override
    public void saveInfo(Customer customer) {
         customer.setAddtime(new Date());
         customerMapper.insert(customer);
    }
    @Override
    public List<Customer> getCustomerList() {
        CustomerExample example = new CustomerExample();
        return customerMapper.selectByExample(example);
    }
    @Override
    public Customer findById(Integer cid) {
        return customerMapper.selectByPrimaryKey(cid);
    }
    @Override
    public void updatecust(Customer customer) {
//        传进来啥我就修改啥,其他信息不回动
        customerMapper.updateByPrimaryKeySelective(customer);
    }
    @Override
    public boolean batchDelete(Integer[] ids) {
        List<Integer> cids = Arrays.asList(ids);
        CustomerExample example = new CustomerExample();
        CustomerExample.Criteria criteria = example.createCriteria();
        //需要一个list集合。
        criteria.andIdIn(cids);
        int i = customerMapper.deleteByExample(example);
        return  ids.length == i;
    }
    @Override
    public List<Customer> searchCust(Integer cid, String keyword, Integer orderby) {
        CustomerExample example = new CustomerExample();
        CustomerExample.Criteria criteria = example.createCriteria();
        if (cid==0){
//            对公司名称进行模糊查询
            criteria.andComnameLike("%"+keyword+"%");
//            再添加一个条件后需要冲虚创建一个crirerial对象
            CustomerExample.Criteria criteria1 = example.createCriteria();
//            对公司职员进行模糊查询
            criteria1.andCompanypersonLike("%"+keyword+"%");
//            两者的查询条件是活着的关系
            example.or(criteria1);
        }else if (cid == 1){
//            为1的话对公司名称进行模糊查询
            criteria.andComnameLike("%"+keyword+"%");
        }else {
//            为2的话对联系人姓名进行模糊查询
            criteria.andCompanypersonLike("%"+keyword+"%");
        }
        if (orderby==1){
            example.setOrderByClause("id desc");
        }
        List<Customer> list = customerMapper.selectByExample(example);
        return  list;
    }
}