package com.example.demo.backstage.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DashboardMapper {
    Integer getTeacherCount();
    Integer getStudentCount();
    Integer getReserveCount();
    Integer getStartCount();
    Integer getEndCount();
}
