package com.ghh.usual.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.ghh.usual.bean.Notice;
import com.ghh.usual.bean.NoticeExample;
import com.ghh.usual.mapper.NoticeMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class NoticeServiceImp implements NoticeService {

    @Resource
    private NoticeMapper noticeMapper;

    @Resource
    private JedisPool jedisPool;
    @Override
    public int saveInfo(Notice notice) {
        notice.setNdate(new Date());
        return noticeMapper.insert(notice);
    }

    @Override
    public PageInfo<Notice> getNoticeList(Integer pageNum) {
        PageHelper.startPage(pageNum,3);
        List<Notice> list = noticeMapper.selectByExample(null);
        PageInfo<Notice> lists = new PageInfo(list,3);
        return lists;
    }

    @Override
    public List<Notice> getNotices() {
        Jedis resource = jedisPool.getResource();
        String notice3 = resource.get("notice3");
        if (notice3 == null || notice3.length()==0){
            NoticeExample example = new NoticeExample();
            example.setOrderByClause("ndate desc limit 3");
            Notice notice = JSON.parseObject(notice3,Notice.class);
            List<Notice> list = noticeMapper.selectByExample(example);
            String s = JSON.toJSONString(list);
            resource.set("notice3",s);
            return list;
        }else {
            List<Notice> list = JSON.parseArray(notice3, Notice.class);
            return list;
        }
    }
    @Override
    public Notice noticedetail(Integer nid) {
        return noticeMapper.selectByPrimaryKey(nid);
    }
}
