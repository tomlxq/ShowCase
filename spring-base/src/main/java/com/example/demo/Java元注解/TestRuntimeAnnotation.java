package com.example.demo.Java元注解;

/**
 * Created by tom on 2018/1/26.
 */
@ClassInfo("Test Class")
public class TestRuntimeAnnotation {

    @FieldInfo(value = {1, 2})
    public String fieldInfo = "FiledInfo";

    @FieldInfo(value = {10086})
    public int i = 100;

    @MethodInfo(name = "BlueBird", data = "Big")
    public static String getMethodInfo() {
        return TestRuntimeAnnotation.class.getSimpleName();
    }
}