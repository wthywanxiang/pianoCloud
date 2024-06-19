package com.example.demoauthorization.mapper;

import com.example.democommon.entity.Student;
import com.example.democommon.entity.Teacher;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    Integer teacherLogin(Teacher teacher);
    Integer studentLogin(Student student);
}
