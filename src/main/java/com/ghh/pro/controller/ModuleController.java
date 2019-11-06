package com.ghh.pro.controller;

import com.ghh.commn.Page;
import com.ghh.commn.ResultEntity;
import com.ghh.pro.bean.Model;
import com.ghh.pro.service.ModuleService;
import com.github.pagehelper.PageInfo;
import org.omg.PortableInterceptor.RequestInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/mod")
public class ModuleController {

    @Resource
    private ModuleService moduleService;

    //返回模块的列表
    @RequestMapping(value = "findAll",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> findAll(HttpServletRequest request,@RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum){
        Map<String, Object> search = WebUtils.getParametersStartingWith(request,"search_");
        //查询出来的数据
        PageInfo<Model> page =   moduleService.findAll(pageNum,search);
        String requestURI = request.getRequestURI();
        String requestInfo = Page.addSearch(search);
        Map<String,Object> map  = new HashMap<>();
        map.put("page",page);
        map.put("requestURI",requestURI);
        map.put("requestInfo", requestInfo);
        return map;
    }
    //功能模块的展示列表接口
    @RequestMapping(value = "insertInfo",method = RequestMethod.POST)
    public String insertInfo(Model model){
        moduleService.insertInfo(model);
        return "redirect:/mod/findAll" ;
    }
}
