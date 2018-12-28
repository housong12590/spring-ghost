package com.spring.ghost.service;

import com.spring.ghost.bean.DockerImage;

import java.util.List;

public interface DockerImageService {

    boolean insert(DockerImage image);

    DockerImage getLastImageByName(String name);

    List<DockerImage> getImageByName(String name);

    DockerImage getImageByTag(String tag);

    boolean deleteImage(int id);

    DockerImage getImageByPrimaryKey(int id);
}
