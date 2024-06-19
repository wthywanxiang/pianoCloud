package com.example.demouser.controller;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.democommon.util.UserTokenUtil;
import com.example.demouser.feign.AuthorizationFeignService;
import com.example.demouser.mapper.UserMapper;
import com.example.democommon.entity.Student;
import com.example.democommon.entity.Teacher;
import com.example.democommon.util.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    UserMapper userMapper;
    @Autowired
    AuthorizationFeignService authorizationFeignService;
    @PostMapping("/teacherLogin")
    public ServerResponse<String> teacherLogin(@RequestBody Teacher teacher){
        return authorizationFeignService.teacherLogin(teacher);
    }

    @PostMapping("/studentLogin")
    public ServerResponse<String> studentLogin(@RequestBody Student student){
        return authorizationFeignService.studentLogin(student);
    }
    @PostMapping("/checkAvatar")
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
