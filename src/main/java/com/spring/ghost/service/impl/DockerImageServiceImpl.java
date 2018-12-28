package com.spring.ghost.service.impl;

import com.spring.ghost.bean.DockerImage;
import com.spring.ghost.mapper.DockerImageMapper;
import com.spring.ghost.service.DockerImageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class DockerImageServiceImpl implements DockerImageService {

    @Autowired
    DockerImageMapper dockerImageMapper;

    @Override
    public boolean insert(DockerImage image) {
        return dockerImageMapper.insert(image);
    }

    @Override
    public DockerImage getLastImageByName(String name) {
        return dockerImageMapper.getLastImageByName(name);
    }

    @Override
    public List<DockerImage> getImageByName(String name) {
        return dockerImageMapper.getImageByName(name);
    }

    @Override
    public DockerImage getImageByTag(String tag) {
        return dockerImageMapper.getImageByTag(tag);
    }

    @Override
    public boolean deleteImage(int id) {
        return dockerImageMapper.deleteImage(id);
    }

    @Override
    public DockerImage getImageByPrimaryKey(int id) {
        return dockerImageMapper.getImageByPrimaryKey(id);
    }
}
