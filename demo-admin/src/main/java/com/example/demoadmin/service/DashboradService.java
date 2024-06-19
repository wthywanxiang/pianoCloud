package com.example.demoadmin.service;

import com.example.demoadmin.mapper.DashboardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DashboradService {
    @Autowired
    DashboardMapper dashboardMapper;
    public Integer getUserCount(){
       return dashboardMapper.getStudentCount()+dashboardMapper.getTeacherCount();
    }
    public Integer getReserveCount(){
        return dashboardMapper.getReserveCount();
    }
    public Integer getStartCount(){
        return dashboardMapper.getStartCount();
    }
    public Integer getEndCount(){
        return dashboardMapper.getEndCount();
    }

}
