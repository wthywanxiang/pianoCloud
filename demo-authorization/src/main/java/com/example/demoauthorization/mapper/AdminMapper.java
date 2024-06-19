package com.example.demoauthorization.mapper;

import com.example.democommon.entity.Admin;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface AdminMapper {
    Admin adminLogin(String username, String password);
    Admin adminInfo(Integer userId);
}
