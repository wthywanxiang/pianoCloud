package com.example.demoauthorization.controller;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.democommon.entity.Admin;
import com.example.democommon.entity.AdminDetail;
import com.example.demoauthorization.service.AdminService;
import com.example.democommon.util.AdminTokenUtil;
import com.example.democommon.util.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AdminController {
    @Autowired
    AdminService adminService;
    @PostMapping("/admin/login")
    public ServerResponse<String> login(@RequestBody Admin admin){
        String token= adminService.userLogin(admin.getUsername(), admin.getPassword());
        if(token==null){
            return ServerResponse.createByErrorMessage("用户不存在");
        }
        return ServerResponse.createBySuccess(token);
    }
    @PostMapping("/admin/info")
    public ServerResponse<AdminDetail> adminInfo(String token){
        DecodedJWT jwt = AdminTokenUtil.analysisToken(token);
        AdminDetail adminDetail = adminService.adminInfo(jwt.getClaim("userId").asInt());
        if(adminDetail==null){
            return ServerResponse.createByErrorMessage("查无此人");
        }
        return ServerResponse.createBySuccess(adminDetail);
    }
}
