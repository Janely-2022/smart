package com.ega.smartoutlet.models;

import android.annotation.SuppressLint;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.google.gson.annotations.SerializedName;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;

public class SensorReadings {
    private int id;
    private String createdAt;
    @SerializedName("active")
    private boolean active;

    @SerializedName("voltage")
    private double voltage;

    @SerializedName("current")
    private double current;

    @SerializedName("power")
    private double power;

    @SerializedName("energyConsumed")
    private double energyConsumed;

    @SerializedName("deviceId")
    private Device deviceId;

    @SerializedName("deviceName")
    private String deviceName;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Date getCreatedAtDate() {
        // Define the DateTimeFormatter for the ISO 8601 format with nanoseconds
        DateTimeFormatter formatter = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSS");
        }

        try {
            // Parse the createdAt string into a LocalDateTime
            LocalDateTime localDateTime = LocalDateTime.parse(createdAt, formatter);

            // Convert LocalDateTime to Date using the system's default time zone
            return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        } catch (DateTimeParseException e) {
            // Handle the exception for invalid format
            System.err.println("Unparseable date: " + createdAt);
            return null; // or throw an exception if preferred
        }
    }// New field for timestamp


    // Getter for formatted device name
    public String getDeviceName() {
        return deviceName;
    }

    // Getters with SI units
    @SuppressLint("DefaultLocale")
    public String getFormattedVoltage() {
        return String.format("%.2f V", voltage);
    }

    @SuppressLint("DefaultLocale")
    public String getFormattedCurrent() {
        return String.format("%.2f A", current);
    }

    @SuppressLint("DefaultLocale")
    public String getFormattedPower() {
        return String.format("%.2f W", power);
    }

    @SuppressLint("DefaultLocale")
    public String getFormattedEnergyConsumed() {
        return String.format("%.2f kWh", energyConsumed);
    }

    public String getFormattedActiveStatus() {
        return active ? "ON" : "OFF";
    }

    // Standard getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public double getVoltage() {
        return voltage;
    }

    public void setVoltage(double voltage) {
        this.voltage = voltage;
    }

    public double getCurrent() {
        return current;
    }

    public void setCurrent(double current) {
        this.current = current;
    }

    public double getPower() {
        return power;
    }

    public void setPower(double power) {
        this.power = power;
    }

    public double getEnergyConsumed() {
        return energyConsumed;
    }

    public void setEnergyConsumed(double energyConsumed) {
        this.energyConsumed = energyConsumed;
    }

    public Device getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Device deviceId) {
        this.deviceId = deviceId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public static class Device {
        private int id;
        private String deviceName;
        private String deviceUuid;

        // Getters and setters
        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getDeviceName() {
            return deviceName;
        }

        public void setDeviceName(String deviceName) {
            this.deviceName = deviceName;
        }

        public String getDeviceUuid() {
            return deviceUuid;
        }

        public void setDeviceUuid(String deviceUuid) {
            this.deviceUuid = deviceUuid;
        }
    }
}
