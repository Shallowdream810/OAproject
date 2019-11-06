package com.ghh.pro.service;

import com.ghh.pro.bean.Analysis;
import com.ghh.pro.bean.AnalysisExample;
import com.ghh.pro.mapper.AnalysisMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class AnalysisServiceImp implements AnalysisService {
    @Resource
    private AnalysisMapper analysisMapper;
    @Override
    public void saveInfo(Analysis analysis) {
        analysisMapper.insert(analysis);
    }

    @Override
    public List<Analysis> findAll() {
        AnalysisExample example  = new AnalysisExample();
        List<Analysis> list = analysisMapper.selectByExample(example);
        return list;
    }

    @Override
    public Analysis findById(Integer id) {
        return analysisMapper.selectByPrimaryKey(id);
    }
    @Override
    public void updateInfo(Analysis analysis) {
        analysis.setUpdatetime(new Date());
        analysisMapper.updateByPrimaryKeySelective(analysis);
    }

    @Override
    public List<Analysis> searchByLike(Integer cid, String keyworld, Integer orderby) {
        if (cid ==0 && (keyworld == null || keyworld == "")) {
            return analysisMapper.selectByExample(null);
        }else
            return analysisMapper.searchByLike(cid, keyworld, orderby);
    }
}
