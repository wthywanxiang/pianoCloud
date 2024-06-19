package com.example.demouser.controller;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.democommon.util.UserTokenUtil;
import com.example.demouser.util.LocalDateTimeUtil;
import com.example.demouser.mapper.ReserveMapper;
import com.example.demouser.service.ReserveService;
import com.example.democommon.entity.Reserve;
import com.example.democommon.entity.Student;
import com.example.democommon.entity.UserDetail;
import com.example.democommon.util.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class ReserveController {
    @Autowired
    ReserveMapper reserveMapper;
    @Autowired
    ReserveService reserveService;

    @PostMapping("/Reserve/CreateReserve")
    public ServerResponse<Boolean> CreateReserve(
            @RequestHeader("Authorization") String token,
            @RequestBody Map<String, Object> params
    ) {
        DecodedJWT jwt = UserTokenUtil.analysisToken(token);
        Reserve reserve = new Reserve();
        reserve.setSno(jwt.getClaim("number").asInt());
        reserve.setBeginTime(LocalDateTimeUtil.transformLocalDateTime((Long) params.get("beginTime")));
        reserve.setEndTime(LocalDateTimeUtil.transformLocalDateTime((Long) params.get("endTime")));
        reserve.setRoomId((Integer) params.get("roomId"));
        if (reserve.getSno() == null
                || reserve.getBeginTime() == null
                || reserve.getEndTime() == null
                || reserve.getRoomId() == null
        ) {
            System.out.println("reserve = " + reserve);
            return ServerResponse.createByErrorMessage("参数不足");
        }
        if (reserveService.createReserve(reserve)) {
            return ServerResponse.createBySuccess();
        }
        return ServerResponse.createByErrorMessage("哎呀，手速慢了，已经房间已经被抢走了");
    }

    @PostMapping("/api/Reserve/UnfinishedReserve")
    public ServerResponse<List<Reserve>> UnfinishedReserve(@RequestHeader("Authorization") String token) {
        DecodedJWT jwt = UserTokenUtil.analysisToken(token);
        Integer sno = jwt.getClaim("number").asInt();
        if (sno == null) {
            return ServerResponse.createByErrorMessage("未获取学号");
        }
        return ServerResponse.createBySuccess(reserveService.getUnfinishedReserve(sno));
    }

    @PostMapping("/api/Reserve/CancelReserve")
    public ServerResponse<Boolean> CancelReserve(
            @RequestHeader("Authorization") String token,
            @RequestBody Map<String, Object> param
    ) {
        DecodedJWT jwt = UserTokenUtil.analysisToken(token);
        Integer number = jwt.getClaim("number").asInt();
        Integer roomId = (Integer) param.get("roomId");
        LocalDateTime beginTime = LocalDateTimeUtil.transformLocalDateTime((Long) param.get("beginTime"));
        LocalDateTime endTime = LocalDateTimeUtil.transformLocalDateTime((Long) param.get("endTime"));
        if (number == null || roomId == null || beginTime == null || endTime == null) {
            return ServerResponse.createByErrorMessage("参数不足");
        }
        return ServerResponse.createByOtherMessage(reserveService.cancelReserve(number, roomId, beginTime, endTime));
    }

    @PostMapping("/api/MP/GetUserDetail")
    public ServerResponse<UserDetail> GetUserDetail(@RequestHeader("Authorization") String token) {
        DecodedJWT jwt = UserTokenUtil.analysisToken(token);
        if (jwt.getClaim("number").asInt() == null) {
            return ServerResponse.createByErrorMessage("用户不存在");
        }
        return ServerResponse.createBySuccess(reserveService.getUserDetailBySno(jwt.getClaim("number").asInt()));
    }

    @PostMapping("/api/Reserve/TopTen")
    public ServerResponse<List<List<Student>>> TopTen() {
        return ServerResponse.createBySuccess(reserveService.getTopTen());
    }

    @PostMapping("/api/Reserve/HistoryRecord")
    public ServerResponse<List<Reserve>> HistoryRecord(@RequestHeader("Authorization") String token) {
        DecodedJWT jwt = UserTokenUtil.analysisToken(token);
        if (
                jwt.getClaim("isTeacher").asBoolean() || jwt.getClaim("number").asInt() == null
        ) {
            return ServerResponse.createByErrorMessage("学生不存在");
        }
        return ServerResponse.createBySuccess(reserveService.getHistoryBySno(jwt.getClaim("number").asInt()));
    }

    @PostMapping("/api/Reserve/ViewStudents")
    public ServerResponse<List<List<Student>>> ViewStudents(@RequestHeader("Authorization") String token) {
        DecodedJWT jwt = UserTokenUtil.analysisToken(token);
        if (
                !jwt.getClaim("isTeacher").asBoolean() || jwt.getClaim("number").asInt() == null
        ) {
            return ServerResponse.createByErrorMessage("老师不存在");
        }
        return ServerResponse.createBySuccess(reserveService.getStudentByTno(jwt.getClaim("number").asInt()));
    }

    @PostMapping("/api/Reserve/signIn")
    public ServerResponse signIn(@RequestBody Reserve reserve) {
        return ServerResponse.createByErrorCodeMessage(-1, reserveService.signIn(reserve));
    }

}
