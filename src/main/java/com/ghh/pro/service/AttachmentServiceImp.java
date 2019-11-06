package com.ghh.pro.service;

import com.ghh.pro.bean.Attachment;
import com.ghh.pro.mapper.AttachmentMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AttachmentServiceImp implements AttachmentService {
    @Resource
    private AttachmentMapper attachmentMapper;
    @Override
    public void saveAttaInfo(Attachment atta) {
        attachmentMapper.insert(atta);
    }

    @Override
    public List<Attachment> findAll() {
       return attachmentMapper.selectByExample(null);
    }
}
