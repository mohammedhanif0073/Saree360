package com.example.saree360.Activity;

import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.viewpager.widget.ViewPager;


import com.example.saree360.AdapterClass.CustomExpandableListAdapter;
import com.example.saree360.fragments.CartItem;
import com.example.saree360.fragments.DashboardFragment;
import com.example.saree360.fragments.OrderFragment;
import com.example.saree360.fragments.ProfileFragment;
import com.example.saree360.AdapterClass.MenuModel;
import com.example.saree360.R;
import com.example.saree360.fragments.Sample2Fragment;
import com.example.saree360.fragments.Sample1Fragment;
import com.google.android.material.navigation.NavigationView;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private AppBarConfiguration mAppBarConfiguration;
    ViewPager viewPager;
    LinearLayout sliderDotspanel;
    private int dotscount;
    private ImageView[] dots;
    Button button;
    ImageView imageView;
    FrameLayout frameLayout;
   //public static BottomNavigationView navView;
    CustomExpandableListAdapter expandableListAdapter;
    ExpandableListView expandableListView;
    List<MenuModel> headerList;
    HashMap<MenuModel, List<String>> childList ;
    public static int[] icon = { R.drawable.dashboard};
    String str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        str = intent.getStringExtra("login");
        Log.d("login_id",str);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.bringToFront();
        setSupportActionBar(toolbar);


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.navigationView);

       // navView = findViewById(R.id.bottomNavigationView);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);

        NavigationUI.setupWithNavController(navigationView, navController);

        loadFragment(new DashboardFragment());

        expandableListView = findViewById(R.id.expandableListView);
       // prepareMenuData();
        prepareListData();
        populateExpandableList();

        navigationView.setNavigationItemSelectedListener(MainActivity.this);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if(id == R.id.cart){
                CartItem ct =new CartItem();
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction().replace(R.id.nav_host_fragment, ct, "Test Fragment");
                transaction.addToBackStack(null).commit();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    private void prepareListData() {
        headerList = new ArrayList<>();
        childList = new HashMap<>();

        MenuModel item1 = new MenuModel();
        item1.setIconName("Dashboard");
        item1.setIconImg(R.drawable.ic_baseline_dashboard_24);
        headerList.add(item1);

        MenuModel item2 = new MenuModel();
        item2.setIconName("Order History");
        item2.setIconImg(R.drawable.ic_baseline_history_24);
        headerList.add(item2);

        MenuModel item3 = new MenuModel();
        item3.setIconName("Category");
        item3.setIconImg(R.drawable.ic_baseline_format_list_bulleted_24);
        headerList.add(item3);

        MenuModel item4 = new MenuModel();
        item4.setIconName("My Cart");
        item4.setIconImg(R.drawable.ic_baseline_shopping_cart_24);
        headerList.add(item4);

        MenuModel item5 = new MenuModel();
        item5.setIconName("Profile");
        item5.setIconImg(R.drawable.ic_baseline_person_pin_24);
        headerList.add(item5);

        MenuModel item6 = new MenuModel();
        item6.setIconName("Logout");
        item6.setIconImg(R.drawable.ic_baseline_exit_to_app_24);
        headerList.add(item6);

        List<String> heading1 = new ArrayList<String>();
//        heading1.add("Submenu of item 1");

        List<String> heading2 = new ArrayList<String>();
//        heading2.add("Submenu of item 2");
//        heading2.add("Submenu of item 2");
//        heading2.add("Submenu of item 2");

        List<String> heading3 = new ArrayList<String>();

        List<String> heading4 = new ArrayList<String>();

        List<String> heading5 = new ArrayList<String>();

        List<String> heading6 = new ArrayList<String>();

        childList.put(headerList.get(0), heading1);// Header, Child data
        childList.put(headerList.get(1), heading2);
        childList.put(headerList.get(2), heading3);
        childList.put(headerList.get(3), heading4);
        childList.put(headerList.get(4), heading5);
        childList.put(headerList.get(5), heading6);
    }

    private void populateExpandableList() {

        expandableListAdapter = new CustomExpandableListAdapter(this, headerList, childList,expandableListView);
        expandableListView.setAdapter(expandableListAdapter);
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        headerList.get(groupPosition) + " List Expanded.",
                        Toast.LENGTH_SHORT).show();

            }
        });

        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        headerList.get(groupPosition) + " List Collapsed.",
                        Toast.LENGTH_SHORT).show();

            }
        });

        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {

                   if (groupPosition == 0 ) {

                       DashboardFragment hf = new DashboardFragment();
                       FragmentManager manager = getSupportFragmentManager();
                       FragmentTransaction transaction = manager.beginTransaction().replace(R.id.nav_host_fragment, hf, "Test Fragment");
                       transaction.addToBackStack(null).commit();
                   }
               else if (groupPosition == 1 ) {
                       OrderFragment prod = new OrderFragment();
                       FragmentManager manager = getSupportFragmentManager();
                       FragmentTransaction transaction = manager.beginTransaction().replace(R.id.nav_host_fragment, prod, "Test Fragment");
                       transaction.addToBackStack(null).commit();
                   }
                 else if (groupPosition == 2) {
                       DashboardFragment frag = new DashboardFragment();
                   FragmentManager manager = getSupportFragmentManager();
                   FragmentTransaction transaction = manager.beginTransaction().replace(R.id.nav_host_fragment, frag, "Test Fragment");
                   transaction.addToBackStack(null).commit();

               } else if (groupPosition == 3) {
                       CartItem prod = new CartItem();
                       FragmentManager manager = getSupportFragmentManager();
                       FragmentTransaction transaction = manager.beginTransaction().replace(R.id.nav_host_fragment, prod, "Test Fragment");
                       transaction.addToBackStack(null).commit();

                   } else if (groupPosition == 4) {
                       Bundle args = new Bundle();
                       args.putString("data",""+str);
                       Log.d("Profile",""+args);
                       Toast.makeText(MainActivity.this,"profiledetails"+args,Toast.LENGTH_SHORT).show();

                           ProfileFragment prod = new ProfileFragment();
                           FragmentManager manager = getSupportFragmentManager();
                           FragmentTransaction transaction = manager.beginTransaction().replace(R.id.nav_host_fragment, prod, "Test Fragment");
                           prod.setArguments(args);
                           transaction.addToBackStack(null).commit();



               } else if (groupPosition == 5) {
                   Intent intent = new Intent(MainActivity.this, SigninActivity.class);
                   startActivity(intent);
               }

                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);
                Log.e("Abstract", "DONE");
                return false;
            }
        });

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

                         Toast.makeText(getApplicationContext(), headerList.get(groupPosition)
                                 + " -> " + childList.get(headerList.get(groupPosition)).get(
                                childPosition), Toast.LENGTH_SHORT).show();

                            Log.i("FragmentList", "Item clicked: " + id);


                            if(groupPosition == 0 && id == 0) {

                                Sample1Fragment frag = new Sample1Fragment();
                                FragmentManager manager = getSupportFragmentManager();
                                FragmentTransaction transaction = manager.beginTransaction().replace(R.id.nav_host_fragment, frag, "Test Fragment");
                                transaction.commit();

                            }
                            else if(groupPosition == 1 && id == 0)
                            {
                                Sample2Fragment prod = new Sample2Fragment();
                                FragmentManager manager = getSupportFragmentManager();
                                FragmentTransaction transaction = manager.beginTransaction().replace(R.id.nav_host_fragment, prod, "Test Fragment");
                                transaction.commit();
                            }
                            else if(groupPosition ==1 && id == 1)
                            {
                                ProfileFragment p = new ProfileFragment();
                                FragmentManager manager = getSupportFragmentManager();
                                FragmentTransaction transaction = manager.beginTransaction().replace(R.id.nav_host_fragment, p, "Test Fragment");
                                transaction.commit();

                            }
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);
                Log.e("Abstract", "DONE");
                return false;
            }
        });
    }
    public void loadFragment(DashboardFragment fragment) {
        Bundle args = new Bundle();
        args.putString("data",""+str);
        Log.d("Profile",""+args);
        Toast.makeText(MainActivity.this,"profiledetails"+args,Toast.LENGTH_SHORT).show();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.nav_host_fragment, fragment);
        fragment.setArguments(args);
        transaction.commit();
    }

}