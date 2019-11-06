package com.ghh.sys.controller;

import com.ghh.commn.Page;
import com.ghh.sys.bean.BaoXiao;
import com.ghh.sys.bean.Employee;
import com.ghh.sys.service.BaoXiaoService;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping("bx")
public class BaoXiaoController {
    @Resource
    private BaoXiaoService baoXiaoService;

    //添加个人报销账单
    @RequestMapping(value = "saveInfo",method = RequestMethod.POST)
    public String saveInfo(BaoXiao baoXiao,HttpSession session){
        Employee user = (Employee) session.getAttribute("user");
        baoXiao.setEmpFk(user.getEid());
        baoXiaoService.saveInfo(baoXiao);
        return "redirect:/bx/findAll";
    }

//    当请求展示页面后，需要传递当前页码，
    @RequestMapping(value = "findAll",method = RequestMethod.GET)
    public ModelAndView findAll(HttpServletRequest request, HttpSession session, @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum){
//        获取带有指定字符串关键字，并且将该关键字符串删除
        Map<String, Object> search = WebUtils.getParametersStartingWith(request,"search_");
        //将map集合转换成字符串拼接后返回到前台页面，
        String requestInfo = Page.addSearch(search);
        String requestURI = request.getRequestURI();
        ModelAndView mv = new ModelAndView("mybaoxiao-base");
        // 获取域对象中的用户名，根据此id去查询   我的报销
        Employee user = (Employee)session.getAttribute("user");
//        将查询得到的数据封装到pageInfo中，因为pageInfo中不仅仅存放这数据列表
        PageInfo<BaoXiao> page =  baoXiaoService.findAll(pageNum,user.getEid(),search);
        //将数据列表传回前台
        mv.addObject("page",page);
        mv.addObject("requestInfo",requestInfo);
        mv.addObject("requestURI",requestURI);
        return  mv;
    }
}
