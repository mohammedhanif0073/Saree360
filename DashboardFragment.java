package com.example.saree360.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.example.saree360.Activity.MainActivity;
import com.example.saree360.AdapterClass.ViewPagerAdapter;
import com.example.saree360.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Timer;
import java.util.TimerTask;

public class DashboardFragment extends Fragment {
    public static BottomNavigationView navView;
    ViewPager viewPager;
    LinearLayout sliderDotspanel;
    private int dotscount;
    Button button;
    ImageView imageView;
    private ImageView[] dots;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    ViewPagerAdapter viewPagerAdapter;
    String fvalue;
    public static DashboardFragment newInstance() {
        DashboardFragment fragment = new DashboardFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_dashboard, container, false);
        fvalue = this.getArguments().getString("data");
        Log.d("Profile id",fvalue);
        viewPager = (ViewPager) rootView.findViewById(R.id.viewPager);


        sliderDotspanel = (LinearLayout) rootView.findViewById(R.id.sliderdot);
        viewPagerAdapter = new ViewPagerAdapter(getContext());

        viewPager.setAdapter(viewPagerAdapter);


        dotscount = viewPagerAdapter.getCount();
        dots = new ImageView[dotscount];
        for(int i = 0; i < dotscount; i++){

            dots[i] = new ImageView(getContext());
            dots[i].setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.non_active_dot));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            params.setMargins(8, 0, 8, 0);

            sliderDotspanel.addView(dots[i], params);

        }
        dots[0].setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.active_dot));




        // Auto start of viewpager
//        final Handler handler = new Handler();
//        final Runnable Update = new Runnable() {
//            public void run() {
//                if (currentPage == NUM_PAGES) {
//                    currentPage = 0;
//                }
//                viewPager.setCurrentItem(currentPage++, true);
//            }
//        };
//        Timer swipeTimer = new Timer();
//        swipeTimer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                handler.post(Update);
//            }
//        }, 3000, 3000);



        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                for(int i = 0; i< dotscount; i++){
                    dots[i].setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.non_active_dot));
                }

                dots[position].setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.active_dot));

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        button = (Button)rootView.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //MainActivity.navView.setVisibility(View.GONE);
                CartFragment gf = new CartFragment();
                FragmentManager manager = getFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction().replace(R.id.nav_host_fragment, gf, "Test Fragment");
                transaction.addToBackStack(null).commit();
            }
        });

        imageView = (ImageView)rootView.findViewById(R.id.imageView1);
        imageView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
               // MainActivity.navView.setVisibility(View.GONE);
                CartFragment gf = new CartFragment();
                FragmentManager manager = getFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction().replace(R.id.nav_host_fragment, gf, "Test Fragment");
                transaction.addToBackStack(null).commit();
            }
        });
        navView = rootView.findViewById(R.id.bottomNavigationView);
        navView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch(item.getItemId()){
                    case R.id.nav_dashboard:


                        DashboardFragment hf = new DashboardFragment();
                        FragmentManager manager = getFragmentManager();
                        FragmentTransaction transaction = manager.beginTransaction().replace(R.id.nav_host_fragment, hf, "Test Fragment");
                        transaction.addToBackStack(null).commit();
                        break;


                    case R.id.nav_cart:
                        CartItem cf = new CartItem();
                        FragmentManager manager2 = getFragmentManager();
                        FragmentTransaction transaction2 = manager2.beginTransaction().replace(R.id.nav_host_fragment, cf, "Test Fragment");
                        transaction2.addToBackStack(null).commit();
                        break;

                    case R.id.nav_profile:
                        Bundle args = new Bundle();
                        args.putString("data",""+fvalue);
                        Log.d("Profile",""+args);
                        Toast.makeText(getContext(),"profiledetails"+args,Toast.LENGTH_SHORT).show();
                        ProfileFragment prod = new ProfileFragment();
                        FragmentManager manager4 = getFragmentManager();
                        FragmentTransaction transaction3 = manager4.beginTransaction().replace(R.id.nav_host_fragment, prod, "Test Fragment");
                        prod.setArguments(args);
                        transaction3.addToBackStack(null).commit();
                        break;
                }
                return false;
            }
        });


        return rootView;
    }
}
