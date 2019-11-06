package com.ujiuye.pro.controller;

import com.ujiuye.pro.bean.Project;
import com.ujiuye.pro.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("pro")
public class ProjectController {
    @Autowired
    private ProjectService projectService;
    @RequestMapping(value = "saveInfo",method = RequestMethod.POST)
    public String saveInfo(Project project){
        projectService.saveInfo(project);
        return "redirect:/pro/getProjects";
    }
    @RequestMapping(value = "/getProjects",method = RequestMethod.GET)
    public ModelAndView getProjectList(){
        ModelAndView mv=new ModelAndView("project-base");
        List<Project> projects = projectService.getProjectList();
        mv.addObject("projects",projects);
        return mv;
    }
    @RequestMapping(value = "getJsonList",method =RequestMethod.GET )
    @ResponseBody
    public List<Project> getJsonList(){
        return projectService.getProjectList();
    }
}
