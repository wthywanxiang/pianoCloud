package com.example.demoadmin.controller;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.demoadmin.feign.AuthorizationFeignService;
import com.example.democommon.entity.Admin;
import com.example.democommon.entity.AdminDetail;
import com.example.demoadmin.service.AdminService;
import com.example.democommon.util.AdminTokenUtil;
import com.example.democommon.util.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    AdminService adminService;

    @Autowired
    AuthorizationFeignService authorizationFeignService;
    @PostMapping("/login")
    public ServerResponse<String> login(@RequestBody Admin admin){
        return authorizationFeignService.login(admin);
    }
    @PostMapping("/checkBackUser")
    public ServerResponse<List<Admin>> checkBackUser(@RequestHeader("Authorization") String token){
        DecodedJWT jwt = AdminTokenUtil.analysisToken(token);
        List<Admin>list=adminService.checkBackUser(jwt.getClaim("userId").asInt());
        if(list==null){
            return ServerResponse.createByErrorMessage("用户权限不足");
        }
        return ServerResponse.createBySuccess(list);
    }
    @PostMapping("/info")
    public ServerResponse<AdminDetail> adminInfo(@RequestHeader("Authorization") String token){
        return authorizationFeignService.adminInfo(token);
    }

    @PostMapping("/addUser")
    public ServerResponse addUser(@RequestBody Admin admin){
        adminService.addUser(admin);
        return ServerResponse.createBySuccess();
    }
    @PostMapping("/deleteUser")
    public ServerResponse<Boolean> deleteUser(@RequestHeader("Authorization")String token, @RequestBody Map<String,Integer>params){
        DecodedJWT jwt = AdminTokenUtil.analysisToken(token);
        if(adminService.deleteUser(jwt.getClaim("userId").asInt(),params.get("id"))){
            return ServerResponse.createBySuccess();
        }
        return ServerResponse.createByErrorMessage("权限不足");
    }
}
