package com.ega.smartoutlet;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ega.smartoutlet.adapters.GroupDeviceAdapter;

import java.util.ArrayList;

public class GroupDetailsActivity extends AppCompatActivity {

    ImageView  backBtn;
    TextView title;
    RecyclerView groupDevices;
    private GroupDeviceAdapter deviceAdapter;
    private ArrayList<DeviceModel> deviceArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_details);

        backBtn = findViewById(R.id.backBtn);
        title = findViewById(R.id.screenTitle);
        groupDevices = findViewById(R.id.groupDeviceCont);


        Intent intent  = getIntent();
        deviceArrayList = new ArrayList<>();
        deviceAdapter = new GroupDeviceAdapter(deviceArrayList,getApplicationContext());
        groupDevices.setAdapter(deviceAdapter);
        groupDevices.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false));
        groupDevices.setNestedScrollingEnabled(false);

        title.setText(intent.getStringExtra("groupName"));
        backBtn.setOnClickListener(v -> {
            finish();
        });

        createDevices();
    }


    public void createDevices(){
        deviceArrayList.add(new DeviceModel(
                "Light",
                "Consuming",
                2.6,
                true,
                4
        ));
        deviceArrayList.add(new DeviceModel(
                "Heater",
                "Not consuming",
                0.0,
                false,
                2
        ));
        deviceArrayList.add(new DeviceModel(
                "Air conditioner",
                "Consuming",
                7.6,
                true,
                0.5
        ));
        deviceArrayList.add(new DeviceModel(
                "Smart Tv",
                "Consuming",
                2.1,
                false,
                8
        ));
        deviceArrayList.add(new DeviceModel(
                "Router",
                "Consuming",
                1.1,
                false,
                12
        ));
    }

}