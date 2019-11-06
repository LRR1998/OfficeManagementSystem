package com.ujiuye.usual.service;

import com.github.pagehelper.PageInfo;
import com.ujiuye.usual.bean.Baoxiao;

public interface BaoxiaoService {
    PageInfo<Baoxiao> getMyBaoxiao(Integer eid, int pageNum);

    void saveInfo(Baoxiao baoxiao);
}
