package com.ghh.cust.service;

import com.ghh.cust.bean.Customer;

import java.util.List;

public interface ICustService {
    public void saveInfo(Customer customer);

    List<Customer> getCustomerList();

    Customer findById(Integer cid);

    void updatecust(Customer customer);

    boolean batchDelete(Integer[] ids);

    List<Customer> searchCust(Integer cid, String keyword, Integer orderby);
}
