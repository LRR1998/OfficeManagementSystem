package com.ujiuye.usual.controller;

import com.github.pagehelper.PageInfo;
import com.ujiuye.sys.bean.Employee;
import com.ujiuye.usual.bean.Baoxiao;
import com.ujiuye.usual.service.BaoxiaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/baoxiao")
public class BaoxiaoController {
    @Autowired
    private BaoxiaoService baoxiaoService;
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public ModelAndView getBaoxiaoList(HttpSession session,int pageNum){
        Employee user = (Employee)session.getAttribute("user");
        ModelAndView mv = new ModelAndView("mybaoxiao-base");
        PageInfo<Baoxiao> page = baoxiaoService.getMyBaoxiao(user.getEid(),pageNum);
        mv.addObject("page",page);
        return mv;
    }
    @RequestMapping(value = "/saveInfo" ,method = RequestMethod.POST)
    public String saveInfo(Baoxiao baoxiao,int eid){
        baoxiao.setEmpFk(eid);
        baoxiaoService.saveInfo(baoxiao);
        return "redirect:/baoxiao/list?pageNum=1";
    }
}
