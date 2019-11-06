package com.ujiuye.usual.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ujiuye.usual.bean.Baoxiao;
import com.ujiuye.usual.bean.BaoxiaoExample;
import com.ujiuye.usual.mapper.BaoxiaoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class BaoxiaoServiceIml implements BaoxiaoService{
    @Autowired
    private BaoxiaoMapper baoxiaoMapper;

    @Override
    public PageInfo<Baoxiao> getMyBaoxiao(Integer eid, int pageNum) {
        PageHelper.startPage(pageNum,3);
        BaoxiaoExample example = new BaoxiaoExample();
        BaoxiaoExample.Criteria criteria = example.createCriteria();
        criteria.andEmpFkEqualTo(eid);
        List<Baoxiao> list = baoxiaoMapper.selectByExample(example);
        //navigatePages: 显示的导航页数
        PageInfo<Baoxiao> page = new PageInfo<>(list,3);
        return page;
    }

    @Override
    public void saveInfo(Baoxiao baoxiao) {
        baoxiao.setBxid(UUID.randomUUID().toString().replace("-",""));
        baoxiao.setBxtime(new Date());
        baoxiao.setBxstatus(0);
        baoxiaoMapper.insert(baoxiao);
    }
}
