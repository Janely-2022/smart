package com.ega.smartoutlet.httpRequests;

import android.content.Context;
import android.util.Log;

import com.ega.smartoutlet.LoginActivity;
import com.ega.smartoutlet.adapters.SharedPreferenceHelper;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {


    private static final String BASE_URL ="http://10.0.2.2:9090";
    private static Retrofit retrofit;

    public static Retrofit getRetrofitInstance(){
        if (retrofit == null){
            retrofit =  new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static Retrofit getStatisticsData(Context context){
        SharedPreferenceHelper sharedPreferenceHelper = new SharedPreferenceHelper(context);
        String token = sharedPreferenceHelper.getAccessToken();

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(chain -> {
                    Request request = chain.request().newBuilder()
                            .addHeader("Authorization", "Bearer " + token)
                            .build();
                    return chain.proceed(request);
                })
                .build();
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
    }
}
