package com.example.demouser.mapper;

import com.example.democommon.entity.Reserve;
import com.example.democommon.entity.Student;
import com.example.democommon.entity.UserDetail;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDateTime;
import java.util.List;
@Mapper
public interface ReserveMapper {
    List<Reserve> getUnfinishedReserve(Integer sno);
    void cancelReserve(Integer number, Integer roomId , LocalDateTime beginTime, LocalDateTime endTime);
    void createReserve(Reserve reserve);
    Integer getNameByRoomId(Integer roomId);
    UserDetail getUserDetailBySno(Integer sno);
    void updateReserve(LocalDateTime localDateTime);
    List<Student> getAllGradeTopTen();
    List<Student> getStudentByGrade(Integer grade);
    List<Student>getStudentByClazz(String clazz);
    List<Reserve> getHistoryBySno(Integer sno);
    void setRealBeginTime(Reserve reserve);
    void setRealEndTime(Reserve reserve);
    Integer checkRoomAvailability(Reserve reserve);
}
