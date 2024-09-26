package com.ega.smartoutlet.requestHandler;

import android.content.Context;
import android.util.Log;

import com.ega.smartoutlet.adapters.SharedPreferenceHelper;
import com.ega.smartoutlet.httpRequests.ApiClient;
import com.ega.smartoutlet.httpRequests.ApiService;
import com.ega.smartoutlet.httpRequests.RequestsHandler;
import com.ega.smartoutlet.httpRequests.ResponseHandler;
import com.ega.smartoutlet.models.DeviceModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeviceRequestHandler {

    private final SharedPreferenceHelper sharedPreferenceHelper;
    private final ApiService apiService;

    public DeviceRequestHandler(Context context) {
        sharedPreferenceHelper = new SharedPreferenceHelper(context);
        apiService = ApiClient.getRetrofitInstance().create(ApiService.class);
    }


    public void fetchUnregisteredDevices(OnDevicesFetchedListener listener) {
        String token = sharedPreferenceHelper.getAccessToken();

        Call<ResponseHandler.DevicesResponse> call = apiService.fetchUnregisteredDevices("Bearer " + token);
        call.enqueue(new Callback<ResponseHandler.DevicesResponse>() {
            @Override
            public void onResponse(Call<ResponseHandler.DevicesResponse> call, Response<ResponseHandler.DevicesResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<DeviceModel> deviceModelArrayList = new ArrayList<>();
                    List<ResponseHandler.DevicesResponse.Device> devices = response.body().getContent();

                    for (ResponseHandler.DevicesResponse.Device device : devices) {
                        deviceModelArrayList.add(new DeviceModel(
                                device.getUuid(),
                                device.getDeviceName(),
                                device.getDeviceUuid(),
                                device.isState(),
                                device.isRegistered(),
                                device.getTimer()
                        ));
                    }

                    listener.onSuccess(deviceModelArrayList);
                } else {
                    listener.onFailure(response.message());
                }
            }

            @Override
            public void onFailure(Call<ResponseHandler.DevicesResponse> call, Throwable t) {
                Log.e("FETCH_DEVICES", "onFailure: " + t.getMessage());
                listener.onFailure("Request failed: " + t.getMessage());
            }
        });
    }

    public interface OnDevicesFetchedListener {
        void onSuccess(List<DeviceModel> devices);
        void onFailure(String errorMessage);
    }


    public interface OnDeviceSavedListener {
        void onSuccess();
        void onFailure(String errorMessage);
    }


    public void saveDevice(String deviceName, String deviceUuid, OnDeviceSavedListener listener) {
        String token = sharedPreferenceHelper.getAccessToken();
        RequestsHandler.SaveDeviceRequest deviceRequest = new RequestsHandler.SaveDeviceRequest(deviceName, deviceUuid);

        Call<ResponseHandler.NewDeviceResponse> call = apiService.saveDevice("Bearer " + token, deviceRequest);
        call.enqueue(new Callback<ResponseHandler.NewDeviceResponse>() {
            @Override
            public void onResponse(Call<ResponseHandler.NewDeviceResponse> call, Response<ResponseHandler.NewDeviceResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    listener.onSuccess();


                } else {
                    listener.onFailure("Request failed");
                }
            }

            @Override
            public void onFailure(Call<ResponseHandler.NewDeviceResponse> call, Throwable t) {
                listener.onFailure("Request failed: " + t.getMessage());
            }
        });
    }



}
