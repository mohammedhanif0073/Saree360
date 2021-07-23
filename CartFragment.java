package com.example.saree360.fragments;

import android.content.Context;
import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;


import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.saree360.AdapterClass.Adapter_Cart;
import com.example.saree360.R;
import com.example.saree360.datavalues.Cart;
import com.example.saree360.utils.Link;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CartFragment extends Fragment {

    ListView listView;
    JSONArray users;
    ArrayList<Cart> list;

    public static CartFragment newInstance() {
        CartFragment fragment = new CartFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart_list, container, false);
        listView = view.findViewById(R.id.cart_list);
        Cartlist();
        return view;
    }

    private void Cartlist() {

        StringRequest stringRequest = new StringRequest(Request.Method.POST,Link.link+"cart.php",
                new Response.Listener<String>() {

                    public void onResponse(String response) {

                        Log.d("ListStudents11: ", response);
                        try {
                            list = new ArrayList<>();
                            JSONObject jsonObject = new JSONObject(response);
                            users = jsonObject.getJSONArray("result");


                            for (int i = 0; i < users.length(); i++) {
                                JSONObject jo = users.getJSONObject(i);
                               // Cart cart = new Cart(jo.optString("id"),Link.images+jo.optString("image"),
                                        Cart cart = new Cart(jo.optString("id"),jo.optString("image"),

                                        jo.optString("sareename"),
                                        jo.optString("brand"),
                                        jo.optString("quantity"),
                                        jo.optString("price"));
                                list.add(cart);
                            }

                            listView.setAdapter(new Adapter_Cart(getContext(), list) {
                                @Override
                                public void registerDataSetObserver(DataSetObserver observer) {

                                }

                                @Override
                                public void unregisterDataSetObserver(DataSetObserver observer) {

                                }

                                @Override
                                public boolean hasStableIds() {
                                    return false;
                                }

                                @Override
                                public int getItemViewType(int position) {
                                    return 0;
                                }

                                @Override
                                public int getViewTypeCount() {
                                    return 1;
                                }

                                @Override
                                public boolean isEmpty() {
                                    return false;
                                }

                                @Override
                                public boolean areAllItemsEnabled() {
                                    return false;
                                }

                                @Override
                                public boolean isEnabled(int position) {
                                    return false;
                                }
                            });
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Toast.makeText(AddDoctorActivity.this,error.toString(),Toast.LENGTH_LONG).show();
                        Log.d("error.toString(): ", error.toString());
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }

}