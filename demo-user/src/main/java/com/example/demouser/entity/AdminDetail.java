package com.example.demouser.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class AdminDetail {
    private String name;
    private String introduction;
    private String avatar;
    private List<String> roles;
}
