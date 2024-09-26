package com.ega.smartoutlet.dialogs;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.ega.smartoutlet.NewDeviceActivity;
import com.ega.smartoutlet.R;
import com.ega.smartoutlet.adapters.SharedPreferenceHelper;
import com.ega.smartoutlet.requestHandler.DeviceRequestHandler;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class InputDialogFragment extends DialogFragment {

    private static final String ARG_DEVICE_UUID = "deviceUuid";
    private String deviceUuid;
    private MaterialButton saveBtn, cancelBtn;
    private ProgressBar progressBar; // Add ProgressBar reference
    private SharedPreferenceHelper sharedPreferenceHelper;

    private NewDeviceActivity newDeviceActivity;

    private Handler handler;

    public static InputDialogFragment newInstance(String deviceUuid) {
        InputDialogFragment fragment = new InputDialogFragment();
        Bundle args = new Bundle();
        args.putString(ARG_DEVICE_UUID, deviceUuid);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.new_device_dialog, container, false);

        sharedPreferenceHelper = new SharedPreferenceHelper(getContext());

        newDeviceActivity =  new NewDeviceActivity();

        // Retrieve the device UUID from arguments
        if (getArguments() != null) {
            deviceUuid = getArguments().getString(ARG_DEVICE_UUID);
        }

        TextInputEditText textInput = view.findViewById(R.id.text_input);
        TextInputLayout textInputLayout = view.findViewById(R.id.text_input_layout);
        saveBtn = view.findViewById(R.id.submit_button);
        cancelBtn = view.findViewById(R.id.cancel_action);
        progressBar = view.findViewById(R.id.progress_loader); // Get ProgressBar

        cancelBtn.setOnClickListener(v -> dismiss());

        saveBtn.setOnClickListener(v -> {
            String name = textInput.getText().toString().trim();
            // Log the device UUID and the text input
            Log.d("TAGED", "onCreateView: id : " + deviceUuid + "   " + name);
            saveDevice(name, deviceUuid);
        });

        textInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(final CharSequence s, final int start, final int count, final int after) {}

            @Override
            public void onTextChanged(final CharSequence s, final int start, final int before, final int count) {
                if (TextUtils.isEmpty(s.toString().trim())) {
                    textInputLayout.setError("Device name cannot be empty");
                } else {
                    textInputLayout.setError(null);
                }
            }

            @Override
            public void afterTextChanged(final Editable s) {}
        });

        return view;
    }

    private void saveDevice(String deviceName, String deviceUuid) {
        DeviceRequestHandler deviceRequestHandler =  new DeviceRequestHandler(getContext());

        progressBar.setVisibility(View.VISIBLE);
        saveBtn.setEnabled(true);

        deviceRequestHandler.saveDevice(deviceName, deviceUuid, new DeviceRequestHandler.OnDeviceSavedListener() {
            @Override
            public void onSuccess() {
                new Handler(Looper.getMainLooper()).postDelayed(() ->{
//                    newDeviceActivity.fectchDevices();
                    progressBar.setVisibility(View.GONE);
                    dismiss();
                },3000);
            }

            @Override
            public void onFailure(String errorMessage) {
                progressBar.setVisibility(View.GONE);
                saveBtn.setEnabled(true);
                Toast.makeText(getContext(),errorMessage,Toast.LENGTH_SHORT).show();
            }
        });



    }
}
