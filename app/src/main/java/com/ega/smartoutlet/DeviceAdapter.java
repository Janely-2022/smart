package com.ega.smartoutlet;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DeviceAdapter extends RecyclerView.Adapter<DeviceAdapter.ViewHolder> {

    private ArrayList<DeviceModel> deviceModelArrayList;
    private LayoutInflater inflater;
    private Context context;

    public DeviceAdapter(ArrayList<DeviceModel> deviceList, Context context){
        this.deviceModelArrayList = deviceList;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = inflater.inflate(R.layout.device_item,parent,false);
        DeviceAdapter.ViewHolder holder =  new DeviceAdapter.ViewHolder(view);
        return holder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.deviceName.setText(deviceModelArrayList.get(position).getDeviceName());
        holder.usageCount.setText(String.valueOf(deviceModelArrayList.get(position).getUsageCount()));
        holder.timeCount.setText(deviceModelArrayList.get(position).getTimer() + " hrs");
        holder.deviceSwitch.setChecked(deviceModelArrayList.get(position).isDeviceState());


        holder.itemView.setOnClickListener( v ->{
            Intent intent =  new Intent(context, DeviceDetailsActivity.class);

            intent.putExtra("deviceName",deviceModelArrayList.get(position).getDeviceName());
            intent.putExtra("energyConsumed", deviceModelArrayList.get(position).getUsageCount());
            intent.putExtra("deviceState", deviceModelArrayList.get(position).isDeviceState());

            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return deviceModelArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView deviceName,usageCount, timeCount;
        Switch deviceSwitch;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            deviceName = itemView.findViewById(R.id.deviceName);
            usageCount =  itemView.findViewById(R.id.countUsage);
            deviceSwitch = itemView.findViewById(R.id.onOffBtn);
            timeCount = itemView.findViewById(R.id.timerCount);
        }
    }

}
