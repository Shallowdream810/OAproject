package com.ghh.sys.mapper;

import org.apache.ibatis.annotations.Param;

public interface RoleSourceMapper {
    void insert(@Param("rid") int id,@Param("sid") int parseInt);
}
