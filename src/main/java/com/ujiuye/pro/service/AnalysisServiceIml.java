package com.ujiuye.pro.service;

import com.ujiuye.pro.bean.Analysis;
import com.ujiuye.pro.bean.AnalysisExample;
import com.ujiuye.pro.mapper.AnalysisMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnalysisServiceIml implements AnalysisService {
    @Autowired
    private AnalysisMapper analysisMapper;

    @Override
    public void saveInfo(Analysis analysis) {
        analysisMapper.insert(analysis);
    }

    @Override
    public List<Analysis> getAnalysisList() {
        AnalysisExample example = new AnalysisExample();
        List<Analysis> analyses = analysisMapper.selectByExample(example);
        return analyses;
    }

    @Override
    public Analysis getAnalysisById(int id) {
        return analysisMapper.selectByPrimaryKey(id);
    }
}
