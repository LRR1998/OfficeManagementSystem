package com.ujiuye.pro.service;

import com.ujiuye.pro.bean.Attachment;

import java.util.List;

public interface AttachmentService {
    List<Attachment> getAttachments();

    void saveInfo(Attachment attachment);
}
