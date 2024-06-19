package com.example.demouser.util;

import com.example.demouser.entity.Student;
import com.example.demouser.entity.UserDetail;

import java.util.Collections;
import java.util.List;

public class DetailUtil {
    public static List<List<Student>> studentDetailDeal(List<List<Student>>param){
        for(List<Student> student : param){
            for(Student s : student){
                UserDetail user = s.getDetail();
                if(user.getLengthOfTime()!=null){
                    user.setLengthOfTime(user.getLengthOfTime()/10000);
                }else{
                    user.setLengthOfTime(0L);
                }
                s.setDetail(user);
            }
            Collections.sort(
                    student,
                    (s1,s2) -> (int)(s2.getDetail().getLengthOfTime()-s1.getDetail().getLengthOfTime())
            );
        }
        return param;
    }

}