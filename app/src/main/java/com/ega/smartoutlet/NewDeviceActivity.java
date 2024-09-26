package com.ega.smartoutlet;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ega.smartoutlet.adapters.SharedPreferenceHelper;
import com.ega.smartoutlet.adapters.UnregisteredDevicesAdapter;
import com.ega.smartoutlet.models.DeviceModel;
import com.ega.smartoutlet.requestHandler.DeviceRequestHandler;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;
import java.util.List;

public class NewDeviceActivity extends AppCompatActivity {

    private CardView cardButton;
    private ImageView backbtn;
    private TextView title;

    private SharedPreferenceHelper sharedPreferenceHelper;
    private RecyclerView devicesRecycler;
    MaterialTextView devicesCount;

    private ArrayList<DeviceModel> deviceModelArrayList;
    private UnregisteredDevicesAdapter adapter;

    private DeviceRequestHandler deviceRequestHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_device);

        sharedPreferenceHelper =  new SharedPreferenceHelper(this);
        devicesCount =  findViewById(R.id.devicesCount);
        devicesRecycler =  findViewById(R.id.devicesList);
        deviceModelArrayList =  new ArrayList<>();
        deviceRequestHandler =  new DeviceRequestHandler(this);

        adapter =  new UnregisteredDevicesAdapter(deviceModelArrayList,getApplicationContext(), getSupportFragmentManager());
        devicesRecycler.setAdapter(adapter);
        devicesRecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false));

        backbtn =  findViewById(R.id.backBtn);
        title =  findViewById(R.id.screenTitle);

        title.setText("New Device");

        fectchDevices();

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void fectchDevices() {
        deviceRequestHandler.fetchUnregisteredDevices(new DeviceRequestHandler.OnDevicesFetchedListener() {
            @Override
            public void onSuccess(List<DeviceModel> devices) {
                deviceModelArrayList.clear();
                deviceModelArrayList.addAll(devices);
                adapter.notifyDataSetChanged();
                devicesCount.setText(String.valueOf(devices.size()));
            }

            @Override
            public void onFailure(String errorMessage) {
                Toast.makeText(NewDeviceActivity.this,errorMessage,Toast.LENGTH_SHORT).show();
            }
        });

    }

}