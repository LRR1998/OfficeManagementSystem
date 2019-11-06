package com.ujiuye.usual.service;

import com.ujiuye.usual.bean.Forumpost;
import com.ujiuye.usual.mapper.ForumpostMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ForumServiceImpl implements ForumService{
    @Autowired
    private ForumpostMapper forumpostMapper;

    @Override
    public void saveInfo(Forumpost forumpost) {
        forumpostMapper.insert(forumpost);
    }
}
