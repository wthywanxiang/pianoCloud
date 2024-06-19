package com.example.demouser.service;

import com.example.demo.app.mapper.RoomMapper;
import com.example.demo.entity.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class RoomService {
    @Autowired
    RoomMapper roomMapper;
    public List<List<Room>> availableRoom(LocalDateTime beginTime, LocalDateTime endTime){
        List<Integer>floors=roomMapper.getAllFloors();
        List<List<Room>>Rooms=new ArrayList<>();
        for(Integer floor:floors){
            Rooms.add(roomMapper.getAvailableRoomByFloor(beginTime,endTime,floor));
        }
        return Rooms;
    }
}
