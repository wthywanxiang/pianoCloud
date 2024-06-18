package com.example.demochild1.Service;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface HelloService {
    String hello() throws Exception;
}
