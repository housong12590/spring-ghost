package com.spring.ghost.controller;

import com.spring.ghost.bean.DockerImage;
import com.spring.ghost.dto.MakerResp;
import com.spring.ghost.service.DockerImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
public class DockerImageController {

    @Autowired
    DockerImageService dockerImageService;

    @PostMapping("push")
    public Object imagePush(@Valid @RequestBody DockerImage image, BindingResult result) {
        if (dockerImageService.insert(image)) {
            return MakerResp.success();
        }
        return MakerResp.failed();
    }

    @GetMapping("/image/{name}")
    public Object getImage(@PathVariable("name") String name) {
        List<DockerImage> images = dockerImageService.getImageByName(name);
        return MakerResp.success(images);
    }

}
