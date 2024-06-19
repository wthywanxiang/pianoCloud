package com.example.demoadmin.service;

import com.example.demoadmin.entity.Admin;
import com.example.demoadmin.entity.AdminDetail;
import com.example.demoadmin.mapper.AdminMapper;
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
    public Integer getRankByUserId(Integer userId) {
        return adminMapper.getRankByUserId(userId);
    }
    public List<Admin>checkBackUser(Integer userId) {
        if(getRankByUserId(userId)!=1)return null;
        return adminMapper.checkBackUser();
    }
    public Boolean deleteUser(Integer userId,Integer id) {
        if(getRankByUserId(userId)!=1)return false;
        adminMapper.deleteUser(id);
        return true;
    }
    public void addUser(Admin admin){
        adminMapper.addUser(admin);
    }
}
