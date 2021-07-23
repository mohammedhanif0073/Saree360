package com.example.saree360.fragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.saree360.R;
import com.example.saree360.datavalues.Cart;
import com.example.saree360.utils.Link;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;


public class AddCartFragment extends Fragment {
    int[] number = {0};
    TextView textqty ;
    TextView id,sareename,brand,quantity,price,totalprice;
    ImageView img;
    JSONArray users;
    String id1;
    String image1;
    String sareename1;
    String brand1;
    String quantity1;
    String price1;
    int total;
    double total_amount = 0.0 ;

/////////////////////////////////
    int selectedItemQuantity = 0;
    ImageView minus, plus;
    TextView quantityValue;
//////////////////////////////
    public static AddCartFragment newInstance() {
        AddCartFragment adf = new AddCartFragment();
        return adf;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view =inflater.inflate(R.layout.fragment_add_cart, container, false);
        Button b1 = (Button)view.findViewById(R.id.button4);
        Button b2 = (Button)view.findViewById(R.id.button5);
        img = (ImageView)view.findViewById(R.id.imageView4);
        id = (TextView)view.findViewById(R.id.textView13) ;
        sareename= (TextView)view.findViewById(R.id.textView8);
        brand = (TextView)view.findViewById(R.id.textView7);
        price =(TextView)view.findViewById(R.id.textView9);
        totalprice = (TextView)view.findViewById(R.id.text10);
////////////////////////////////////////////////////
        minus = view.findViewById(R.id.remove);
        plus = view.findViewById(R.id.add);
        quantityValue =(TextView)view.findViewById(R.id.product_qty);

////////////////////////////////////////////////////////////
        id1 = getArguments().getString("id");
        image1 = getArguments().getString("image");
        sareename1 = getArguments().getString("sareename");
        brand1 = getArguments().getString("brand");
        price1 = getArguments().getString("price");

        id.setText(id1);
        Glide.with(getContext()).load(image1).into(img);
        sareename.setText(sareename1);
        brand.setText(brand1);
        price.setText(price1);
       //totalprice.setText(total);
        LinearLayout linearLayout =(LinearLayout)view.findViewById(R.id.linear);
        linearLayout.bringToFront();

        setQuantityUpdateListeners();

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemadd();
                CartItem fragment = new CartItem();
                FragmentManager manager = getFragmentManager();
                manager.beginTransaction().replace(R.id.nav_host_fragment,fragment).commit();

            }
        });

        return view;
    }
    private void setQuantityUpdateListeners() {
        // Increment Listener
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                price1 = getArguments().getString("price");
                selectedItemQuantity++;
                quantityValue.setText(String.valueOf(selectedItemQuantity));
                quantity1 = quantityValue.getText().toString();
                total += Integer.parseInt(price1);
                price1 = price.getText().toString();
                totalprice.setText(""+total);
                Log.d("price",price1);
                Log.d("total price",""+total);
                Toast.makeText(getContext(),"Quantity="+selectedItemQuantity+"price="+total,Toast.LENGTH_LONG).show();

            }
        });
        // Decrement Listener
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selectedItemQuantity != 1) {
                    price1 = getArguments().getString("price");
                    selectedItemQuantity--;
                    quantityValue.setText(String.valueOf(selectedItemQuantity));
                    quantity1 = quantityValue.getText().toString();
                    total -= Integer.parseInt(price1);
                    price1 = price.getText().toString();
                    totalprice.setText(""+total);
                    Log.d("price",price1);
                    Log.d("total price",""+total);
                    Toast.makeText(getContext(),"Quantity="+selectedItemQuantity+"price="+total,Toast.LENGTH_LONG).show();

                }
            }
        });


    }
    public void itemadd() {

        String url = Link.link+"insertcartitem.php";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("response:", "response:-" + response);
                        if (response.equals("Success"))
                        {
//                            Intent intent = new Intent(AddTimeTableActivity.this,TimetableActivity.class);
//                            startActivity(intent);
//                            Toast.makeText(getContext(), "Success", Toast.LENGTH_LONG).show();

                        } else {
                          //  Toast.makeText(getContext(), "Failed", Toast.LENGTH_LONG).show();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                       // Toast.makeText(getContext(), error.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("id", id1);
                params.put("image",image1);
                params.put("sareename", sareename1);
                params.put("brand", brand1);
                params.put("quantity",quantity1);
                params.put("price",price1);
                params.put("total",""+total);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }

}