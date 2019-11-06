package com.ghh.sys.controller;


import com.ghh.commn.ValidateCode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/code")
public class VaildateCodeController {
    @RequestMapping(value="/getCode")
    public void getCode(@RequestParam(value = "time") String time, HttpServletRequest request, HttpServletResponse response) {
        ValidateCode code = new ValidateCode(80, 30, 4, 30, 25, "validateCode");
//       通过这个方法生成一个code验证码，在方法里将验证码发送到前端，并且存储一份到session中，当你带来时进行判断
        code.getCode(request, response);
    }
}
