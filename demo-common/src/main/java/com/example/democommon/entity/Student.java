package com.example.democommon.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Student {
    private Integer sno;
    private String name;
    private String clazz;
    private Integer grade;
    private UserDetail detail;

    private boolean avatar;
}
