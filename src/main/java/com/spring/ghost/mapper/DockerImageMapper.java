package com.spring.ghost.mapper;

import com.spring.ghost.bean.DockerImage;

import java.util.List;

public interface DockerImageMapper {

    boolean insert(DockerImage image);

    DockerImage getLastImageByName(String name);

    List<DockerImage> getImageByName(String name);

    DockerImage getImageByTag(String tag);

    boolean deleteImage(Integer id);

    DockerImage getImageByPrimaryKey(Integer id);

}
