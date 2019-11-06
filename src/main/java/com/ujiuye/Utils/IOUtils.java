package com.ujiuye.Utils;

import com.ujiuye.cust.bean.Customer;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class IOUtils {

    //下载文件的方法，参数为文件的绝对路径
    public static ResponseEntity<byte[]> downLoad(String path) throws IOException {
        FileInputStream fis = new FileInputStream(new File(path));
        byte[] b = new byte[fis.available()];
        fis.read(b);
        MultiValueMap<String,String> headers = new HttpHeaders();
        String[] split = path.split("/");
        String attname = split[split.length-1];
        attname = new String(attname.getBytes("gbk"),"iso8859-1");
        headers.add("Content-Disposition","attachment;filename="+attname);
        HttpStatus status = HttpStatus.OK;
        ResponseEntity<byte[]> result = new ResponseEntity<byte[]>(b,headers,status);
        return result;
    }

    //上传Excel文件，返回List<Customer>集合
    public static List<Customer> excelToList(InputStream file) throws ParseException, IOException, InvalidFormatException {
        DateUtils dateUtils = new DateUtils();
        Workbook workbook = null;
        workbook = WorkbookFactory.create(file);
        List<Customer> list= new ArrayList<>();
        for (int k = 0; k < workbook.getNumberOfSheets(); k++) {
            Sheet sheet = workbook.getSheetAt(k);
            for (int i = sheet.getFirstRowNum()+1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                Customer customer = new Customer();
                for (int j = row.getFirstCellNum(); j < row.getLastCellNum(); j++) {
                    Cell cell = row.getCell(j);
                    String value = parseExcelValueToString(cell);
                    if (j==0){
                        value = value.substring(0,value.indexOf("."));
                        customer.setId(Integer.parseInt(value));
                    } else if (j==1){
                        customer.setCompanyperson(value);
                    } else if (j==2){
                        customer.setComname(value);
                    } else if (j==3){
                        customer.setAddtime(dateUtils.StringToDate(value));
                    } else if (j==4){
                        customer.setComphone(value);
                    } else if (j==5){
                        customer.setComaddress(value);
                    } else if (j==6){
                        customer.setCamera(value);
                    } else if (j==7){
                        customer.setPresent(value);
                    } else if (j==8){
                        customer.setRemark(value);

                    }
                }
                list.add(customer);
            }
        }
        return  list;
    }
    //将表格内的各种数据类型转化为String类型返回
    private static String parseExcelValueToString(Cell cell) {
        String result = "";
        int cellType = cell.getCellType();
        if (cellType==1){
            result = cell.getStringCellValue();
        } else if (cellType==4){
            result = String.valueOf(cell.getBooleanCellValue());
        } else if (cellType==0){
            result = cell.getNumericCellValue() +"";
        } else if (cellType==2){
            result = cell.getCellFormula();
        } else if (cellType==3){
            result = "";
        }
        return result;
    }
}
