package com.ujiuye.usual.controller;

import com.ujiuye.sys.bean.Employee;
import com.ujiuye.usual.bean.Forumpost;
import com.ujiuye.usual.service.ForumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.Date;

@Controller
@RequestMapping("/forum")
public class ForumController {
    @Autowired
    private ForumService forumService;
    @RequestMapping(value = "/saveInfo",method = RequestMethod.POST)
    public String saveInfo(Forumpost forumpost, HttpSession session){
        Employee employee = (Employee) session.getAttribute("user");
        forumpost.setEmpFk3(employee.getEid());
        forumpost.setCreatetime(new Date());
        forumService.saveInfo(forumpost);
        return "redirect:/main.jsp";
    }

}
