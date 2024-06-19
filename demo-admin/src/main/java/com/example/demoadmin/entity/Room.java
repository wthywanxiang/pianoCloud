package com.example.demoadmin.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class Room {
    private Integer id;
    private String piece;
    private Integer floor;
    private Integer name;
}
