package com.example.demo.delegate;

public class Manager implements IExecutor {
    IExecutor executor = null;

    Manager(IExecutor executor) {
        this.executor = executor;
    }

    @Override
    public void doing() {
        executor.doing();
    }
}
