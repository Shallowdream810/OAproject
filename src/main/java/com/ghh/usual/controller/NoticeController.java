package com.ghh.usual.controller;


import com.alibaba.fastjson.JSON;
import com.ghh.commn.ResultEntity;
import com.ghh.usual.bean.Notice;
import com.ghh.usual.service.NoticeService;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.xml.transform.Result;
import java.util.List;

@Controller
@RequestMapping("/not")
public class NoticeController {


    @Resource
    private NoticeService noticeService;

    //点击显示通知详情
    @RequestMapping(value = "/noticedetail/{nid}",method = RequestMethod.GET)
    @ResponseBody
    public Notice noticedetail(@PathVariable("nid")Integer nid){
        return noticeService.noticedetail(nid);
    }

    //主页面展示前三条最新通知
    @RequestMapping(value = "findNotices",method = RequestMethod.GET)
    @ResponseBody
    public ResultEntity getNotices(){
        List<Notice> list =  noticeService.getNotices();
        return   ResultEntity.success().put("list",list);
    }
    //通知列表
    @RequestMapping(value = "/findAll",method = RequestMethod.GET)
    @ResponseBody
    public ResultEntity getNoticeList(HttpServletRequest request, @RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum){
        PageInfo<Notice> pagelist =  noticeService.getNoticeList(pageNum);
        String requestURI = request.getRequestURI();
        String requestInfo = "";
        return  ResultEntity.success().put("pagelist",pagelist).put("requestURI",requestURI).put("requestInfo",requestInfo);
    }
    //发布通知
    @RequestMapping(value = "saveInfo",method = RequestMethod.POST)
    @ResponseBody
    public ResultEntity saveInfo(Notice notice){
         int i =  noticeService.saveInfo(notice);
        System.out.println(i);
        if (i>0){
            return ResultEntity.success();
        }else
            return ResultEntity.error();
    }
}
