package com.ghh.sys.service;

import com.ghh.sys.bean.Role;
import com.ghh.sys.mapper.RoleSourceMapper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;

@Service
public class RoleSourceServiceImpl implements  RoleSourceService {

    @Resource
    private RoleSourceMapper roleSourceMapper;
    @Override
    public void saveInfo(int id, String ids) {
        String[] split = ids.split(",");
        for (int i = 0; i < split.length; i++) {
            roleSourceMapper.insert(id,Integer.parseInt(split[i]));
        }
    }

}
