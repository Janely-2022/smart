package com.ega.smartoutlet;

public class DeviceModel {

    private String deviceName;
    private String usageStatus;
    private double usageCount;
    private boolean deviceState;
    private double timer;

    public DeviceModel(String deviceName, String usageStatus, double usageCount, boolean deviceState, double timer) {
        this.deviceName = deviceName;
        this.usageStatus = usageStatus;
        this.usageCount = usageCount;
        this.deviceState = deviceState;
        this.timer =  timer;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getUsageStatus() {
        return usageStatus;
    }

    public void setUsageStatus(String usageStatus) {
        this.usageStatus = usageStatus;
    }

    public double getUsageCount() {
        return usageCount;
    }

    public void setUsageCount(double usageCount) {
        this.usageCount = usageCount;
    }

    public boolean isDeviceState() {
        return deviceState;
    }

    public void setDeviceState(boolean deviceState) {
        this.deviceState = deviceState;
    }

    public double getTimer() {
        return timer;
    }

    public void setTimer(double timer) {
        this.timer = timer;
    }
}
