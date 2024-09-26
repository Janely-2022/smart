package com.ega.smartoutlet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.ega.smartoutlet.adapters.SharedPreferenceHelper;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.android.material.textview.MaterialTextView;


public class HomeFragment extends Fragment {

    private SharedPreferenceHelper sharedPreferenceHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view;
       view = inflater.inflate(R.layout.fragment_home, container, false);

       sharedPreferenceHelper =  new SharedPreferenceHelper(getContext());

        TabLayout tabLayout =  view.findViewById(R.id.tabLayout);
        ViewPager2 viewPager2 =  view.findViewById(R.id.viewPager);
        MaterialTextView userFullname = view.findViewById(R.id.userFullname);
        userFullname.setText("Hello " + sharedPreferenceHelper.getFullname());

        FragmentStateAdapter pageAdapter =  new SectionsPagerAdapter(getActivity());
        viewPager2.setAdapter(pageAdapter);

        new TabLayoutMediator(tabLayout, viewPager2, (tab, position) -> {
            switch (position) {
                case 0:
                    tab.setText("Groups");
                    break;
                case 1:
                    tab.setText("Devices");
                    break;
            }
        }).attach();
        return view;

    }

    private static class SectionsPagerAdapter extends FragmentStateAdapter {
        public SectionsPagerAdapter(FragmentActivity fa) {
            super(fa);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            switch (position){
                case 0:
                    return new GroupsFragment();
                case 1:
                    return new DevicesFragment();
                default:
                    return new GroupsFragment();
            }
        }


        @Override
        public int getItemCount() {
            return 2;
        }

    }
}