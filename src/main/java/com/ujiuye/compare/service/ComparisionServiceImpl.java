package com.ujiuye.compare.service;

import com.ujiuye.compare.bean.Comparision;
import com.ujiuye.compare.bean.ComparisionExample;
import com.ujiuye.compare.mapper.ComparisionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComparisionServiceImpl implements ComparisionService{
    @Autowired
    private ComparisionMapper comparisionMapper;

    @Override
    public void saveInfo(Comparision comparision) {
        comparisionMapper.insert(comparision);
    }

    @Override
    public List<Comparision> getComparisions() {
        ComparisionExample example = new ComparisionExample();
        return comparisionMapper.selectByExample(example);
    }
}
