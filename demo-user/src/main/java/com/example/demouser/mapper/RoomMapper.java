package com.example.demouser.mapper;

import com.example.demouser.entity.Room;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface RoomMapper {
    List<Room> getAvailableRoomByFloor(LocalDateTime beginTime,LocalDateTime endTime, Integer floor);

    List<Integer> getAllFloors();
}
