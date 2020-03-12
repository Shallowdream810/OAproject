package com.ghh.sys.controller;


import com.ghh.sys.bean.Car;
import com.ghh.sys.service.CarTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
// 第一批实习，实习结束，找到工作了，属于在家办公。
@Controller
@RequestMapping("/car")
public class CarController {
    @Autowired
    private Car car;

    @Autowired
    CarTestService carTestService;
    @RequestMapping("/test")
    public  String test(){
        carTestService.saveCar();
        carTestService.delete();
        car.setName("123");
        return "info";
    }
}
