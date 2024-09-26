package com.ega.smartoutlet;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ega.smartoutlet.adapters.SharedPreferenceHelper;
import com.ega.smartoutlet.httpRequests.ApiClient;
import com.ega.smartoutlet.httpRequests.ApiService;
import com.ega.smartoutlet.httpRequests.ResponseHandler;
import com.ega.smartoutlet.models.GroupModel;

import java.util.ArrayList;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class GroupsFragment extends Fragment {
    private RecyclerView recyclerView;
    private ArrayList<GroupModel> groupList;
    private GroupAdapter groupAdapter;
    private SharedPreferenceHelper sharedPreferenceHelper;
    private ApiService apiService;
    private ApiClient apiClient;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_groups, container, false);
        sharedPreferenceHelper = new SharedPreferenceHelper(getContext());
        apiService = ApiClient.getRetrofitInstance().create(ApiService.class);

        recyclerView = view.findViewById(R.id.groupRecycler);
        groupList = new ArrayList<>();
        groupAdapter = new GroupAdapter(groupList, getContext());
        recyclerView.setAdapter(groupAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext() , 2));
        recyclerView.setNestedScrollingEnabled(false);

        fetchGroupsData();
        return view;
    }

//    private void createGroup(){
//        groupList.add(new GroupModel(
//                "Living room",
//                "Consuming",
//                4,
//                7.2,
//                true
//        ));
//        groupList.add(new GroupModel(
//                "Bedroom",
//                "Not consuming",
//                6,
//                0,
//                false
//        ));
//
//        groupList.add(new GroupModel(
//                "Bathroom",
//                "Not consuming",
//                4,
//                7.2,
//                true
//        ));
//        groupList.add(new GroupModel(
//                "Kitchen",
//                "Consuming",
//                9,
//                12,
//                true
//        ));
//    }

    private  void fetchGroupsData(){
        String token = sharedPreferenceHelper.getAccessToken();
        Call<ResponseHandler.DeviceGroupResponse> call = apiService.getAllGroups("Bearer " +token);

        call.enqueue(new Callback<ResponseHandler.DeviceGroupResponse>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(@NonNull Call<ResponseHandler.DeviceGroupResponse> call, @NonNull Response<ResponseHandler.DeviceGroupResponse> response) {
                if (response.isSuccessful() && response.body() != null){
                    groupList.clear();
                    groupList.addAll(response.body().getContent());
                    groupAdapter.notifyDataSetChanged();
                    Log.d("Message" , response.body().toString());
                }
                else{
                    Toast.makeText(getContext(), "Failed to load data", Toast.LENGTH_SHORT).show();
                    Log.e("Error" , "error fetching data");
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseHandler.DeviceGroupResponse> call, @NonNull Throwable t) {
                Toast.makeText(getContext(), "Error : ", Toast.LENGTH_SHORT).show();
                Log.e("Error" , Objects.requireNonNull(t.getMessage()));
            }
        });
    }
}