package com.ega.smartoutlet.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ega.smartoutlet.DeviceDetailsActivity;
import com.ega.smartoutlet.DeviceModel;
import com.ega.smartoutlet.R;

import java.util.ArrayList;

public class GroupDeviceAdapter extends RecyclerView.Adapter<GroupDeviceAdapter.ViewHolder> {

    private ArrayList<DeviceModel> deviceModelArrayList;
    private LayoutInflater inflater;
    private Context context;

    public GroupDeviceAdapter(ArrayList<DeviceModel> deviceList, Context context){
        this.deviceModelArrayList = deviceList;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = inflater.inflate(R.layout.group_device,parent,false);
        GroupDeviceAdapter.ViewHolder holder =  new GroupDeviceAdapter.ViewHolder(view);
        return holder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.deviceName.setText(deviceModelArrayList.get(position).getDeviceName());


        holder.itemView.setOnClickListener( v ->{
            Intent intent =  new Intent(context, DeviceDetailsActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

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

        TextView deviceName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            deviceName = itemView.findViewById(R.id.deviceName);
        }
    }

}
