package com.springmvc.service.impl;
import com.springmvc.annotation.Service;
import com.springmvc.service.MyService2;


import java.util.Map;
/**
 * Created by tom on 2018/1/26.
 */


@Service("MyService2Impl")
public class MyService2Impl implements MyService2 {

    @Override
    public int insert(Map map) {
        System.out.println("MyService2Impl:" + "insert");
        return 0;
    }

    @Override
    public int delete(Map map) {
        System.out.println("MyService2Impl:" + "delete");
        return 0;
    }

    @Override
    public int update(Map map) {
        System.out.println("MyService2Impl:" + "update");
        return 0;
    }

    @Override
    public int select(Map map) {
        System.out.println("MyService2Impl:" + "select");
        return 0;
    }

}