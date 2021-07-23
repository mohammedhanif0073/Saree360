package com.example.saree360.AdapterClass;

import android.app.Activity;
import android.content.Context;

import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import androidx.fragment.app.FragmentManager;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.saree360.R;
import com.example.saree360.datavalues.Cart;
import com.example.saree360.fragments.AddCartFragment;
import com.example.saree360.fragments.CartItem;
import com.example.saree360.utils.Link;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public  class Adapter_Cart extends BaseAdapter {

    Context context;
    List<Cart> rowItems;
    String id,sareename,brand,price,img;
    JSONArray users;
    ViewHolder holder;
    public Adapter_Cart(Context context, List<Cart> items)
    {
        this.context = context;
        this.rowItems = items;

    }

    class ViewHolder {
        TextView id;
        ImageView image;
        TextView sareename;
        TextView brand;
        TextView price;
        CardView card;
    }


    public View getView(final int position, View convertView, ViewGroup parent) {

        final Cart rowItem = (Cart) getItem(position);
        id=rowItem.getId();

        LayoutInflater mInflater = (LayoutInflater)
                context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.layout_cart, null);
            holder = new ViewHolder();
            holder.id =(TextView)convertView.findViewById(R.id.textView12);
            holder.image = (ImageView) convertView.findViewById(R.id.imageView4);
            holder.sareename = (TextView)convertView.findViewById(R.id.textView7);
            holder.brand = (TextView)convertView.findViewById(R.id.textView8);
            holder.price = (TextView)convertView.findViewById(R.id.textView9);
            holder.card = (CardView)convertView.findViewById(R.id.card21);

            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }
        Glide.with(context).load(rowItems.get(position).getImage()).into(holder.image);
        holder.id.setText(rowItem.getId());
        Log.d("Adapter id","id"+rowItem.getId());
        holder.sareename.setText(rowItem.getSareename());
        holder.brand.setText(rowItem.getBrand());
        holder.price.setText(rowItem.getPrice());
        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddCartFragment fragment = new AddCartFragment();
                Bundle args = new Bundle();
                args.putString("id", rowItem.getId());
                args.putString("image",rowItems.get(position).getImage());
                args.putString("sareename",rowItem.getSareename());
                args.putString("brand",rowItem.getBrand());
                args.putString("price",rowItem.getPrice());
                args.putString("quantity",rowItem.getQuantity());
                FragmentManager manager = ((AppCompatActivity)context).getSupportFragmentManager();
                manager.beginTransaction().replace(R.id.nav_host_fragment,fragment).commit();
                fragment.setArguments(args);

            }
        });

        return convertView;
    }

    public int getCount() {
        return rowItems.size();
    }

    public Object getItem(int position) {
        return rowItems.get(position);
    }

    public long getItemId(int position) {
        return rowItems.indexOf(getItem(position));
    }

}
