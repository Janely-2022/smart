package com.ega.smartoutlet.adapters;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ega.smartoutlet.DeviceDetailsActivity;
import com.ega.smartoutlet.R;
import com.ega.smartoutlet.dialogs.InputDialogFragment;
import com.ega.smartoutlet.models.DeviceModel;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;

public class UnregisteredDevicesAdapter extends RecyclerView.Adapter<UnregisteredDevicesAdapter.ViewHolder> {

    private ArrayList<com.ega.smartoutlet.models.DeviceModel> deviceModelArrayList;
    private LayoutInflater inflater;
    private Context context;
    private FragmentManager fragmentManager;

    public UnregisteredDevicesAdapter(ArrayList<DeviceModel> deviceList, Context context, FragmentManager fragmentManager){
        this.deviceModelArrayList = deviceList;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.fragmentManager =  fragmentManager;
    }

    @NonNull
    @Override
    public UnregisteredDevicesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = inflater.inflate(R.layout.unregistered_device,parent,false);
        UnregisteredDevicesAdapter.ViewHolder holder =  new UnregisteredDevicesAdapter.ViewHolder(view);
        return holder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull UnregisteredDevicesAdapter.ViewHolder holder, int position) {
        holder.deviceId.setText(deviceModelArrayList.get(position).getDeviceUuid());
        if(deviceModelArrayList.get(position).getDeviceName() == null){
            holder.deviceName.setText("No Name");
        }else {
            holder.deviceName.setText(deviceModelArrayList.get(position).getDeviceName());
        }



        holder.itemView.setOnClickListener( v ->{
            Intent intent =  new Intent(context, DeviceDetailsActivity.class);

            intent.putExtra("deviceName",deviceModelArrayList.get(position).getDeviceName());
            intent.putExtra("deviceUuid", deviceModelArrayList.get(position).getDeviceUuid());

            String deviceUuid = deviceModelArrayList.get(position).getDeviceUuid();

            InputDialogFragment inputDialogFragment = InputDialogFragment.newInstance(deviceUuid);
            inputDialogFragment.show(fragmentManager, "InputDialogFragment");
        });
    }

    @Override
    public int getItemCount() {
        return deviceModelArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        MaterialTextView deviceName, deviceId;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            deviceName = itemView.findViewById(R.id.deviceName);
            deviceId = itemView.findViewById(R.id.deviceId);
        }
    }

}
