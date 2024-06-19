package com.example.demoadmin.mapper;

import com.example.democommon.entity.Admin;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface AdminMapper {
    Admin adminLogin(String username, String password);
    Admin adminInfo(Integer userId);
    Integer getRankByUserId(Integer userId);
    List<Admin> checkBackUser();
    void deleteUser(Integer userId);
    void addUser(Admin admin);
}
