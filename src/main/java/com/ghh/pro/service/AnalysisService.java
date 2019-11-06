package com.ghh.pro.service;

import com.ghh.pro.bean.Analysis;

import java.util.List;

public interface AnalysisService {
    void saveInfo(Analysis analysis);

    List<Analysis> findAll();

    Analysis findById(Integer id);

    void updateInfo(Analysis analysis);

    List<Analysis> searchByLike(Integer cid, String keyworld, Integer orderby);
}
