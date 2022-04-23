package com.h.linuxmonitor.bean;

public class CpuInfo {
    private double use;

    public CpuInfo(double use) {
        this.use = use;
    }

    public double getUse() {
        return use;
    }

    public void setUse(double use) {
        this.use = use;
    }
}
