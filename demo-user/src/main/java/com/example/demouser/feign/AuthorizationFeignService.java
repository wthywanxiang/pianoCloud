package com.example.demouser.feign;

import com.example.democommon.entity.Student;
import com.example.democommon.entity.Teacher;
import com.example.democommon.util.ServerResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("demo-authorization")
public interface AuthorizationFeignService {
    @PostMapping("/api/auth/teacherLogin")
    ServerResponse<String> teacherLogin(@RequestBody Teacher teacher);
    @PostMapping("/api/auth/studentLogin")
    ServerResponse<String> studentLogin(@RequestBody Student student);

    @PostMapping("/api/auth/checkTeacherExist")
    Boolean checkTeacherExist(@RequestBody Teacher teacher);

    @PostMapping("/api/auth/checkStudentExist")
    Boolean checkStudentExist(@RequestBody Student student);
}
