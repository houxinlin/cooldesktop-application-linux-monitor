package com.h.linuxmonitor.bean;

public class Process {
    private int id;
    private String name;
    private String path;
    private int threadCount;

    private String userName;
    private long openFiles;

    public long getOpenFiles() {
        return openFiles;
    }

    public void setOpenFiles(long  openFiles) {
        this.openFiles = openFiles;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getThreadCount() {
        return threadCount;
    }

    public void setThreadCount(int threadCount) {
        this.threadCount = threadCount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
