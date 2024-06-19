package com.example.demouser.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDetail {
    /**
     * lengthOfTime 总时长
     * reserveCount 总次数
     * lateOfTime 迟到次数
     * violationCount 违约次数
     * length 练习总时长
     * */
    private Long lengthOfTime;
    private Integer reserveCount;
    private Integer lateOfTime;
    private Integer violationCount;
}
