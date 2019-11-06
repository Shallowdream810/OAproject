package com.ghh.pro.service;

import com.ghh.cust.bean.Customer;
import com.ghh.cust.mapper.CustomerMapper;
import com.ghh.pro.bean.Project;
import com.ghh.pro.bean.ProjectExample;
import com.ghh.pro.mapper.ProjectMapper;
import com.ghh.sys.bean.Employee;
import com.ghh.sys.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class ProjectService implements  IProjectService {
    @Resource
    private ProjectMapper projectMapper;

    @Resource
    private CustomerMapper customerMapper;

    @Resource
    private EmployeeMapper employeeMapper;
    @Override
    public void insertInto(Project project) {
        projectMapper.insert(project);
    }

    //多表查询
    @Override
    public List<Project> getProjectList() {
        return  projectMapper.selectByExample(null);
    }


    //通过map集合去查询
    @Override
    public List<Map<String, Object>> selectComnameAndEname() {
        return projectMapper.selectComnameAndEname();
    }

    @Override
    public Map<String,Object> selectProjectAllById(Integer pid) {
        return projectMapper.selectProjectAllById(pid);
    }

    @Override
    public void updateById(Project project) {
        projectMapper.updateByPrimaryKeySelective(project);
    }

    @Override
    public void deletePro(Integer[] ids) {
        ProjectExample example = new ProjectExample();
        ProjectExample.Criteria criteria = example.createCriteria();
        criteria.andPidIn(Arrays.asList(ids));
        projectMapper.deleteByExample(example);
    }

    @Override
    public List<Map<String, Object>> selectProjectAllByLike(Integer position, String keyword, Integer orderby) {
        return projectMapper.selectProjectAllByLike(position,keyword,orderby);
    }

    @Override
    public List<Project> getProjectListInNeed() {
        return projectMapper.getProjectListInNeed();
    }

}