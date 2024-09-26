package com.ega.smartoutlet.adapters;//package com.ega.smartoutlet.adapters;
//
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.ega.smartoutlet.R;
//import com.ega.smartoutlet.models.SensorReadings;
//
//import java.util.List;
//
//public class SensorReadingsAdapter extends RecyclerView.Adapter<SensorReadingsAdapter.ViewHolder> {
//    private final List<SensorReadings> sensorReadingsList;
//    private SensorReadings sensorReadings = new SensorReadings();
//
//    public SensorReadingsAdapter(List<SensorReadings> sensorReadingsList){
//        this.sensorReadingsList = sensorReadingsList;
//    }
//
//    @NonNull
//    @Override
//    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.statistics_recycle_view,parent,false);
//        return new ViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
////        sensorReadings = sensorReadingsList.get(position);
////        holder.deviceTextView.setText(sensorReadings.getDeviceName());
////        holder.powerUsageTextView.setText(sensorReadings.getFormattedPower());
//        Log.d("Adapter", "Device Name: " + sensorReadings.getDeviceId().getDeviceName()+"================");
//        Log.d("Adapter", "Power Usage: " + sensorReadings.getFormattedPower()+"================");
//
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return sensorReadingsList.size();
//    }
//
//    public class ViewHolder extends RecyclerView.ViewHolder{
//        public TextView deviceTextView;
//        public TextView powerUsageTextView;
//
//        public ViewHolder(@NonNull View itemView) {
//            super(itemView);
//
//            deviceTextView = itemView.findViewById(R.id.deviceName);
//            powerUsageTextView = itemView.findViewById(R.id.powerUsage);
//        }
//    }
//}


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ega.smartoutlet.R;
import com.ega.smartoutlet.models.SensorReadings;

import java.util.List;

public class SensorReadingsAdapter extends RecyclerView.Adapter<SensorReadingsAdapter.ViewHolder> {
    private Context context;
    private List<SensorReadings> sensorReadingsList;
    private LayoutInflater inflater;

    public SensorReadingsAdapter(Context context, List<SensorReadings> sensorReadingsList) {
        this.context = context;
        this.sensorReadingsList = sensorReadingsList;
        this.inflater = LayoutInflater.from(context);
    }

    public SensorReadingsAdapter(List<SensorReadings> topReadings) {
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.statistics_recycle_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SensorReadings reading = sensorReadingsList.get(position);
        holder.deviceTextView.setText(reading.getDeviceName());
        holder.powerUsageTextView.setText(reading.getFormattedPower());
    }

    @Override
    public int getItemCount() {
        return sensorReadingsList != null ? sensorReadingsList.size() : 0; // Prevent NullPointerException
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView deviceTextView;
        public TextView powerUsageTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            deviceTextView = itemView.findViewById(R.id.deviceName);
            powerUsageTextView = itemView.findViewById(R.id.powerUsage);
        }
    }
}
