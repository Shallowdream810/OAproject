package com.ghh.pro.controller;

import com.ghh.cust.service.CustService;
import com.ghh.cust.service.ICustService;
import com.ghh.pro.bean.Attachment;
import com.ghh.pro.service.AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping(value = "/atta")
public class AttachmentController  {

    @Resource
    private AttachmentService attachmentService;

    //添加数据+文件上传
    @RequestMapping(value = "saveInfo",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> saveAttaInfo(Attachment atta, MultipartFile attachment, HttpSession session){
        String realPath = session.getServletContext().getRealPath("/upload");
        File file = new File(realPath);
        if (!file.exists()){
            file.mkdirs();
        }
        String filename = attachment.getOriginalFilename();
        String realName =  UUID.randomUUID().toString().replaceAll("-","")+filename;
        try {
            attachment.transferTo(new File(realPath+"/"+realName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        atta.setPath(realPath+"/"+realName);
        attachmentService.saveAttaInfo(atta);
        Map<String,Object> map = new HashMap<>();
        map.put("statusCode",200);
        return map;
    }

    //查询所有数据并跳转页面页面
    @RequestMapping(value = "findAll",method = RequestMethod.GET)
    public String findAll(Map<String,Object> map){
        List<Attachment> list = attachmentService.findAll();
        map.put("attaList",list);
        return  "project-file";
    }

}
