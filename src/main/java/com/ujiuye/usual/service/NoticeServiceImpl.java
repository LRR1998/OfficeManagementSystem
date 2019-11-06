package com.ujiuye.usual.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ujiuye.usual.bean.Notice;
import com.ujiuye.usual.bean.NoticeExample;
import com.ujiuye.usual.mapper.NoticeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class NoticeServiceImpl implements NoticeService {
    @Autowired
    private NoticeMapper noticeMapper;

    @Override
    public void saveInfo(Notice notice) {
        notice.setNdate(new Date());
        noticeMapper.insert(notice);
    }

    @Override
    public PageInfo<Notice> getNotices(int num) {
        PageHelper.startPage(num,3);
        NoticeExample example = new NoticeExample();
        List<Notice> notices = noticeMapper.selectByExample(example);
        PageInfo<Notice> page = new PageInfo<>(notices,3);
        return page;
    }

    @Override
    public List<Notice> getNewNotice() {
        NoticeExample example = new NoticeExample();
        example.setOrderByClause("ndate desc limit 3");
        List<Notice> notices = noticeMapper.selectByExample(example);
        return notices;
    }

    @Override
    public Notice getNoticeById(int nid) {
        return noticeMapper.selectByPrimaryKey(nid);
    }
}
