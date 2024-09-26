package com.ega.smartoutlet;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import java.util.ArrayList;

public class DevicesFragment extends Fragment {

    private DeviceAdapter deviceAdapter;
    private ArrayList<DeviceModel> deviceModelArrayList;
    private RecyclerView recyclerView;
    private ExtendedFloatingActionButton floatingActionButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view;
        view = inflater.inflate(R.layout.fragment_devices, container, false);

        deviceModelArrayList =  new ArrayList<>();
        floatingActionButton = view.findViewById(R.id.fab_add_node);
        recyclerView = view.findViewById(R.id.deviceRecycler);
        deviceAdapter =  new DeviceAdapter(deviceModelArrayList, getContext());
        recyclerView.setAdapter(deviceAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        recyclerView.setNestedScrollingEnabled(false);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigateActivity(new NewDeviceActivity());
            }
        });

        createDevices();

        return view;
    }


    public void navigateActivity(Activity activity){
        Intent intent =  new Intent(getContext(), activity.getClass());
        startActivity(intent);
    }

    public void createDevices(){
        deviceModelArrayList.add(new DeviceModel(
                "Light",
                "Consuming",
                2.6,
                true,
                4
        ));
        deviceModelArrayList.add(new DeviceModel(
                "Heater",
                "Not consuming",
                0.0,
                false,
                2
        ));
        deviceModelArrayList.add(new DeviceModel(
                "Air conditioner",
                "Consuming",
                7.6,
                true,
                0.5
        ));
        deviceModelArrayList.add(new DeviceModel(
                "Smart Tv",
                "Consuming",
                2.1,
                false,
                8
        ));
        deviceModelArrayList.add(new DeviceModel(
                "Router",
                "Consuming",
                1.1,
                false,
                12
        ));
    }
}