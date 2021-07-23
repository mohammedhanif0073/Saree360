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
import com.example.saree360.datavalues.OrderList;
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

public  class OrderAdapter extends BaseAdapter {

    Context context;
    List<OrderList> rowItems;
    String id,sareename,brand,price,img;
    JSONArray users;
    ViewHolder holder;
    public OrderAdapter(Context context, List<OrderList> items)
    {
        this.context = context;
        this.rowItems = items;

    }

    class ViewHolder {
        TextView id;
        ImageView image;
        TextView sareename;
        TextView qty,total;
        TextView price;
        CardView card;
    }


    public View getView(final int position, View convertView, ViewGroup parent) {

        final OrderList rowItem = (OrderList) getItem(position);
        id=rowItem.getId();

        LayoutInflater mInflater = (LayoutInflater)
                context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.layout_orderedlist, null);
            holder = new ViewHolder();
            holder.id =(TextView)convertView.findViewById(R.id.id);
            holder.image = (ImageView) convertView.findViewById(R.id.image4);
            holder.sareename = (TextView)convertView.findViewById(R.id.text8);
            holder.qty = (TextView)convertView.findViewById(R.id.qty);
            holder.price = (TextView)convertView.findViewById(R.id.text9);
            holder.total = (TextView)convertView.findViewById(R.id.total);
          //  holder.card = (CardView)convertView.findViewById(R.id.card21);

            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }
        Glide.with(context).load(rowItems.get(position).getImage()).into(holder.image);
        holder.id.setText(rowItem.getId());
        Log.d("Adapter id","id"+rowItem.getId());
        holder.sareename.setText(rowItem.getSareename());
        holder.qty.setText(rowItem.getQuantity());
        holder.price.setText("Price- "+rowItem.getPrice());
        holder.total.setText(String.valueOf("Total price- "+rowItem.getTotal()));

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
