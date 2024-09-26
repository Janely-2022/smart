package com.ega.smartoutlet.models;

import java.util.List;

public  class GroupModel{

    private String groupName;
    private String uuid;
    private int id;
    private double groupEnergyConsumed;
    private List<DeviceModel> devices;

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getGroupEnergyConsumed() {
        return groupEnergyConsumed;
    }

    public void setGroupEnergyConsumed(double groupEnergyConsumed) {
        this.groupEnergyConsumed = groupEnergyConsumed;
    }

    public List<DeviceModel> getDevices() {
        return devices;
    }

    public void setDevices(List<DeviceModel> devices) {
        this.devices = devices;
    }
}