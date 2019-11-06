package com.ujiuye.compare.controller;

import com.ujiuye.compare.bean.Comparision;
import com.ujiuye.compare.service.ComparisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/comparision")
public class ComparisionController {
    @Autowired
    private ComparisionService comparisionService;

    @RequestMapping(value = "/saveInfo",method = RequestMethod.POST)
    public String saveInfo(Comparision comparision){
        comparisionService.saveInfo(comparision);
        return "redirect:/indexvalue-base.jsp";
    }
    @RequestMapping(value = "/getComparisions",method = RequestMethod.GET)
    @ResponseBody
    public List<Comparision> getComparisions(){
        return comparisionService.getComparisions();
    }
}
