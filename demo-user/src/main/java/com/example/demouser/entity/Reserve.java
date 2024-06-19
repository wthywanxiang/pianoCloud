package com.example.demouser.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class Reserve {
    private Integer id;
    private Integer sno;
    private LocalDateTime beginTime;
    private LocalDateTime realBeginTime;
    private LocalDateTime endTime;
    private LocalDateTime realEndTime;
    private Integer status;
    private Integer roomId;
    //房间名
    private String name;
    private Long lengthOfTime;

}
