package com.example.demo.delegate;

public class ExecutorA implements IExecutor {
    @Override
    public void doing() {
        System.out.println("员工Ａ开发需求");
    }
}
