package com.example.demoadmin.feign;

import com.example.democommon.entity.Admin;
import com.example.democommon.entity.AdminDetail;
import com.example.democommon.entity.Student;
import com.example.democommon.entity.Teacher;
import com.example.democommon.util.ServerResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("demo-authorization")
public interface AuthorizationFeignService {
    @PostMapping("/api/auth/admin/login")
    ServerResponse<String> login(@RequestBody Admin admin);
    @PostMapping("/api/auth/admin/info")
    ServerResponse<AdminDetail> adminInfo(@RequestParam("token") String token);
}
