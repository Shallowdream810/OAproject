package com.ghh.pro.service;


import com.ghh.pro.bean.Attachment;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface AttachmentService {
    void saveAttaInfo(Attachment atta);

    List<Attachment> findAll();
}
