package com.ghh.sys.service;

import com.ghh.sys.bean.BaoXiao;
import com.github.pagehelper.PageInfo;

import java.util.Map;

public interface BaoXiaoService {
    PageInfo<BaoXiao>  findAll(Integer pageNum, Integer eid, Map<String, Object> search);

    void saveInfo(BaoXiao baoXiao);
}
