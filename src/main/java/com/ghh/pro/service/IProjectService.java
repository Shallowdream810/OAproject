package com.ghh.pro.service;

import com.ghh.pro.bean.Project;

import java.util.List;
import java.util.Map;

public interface IProjectService {
    void insertInto(Project project);

    List<Project> getProjectList();

    List<Map<String,Object>>  selectComnameAndEname();

    Map<String,Object> selectProjectAllById(Integer pid);

    void updateById(Project project);

    void deletePro(Integer[] ids);

    List<Map<String, Object>> selectProjectAllByLike(Integer position, String keyword, Integer orderby);

    List<Project> getProjectListInNeed();
}
