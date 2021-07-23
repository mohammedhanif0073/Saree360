package com.example.saree360.fragments;
import android.database.DataSetObserver;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.saree360.AdapterClass.Adapter_Cart;
import com.example.saree360.AdapterClass.Adapter_Cart_Item;
import com.example.saree360.R;
import com.example.saree360.datavalues.Cart;
import com.example.saree360.datavalues.CartItemdata;
import com.example.saree360.datavalues.OrderList;
import com.example.saree360.utils.Link;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class CartItem extends Fragment {
    JSONObject jo;
    Button button;
    ListView listView;
    JSONArray users;
    List<CartItemdata> list;
    String cartId,id1;
    int amount;
    TextView totalamount;
    Button btn;
    int total_amount;
    public static CartItem newInstance() {
        CartItem ci = new CartItem();
        return ci;
    }
    //@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart_item_list, container, false);
        button = (Button)view.findViewById(R.id.btn);
        listView = view.findViewById(R.id.cart_item);
        totalamount = view.findViewById(R.id.textView5);
        btn = view.findViewById(R.id.btn);
        Cartlist();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OrderFragment fragment = new OrderFragment();
                FragmentManager manager = getFragmentManager();
                manager.beginTransaction().replace(R.id.nav_host_fragment,fragment).commit();
                Toast.makeText(getContext(),"Successfull Ordered!!!",Toast.LENGTH_LONG).show();
            }
        });

    return view;
    }
    private void Cartlist() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Link.link+"cart_item.php",
                new Response.Listener<String>() {

                    public void onResponse(String response) {

                        Log.d("item in cart: ", response);
                        try {
                            list = new ArrayList<>();
                            JSONObject jsonObject = new JSONObject(response);
                            users = jsonObject.getJSONArray("result");
                            for (int i = 0; i < users.length(); i++) {
                                jo = users.getJSONObject(i);
                                CartItemdata cart = new CartItemdata(jo.optString("id"),
                                        jo.optString("image"),
                                        jo.optString("sareename"),
                                        jo.optString("brand"),
                                        jo.optString("quantity"),
                                        jo.optString("price"),
                                        jo.optInt("total"));

                                list.add(cart);
                                amount = jo.getInt("total");
                                total_amount += amount;
                            }
                            totalamount.setText("Total Amount- "+total_amount);
                            Log.d("total value",""+total_amount);
                            listView.setAdapter(new Adapter_Cart_Item(getContext(), list) {
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