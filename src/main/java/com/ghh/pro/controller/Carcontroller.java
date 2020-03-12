package com.ghh.pro.controller;
/**
 *   Description
 *   @author  ghh
 *   @date Created on 2020/3/10
 */

import com.ghh.pro.bean.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Carcontroller {
    @Autowired
    Car car;
    @RequestMapping("/test")
    public  String test(){
        car.setName("123");
        return "info";
    }
}
