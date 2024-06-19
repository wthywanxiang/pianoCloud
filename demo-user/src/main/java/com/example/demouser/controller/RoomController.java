package com.example.demouser.controller;

import com.example.democommon.util.ServerResponse;
import com.example.democommon.entity.Reserve;
import com.example.democommon.entity.Room;
import com.example.demouser.service.RoomService;
import com.example.demouser.util.LocalDateTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class RoomController {

    @Autowired
    RoomService roomService;
    @PostMapping("Reserve/AvailableRoom")
    public ServerResponse<List<List<Room>>> AvailableRoom(@RequestBody Map<String,Long>Param){
        Reserve reserve = new Reserve();
        Long time = Param.get("beginTime");
        LocalDateTime beginTime= LocalDateTimeUtil.transformLocalDateTime(time);
        time = Param.get("endTime");
        LocalDateTime endTime=LocalDateTimeUtil.transformLocalDateTime(time);
        return ServerResponse.createBySuccess(roomService.availableRoom(beginTime,endTime));
    }
}
