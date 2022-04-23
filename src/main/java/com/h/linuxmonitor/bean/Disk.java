package com.h.linuxmonitor.bean;

public class Disk {
    private String name;

    private long  total;

    private long use;

    private long free;

    public Disk(String name, long total, long free) {
        this.name = name;
        this.total = total;
        this.free = free;
        this.use=total-free;
    }

    public long getFree() {
        return free;
    }

    public void setFree(long free) {
        this.free = free;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }




    public long getUse() {
        return use;
    }

    public void setUse(long use) {
        this.use = use;
    }
}
