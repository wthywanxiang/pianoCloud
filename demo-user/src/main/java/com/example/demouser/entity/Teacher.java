package com.example.demouser.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Teacher {
    private Integer tno;
    private String name;
    private List<String> clazz;
    private boolean avatar;
}
