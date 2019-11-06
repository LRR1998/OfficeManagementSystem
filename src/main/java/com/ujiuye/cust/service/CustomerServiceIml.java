package com.ujiuye.cust.service;

import com.ujiuye.cust.bean.Customer;
import com.ujiuye.cust.bean.CustomerExample;
import com.ujiuye.cust.mapper.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class CustomerServiceIml implements CustomerService{


    @Autowired
    private CustomerMapper customerMapper;
    @Override
    public void saveInfo(Customer customer) {
            customerMapper.insert(customer);
    }

    @Override
    public List<Customer> getCustomerList() {
        return customerMapper.selectByExample(new CustomerExample());
    }

    @Override
    public boolean checkPhone(String phone) {
        CustomerExample customerExample = new CustomerExample();
        CustomerExample.Criteria criteria = customerExample.createCriteria();
        criteria.andComphoneLike(phone);
        List<Customer> customers = customerMapper.selectByExample(customerExample);
        if (customers.size()==0){
            return false;
        }
        return true;
    }

    @Override
    public Customer detail(Integer id) {
        Customer customer = customerMapper.selectByPrimaryKey(id);
        return customer;
    }

    @Override
    public void updateCustomer(Customer customer) {
        customerMapper.updateByPrimaryKeySelective(customer);
    }

    @Override
    public boolean delete(String ids) {
        System.out.println(ids);
        String[] split = ids.split(",");
        List<String> strings = Arrays.asList(split);
        int rows = customerMapper.batchDelete(strings);
        if (rows==split.length){
            return true;
        }
        return false;
    }

    @Override
    public List<Customer> search(Integer cid, String keyword, Integer orderby) {
        CustomerExample example = new CustomerExample();
        CustomerExample.Criteria criteria = example.createCriteria();
        if(cid==0){
            criteria.andComnameLike("%"+keyword+"%");
            CustomerExample.Criteria criteria2 = example.createCriteria();
            criteria2.andCompanypersonLike("%"+keyword+"%");
            example.or(criteria2);
        } else if (cid==1){
            criteria.andComnameLike("%"+keyword+"%");
        } else {
            criteria.andCompanypersonLike("%"+keyword+"%");
        }
        if (orderby==1){
            example.setOrderByClause("id desc");
        } else {
            example.setOrderByClause("id");
        }
        List<Customer> customers = customerMapper.selectByExample(example);
        return customers;
    }

    @Override
    public void batchInsert(List<Customer> customers) {
        customerMapper.batchInsert(customers);
    }
}
