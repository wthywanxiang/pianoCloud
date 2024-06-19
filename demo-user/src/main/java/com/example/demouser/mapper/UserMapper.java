package com.example.demouser.mapper;

import com.example.demouser.entity.Student;
import com.example.demouser.entity.Teacher;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    Integer TeacherLogin(Teacher teacher);
    Integer StudentLogin(Student student);
    List<Integer> getAllGrade();
    List<String>getClazzByTno(Integer tno);
    Boolean studentAvatar(Integer sno);
    Boolean teacherAvatar(Integer tno);
    void saveStudentImg(Integer sno);
    void saveTeacherImg(Integer tno);
}