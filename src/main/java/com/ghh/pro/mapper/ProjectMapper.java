package com.ghh.pro.mapper;

import java.util.List;
import java.util.Map;

import com.ghh.pro.bean.Project;
import com.ghh.pro.bean.ProjectExample;
import org.apache.ibatis.annotations.Param;

public interface ProjectMapper {
    int countByExample(ProjectExample example);

    int deleteByExample(ProjectExample example);

    int deleteByPrimaryKey(Integer pid);

    int insert(Project record);

    int insertSelective(Project record);

    List<Project> selectByExample(ProjectExample example);

    Project selectByPrimaryKey(Integer pid);

    int updateByExampleSelective(@Param("record") Project record, @Param("example") ProjectExample example);

    int updateByExample(@Param("record") Project record, @Param("example") ProjectExample example);

    int updateByPrimaryKeySelective(Project record);

    int updateByPrimaryKey(Project record);

    List<Map<String,Object>> selectComnameAndEname();

    Map<String,Object> selectProjectAllById(Integer pid);



    List<Project> getProjectListInNeed();

    List<Map<String, Object>> selectProjectAllByLike(@Param("position") Integer position,@Param("keyword") String keyword,@Param("orderby") Integer orderby);
}