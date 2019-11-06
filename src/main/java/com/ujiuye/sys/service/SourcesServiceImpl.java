package com.ujiuye.sys.service;

import com.ujiuye.sys.bean.Sources;
import com.ujiuye.sys.bean.SourcesExample;
import com.ujiuye.sys.mapper.SourcesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SourcesServiceImpl implements SourcesService{

    @Autowired
    private SourcesMapper sourcesMapper;

    @Override
    public List<Sources> getSourcesByPid(int i) {
        SourcesExample example = new SourcesExample();
        SourcesExample.Criteria criteria = example.createCriteria();
        criteria.andPidEqualTo(i);
        return sourcesMapper.selectByExample(example);
    }

    @Override
    public void deleteById(int id) {
        sourcesMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void saveInfo(Sources sources) {
        sourcesMapper.insert(sources);
    }

    @Override
    public Sources findSources(int id) {
        Sources sources = sourcesMapper.selectByPrimaryKey(id);
        return sources;
    }

    @Override
    public void updateSources(Sources sources) {
        sourcesMapper.updateByPrimaryKeySelective(sources);
    }
}
