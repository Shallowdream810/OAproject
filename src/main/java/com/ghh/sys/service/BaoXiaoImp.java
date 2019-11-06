package com.ghh.sys.service;

import com.ghh.commn.Page;
import com.ghh.sys.bean.BaoXiao;
import com.ghh.sys.bean.BaoXiaoExample;
import com.ghh.sys.mapper.BaoXiaoMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class BaoXiaoImp implements BaoXiaoService {
    @Resource
    private BaoXiaoMapper baoXiaoMapper;

    //在service层做分页
    @Override
    public PageInfo<BaoXiao> findAll(Integer pageNum, Integer eid, Map<String, Object> search) {
        BaoXiaoExample example = new BaoXiaoExample();
        BaoXiaoExample.Criteria criteria = example.createCriteria();
//        按照id去查询所有的数据
        criteria.andEmpFkEqualTo(eid);
//        解析map集合
        Map<String,String> map =  Page.getMapString(search);
        //获取其中的状态值进行查询
        String status = map.get("status");
        //将条件添加进去
//        当前台第一次点击报销进入列表也面时，并未携带status，所以要进行判断，当第一次进入不添加此条件
       if (status !=null && status !=""){
           criteria.andBxstatusEqualTo(Integer.parseInt(status));
       }
       //模糊查询的字段
        String keyword = map.get("like_keyword");
       if (keyword!=null && keyword!=""){
           criteria.andBxremarkLike(keyword);
       }
//        将对应的前端关键字获取出来
        /*使用分页的步骤
        *   1、导入对应jar包
        *   2、在mybatis全局配置文件中配置插件
        *   3、在查询操作之前，开启分页，分配当前页码以及每页显示数量
        *   4、将查询出来的list包装成pageInfo对象，并给定导航层信息(展示几个页码信息)
         */
        PageHelper.startPage(pageNum,3);
        List<BaoXiao> list = baoXiaoMapper.selectByExample(example);
        PageInfo<BaoXiao> page = new PageInfo<>(list,3);
        return page;
    }
    @Override
    public void saveInfo(BaoXiao baoXiao) {
        String bxid = UUID.randomUUID().toString().replaceAll("-", "");
        baoXiao.setBxid(bxid);
        baoXiao.setBxstatus(1);
        baoXiaoMapper.insert(baoXiao);
    }
}
