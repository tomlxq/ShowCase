package com.example.demo.pattern.delegate;

public class ExecutorB implements IExecutor {
    @Override
    public void doing() {
        System.out.println("员工B开发需求");
    }
}
