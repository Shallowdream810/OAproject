package com.ghh.usual.service;

import com.ghh.usual.bean.Notice;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface NoticeService {
    int saveInfo(Notice notice);

    PageInfo<Notice> getNoticeList(Integer pageNum);

    List<Notice> getNotices();

    Notice noticedetail(Integer nid);
}
