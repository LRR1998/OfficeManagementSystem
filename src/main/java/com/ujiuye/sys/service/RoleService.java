package com.ujiuye.sys.service;

import com.ujiuye.sys.bean.Role;

public interface RoleService {
    int saveInfo(Role role);

    void roleAndSources(Integer roleid, String[] split);
}
