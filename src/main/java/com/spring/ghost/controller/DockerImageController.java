package com.spring.ghost.controller;

import com.spring.ghost.bean.MakerResp;
import com.spring.ghost.bean.PageInfo;
import com.spring.ghost.dto.DockerImage;
import com.spring.ghost.dto.User;
import com.spring.ghost.service.DockerImageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
public class DockerImageController {

    @Autowired
    DockerImageService dockerImageService;

    @PostMapping("push")
    public Object buildImage(@RequestBody DockerImage image) {
        if (dockerImageService.insert(image)) {
            List<User> users = new ArrayList<>();
            User user1 = new User();
            user1.setId("1");
            user1.setName("hous");

            User user2 = new User();
            user2.setId("2");
            user2.setName("czlyj");

            users.add(user1);
            users.add(user2);
            PageInfo<User> pageInfo = new PageInfo<>();
            pageInfo.setList(users);
            pageInfo.setPage(1);
            pageInfo.setPageSize(20);

            return MakerResp.success(pageInfo);
        }
        return MakerResp.failed();
    }
}
