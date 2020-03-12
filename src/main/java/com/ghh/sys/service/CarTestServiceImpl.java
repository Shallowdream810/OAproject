package com.ghh.sys.service;

import org.springframework.stereotype.Service;

@Service
public class CarTestServiceImpl implements CarTestService {
    @Override
    public void saveCar() {
        System.out.println("123");
    }
}
