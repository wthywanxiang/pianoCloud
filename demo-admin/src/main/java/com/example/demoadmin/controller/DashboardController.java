package com.example.demoadmin.controller;

import com.example.demoadmin.service.DashboradService;
import com.example.democommon.util.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/api/admin")
public class DashboardController {
    @Autowired
    DashboradService dashboradService;
    @PostMapping("/getUserCount")
    public ServerResponse<Integer> getUserCount(){
        return ServerResponse.createBySuccess(dashboradService.getUserCount());
    }
    @PostMapping("/getReverseCount")
    public ServerResponse<Integer> getReverseCount(){
        return ServerResponse.createBySuccess(dashboradService.getReserveCount());
    }
    @PostMapping("/getStartCount")
    public ServerResponse<Integer> getStartCount(){
        return ServerResponse.createBySuccess(dashboradService.getStartCount());
    }
    @PostMapping("/getEndCount")
    public ServerResponse<Integer> getEndCount(){
        return ServerResponse.createBySuccess(dashboradService.getEndCount());
    }
}
