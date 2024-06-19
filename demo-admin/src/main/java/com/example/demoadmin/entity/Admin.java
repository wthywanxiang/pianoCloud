package com.example.demoadmin.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Admin {
    private Integer id;
    private String username;
    private String password;
    private Integer rank;
    private String introduction;
    private String avatar;
}
