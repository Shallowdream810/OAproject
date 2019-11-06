package com.test;

import com.ghh.cust.bean.Goods;
import com.ghh.cust.service.GoodsService;
import com.ghh.cust.service.GoodsServiceImp;
import com.ghh.pro.service.IProjectService;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.*;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

public class testapp {

    @Test
    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean-quartz.xml");
    }

    @Test
    public void test02() {
        String s1 = "3:商城";
        String[] split = s1.split(":");
        System.out.println(split[1]);
//        for (int i = 0; i < split.length+1; i++) {
//            System.out.println(split[i]);
//        }
    }

    @Test
    public void test03() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
        GoodsService bean = ac.getBean(GoodsServiceImp.class);
        List<Goods> goodsList = bean.getGoodsList();
//        for (Goods goods : goodsList) {
//            System.out.println(goods);
//        }

    }

    @Test
    public void test04() {
        //创建一个Excel表格，workbook是一个接口
        Workbook wb = new XSSFWorkbook();
        //创建多个sheet
        Sheet sheet1 = wb.createSheet("sheet1");
//        创建多行（createRow(0);）
        Row row = sheet1.createRow(0);
        //创建第一个表的第一行的几个列
        Cell cell0 = row.createCell(0);
        cell0.setCellValue("id");
        FileOutputStream fos = null;
        try {
//            输出流写出到指定位置指定文件名
            fos = new FileOutputStream("C:\\Users\\Administrator\\Desktop\\custom.xls");
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
    }
    @Test
    public void test05() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");
        IProjectService bean = applicationContext.getBean(IProjectService.class);
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
        List<Map<String, Object>> maps = bean.selectComnameAndEname();
        for (int i = 0; i < maps.size(); i++) {
            Map<String, Object> map = maps.get(i);
            //第一行已经被创建
            Row row1 = sheet.createRow(i+1);
            //创建9列
            Cell[] cells = new HSSFCell[9];
            for (int j = 0; j < 9; j++) {
                cells[j] = row1.createCell(j);
            }
            cells[0].setCellValue((Integer)map.get("pid"));
            cells[1].setCellValue((String)map.get("pname"));
            cells[2].setCellValue((String)map.get("comname"));
            cells[3].setCellValue((String)map.get("companyperson"));
            cells[4].setCellValue((String)map.get("ename"));
            Date starttime = (Date)map.get("starttime");
            cells[5].setCellValue(sdf.format(starttime));
            Date buildtime = (Date)map.get("buildtime");
            cells[6].setCellValue(sdf.format(buildtime));
            Date endtime = (Date)map.get("endtime");
            cells[7].setCellValue(sdf.format(endtime));
            cells[8].setCellValue((String)map.get("remark"));
        }
        FileOutputStream fos = null;
        try{
//            输出流写出到指定位置指定文件名
            fos  = new FileOutputStream("C:\\Users\\Administrator\\Desktop\\project.xls");
            //将文件入到excel表格中
            wb.write(fos);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (fos !=null){
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    @Test
    public  void test06(){

        try {
            Workbook wb = WorkbookFactory.create(new File("C:\\Users\\Administrator\\Desktop\\project.xls"));
            Sheet sheet = wb.getSheetAt(0);
            //获取当前表的第一行
            int firstRowNum = sheet.getFirstRowNum();
            //获取当前表的最后一行
            int lastRowNum = sheet.getLastRowNum();
            //遍历每一行
            for (int i = 0; i <= lastRowNum; i++) {
                Row row = sheet.getRow(i);
                //遍历每一列
                for (int j = 0; j < row.getLastCellNum(); j++) {
                    Cell cell = row.getCell(j);
//                    对表中的每一列进行格式解析
                   String value =  parseExcelCell(cell);
                   //如果是第一列ID正整数值，我们将其切割成
                   if (i>0 && j==0){
                      value =  value.substring(0,value.indexOf("."));
                   }
                    System.out.print(value+"  ");
                }
                System.out.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //对每一列进行格式转换
    private String parseExcelCell(Cell cell) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String  result = "";
        switch (cell.getCellType()){
            case Cell.CELL_TYPE_STRING:
                result = cell.getStringCellValue();
                break;
            case Cell.CELL_TYPE_BOOLEAN:
                result= String.valueOf(cell.getBooleanCellValue());
                break;
            case Cell.CELL_TYPE_FORMULA://函数，返回值就是String类型
                result = cell.getCellFormula();
                break;
            case Cell.CELL_TYPE_NUMERIC://时间属于这一类
                if(HSSFDateUtil.isCellDateFormatted(cell)){
                    Date date = cell.getDateCellValue();
                    result = sdf.format(date);
                }
//                如果你的这个类型是数字类型，并不是时间类型
                else {
                    BigDecimal bd = new BigDecimal(cell.getNumericCellValue());
                    result = bd.toString();
                }
                break;
            default:
                result="";
        }
        return  result;
    }
}