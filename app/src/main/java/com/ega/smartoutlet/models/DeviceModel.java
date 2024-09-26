package com.ega.smartoutlet.models;

import java.util.List;

public class DeviceModel {
    private int id;
    private String uuid;

    String deviceName;
    private String deviceUuid;
    private boolean state;
    private boolean registered;
    private double timer;
    private String startTime ,endTime , groupName , groupUuid;
    private List<Object> sensorReadings;

    // Constructor
    public DeviceModel(String uuid, String deviceName, String deviceUuid, boolean state, boolean registered, double timer) {
        this.uuid = uuid;
        this.deviceName = deviceName;
        this.deviceUuid = deviceUuid;
        this.state = state;
        this.registered = registered;
        this.timer = timer;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getDeviceUuid() {
        return deviceUuid;
    }

    public void setDeviceUuid(String deviceUuid) {
        this.deviceUuid = deviceUuid;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public boolean isRegistered() {
        return registered;
    }

    public void setRegistered(boolean registered) {
        this.registered = registered;
    }

    public double getTimer() {
        return timer;
    }

    public void setTimer(double timer) {
        this.timer = timer;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupUuid() {
        return groupUuid;
    }

    public void setGroupUuid(String groupUuid) {
        this.groupUuid = groupUuid;
    }

    public List<Object> getSensorReadings() {
        return sensorReadings;
    }

    public void setSensorReadings(List<Object> sensorReadings) {
        this.sensorReadings = sensorReadings;
    }
}
