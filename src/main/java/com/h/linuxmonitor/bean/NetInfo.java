package com.h.linuxmonitor.bean;

public class NetInfo {
    private int port;
    private int remotePort;
    private String type;

    private int  processId;
    private String state;
    private String localAddress;
    private String remoteAddress;

    public NetInfo(int port, int remotePort, String type, int  processId, String state, String localAddress, String remoteAddress) {
        this.port = port;
        this.remotePort = remotePort;
        this.type = type;
        this.processId = processId;
        this.state = state;
        this.localAddress = localAddress;
        this.remoteAddress = remoteAddress;
    }

    public int getRemotePort() {
        return remotePort;
    }

    public void setRemotePort(int remotePort) {
        this.remotePort = remotePort;
    }

    public String getLocalAddress() {
        return localAddress;
    }

    public void setLocalAddress(String localAddress) {
        this.localAddress = localAddress;
    }

    public String getRemoteAddress() {
        return remoteAddress;
    }

    public void setRemoteAddress(String remoteAddress) {
        this.remoteAddress = remoteAddress;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getProcessId() {
        return processId;
    }

    public void setProcessId(int processId) {
        this.processId = processId;
    }
}
