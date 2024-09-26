package com.ega.smartoutlet;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;

import java.util.Calendar;
import java.util.Objects;

public class DeviceDetailsActivity extends AppCompatActivity {

    ImageView backBtn;
    TextView title,usageCount;
    MaterialTextView deviceName,deviceId,status, usage;
    MaterialButton startTime,endTime;
    Switch  onOffBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_details);

        backBtn =  findViewById(R.id.backBtn);
        title = findViewById(R.id.screenTitle);
        title.setText("Device Details");
        deviceName = findViewById(R.id.deviceName);
        deviceId = findViewById(R.id.deviceid);
        status = findViewById(R.id.deviceStatus);
        usageCount = findViewById(R.id.countUsages);
        startTime = findViewById(R.id.startTime);
        endTime = findViewById(R.id.endTime);
        onOffBtn =  findViewById(R.id.onOffBtn);

        Intent intent =  getIntent();

        deviceName.setText(intent.getStringExtra("deviceName"));
        usageCount.setText(String.valueOf(intent.getDoubleExtra("energyConsumed",200)));
        if (Objects.equals(intent.getBooleanExtra("deviceState",false), true)){
            onOffBtn.setChecked(true);
            status.setText("ON");
        }
        else {
            onOffBtn.setChecked(false);
            status.setText("OFF");
        }

        backBtn.setOnClickListener(v -> {
            finish();
        });

        startTime.setOnClickListener(v ->{
            openTimePicker(startTime);
        });

        endTime.setOnClickListener( v-> {
            openTimePicker(endTime);
        });

    }

    private void openTimePicker(MaterialButton buttonName){
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute =  calendar.get(Calendar.MINUTE);

        TimePickerDialog timePicker = new TimePickerDialog(this,
                (TimePicker view, int selectedHour, int selectedMin) ->{
            String formateedTime  = String.format("%02d:%02d", selectedHour,selectedMin);
            buttonName.setText(formateedTime);
                }, hour,minute,true);
        timePicker.show();
    }
}