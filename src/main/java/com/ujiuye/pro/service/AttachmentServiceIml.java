package com.ujiuye.pro.service;

import com.ujiuye.pro.bean.Attachment;
import com.ujiuye.pro.bean.AttachmentExample;
import com.ujiuye.pro.bean.Project;
import com.ujiuye.pro.mapper.AttachmentMapper;
import com.ujiuye.pro.mapper.ProjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttachmentServiceIml implements AttachmentService{

    @Autowired
    private AttachmentMapper attachmentMapper;
    @Autowired
    private ProjectMapper projectMapper;

    @Override
    public List<Attachment> getAttachments() {
        AttachmentExample example = new AttachmentExample();
        List<Attachment> attachments = attachmentMapper.selectByExample(example);
        for (Attachment attachment:attachments) {
            Integer pid = attachment.getProFk();
            Project project = projectMapper.selectByPrimaryKey(pid);
            attachment.setProname(project.getPname());
        }
        return attachments;
    }

    @Override
    public void saveInfo(Attachment attachment) {
        attachmentMapper.insert(attachment);
    }
}
