package com.ujiuye.sys.service;

import com.ujiuye.sys.bean.Sources;

import java.util.List;

public interface SourcesService {
    List<Sources> getSourcesByPid(int i);

    void deleteById(int id);

    void saveInfo(Sources sources);

    Sources findSources(int id);

    void updateSources(Sources sources);
}
