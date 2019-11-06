package com.ujiuye.pro.service;

import com.ujiuye.pro.bean.Project;

import java.util.List;

public interface ProjectService {


    void saveInfo(Project project);

    List<Project> getProjectList();
}
