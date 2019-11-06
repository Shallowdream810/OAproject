package com.ghh.pro.mapper;
import java.util.List;

import com.ghh.pro.bean.Analysis;
import com.ghh.pro.bean.AnalysisExample;
import org.apache.ibatis.annotations.Param;

public interface AnalysisMapper {
    int countByExample(AnalysisExample example);

    int deleteByExample(AnalysisExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Analysis record);

    int insertSelective(Analysis record);

    List<Analysis> selectByExample(AnalysisExample example);

    Analysis selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Analysis record, @Param("example") AnalysisExample example);

    int updateByExample(@Param("record") Analysis record, @Param("example") AnalysisExample example);

    int updateByPrimaryKeySelective(Analysis record);

    int updateByPrimaryKey(Analysis record);
    List<Analysis> searchByLike(@Param("cid") Integer cid,@Param("keyworld") String keyworld,@Param("orderby") Integer orderby);
}