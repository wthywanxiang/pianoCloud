package com.example.demouser.controller;

import com.example.democommon.util.ServerResponse;
import com.example.demouser.entity.Reserve;
import com.example.demouser.entity.Room;
import com.example.demouser.service.RoomService;
import com.example.demouser.util.LocalDateTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
public class RoomController {

    @Autowired
    RoomService roomService;
    @PostMapping("/api/Reserve/AvailableRoom")
    public ServerResponse<List<List<Room>>> AvailableRoom(@RequestBody Map<String,Long>Param){
        Reserve reserve = new Reserve();
        Long time = Param.get("beginTime");
        LocalDateTime beginTime= LocalDateTimeUtil.transformLocalDateTime(time);
        time = Param.get("endTime");
        LocalDateTime endTime=LocalDateTimeUtil.transformLocalDateTime(time);
        return ServerResponse.createBySuccess(roomService.availableRoom(beginTime,endTime));
    }
}
