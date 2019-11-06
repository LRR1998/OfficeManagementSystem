import com.ujiuye.Utils.DateUtils;
import com.ujiuye.Utils.IOUtils;
import com.ujiuye.cust.bean.Customer;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class AppTest {
    @Test
    public void test02() throws MessagingException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans-email.xml");
        JavaMailSenderImpl bean = context.getBean(JavaMailSenderImpl.class);

        //邮件对象
        MimeMessage mimeMessage = bean.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        helper.setFrom("l17805596938@163.com");
        helper.setTo("1823307180@qq.com");
        helper.setSubject("这是0708班的邮件测试");
        helper.setText("1123412315");
        //发送邮件
        bean.send(mimeMessage);
        System.out.println("EMAIL PASS");
    }
    @Test
    public void test01() throws ParseException, InvalidFormatException, IOException {
        //List<Customer> list= IOUtils.excelToList(new File("D:\\desktop\\customers.xlsx"));
//        for (Customer c: list) {
//            System.out.println(c);
//        }
    }

    private String parseExcelValueToString(Cell cell) {
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
