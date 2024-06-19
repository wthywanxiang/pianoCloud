package com.example.demoauthorization.controller;

import com.example.democommon.util.UserTokenUtil;
import com.example.demoauthorization.mapper.UserMapper;
import com.example.democommon.entity.Student;
import com.example.democommon.entity.Teacher;
import com.example.democommon.util.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class UserController {
    @Autowired
    UserMapper userMapper;

    @PostMapping("/teacherLogin")
    public ServerResponse<String> teacherLogin(@RequestBody Teacher teacher) {
        Integer number = userMapper.teacherLogin(teacher);
        if (number == 1) {
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

    @PostMapping("/studentLogin")
    public ServerResponse<String> studentLogin(@RequestBody Student student) {
        Integer number = userMapper.studentLogin(student);
        if (number == 1) {
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
    @PostMapping("/checkTeacherExist")
    public Boolean checkTeacherExist(@RequestBody Teacher teacher) {
        Integer number = userMapper.teacherLogin(teacher);
        return number == 1;
    }

    @PostMapping("/checkStudentExist")
    public Boolean checkStudentExist(@RequestBody Student student) {
        Integer number = userMapper.studentLogin(student);
        return number == 1;
    }
}