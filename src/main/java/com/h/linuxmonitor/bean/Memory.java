package com.h.linuxmonitor.bean;

public class Memory {
    private double total;

    private double available;

    private double use;

    public Memory(double total, double available, double use) {
        this.total = total;
        this.available = available;
        this.use = use;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getAvailable() {
        return available;
    }

    public void setAvailable(double available) {
        this.available = available;
    }

    public double getUse() {
        return use;
    }

    public void setUse(double use) {
        this.use = use;
    }
}
