package com.example.demouser.service;

import com.example.demouser.util.DetailUtil;
import com.example.demouser.mapper.ReserveMapper;
import com.example.demouser.mapper.UserMapper;
import com.example.demouser.entity.Reserve;
import com.example.demouser.entity.Student;
import com.example.demouser.entity.UserDetail;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReserveService {
    @Autowired
    ReserveMapper reserveMapper;
    @Autowired
    UserMapper userMapper;
    public List<Reserve> getUnfinishedReserve(Integer sno){
        List<Reserve> reserves=  reserveMapper.getUnfinishedReserve(sno);
        for (Reserve reserve : reserves) {
            reserve.setLengthOfTime(reserve.getLengthOfTime()/10000);
        }
        return reserves;
    }
    //定期执行，每天的0时0分0秒执行，并且项目每次运行都会执行,不对前端开放
    @Scheduled(cron = "0 0 0 * * *")
    @PostConstruct
    public void updateReserve(){
        reserveMapper.updateReserve(LocalDateTime.now());
    }
    public UserDetail getUserDetailBySno(Integer sno){
        UserDetail userDetail=reserveMapper.getUserDetailBySno(sno);
        if(userDetail.getLengthOfTime()!=null){
            userDetail.setLengthOfTime(userDetail.getLengthOfTime()/10000);
        }
        else {
            userDetail.setLengthOfTime(0L);
        }
        return userDetail;
    }
    public List<List<Student>> getTopTen(){
        List<Integer> grades=userMapper.getAllGrade();
        List<List<Student>> students= new ArrayList<>();
        List<List<Student>>topTen=new ArrayList<>();
        students.add(reserveMapper.getAllGradeTopTen());
        for(Integer grade : grades){
            students.add(reserveMapper.getStudentByGrade(grade));
        }
        students= DetailUtil.studentDetailDeal(students);
        for (List<Student> student : students) {
            if(student.size()>10){
                topTen.add(student.subList(0,10));
            }else{
                topTen.add(student);
            }
        }
        return topTen;
    }

    public List<List<Student>> getStudentByTno(Integer tno) {
        List<List<Student>> students = new ArrayList<>();
        List<String>clazzs=userMapper.getClazzByTno(tno);
        for(String clazz:clazzs){
            students.add(reserveMapper.getStudentByClazz(clazz));
        }
        return DetailUtil.studentDetailDeal(students);
    }
    public List<Reserve>getHistoryBySno(Integer sno) {
        List<Reserve> reserves = reserveMapper.getHistoryBySno(sno);
        for (Reserve reserve : reserves) {
            if(reserve.getLengthOfTime()==null){
                reserve.setLengthOfTime(0L);
            }else {
                reserve.setLengthOfTime(reserve.getLengthOfTime()/10000);
            }
        }
        return reserves;
    }
    public String signIn(Reserve reserve) {
        LocalDateTime now=LocalDateTime.now();
        if(reserve.getBeginTime().compareTo(now)>0){
            return "现在还太早，不能签到哦";
        }
        if(reserve.getRealBeginTime()!=null){
            reserve.setRealEndTime(now);
            reserveMapper.setRealEndTime(reserve);
        }
        reserve.setRealBeginTime(now);
        reserveMapper.setRealBeginTime(reserve);
        return "签到成功";
    }
    public Boolean createReserve(Reserve reserve){
        synchronized(ReserveService.class){
            Integer count=reserveMapper.checkRoomAvailability(reserve);
            if(count==0){
                reserveMapper.createReserve(reserve);
                return true;
            }
            return false;
        }
    }
    public String cancelReserve(Integer number,Integer roomId ,LocalDateTime beginTime,LocalDateTime endTime){
        if(LocalDateTime.now().compareTo(beginTime)<=0){
            reserveMapper.cancelReserve(number,roomId ,beginTime,endTime);
            return "取消成功";
        }
        return "预约已经开始，请按时前往琴房练习";
    }
}
