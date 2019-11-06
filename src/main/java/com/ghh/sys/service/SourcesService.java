package com.ghh.sys.service;

import com.ghh.sys.bean.Sources;

import java.util.List;

public interface SourcesService {
    List<Sources> getSourcelist(int i);

    void saveInfo(Sources sources);

    Sources findInfo(Integer id);

    void updateInfo(Sources sources);
}
