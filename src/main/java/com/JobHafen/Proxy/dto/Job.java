package com.JobHafen.Proxy.dto;

public class Job {
    private String name;
    private boolean applied;

    public Job(String name, boolean applied) {
        this.name = name;
        this.applied = applied;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
