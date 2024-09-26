package com.ega.smartoutlet.adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class SharedPreferenceHelper {

    private static final String PREF_NAME ="myPrefs";

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public SharedPreferenceHelper(){

    }
    public SharedPreferenceHelper(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void saveUserData(String fullname, String username, String token){
       if (editor != null){
           editor.putString("fullname",fullname);
           editor.putString("username", username);
           editor.putString("token", token);
           editor.apply();
       }
       else {
           Log.e("saving data", "saveUserData: Editor is null" );
       }
    }

    public String getAccessToken() {
        return sharedPreferences.getString("token","");
    }

    public String getFullname(){
        return sharedPreferences.getString("fullname","");
    }

    public String getUsername() {
        return sharedPreferences.getString("username","");
    }


}
