package com.ujiuye.usual.service;

import com.github.pagehelper.PageInfo;
import com.ujiuye.usual.bean.Notice;

import java.util.List;

public interface NoticeService {
    void saveInfo(Notice notice);

    PageInfo<Notice> getNotices(int num);

    List<Notice> getNewNotice();

    Notice getNoticeById(int nid);
}
