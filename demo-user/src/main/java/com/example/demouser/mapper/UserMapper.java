package com.example.demouser.mapper;


import com.example.democommon.entity.Student;
import com.example.democommon.entity.Teacher;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    List<Integer> getAllGrade();
    List<String>getClazzByTno(Integer tno);
    Boolean studentAvatar(Integer sno);
    Boolean teacherAvatar(Integer tno);
    void saveStudentImg(Integer sno);
    void saveTeacherImg(Integer tno);
}
