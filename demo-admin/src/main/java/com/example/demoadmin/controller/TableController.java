package com.example.demoadmin.controller;

import com.example.democommon.entity.Reserve;
import com.example.democommon.entity.Room;
import com.example.democommon.entity.Student;
import com.example.democommon.entity.Teacher;
import com.example.demoadmin.service.TableService;
import com.example.democommon.util.ServerResponse;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@CrossOrigin
@RestController
public class TableController {
    @Autowired
    TableService tableService;
    @PostMapping("/api/getStudentDetailsByFuzzyQuery")
    public ServerResponse<PageInfo<Student>> getStudentDetailsByFuzzyQuery(@RequestBody Map<String,Object> queryBody){
        return ServerResponse.createBySuccess(tableService.getStudentDetailsByFuzzyQuery(queryBody));
    }
    @PostMapping("/api/addStudent")
    public ServerResponse addStudent(@RequestBody Student student){
        tableService.addStudent(student);
        return ServerResponse.createBySuccess();
    }
    @PostMapping("/api/deleteStudent")
    public ServerResponse deleteStudent(@RequestBody Map<String,Integer>params){
        tableService.deleteStudent(params);
        return ServerResponse.createBySuccess();
    }

    @PostMapping("/api/getTeacherDetailsByFuzzyQuery")
    public ServerResponse<PageInfo<Teacher>> getTeacherDetailsByFuzzyQuery(@RequestBody Map<String,Object> queryBody){
        return ServerResponse.createBySuccess(tableService.getTeacherDetailsByFuzzyQuery(queryBody));
    }
    @PostMapping("/api/addTeacher")
    public ServerResponse addTeacher(@RequestBody Teacher teacher){
        tableService.addTeacher(teacher);
        return ServerResponse.createBySuccess();
    }
    @PostMapping("/api/deleteTeacher")
    public ServerResponse deleteTeacher(@RequestBody Map<String,Integer>params){
        tableService.deleteTeacher(params);
        return ServerResponse.createBySuccess();
    }
    @PostMapping("/api/getRoomByFuzzyQuery")
    public ServerResponse<PageInfo<Room>> getRoomByFuzzyQuery(@RequestBody Map<String,Object> queryBody){
        return ServerResponse.createBySuccess(tableService.getRoomByFuzzyQuery(queryBody));
    }
    @PostMapping("/api/addRoom")
    public ServerResponse addRoom(@RequestBody Room room){
        tableService.addRoom(room);
        return ServerResponse.createBySuccess();
    }
    @PostMapping("/api/deleteRoom")
    public ServerResponse deleteRoom(@RequestBody Map<String,Integer>params){
        tableService.deleteRoom(params);
        return ServerResponse.createBySuccess();
    }
    @PostMapping("/api/getReserveByFuzzyQuery")
    public ServerResponse<PageInfo<Reserve>> getReserveByFuzzyQuery(@RequestBody Map<String,Object>params){
        return ServerResponse.createBySuccess(tableService.getReserveByFuzzyQuery(params));
    }
}
