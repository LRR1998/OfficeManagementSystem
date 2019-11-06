package com.ujiuye.sys.controller;

import com.ujiuye.sys.bean.Employee;
import com.ujiuye.sys.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("emp")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    @RequestMapping(value = "/others",method = RequestMethod.GET)
    @ResponseBody
    public List<Employee> getOthers(HttpSession session){
        Employee employee = (Employee)session.getAttribute("user");
        return employeeService.getOthers(employee.getEid());
    }

    @RequestMapping(value = "manager",method = RequestMethod.GET)
    @ResponseBody
    public List<Employee> getManagers(){
        return employeeService.getManagers();
    }
    @RequestMapping(value = "login",method = RequestMethod.POST)
    public String login(Employee employee, String validate, HttpSession session, RedirectAttributes redirectAttributes){
        String sessionKey = (String)session.getAttribute("validateCode");
        if (validate.equalsIgnoreCase(sessionKey)){
            session.removeAttribute("validateCode");
            Employee e = employeeService.login(employee);
            if (e!=null){
                session.setAttribute("user",e);
                return "redirect:/index.jsp";
            } else {
                redirectAttributes.addFlashAttribute("msg","用户名或密码错误!");
                return "redirect:/login";
            }
        }
        redirectAttributes.addFlashAttribute("msg","验证码错误!");
        return "redirect:/login";
    }
    @RequestMapping(value = "logout",method = RequestMethod.GET)
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/login.jsp";
    }
}
