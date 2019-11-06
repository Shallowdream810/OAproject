package com.ghh.sys.controller;

import com.ghh.sys.bean.Sources;
import com.ghh.sys.service.SourcesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/sou")
public class SourcesController {

    @Resource
    private SourcesService sourcesService;

    //修改操作
    @RequestMapping(value = "updateInfo",method = RequestMethod.PUT)
    public  String updateInfo(Sources sources){
        sourcesService.updateInfo(sources);

        return "redirect:/pm.jsp";
    }



    //通过id查找一个权限页面资源，数据回显
    @RequestMapping(value = "/findInfo/{id}",method = RequestMethod.GET)
    public ModelAndView findInfo(@PathVariable("id")Integer id){
        Sources sources =  sourcesService.findInfo(id);
        ModelAndView mv = new ModelAndView("pm-edit");
        mv.addObject("onesource",sources);
        return mv;
    }

    //操作zTree的，对权限资源增删改
    //保存一个权限页面资源
    @RequestMapping(value = "saveInfo",method = RequestMethod.POST)
    public  String saveInfo(Sources sources){
        sourcesService.saveInfo(sources);
        return "redirect:/pm.jsp";
    }



    //获取权限资源树显示在页面上，返回jeson串
    @RequestMapping(value = "sourcelist",method = RequestMethod.GET)
    @ResponseBody
    public List<Sources> getSourcelist(){
        //拿到顶级菜单
        List<Sources> list =  sourcesService.getSourcelist(0);
        //拿到顶级菜单下的子菜单
        getChildrenSources(list.get(0));
        return  list;
    }
    //递归思想，查询，递归的结束条件，当sources为null时，是没有id的
    private void getChildrenSources(Sources sources) {
        //拿到顶级sources的id值
        Integer id = sources.getId();
        //通过顶级sources的id去查二级菜单,获取二级菜单
        List<Sources> list =  sourcesService.getSourcelist(id);
        for (Sources sources1 : list) {
            getChildrenSources(sources1);
        }
        //将查询出来的二级菜单添加到顶级菜单的属性
        sources.setChildren(list);
    }
}
