package com.ghh.sys.controller;

import com.ghh.sys.bean.Employee;
import com.ghh.sys.service.IEmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/sys")
public class EmployeeController {

    @Resource
    private IEmployeeService employeeService;

    //发送邮件界面查询收件人
    @RequestMapping(value = "findemail",method = RequestMethod.GET)
    @ResponseBody
    public List<Map<String,Object>> findemail(HttpServletRequest request){
       Employee employee =  (Employee)request.getSession().getAttribute("user");
        Integer eid = employee.getEid();
        return employeeService.findemail(eid);
    }





    //退出
    @RequestMapping(value = "loginOut",method = RequestMethod.GET)
    public String loginOut(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/login.jsp";
    }
    //登陆
    @RequestMapping(value = "loginIn",method = RequestMethod.POST)
    public String loginIn(Employee employee, String code, HttpSession session, RedirectAttributes redirectAttributes){
        String realCode =(String)session.getAttribute("validateCode");
        if (code.equalsIgnoreCase(realCode)){
            //如果验证码正确时直接将session中的验证码清除
            session.removeAttribute("validateCode");
            employee =  employeeService.loginIn(employee);
            if (employee!=null){
                //若能够查询到则将该用户存储进session中
                session.setAttribute("user",employee);
                return "redirect:/index.jsp";
            }else {
                redirectAttributes.addFlashAttribute("message","用户名或密码错误");
                return "redirect:/login";
            }
        }else {
            redirectAttributes.addFlashAttribute("message","验证码错误");
            return "redirect:/login";
        }
    }
//   注册时 查询用户名是否存在
    @RequestMapping(value = "searchUser",method = RequestMethod.GET)
    public String searchUser(){
        return  null;
    }

//    查询本公司的所有经理的接口
    @RequestMapping(value = "empLeaders",method = RequestMethod.GET)
    @ResponseBody
    public List<Employee> getEmpLeaders(){
        List<Employee> empLeaders = employeeService.getEmpLeaders();
        return  empLeaders;
    }



}
