package com.ghh.sys.controller;

import com.ghh.commn.ResultEntity;
import com.ghh.sys.bean.Role;
import com.ghh.sys.service.RoleService;
import com.ghh.sys.service.RoleSourceService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;
    @Resource
    private RoleSourceService roleSourceService;

    @RequestMapping(value = "jesonList",method = RequestMethod.GET)
    @ResponseBody
    public ResultEntity getJesonList(){
       List<Role> list =   roleService.getJesonList();
       return  ResultEntity.success().put("list",list);
    }



    //分页查询所有角色的列表信息,分页信息，
    @RequestMapping(value = "getRoleList",method = RequestMethod.GET)
    @ResponseBody
    public ResultEntity getRoleList(HttpServletRequest request, @RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum){
        PageInfo<Role> page = roleService.getRoleList(pageNum);
        String requestURI = request.getRequestURI();
        String requestInfo = "";
        return  ResultEntity.success().put("pagelist",page).put("requestURI",requestURI).put("requestInfo",requestInfo);
    }













    //添加角色信息
    @RequestMapping(value = "saveInfo",method = RequestMethod.POST)
    @ResponseBody
    public ResultEntity saveInfo(Role role,String ids){
        //1、添加角色，一个角色对应多个权限资源，所以我们在将添加的角色信息放到数据库中
        //之后，还要获取他的id值，去存储到role_sources的数据库中，实现一个角色多个权限的逻辑
        int id =  roleService.saveInfo(role);
        //2、将ids带表的权限类的信息id和role的id，再存储到role_source表中
        roleSourceService.saveInfo(id,ids);
        return ResultEntity.success();
    }
}
