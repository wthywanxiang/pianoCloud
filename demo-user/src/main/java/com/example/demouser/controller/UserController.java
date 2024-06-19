package com.example.demouser.controller;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.democommon.util.UserTokenUtil;
import com.example.demouser.mapper.UserMapper;
import com.example.demouser.entity.Student;
import com.example.demouser.entity.Teacher;
import com.example.democommon.util.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    UserMapper userMapper;
    @PostMapping("/TeacherLogin")
    public ServerResponse<String>TeacherLogin(@RequestBody Teacher teacher){
        Integer number=userMapper.TeacherLogin(teacher);
        if(number==1){
            return ServerResponse.createBySuccess(
                    "登陆成功",
                    UserTokenUtil.generateToken(
                            teacher.getTno(),
                            teacher.getName(),
                            true)
            );
        }
        return ServerResponse.createByErrorMessage("信息不匹配");
    }

    @PostMapping("/StudentLogin")
    public ServerResponse<String> StudentLogin(@RequestBody Student student){
        Integer number=userMapper.StudentLogin(student);
        if(number==1){
            return ServerResponse.createBySuccess(
                    "登陆成功",
                    UserTokenUtil.generateToken(
                            student.getSno(),
                            student.getName(),
                            false
                            )
            );
        }
        return ServerResponse.createByErrorMessage("信息不匹配");
    }
    @PostMapping("/api/checkAvatar")
    public ServerResponse<Boolean>checkAvatar(@RequestHeader("Authorization")String token){
        DecodedJWT jwt=UserTokenUtil.analysisToken(token);
        Integer number=jwt.getClaim("number").asInt();
        Boolean isTeacher=jwt.getClaim("isTeacher").asBoolean();
        Boolean avatar;
        if(isTeacher){
            avatar=userMapper.teacherAvatar(number);
        }else {
            avatar=userMapper.studentAvatar(number);
        }
        return ServerResponse.createBySuccess(avatar);
    }
}
