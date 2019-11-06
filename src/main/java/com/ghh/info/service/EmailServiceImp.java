package com.ghh.info.service;

import com.ghh.info.bean.Email;
import com.ghh.info.mapper.EmailMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class EmailServiceImp implements EmailService {

    @Resource
    private EmailMapper emailMapper;

    @Override
    public void saveInfo(Email email) {
        emailMapper.insert(email);
    }
}
