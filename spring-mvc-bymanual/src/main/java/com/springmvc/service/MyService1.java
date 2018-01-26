package com.springmvc.service;
import java.util.Map;
/**
 * Created by tom on 2018/1/26.
 */


public interface MyService1 {
    int insert(Map map);

    int delete(Map map);

    int update(Map map);

    int select(Map map);
}