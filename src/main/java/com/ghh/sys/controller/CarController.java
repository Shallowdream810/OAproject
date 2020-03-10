package com.ghh.sys.controller;


import com.ghh.sys.bean.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/car")
public class CarController {
    @Autowired
    private Car car;

    @RequestMapping("/test")
    public  String test(){
        car.setName("123");
        return "info";
    }
}
