package com.ghh.cust.service;

import com.ghh.cust.bean.Goods;
import com.ghh.cust.mapper.GoodsMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@Service
public class GoodsServiceImp implements GoodsService {

    @Resource
    private GoodsMapper goodsMapper;

    @Override
    public List<Goods> getGoodsList() {
        PageHelper.startPage(3,4);
        List<Goods> goodsList = goodsMapper.getGoodsList();
        PageInfo<Goods> page = new PageInfo(goodsList,3);//导航层
        System.out.println("总页数："+page.getPages());//总页数
        System.out.println("总记录数："+page.getTotal());

        System.out.println("当前页："+page.getPageNum());
        System.out.println("上一页："+page.getPrePage());
        System.out.println("下一页："+page.getNextPage());
        System.out.println("是否有上一页："+page.isHasPreviousPage());
        System.out.println("是否有下一页："+page.isHasNextPage());
        System.out.println("是否为首页："+page.isIsFirstPage());
        System.out.println("是否为末页："+page.isIsLastPage());
        System.out.println("PageSize："+page.getPageSize());
        List<Goods> list = page.getList();//获得所有数据
        for (Goods goods : list) {
            System.out.println(goods);
        }
        //获取pageInfo中的导航页信息，
        int[] nums = page.getNavigatepageNums();
        System.out.println(Arrays.toString(nums));
        return goodsList;
    }
}
