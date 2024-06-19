package com.example.democommon.util;

import com.github.pagehelper.PageHelper;

import java.util.Map;

public class StartPageHelper {
    public static void startPage(Map<String,Object> params){
        Integer page= (Integer) params.get("page");
        Integer limit= (Integer) params.get("limit");

        PageHelper.startPage(page != null ? page : 1, limit != null ? limit : 10);
    }
}