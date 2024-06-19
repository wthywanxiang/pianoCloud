package com.example.demouser.service;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.democommon.util.UserTokenUtil;
import com.example.demouser.mapper.UserMapper;
import com.example.demouser.entity.Student;
import com.example.demouser.entity.Teacher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
@Service
public class ImagesUploadService {
    @Autowired
    UserMapper userMapper;
    public String uploadImage(HttpServletRequest request, String token, MultipartFile photo){
        DecodedJWT jwt= UserTokenUtil.analysisToken(token);
        Integer number=jwt.getClaim("number").asInt();
        Integer count;
        Boolean isTeacher=jwt.getClaim("isTeacher").asBoolean();
        if(isTeacher){
            Teacher teacher=new Teacher();
            teacher.setName(jwt.getClaim("name").asString());
            teacher.setTno(number);
            count=userMapper.TeacherLogin(teacher);
        }else {
            Student student=new Student();
            student.setSno(number);
            student.setName(jwt.getClaim("name").asString());
            count=userMapper.StudentLogin(student);
        }
        if(count!=1){
            return "上传失败，不存在该用户";
        }
        String path=request.getServletContext().getRealPath("/upload/images");
        return saveImage(number,path,photo,isTeacher);
    }
    private String saveImage(Integer number, String path,MultipartFile photo,Boolean isTeacher){
        File dir=new File(path);
        if(!dir.exists()){
            dir.mkdirs();
        }
        File file=new File(path+File.separator+number+".jpg");
        System.out.println("path+number+\".jpg\" = " + path+File.separator+number+".jpg");
        try {
            photo.transferTo(file);
        } catch (IOException e) {
            return "上传失败";
        }
        if(isTeacher){
            userMapper.saveTeacherImg(number);
        }else {
            userMapper.saveStudentImg(number);
        }
        return "上传成功";
    }
}
