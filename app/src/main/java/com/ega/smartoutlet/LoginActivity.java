package com.ega.smartoutlet;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.ega.smartoutlet.adapters.SharedPreferenceHelper;
import com.ega.smartoutlet.httpRequests.ApiClient;
import com.ega.smartoutlet.httpRequests.ApiService;
import com.ega.smartoutlet.httpRequests.RequestsHandler;
import com.ega.smartoutlet.httpRequests.ResponseHandler;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private ImageView  backBtn;
    private TextView signupBtn;

    private MaterialButton loginBtn;
    private TextInputLayout usernameCont, passwordCont;
    private TextInputEditText usernameInput, passwordInput;
    private SharedPreferenceHelper sharedPreferenceHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sharedPreferenceHelper =  new SharedPreferenceHelper(this);

        backBtn =   findViewById(R.id.backBtn);
        signupBtn =  findViewById(R.id.signupBtn);
        loginBtn =  findViewById(R.id.login_cont);
        usernameCont = findViewById(R.id.usernameInputLayout);
        passwordCont = findViewById(R.id.passwordInputLayout);
        usernameInput = findViewById(R.id.usernameInput);
        passwordInput = findViewById(R.id.passwordInput);

        ValidateForms.setTextWatcher(usernameCont,"username can not be empty");
        ValidateForms.setTextWatcher(passwordCont,"Password can not be empty");


        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigateActivity(new SignupActivity());
            }
        });

        loginBtn.setOnClickListener( v -> {

            boolean isUsernameEmpty =  ValidateForms.isInputEmpty(usernameCont,"Username is required");
            boolean isPasswordEmpty  =  ValidateForms.isInputEmpty(passwordCont,"Password is required");

            if(!isUsernameEmpty && ! isPasswordEmpty){

                String username = usernameInput.getText().toString().trim();
                String password = passwordInput.getText().toString().trim();

                loginUser(username,password);
            }


        });

    }


    public void navigateActivity(Activity activity){
        Intent intent =  new Intent(LoginActivity.this, activity.getClass());
        startActivity(intent);
    }

    private void loginUser(String username, String password) {
        ApiService apiService = ApiClient.getRetrofitInstance().create(ApiService.class);

        RequestsHandler.LoginRequest loginRequest =  new RequestsHandler.LoginRequest(username,password);

        Call<ResponseHandler.LoginResponse> call = apiService.login(loginRequest);
        call.enqueue(new Callback<ResponseHandler.LoginResponse>() {
            @Override
            public void onResponse(Call<ResponseHandler.LoginResponse> call, Response<ResponseHandler.LoginResponse> response) {
                if(response.isSuccessful() && response.body() != null){
                    ResponseHandler.LoginResponse.LoginData loginData = response.body().getData();
                    String token=  loginData.getToken();
                    String fullname = loginData.getFullname();
                    String username = loginData.getUsername();

                    Toast.makeText(LoginActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    sharedPreferenceHelper.saveUserData(fullname,username,token);
                    navigateActivity(new LandingActivity());
                }
                else {
                    Toast.makeText(LoginActivity.this,"Request failed please try again",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseHandler.LoginResponse> call, Throwable t) {
                Log.e("LOGIN", "onFailure: " + t.getMessage());
                Toast.makeText(LoginActivity.this,"Request failed",Toast.LENGTH_SHORT).show();
            }
        });
    }


}