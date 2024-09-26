package com.ega.smartoutlet.httpRequests;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface ApiService {

    @POST("/auth/login")
    Call<ResponseHandler.LoginResponse> login(@Body RequestsHandler.LoginRequest loginRequest);

    @GET("api/v1/devices/get-registered-devices")
    Call<ResponseHandler.DevicesResponse> fetchRegisteredDevices(@Header("Authorization") String token);

    @GET("api/v1/devices/get-unregistered-devices")
    Call<ResponseHandler.DevicesResponse> fetchUnregisteredDevices(@Header("Authorization") String token);

    @GET("/api/v1/sensor/all-devices-readings")
    Call<ResponseHandler.SensorReadingsResponse> getAllDeviceReadings();

    @PUT("/api/v1/devices/save-device")
    Call<ResponseHandler.NewDeviceResponse> saveDevice(@Header("Authorization") String token, @Body RequestsHandler.SaveDeviceRequest deviceRequest);

    @GET("api/v1/groups/all-groups")
    Call<ResponseHandler.DeviceGroupResponse> getAllGroups(@Header("Authorization") String token);

}
