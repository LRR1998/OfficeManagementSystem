package com.ujiuye.pro.service;

import com.ujiuye.pro.bean.Analysis;

import java.util.List;

public interface AnalysisService {
    void saveInfo(Analysis analysis);

    List<Analysis> getAnalysisList();

    Analysis getAnalysisById(int id);
}
