package com.example.demoadmin.service;

import com.example.demoadmin.entity.Reserve;
import com.example.demoadmin.entity.Room;
import com.example.demoadmin.entity.Student;
import com.example.demoadmin.entity.Teacher;
import com.example.demoadmin.mapper.TableMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TableService {
    @Autowired
    TableMapper tableMapper;
    private String getClazz(Map<String,Object> params){
        String clazz;
        Integer grade= (Integer) params.get("grade");
        Integer cla= (Integer) params.get("clazz");
        if((grade==null&&cla==null)){
            clazz=null;
        }else if(grade==null){
            clazz=cla+"班";
        }else if(cla==null) {
            clazz=grade+"级";
        }else{
            clazz=grade+"级"+cla+"班";
        }
        return clazz;
    }
    private void startPage(Map<String,Object> params){
        Integer page= (Integer) params.get("page");
        Integer limit= (Integer) params.get("limit");
        PageHelper.startPage(page!=null?page:1,limit!=null?limit:10);
    }
    private Integer getNumber(String numberString){
        Integer number;
        if(numberString!=null&&numberString!=""){
            number=Integer.parseInt(numberString);
        }else {
            number=null;
        }
        return number;
    }
    public PageInfo<Student> getStudentDetailsByFuzzyQuery(Map<String,Object> params){
        startPage(params);
        Integer number=getNumber((String) params.get("number"));
        String clazz=getClazz(params);
        String name=(String)params.get("name");
        return new PageInfo<>(tableMapper.getStudentDetailsByFuzzyQuery(number,clazz,name));
    }
    public void addStudent(Student student){
        tableMapper.addStudent(student);
    }
    public void deleteStudent(Map<String,Integer>params) {
        Integer sno=params.get("sno");
        tableMapper.deleteStudent(sno);
    }
    public PageInfo<Teacher>getTeacherDetailsByFuzzyQuery(Map<String,Object> params){
        startPage(params);
        Integer number=getNumber((String) params.get("number"));
        String clazz=getClazz(params);
        String name=(String)params.get("name");
        return new PageInfo<>(tableMapper.getTeacherDetailsByFuzzyQuery(number, clazz, name));
    }
    public void addTeacher(Teacher teacher){
        tableMapper.addTeacher(teacher);
        List<String>clazz=teacher.getClazz();
        Integer number=teacher.getTno();
        for (String s : clazz) {
            tableMapper.addTeacherToClazz(number,s);
        }
    }
    public void deleteTeacher(Map<String,Integer>params) {
        Integer tno=params.get("tno");
        tableMapper.deleteTeacher(tno);
        tableMapper.deleteTeacherToClazz(tno);
    }
    public PageInfo<Room>getRoomByFuzzyQuery(Map<String,Object> params){
        startPage(params);
        String floorString=(String) params.get("floor");
        Integer floor;
        if(floorString!=null&&floorString!=""){
            floor= Integer.parseInt(floorString);
        }else {
            floor=null;
        }
        Integer number=getNumber((String) params.get("number"));
        String piece= (String) params.get("piece");
        return new PageInfo<>(tableMapper.getRoomByFuzzyQuery(floor,number,piece));
    }
    public void addRoom(Room room){
        tableMapper.addRoom(room);
    }
    public void deleteRoom(Map<String,Integer>params) {
        Integer id=params.get("id");
        tableMapper.deleteRoom(id);
    }
    public PageInfo<Reserve>getReserveByFuzzyQuery(Map<String,Object>params){
        startPage(params);
        Integer sno= getNumber((String) params.get("sno"));
        Integer roomId=getNumber((String) params.get("roomId"));
        Integer status=getNumber((String)params.get("status"));
        String name= (String) params.get("name");
        return new PageInfo<>(tableMapper.getReserveByFuzzyQuery(sno,roomId,status,name));
    }
}
