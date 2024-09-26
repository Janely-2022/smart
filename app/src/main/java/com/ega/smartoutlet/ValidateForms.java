package com.ega.smartoutlet;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;

import com.google.android.material.textfield.TextInputLayout;

public class ValidateForms {

    public static void setTextWatcher(TextInputLayout textInputLayout, String errorMessage) {
        textInputLayout.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(final CharSequence s, final int start, final int count, final int after) {
                // No action needed before text change
            }

            @Override
            public void onTextChanged(final CharSequence s, final int start, final int before, final int count) {
                if (TextUtils.isEmpty(s.toString().trim())) {
                    textInputLayout.setError(errorMessage);
                } else {
                    textInputLayout.setError(null);
                }
            }

            @Override
            public void afterTextChanged(final Editable s) {
                // No action needed after text change
            }
        });
    }


    public static boolean isInputEmpty(TextInputLayout textInputLayout, String errorMessage) {
        String inputText = textInputLayout.getEditText().getText().toString().trim();
        if (TextUtils.isEmpty(inputText)) {
            textInputLayout.setError(errorMessage);
            return true; // Input is empty
        } else {
            textInputLayout.setError(null);
            return false; // Input is not empty
        }
    }


}
