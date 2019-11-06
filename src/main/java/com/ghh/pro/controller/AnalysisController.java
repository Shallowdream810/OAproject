package com.ghh.pro.controller;

import com.ghh.pro.bean.Analysis;
import com.ghh.pro.service.AnalysisService;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/ana")
public class AnalysisController {
    @Resource
    private AnalysisService analysisService;

    //导出文件
    @RequestMapping(value = "exportExcel",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> exportExcel(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Workbook wb = new HSSFWorkbook();
        Sheet sheet = wb.createSheet("需求分析");
        Row row = sheet.createRow(0);
        Cell[] cell = new  HSSFCell[8];
        for (int i = 0; i < 8; i++) {
            cell[i] = row.createCell(i);
        }
        cell[0].setCellValue("ID");
        cell[1].setCellValue("标题");
        cell[2].setCellValue("项目名称");
        cell[3].setCellValue("添加时间");
        cell[4].setCellValue("修改时间");
        cell[5].setCellValue("简单描述");
        cell[6].setCellValue("详细描述");
        cell[7].setCellValue("备注");
        List<Analysis> list =  analysisService.findAll();
        for (int i = 0; i < list.size(); i++) {
            Analysis analysis = list.get(i);
            Row row1 = sheet.createRow(i + 1);
            Cell[] cells = new HSSFCell[8];
            for (int j = 0; j < 8; j++) {
                cells[j] = row1.createCell(j);
            }
            cells[0].setCellValue(analysis.getId());
            cells[1].setCellValue(analysis.getTitle());
            cells[2].setCellValue(analysis.getProname());
            cells[3].setCellValue(sdf.format(analysis.getAddtime()));
            cells[4].setCellValue(sdf.format(analysis.getUpdatetime()));
            cells[5].setCellValue(analysis.getSimpledis());
            cells[6].setCellValue(analysis.getDetaileddis());
            cells[7].setCellValue(analysis.getRemark());
        }
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream("C:\\Users\\Administrator\\Desktop\\projectneed.xls");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally {
            if (fos!=null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        Map<String,Object> map = new HashMap<>();
        map.put("statu",200);
        map.put("message","导出成功");
        return map;
    }


//    进行模糊查询
    @RequestMapping(value = "searchByLike",method = RequestMethod.GET)
    @ResponseBody
    public List<Analysis>  searchByLike(Integer cid,String keyworld,Integer orderby){
        List<Analysis> list =  analysisService.searchByLike(cid,keyworld,orderby);
        return  list;
    }
    //修改id对应的项目需求表
    @RequestMapping(value = "updateInfo",method = RequestMethod.PUT)
    public String updateInfo(Analysis analysis){
        analysisService.updateInfo(analysis);
        return  "redirect:/ana/findAll";
    }
    //给项目添加需求分析
    @RequestMapping(value = "saveInfo",method = RequestMethod.POST)
    public String saveInfo(Analysis analysis){
        //将项目表的id赋值给需求分析表的id
        String proidname = analysis.getProname();
        String[] split = proidname.split(":");
        analysis.setId(Integer.valueOf(split[0]));
        analysis.setProname(split[1]);
        analysis.setAddtime(new Date());
        analysisService.saveInfo(analysis);
        return "redirect:/ana/findAll";
    }
    //展示所有项目的需求分析
    @RequestMapping(value = "findAll",method = RequestMethod.GET)
    public ModelAndView findAll(){
        ModelAndView mv = new ModelAndView("project-need");
        List<Analysis> list =  analysisService.findAll();
        mv.addObject("list",list);
        return  mv;
    }
    //根据id查询对应的需求分析
    @RequestMapping(value = "findById/{id}",method = RequestMethod.GET)
    public String findById(@PathVariable(value = "id") Integer id, Map<String,Object> map){
        Analysis analysis =  analysisService.findById(id);
        map.put("analysis",analysis);
        return  "project-need-edit";
    }
}
