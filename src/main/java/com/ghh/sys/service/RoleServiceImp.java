package com.ghh.sys.service;

import com.ghh.sys.bean.Role;
import com.ghh.sys.bean.RoleExample;
import com.ghh.sys.mapper.RoleMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RoleServiceImp implements RoleService{

    @Resource
    private RoleMapper roleMapper;
    @Override
    public int saveInfo(Role role) {
        //由于存储进去之后还要获取他的存进去的id，所以我们自己在映射文件中写一个sql语句
        roleMapper.saveInfo(role);
        return role.getRoleid();
    }

    @Override
    public PageInfo<Role> getRoleList(Integer pageNum) {
        RoleExample example = new RoleExample();
        PageHelper.startPage(pageNum,3);
        List<Role> roles = roleMapper.selectByExample(example);
        PageInfo<Role> page = new PageInfo<Role>(roles,3);
        return page;
    }

    @Override
    public List<Role> getJesonList() {
        return roleMapper.selectByExample(null);
    }
}
