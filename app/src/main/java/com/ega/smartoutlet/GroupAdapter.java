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

import com.ega.smartoutlet.models.GroupModel;

import java.util.ArrayList;

public class GroupAdapter extends RecyclerView.Adapter<GroupAdapter.ViewHolder> {

    private ArrayList<GroupModel> groupModelArrayList;
    private Context  context;
    private LayoutInflater inflater;

    public GroupAdapter(ArrayList<GroupModel> groupList, Context context) {
        this.groupModelArrayList = groupList;
        this.context =  context;
        inflater =  LayoutInflater.from(context);
    }


    @NonNull
    @Override
    public GroupAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View  view;
        view =  inflater.inflate(R.layout.group_item, parent,false);
        GroupAdapter.ViewHolder holder =  new GroupAdapter.ViewHolder(view);
        return holder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull GroupAdapter.ViewHolder holder, int position) {
        holder.groupName.setText(groupModelArrayList.get(position).getGroupName());
        holder.deviceNo.setText(String.valueOf(groupModelArrayList.get(position).getDevices().size()));
//        holder.usageState.setText(groupModelArrayList.get(position).getUsageStatus());
        holder.usageCount.setText(groupModelArrayList.get(position).getGroupEnergyConsumed() + " kWh");
//        holder.switchState.setChecked(groupModelArrayList.get(position).isGroupState());
//
        holder.itemView.setOnClickListener(v ->{
            Intent intent =  new Intent(context, GroupDetailsActivity.class);

            intent.putExtra("groupName", groupModelArrayList.get(position).getGroupName());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return groupModelArrayList.size();
    }


    public  static class ViewHolder extends RecyclerView.ViewHolder {
        TextView groupName,deviceNo,usageState,usageCount;
        Switch switchState;

        public ViewHolder(View itemView){
            super(itemView);

            groupName =  itemView.findViewById(R.id.groupName);
            deviceNo = itemView.findViewById(R.id.itemsNo);
            usageState =  itemView.findViewById(R.id.usageStatus);
            usageCount =  itemView.findViewById(R.id.usageCount);
            switchState =  itemView.findViewById(R.id.onOffBtn);
        }
    }
}
