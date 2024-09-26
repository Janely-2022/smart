package com.ega.smartoutlet;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class MainActivity extends AppCompatActivity {

    private ConstraintLayout loginButton;
    private TextView signupBtn;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginButton =  findViewById(R.id.login_cont);
        signupBtn =  findViewById(R.id.signupBtn);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigateActivity(new LoginActivity());
            }
        });

        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigateActivity(new SignupActivity());
            }
        });

    }

    public void navigateActivity(Activity activity){
        Intent intent =  new Intent(MainActivity.this, activity.getClass());
        startActivity(intent);
    }
}