package com.ujiuye.sys.controller;

import com.ujiuye.common.ResultEntity;
import com.ujiuye.sys.bean.Role;
import com.ujiuye.sys.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ContextLoaderListener;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;
    @RequestMapping(value = "/saveInfo",method = RequestMethod.POST)
    @ResponseBody
    public ResultEntity saveInfo(Role role, String ids){
        int row = roleService.saveInfo(role);
        String[] split = ids.split(",");
        if (row>0){
            Integer roleid = role.getRoleid();
            roleService.roleAndSources(roleid,split);
            return ResultEntity.success();
        }
        return ResultEntity.error();
    }
}
