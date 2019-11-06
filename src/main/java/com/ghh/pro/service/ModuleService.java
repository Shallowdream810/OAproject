package com.ghh.pro.service;

import com.ghh.pro.bean.Model;
import com.github.pagehelper.PageInfo;

import java.util.Map;

public interface ModuleService {
    PageInfo<Model> findAll(Integer pageNum, Map<String, Object> search);

    void insertInfo(Model model);
}
