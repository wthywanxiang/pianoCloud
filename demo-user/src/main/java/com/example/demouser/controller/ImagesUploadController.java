package com.example.demouser.controller;

import com.example.democommon.util.ServerResponse;
import com.example.demouser.service.ImagesUploadService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

//TODO 正常应该搭建文件服务，如minio等，此处为了开发方便，直接使用springboot提供的文件映射
@RestController
@RequestMapping("/api/user")
public class ImagesUploadController  {
    @Autowired
    ImagesUploadService imagesUploadService;
    @PostMapping("/upload/images")
    public ServerResponse<String> uploadImage(
            HttpServletRequest request,
            @RequestHeader("Authorization") String token,
            MultipartFile photo
    ){
        return  ServerResponse.createByErrorCodeMessage(-1,imagesUploadService.uploadImage(request, token,photo));
    }
}