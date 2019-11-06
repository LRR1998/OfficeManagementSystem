package com.ujiuye.pro.controller;

import com.ujiuye.pro.bean.Analysis;
import com.ujiuye.pro.service.AnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("anal")
public class AnalysisController {
    @Autowired
    private AnalysisService analysisService;

    @RequestMapping(value = "/detail/{id}",method = RequestMethod.GET)
    public ModelAndView getAnalysisById(@PathVariable("id") int id){
        ModelAndView mv = new ModelAndView("project-need-look");
        Analysis analysis = analysisService.getAnalysisById(id);
        mv.addObject("analysis",analysis);
        return mv;
    }
    //获取所有需求分析
    @RequestMapping(value = "/getAnals",method = RequestMethod.GET)
    public ModelAndView getAnalysisList(){
        ModelAndView mv = new ModelAndView("project-need");
        List<Analysis> list = analysisService.getAnalysisList();
        mv.addObject("list",list);
        return mv;
    }
    //添加需求分析
    @RequestMapping(value = "saveInfo",method = RequestMethod.POST)
    public String saveInfo(Analysis analysis){
        analysis.setAddtime(new Date());
        analysis.setUpdatetime(new Date());
        analysisService.saveInfo(analysis);
        return "redirect:getAnals";
    }

}
