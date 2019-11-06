package com.ghh.sys.controller;

import com.ghh.commn.ResultEntity;
import com.ghh.sys.bean.Position;
import com.ghh.sys.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/pos")
public class PositionController {

    @Resource
    private PositionService positionService;

    @RequestMapping(value = "jesonList",method = RequestMethod.GET)
    @ResponseBody
    public ResultEntity getJesonList(){

        List<Position> list =  positionService.getJesonList();
        return  ResultEntity.success().put("list",list);
    }

}
