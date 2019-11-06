package com.ujiuye.usual.controller;

import com.github.pagehelper.PageInfo;
import com.ujiuye.common.ResultEntity;
import com.ujiuye.usual.bean.Notice;
import com.ujiuye.usual.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("notice")
public class NoticeControlleer {
    @Autowired
    private NoticeService noticeService;

    @RequestMapping(value = "getNoticeById",method = RequestMethod.GET)
    @ResponseBody
    public Notice getNoticeById(int nid){
        return noticeService.getNoticeById(nid);
    }
    @RequestMapping(value = "getNotice",method = RequestMethod.GET)
    @ResponseBody
    public ResultEntity getNewNotice(){
        List<Notice> list = noticeService.getNewNotice();
        return ResultEntity.success().put("list",list);
    }
    //查询所有通知
    @RequestMapping(value = "showAll",method = RequestMethod.GET)
    @ResponseBody
    public ResultEntity getNotices(String num){
        if(num==null||num=="0"){
            num="1";
        }
        PageInfo<Notice> page = noticeService.getNotices(Integer.valueOf(num));
        return ResultEntity.success().put("page",page);
    }
    //添加通知
    @RequestMapping(value = "saveInfo",method = RequestMethod.POST)
    @ResponseBody
    public ResultEntity saveInfo(Notice notice){
        noticeService.saveInfo(notice);
        return  ResultEntity.success();
    }

}
