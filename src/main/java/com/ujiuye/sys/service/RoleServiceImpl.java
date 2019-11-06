package com.ujiuye.sys.service;

import com.ujiuye.sys.bean.Role;
import com.ujiuye.sys.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Arrays;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public int saveInfo(Role role) {
        int row = roleMapper.saveInfo(role);
        return row;
    }

    @Override
    public void roleAndSources(Integer roleid, String[] split) {
        List<String> strings = Arrays.asList(split);
        roleMapper.roleAndSources(roleid,strings);
    }
}
