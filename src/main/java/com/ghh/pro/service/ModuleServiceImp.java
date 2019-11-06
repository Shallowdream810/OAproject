package com.ghh.pro.service;

import com.ghh.commn.Page;
import com.ghh.pro.bean.Model;
import com.ghh.pro.bean.ModelExample;
import com.ghh.pro.mapper.AnalysisMapper;
import com.ghh.pro.mapper.ModelMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class ModuleServiceImp implements ModuleService {

    @Resource
    private ModelMapper modelMapper;
    @Resource
    private AnalysisMapper analysisMapper;

    @Override
    public PageInfo<Model> findAll(Integer pageNum, Map<String, Object> search) {
        //查询需求分析

        ModelExample example = new ModelExample();
        ModelExample.Criteria criteria = example.createCriteria();
        Map<String, String> mapString = Page.getMapString(search);
        //获取第一个查询条件,
        String style = mapString.get("style");
        String keyword = mapString.get("like_keyword");
        if (style !=null && style != ""){
            if (Integer.valueOf(style) == 0){
                criteria.andPronameLike(keyword);
                ModelExample.Criteria criteria1 = example.createCriteria();
                criteria1.andModnameLike(keyword);
                example.or(criteria1);
            }else if (Integer.valueOf(style) == 1){
                criteria.andPronameLike(keyword);
            }
            else {
                criteria.andModnameLike(keyword);
            }
        }
        //最后封装到pageInfo中的
        PageHelper.startPage(pageNum,3);
        List<Model> models = modelMapper.selectByExample(example);
        for (Model m:models) {
            Integer analysisFk = m.getAnalysisFk();
            String title = analysisMapper.selectByPrimaryKey(analysisFk).getTitle();
            m.setAnalyname(title);
        }
        PageInfo<Model> modelPageInfo = new PageInfo<>(models,5);
        return modelPageInfo;
    }
    @Override
    public void insertInfo(Model model) {
        modelMapper.insert(model);
    }
}
