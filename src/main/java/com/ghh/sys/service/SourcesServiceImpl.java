package com.ghh.sys.service;

import com.ghh.sys.bean.Sources;
import com.ghh.sys.bean.SourcesExample;
import com.ghh.sys.mapper.SourcesMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SourcesServiceImpl implements SourcesService {
    @Resource
    private SourcesMapper sourcesMapper;
    @Override
    //其实就一个source对象，根节点
    public List<Sources> getSourcelist(int i) {
        SourcesExample example = new SourcesExample();
        SourcesExample.Criteria criteria = example.createCriteria();
        criteria.andPidEqualTo(i);
        List<Sources> sources = sourcesMapper.selectByExample(example);
        return sources;
    }
    @Override
    public void saveInfo(Sources sources) {
        sourcesMapper.insert(sources);
    }
    @Override
    public Sources findInfo(Integer id) {
        return sourcesMapper.selectByPrimaryKey(id);
    }
    @Override
    public void updateInfo(Sources sources) {
        sourcesMapper.updateByPrimaryKeySelective(sources);
    }

}
