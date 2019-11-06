package com.ghh.sys.service;

import com.ghh.sys.bean.Role;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface RoleService {
    int saveInfo(Role role);

    PageInfo<Role> getRoleList(Integer pageNum);

    List<Role> getJesonList();
}
