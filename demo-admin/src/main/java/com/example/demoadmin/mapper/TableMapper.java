package com.example.demoadmin.mapper;

import com.example.democommon.entity.Reserve;
import com.example.democommon.entity.Room;
import com.example.democommon.entity.Student;
import com.example.democommon.entity.Teacher;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TableMapper {
    List<Student> getStudentDetailsByFuzzyQuery(Integer number, String clazz, String name);
    void deleteStudent(Integer sno);
    void addStudent(Student student);
    List<Teacher> getTeacherDetailsByFuzzyQuery(Integer number, String clazz, String name);
    void deleteTeacher(Integer tno);
    void deleteTeacherToClazz(Integer tno);
    void addTeacher(Teacher teacher);
    void addTeacherToClazz(Integer tno, String clazz);
    List<Room> getRoomByFuzzyQuery(Integer floor, Integer name, String piece);
    void deleteRoom(Integer id);
    void addRoom(Room room);
    List<Reserve>getReserveByFuzzyQuery(Integer sno, Integer roomId, Integer status, String name);
}
