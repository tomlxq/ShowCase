package com.springmvc.service.impl;

/**
 * Created by tom on 2018/1/26.
 */

import com.springmvc.annotation.Service;
import com.springmvc.service.MyService1;

import java.util.Map;

@Service("MyService1Impl")
public class MyService1Impl implements MyService1 {
    @Override
    public int insert(Map map) {
        System.out.println("MyServiceImpl:" + "insert");
        return 0;
    }

    @Override
    public int delete(Map map) {
        System.out.println("MyServiceImpl:" + "delete");
        return 0;
    }

    @Override
    public int update(Map map) {
        System.out.println("MyServiceImpl:" + "update");
        return 0;
    }

    @Override
    public int select(Map map) {
        System.out.println("MyServiceImpl:" + "select");
        return 0;
    }
}
