package com.example.demoadmin.controller;

import com.example.demoadmin.service.DashboradService;
import com.example.democommon.util.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
@CrossOrigin
@RestController
public class DashboardController {
    @Autowired
    DashboradService dashboradService;
    @PostMapping("/api/admin/getUserCount")
    public ServerResponse<Integer> getUserCount(){
        return ServerResponse.createBySuccess(dashboradService.getUserCount());
    }
    @PostMapping("/api/admin/getReverseCount")
    public ServerResponse<Integer> getReverseCount(){
        return ServerResponse.createBySuccess(dashboradService.getReserveCount());
    }
    @PostMapping("/api/admin/getStartCount")
    public ServerResponse<Integer> getStartCount(){
        return ServerResponse.createBySuccess(dashboradService.getStartCount());
    }
    @PostMapping("/api/admin/getEndCount")
    public ServerResponse<Integer> getEndCount(){
        return ServerResponse.createBySuccess(dashboradService.getEndCount());
    }
}
