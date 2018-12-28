package com.spring.ghost.service.impl;

import com.spring.ghost.dto.DockerImage;
import com.spring.ghost.mapper.DockerImageMapper;
import com.spring.ghost.service.DockerImageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class DockerImageServiceImpl implements DockerImageService {

    @Autowired
    DockerImageMapper dockerImageMapper;

    @Override
    public boolean insert(DockerImage image) {
        System.out.println(image);
        return dockerImageMapper.insert(image);
    }
}
