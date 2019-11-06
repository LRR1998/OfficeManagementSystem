package com.ujiuye.cust.controller;

import com.ujiuye.Utils.IOUtils;
import com.ujiuye.cust.bean.Customer;
import com.ujiuye.cust.service.CustomerService;
import com.ujiuye.pro.controller.AttachmentController;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/cust")
public class CustomerController {

    @Autowired
    private CustomerService customerService;
    //导入Excel
    @RequestMapping(value = "uploadExcel",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> uploadExcel(MultipartFile excel) throws IOException, ParseException, InvalidFormatException {
        Map<String,Object> map = new HashMap<>();
        InputStream inputStream = excel.getInputStream();
        List<Customer> customers = IOUtils.excelToList(excel.getInputStream());
        if (customers!=null&&customers.size()>0){
            map.put("message","导入成功！");
            customerService.batchInsert(customers);
        } else {
            map.put("message","导入失败！");

        }
        return map;
    }
    //导出Excel
    @RequestMapping(value = "/getExcel",method =RequestMethod.GET )
    public ResponseEntity<byte[]> getExcel() throws IOException {
        Workbook wb = new XSSFWorkbook();
        Sheet sheet_one = wb.createSheet("sheet 1");
        sheet_one.setColumnWidth(3,3000);
        sheet_one.setColumnWidth(4,3500);
        Row row = sheet_one.createRow(0);
        List<String> list = new ArrayList<>();
        list.add("id");
        list.add("联系人");
        list.add("公司名称");
        list.add("添加时间");
        list.add("联系电话");
        for (int i = 0; i <5 ; i++) {
            Cell cell= row.createCell(i);
            cell.setCellValue(list.get(i));
        }
        List<Customer> list2 = customerService.getCustomerList();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for (int i = 0; i <list2.size() ; i++) {
            Customer customer = list2.get(i);
            Row rowi = sheet_one.createRow(i+1);
            Cell[] celli = new Cell[5];
            for (int j = 0; j <5 ; j++) {
                celli[j] = rowi.createCell(j);
            }
            celli[0].setCellValue(customer.getId());
            celli[1].setCellValue(customer.getCompanyperson());
            celli[2].setCellValue(customer.getComname());
            String date = sdf.format(customer.getAddtime());
            celli[3].setCellValue(date);
            celli[4].setCellValue(customer.getComphone());
        }
        try  (OutputStream fileOut = new FileOutputStream("D:\\desktop\\customers.xlsx")) {
            wb.write(fileOut);
        } catch (Exception e) {
            e.printStackTrace();
        }
        AttachmentController attachmentController = new AttachmentController();
        return  attachmentController.download("D:\\desktop/customers.xlsx");
    }
    //异步查询客户信息，根据id查找
    @RequestMapping(value = "/leader",method = RequestMethod.GET)
    @ResponseBody
    public Customer findLeaderById(Integer id){
        return customerService.detail(id);
    }
    //异步查询客户列表
    @RequestMapping(value="/jsonList",method = RequestMethod.GET)
    @ResponseBody
    public List<Customer> getCustomerJsonList(){
        return  customerService.getCustomerList();
    }
    //条件查询
    @RequestMapping(value="/search",method = RequestMethod.GET)
    public ModelAndView search(Integer cid ,String keyword,Integer orderby){
        ModelAndView mv = new ModelAndView("customer");
        List<Customer> list = customerService.search(cid,keyword,orderby);
        mv.addObject("customers",list);
        return  mv;
    }
    //批量删除
    @RequestMapping(value = "/delete",method = RequestMethod.DELETE)
    @ResponseBody
    public Map<String,Object> delete(String ids){
        boolean result = customerService.delete(ids);
        Map<String,Object> map = new HashMap<>();
        if (result){
            map.put("statusCode",200);
            map.put("message","删除成功");
        }else {
            map.put("statusCode",500);
            map.put("message","部分删除失败");
        }
        return map;
    }
    //更新客户信息
    @RequestMapping(value = "/update",method = RequestMethod.PUT)
    public String update(Customer customer){
        customerService.updateCustomer(customer);
        return "redirect:/cust/list";
    }
    //编辑页面
    @RequestMapping(value = "/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Integer id){
        ModelAndView mv=new ModelAndView("customer-edit");
        Customer customer = customerService.detail(id);
        mv.addObject("customer",customer);
        return mv;
    }
    //查看详情
    @RequestMapping(value = "/detail/{id}",method = RequestMethod.GET)
    public ModelAndView detail(@PathVariable("id") Integer id){
        ModelAndView mv=new ModelAndView("customer-look");
        Customer customer = customerService.detail(id);
        mv.addObject("customer",customer);
        return mv;
    }
    //添加新用户
    @RequestMapping(value = "/saveInfo",method = RequestMethod.POST)
    public String saveInfo(Customer customer){
        customer.setAddtime(new Date());
        customer.setId(null);
        customerService.saveInfo(customer);
        return "redirect:/cust/list";
    }
    //查找用户列表
    @RequestMapping(value="/list",method = RequestMethod.GET)
    public ModelAndView getCustomerList(){
        List<Customer> customers = customerService.getCustomerList();
        ModelAndView mv = new ModelAndView("customer");
        mv.addObject("customers",customers);
        return  mv;
    }
    //校验手机号是否已使用
    @RequestMapping("/checkPhone")
    @ResponseBody
    public boolean checkPhone(String phone){
        return customerService.checkPhone(phone);
    }
}
