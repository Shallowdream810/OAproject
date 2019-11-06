package com.ghh.pro.controller;

import com.ghh.pro.bean.Project;
import com.ghh.pro.service.IProjectService;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/pro")
public class ProjectController {

    @Autowired
    private IProjectService iProjectService;

    //导出文件接口
    @RequestMapping(value = "exportExcel", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> exportExcel() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Workbook wb = new HSSFWorkbook();
        Sheet sheet = wb.createSheet();
        Row row = sheet.createRow(0);
        Cell[] cell = new HSSFCell[9];
        for (int i = 0; i < 9; i++) {
            cell[i] = row.createCell(i);
        }
        //创建表格的第一行
        cell[0].setCellValue("ID");
        cell[1].setCellValue("项目名称");
        cell[2].setCellValue("客户公司名称");
        cell[3].setCellValue("客户方负责人");
        cell[4].setCellValue("项目经理");
        cell[5].setCellValue("项目开始时间");
        cell[6].setCellValue("立项时间");
        cell[7].setCellValue("立项完成时间");
        cell[8].setCellValue("状态");
        //获取数据列表
        List<Map<String, Object>> maps = iProjectService.selectComnameAndEname();
        for (int i = 0; i < maps.size(); i++) {
            Map<String, Object> map = maps.get(i);
            //第一行已经被创建
            Row row1 = sheet.createRow(i + 1);
            //创建9列
            Cell[] cells = new HSSFCell[9];
            for (int j = 0; j < 9; j++) {
                cells[j] = row1.createCell(j);
            }
            cells[0].setCellValue((Integer) map.get("pid"));
            cells[1].setCellValue((String) map.get("pname"));
            cells[2].setCellValue((String) map.get("comname"));
            cells[3].setCellValue((String) map.get("companyperson"));
            cells[4].setCellValue((String) map.get("ename"));
            Date starttime = (Date) map.get("starttime");
            cells[5].setCellValue(sdf.format(starttime));
            Date buildtime = (Date) map.get("buildtime");
            cells[6].setCellValue(sdf.format(buildtime));
            Date endtime = (Date) map.get("endtime");
            cells[7].setCellValue(sdf.format(endtime));
            cells[8].setCellValue((String) map.get("remark"));
        }
        FileOutputStream fos = null;
        try {
//            输出流写出到指定位置指定文件名
            fos = new FileOutputStream("C:\\Users\\Administrator\\Desktop\\project.xls");
            //将文件入到excel表格中
            wb.write(fos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        Map<String, Object> map = new HashMap<>();
        map.put("status",200);
        map.put("message","导出成功");
        return map;
    }
    //查询还没有项目需求分析表的接口
    @RequestMapping(value = "jesonListInNeed",method = RequestMethod.GET)
    @ResponseBody
    public List<Project> getProjectListInNeed(){
        List<Project> list =  iProjectService.getProjectListInNeed();
        return  list;
    }
    //查询所有的项目
    @RequestMapping(value = "jesonList",method = RequestMethod.GET)
    @ResponseBody
    public List<Project> getProjectList(){
        List<Project> list =  iProjectService.getProjectList();
        return  list;
    }
    //点击查看详情页面，调用   查看项目所有信息的mapper映射语句
    @RequestMapping(value = "lookDetail/{pid}",method = RequestMethod.GET)
    public String lookDetailById(@PathVariable(value = "pid") Integer pid,Map<String,Object> map){
        Map<String, Object> projectAll = iProjectService.selectProjectAllById(pid);
        map.put("projectInfo",projectAll);
        return  "project-base-look";
    }
//    批量删除项目信息
    @RequestMapping(value = "/delete/{ids}",method = RequestMethod.DELETE)
    @ResponseBody
    public Map<String,Object> deletePro(@PathVariable(value = "ids")Integer[] ids){
        Map<String,Object> map = new HashMap<>();
        iProjectService.deletePro(ids);
        map.put("status","200");
        return map;
    }

//    保存修改的信息语句
    @RequestMapping(value = "/update",method = RequestMethod.PUT)
    public String updateById(Project project){
        iProjectService.updateById(project);
        return  "project-base";
    }

//    通过一个id去查询对应的项目的的所有信息
    @RequestMapping(value = "/findProById/{pid}",method = RequestMethod.GET)
    public String findProById(@PathVariable(value = "pid") Integer pid, Map<String,Object> map){
        Map<String,Object> project = iProjectService.selectProjectAllById(pid);
        map.put("project",project);
        return  "project-base-edit";
    }
//    接收异步请求的列表
//    @RequestMapping(value = "/getProjectList",method = RequestMethod.GET)
//    @ResponseBody
//    public List<Project> getProjectList(){
//        //1、通过多次查询数据库得到所有信息
//        List<Project> projectList = iProjectService.getProjectList();
//        return projectList;
//    }

//    添加项目工程,使用异步之后只需要跳转到页面就行，跳转过去之后再进行异步请求
    @RequestMapping(value = "/insertInto",method = RequestMethod.POST)
    public String insertInto(Project project){
        iProjectService.insertInto(project);
        return "project-base";
    }
    //测试发送jeson串给前端
    @RequestMapping(value = "/all",method = RequestMethod.GET)
    @ResponseBody
    public List<Map<String,Object>> findAll(@RequestParam(value = "position",defaultValue = "7") Integer position,
                                            String keyword,Integer orderby){
        if (position==7){
            //查询所有
            return iProjectService.selectComnameAndEname();
        }else {
            //模糊查询
            List<Map<String, Object>> maps = iProjectService.selectProjectAllByLike(position, keyword, orderby);
            return maps;
        }
    }
}