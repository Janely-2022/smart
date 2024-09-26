package com.ega.smartoutlet;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class LandingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

        BottomNavigationView bottomNavigationView =  findViewById(R.id.bottomNav);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentCont, new HomeFragment()).commit();

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                Fragment selectedFragment = null;

                if (item.getItemId() == R.id.nav_home) {
                    selectedFragment = new HomeFragment();
                }
                else if(item.getItemId() == R.id.nav_stats){
                    selectedFragment =  new GraphsFragment();
                } else if (item.getItemId() == R.id.nav_other) {
                    selectedFragment =  new OtherFragment();
                }
                else if(item.getItemId() == R.id.nav_settings){
                    selectedFragment =  new SettingsFragment();
                }

                if (selectedFragment != null){
                    getSupportFragmentManager().beginTransaction()
                            .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                            .replace(R.id.fragmentCont, selectedFragment)
                            .commit();
                }

                return true;
            }


        });
    }
}