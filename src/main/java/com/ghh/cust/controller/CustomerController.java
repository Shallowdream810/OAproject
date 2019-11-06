package com.ghh.cust.controller;

import com.ghh.cust.bean.Customer;
import com.ghh.cust.service.CustService;
import com.ghh.cust.service.ICustService;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/cust")
public class CustomerController {

    @Autowired
    private ICustService custService;

    //导入Excel表格
    @RequestMapping(value = "importExcel",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> importExcel(MultipartFile file){

        return null;
    }

    //导出excel表格
    @RequestMapping(value = "exportExcel",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> exportExcel(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Workbook wb = new HSSFWorkbook();
        Sheet sheet = wb.createSheet();
        Row row = sheet.createRow(0);
        //通过HSSFCell来创建一个cell数组，用来为一行循环创建多列
        Cell[] cell = new HSSFCell[5];
        for (int i = 0; i < 5; i++) {
            cell[i] = row.createCell(i);
        }
//        第一行标题
        cell[0].setCellValue("ID");
        cell[1].setCellValue("联系人");
        cell[2].setCellValue("公司名称");
        cell[3].setCellValue("添加时间");
        cell[4].setCellValue("联系电话");
        List<Customer> customerList = custService.getCustomerList();
        for (int i = 0; i < customerList.size(); i++) {
//            获取list的每一行，获取每个对象
            Customer customer = customerList.get(i);
//            每遍历一次，创建一行
            Row row1 = sheet.createRow(i + 1);
            Cell[] cell1 = new HSSFCell[5];
            for (int j = 0; j < 5; j++) {
                cell1[j] = row1.createCell(j);
            }
            cell1[0].setCellValue(customer.getId());
            cell1[1].setCellValue(customer.getCompanyperson());
            cell1[2].setCellValue(customer.getComname());
            Date addtime = customer.getAddtime();
            String format = sdf.format(addtime);
            cell1[3].setCellValue(format);
            cell1[4].setCellValue(customer.getComphone());
        }
//      将数据全部填入到表格后，将数据写出
        FileOutputStream fos = null;
        try{
//            输出流写出到指定位置指定文件名
            fos  = new FileOutputStream("C:\\Users\\Administrator\\Desktop\\custom.xls");
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
        Map<String,Object> map = new HashMap<>();
        map.put("statue","200");
        map.put("message1","导出成功");
        return  map;
    }
//    根据ID查询客户公司的公司联系人
    @RequestMapping(value = "getCompanyperson/{id}",method = RequestMethod.GET)
    @ResponseBody
    public Customer getCompanyperson(@PathVariable(value = "id") Integer id){
        return custService.findById(id);
    }

//    基本信息管理中添加时发送的ajax请求响应
    @RequestMapping(value = "custList",method = RequestMethod.GET)
    @ResponseBody
    public List<Customer> custList(){
        return  custService.getCustomerList();
    }

//    通过条件模糊查询
    @RequestMapping(value = "/search",method = RequestMethod.GET)
    public ModelAndView searchCust(Integer cid,String keyword,Integer orderby){
        System.out.println(cid+"=="+keyword+"==="+orderby);
        ModelAndView mv = new ModelAndView("customer");
        List<Customer> list =  custService.searchCust(cid,keyword,orderby);
        mv.addObject("custlist",list);
        return  mv;
    }

//    批量删除操作
    @RequestMapping(value = "/delete/{ids}",method = RequestMethod.DELETE)
    @ResponseBody
    public Map<String,Object> batchDelete(@PathVariable(value = "ids") Integer[] ids){
        boolean b =  custService.batchDelete(ids);
        Map<String,Object> map = new HashMap<>();
        if (b){
            map.put("status",200);
            map.put("msage","删除成功");
        }else{
            map.put("status",200);
            map.put("msage","删除失败");
        }
        return map;
    }
//    点击查看公司详情
    @RequestMapping(value = "/detail/{id}",method = RequestMethod.GET)
    public ModelAndView customerDetai(@PathVariable(value = "id") Integer cid){
        ModelAndView mv = new ModelAndView("customer-look");
        Customer customer =  custService.findById(cid);
        mv.addObject("customerinfo",customer);
        return  mv ;
    }


//    修改界面发送来的修改信息
    @RequestMapping(value = "/update",method = RequestMethod.PUT)
    public String updatecust(Customer customer){
        custService.updatecust(customer);
        return "redirect:/cust/list";
    }

//    编辑前回填用户信息
    @RequestMapping(value = "/findById/{id}",method = RequestMethod.GET)
    public ModelAndView findById(@PathVariable(value = "id") Integer cid){
        ModelAndView mv = new ModelAndView("customer-edit");
        Customer customer =  custService.findById(cid);
        mv.addObject("customerinfo",customer);
        return  mv ;
    }
//    展示所有用户的信息
    @RequestMapping(value = "list",method = RequestMethod.GET)
    public ModelAndView getCustomerList(HttpServletRequest request){
        System.out.println(request.getRemoteAddr());
        ModelAndView mv = new ModelAndView("customer");
        List<Customer> list =  custService.getCustomerList();
        mv.addObject("custlist",list);
        return  mv;
    }
//    添加用户数据
    @RequestMapping(value = "saveInfo",method = RequestMethod.POST)
    public String saveInfo(Customer customer){
        custService.saveInfo(customer);
        return "redirect:/cust/list";
    }
}
