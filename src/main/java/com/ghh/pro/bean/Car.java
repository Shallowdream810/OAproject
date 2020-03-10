package com.ghh.pro.bean;/*
 *   Description
 *   @author  ghh
 *   @date Created on 2020/3/10
 */

import org.springframework.stereotype.Component;

@Component
public class Car {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
