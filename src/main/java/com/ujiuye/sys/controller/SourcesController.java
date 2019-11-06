package com.ujiuye.sys.controller;

import com.ujiuye.common.ResultEntity;
import com.ujiuye.sys.bean.Sources;
import com.ujiuye.sys.service.SourcesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/sources")
public class SourcesController {
    @Autowired
    private SourcesService sourcesService;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @ResponseBody
    public List<Sources> getSourcesList(){
        List<Sources> list = sourcesService.getSourcesByPid(0);
        queryChildren(list.get(0));
        return list;
    }
    //递归查询子节点
    public void queryChildren(Sources sources){
        List<Sources> list = sourcesService.getSourcesByPid(sources.getId());
        for (Sources sources1 : list) {
            queryChildren(sources1);
        }
        sources.setChildren(list);
    }
    //删除节点
    @RequestMapping(value = "delete")
    public ResultEntity deleteById(int id){
        sourcesService.deleteById(id);
        return ResultEntity.success();
    }
    //增加节点
    @RequestMapping(value = "add",method = RequestMethod.POST)
    public String saveInfo(Sources sources){
        sourcesService.saveInfo(sources);
        return "redirect:/pm.jsp";
    }
    //查找节点
    @RequestMapping(value = "find" ,method = RequestMethod.GET)
    public ModelAndView findSources(int id){
        ModelAndView mv = new ModelAndView("pm-edit");
        Sources sources = sourcesService.findSources(id);
        mv.addObject("onesource",sources);
        return mv;
    }
    //编辑节点
    @RequestMapping(value = "update",method = RequestMethod.PUT)
    public String updateSources(Sources sources){
        sourcesService.updateSources(sources);
        return "redirect:/pm.jsp";
    }
}
