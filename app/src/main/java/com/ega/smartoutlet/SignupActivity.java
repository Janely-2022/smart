package com.ega.smartoutlet;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;

public class SignupActivity extends AppCompatActivity {

    private ImageView backBtn;
    private TextInputLayout fullnameCont, usernameCont, passwordCont;
    private MaterialButton loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        loginBtn = findViewById(R.id.login_cont);
        backBtn  =  findViewById(R.id.backBtn);
        fullnameCont = findViewById(R.id.fullNameInputCont);
        usernameCont =  findViewById(R.id.usernameInputCont);
        passwordCont =  findViewById(R.id.passwordInputCont);

        ValidateForms.setTextWatcher(fullnameCont,"Fullname can not be empty");
        ValidateForms.setTextWatcher(usernameCont,"Username can not be empty");
        ValidateForms.setTextWatcher(passwordCont, "Password can not be empty");




        loginBtn.setOnClickListener( v ->{

            boolean isFullNameEmpty =  ValidateForms.isInputEmpty(fullnameCont,"Fullname is required");
            boolean isUsernameEmpty =  ValidateForms.isInputEmpty(usernameCont,"Username is required");
            boolean isPasswordEmpty =  ValidateForms.isInputEmpty(passwordCont,"Password is required");

            if(!isFullNameEmpty && !isUsernameEmpty && !isPasswordEmpty){

                System.out.println("username : " + usernameCont.getEditText().getText().toString().trim());
                System.out.println("username : " + passwordCont.getEditText().getText().toString().trim());
                System.out.println("username : " + fullnameCont.getEditText().getText().toString().trim());

                navigateActivity(new LoginActivity());
            }

        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void navigateActivity(Activity activity){
        Intent intent =  new Intent(SignupActivity.this, activity.getClass());
        startActivity(intent);
    }
}