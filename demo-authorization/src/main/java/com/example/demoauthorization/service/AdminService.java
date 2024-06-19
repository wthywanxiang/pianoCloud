package com.example.demoauthorization.service;

import com.example.demoauthorization.mapper.AdminMapper;
import com.example.democommon.entity.Admin;
import com.example.democommon.entity.AdminDetail;
import com.example.democommon.util.AdminTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class AdminService {
    @Autowired
    private AdminMapper adminMapper;
    public String userLogin(String username,String password) {
        Integer userId=adminMapper.adminLogin(username,password).getId();
        if(userId==null){
            return null;
        }
        return AdminTokenUtil.generateToken(userId,username,password);
    }
    public AdminDetail adminInfo(Integer userId){
        Admin admin= adminMapper.adminInfo(userId);
        AdminDetail adminDetail= new AdminDetail();
        if(admin==null){
            return null;
        }
        adminDetail.setAvatar(admin.getAvatar());
        adminDetail.setName(admin.getUsername());
        if(admin.getRank()==1){
            adminDetail.setRoles(Collections.singletonList("admin"));
        }else {
            adminDetail.setRoles(Collections.singletonList("editor"));
        }
        adminDetail.setIntroduction(admin.getIntroduction());
        return adminDetail;
    }
}
