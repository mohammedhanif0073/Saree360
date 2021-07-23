package com.example.saree360.AdapterClass;

import android.app.Activity;
import android.content.Context;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
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
import com.bumptech.glide.util.Util;
import com.example.saree360.R;
import com.example.saree360.datavalues.CartItemdata;
import com.example.saree360.fragments.CartItem;
import com.example.saree360.utils.Link;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public  class Adapter_Cart_Item extends BaseAdapter {

    Context context;
    List<CartItemdata> rowItems;
    //final int[] quantity = {1};
    String quantity1,id1;
    String price1,total1;
    int totalprice;
    int amount;
    int  quantityprice;
    public Adapter_Cart_Item(Context context, List<CartItemdata> items)
    {
        this.context = context;
        this.rowItems = items;

    }

    class ViewHolder {
        TextView textqty;
        TextView id;
        ImageView image,add,remove;
        TextView sareename;
        TextView brand;
        TextView price,total,amount;
        CardView card;
        Button button;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;

        LayoutInflater mInflater = (LayoutInflater)
                context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.layout_item_cart, null);
            holder = new ViewHolder();
            holder.id = (TextView) convertView.findViewById(R.id.textView16);
            holder.image = (ImageView) convertView.findViewById(R.id.imageView4);
            holder.sareename = (TextView) convertView.findViewById(R.id.textView8);
            holder.brand = (TextView) convertView.findViewById(R.id.textView7);
            holder.price = (TextView) convertView.findViewById(R.id.textView9);
            holder.total = (TextView) convertView.findViewById(R.id.textView17);
            holder.card = (CardView) convertView.findViewById(R.id.cardview4);
            holder.textqty = (TextView) convertView.findViewById(R.id.textview);
            holder.add = (ImageView) convertView.findViewById(R.id.add1);
            holder.remove = (ImageView) convertView.findViewById(R.id.remove1);
            holder.button = (Button)convertView.findViewById(R.id.button);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final CartItemdata rowItem = (CartItemdata) getItem(position);
        Glide.with(context).load(rowItems.get(position).getImage()).into(holder.image);
        final int[] quantity = {Integer.parseInt(rowItems.get(position).getQuantity())};
        final int totalprice = Integer.parseInt(String.valueOf(rowItems.get(position).getTotal()));
         holder.id.setText(rowItem.getId());
        holder.sareename.setText(rowItem.getSareename());
        holder.brand.setText(rowItem.getBrand());
        holder.price.setText(rowItem.getPrice());
        Log.d("price",rowItem.getPrice());
        holder.textqty.setText(String.valueOf(quantity[0]));
        holder.total.setText(String.valueOf(rowItem.getTotal()));
        Log.d("total111111111",""+rowItem.getTotal());


        final ViewHolder finalHolder = holder;
        holder.card.setTag(position);
//        holder.add.setOnClickListener(new View.OnClickListener() {
//
//            public void onClick(View v) {
//
//                number[0] = number[0] + 1;
//                finalHolder.textqty.setText("" + number[0]);
//
////                quantity1 = finalHolder.textqty.getText().toString();
////                Toast.makeText(context, "Quantity=" + number[0], Toast.LENGTH_LONG).show();
//
//
////                price1 = finalHolder.price.getText().toString();
////                totalprice += Integer.parseInt(price1);
////                Toast.makeText(context,"Quantity="+number[0]+"price="+totalprice,Toast.LENGTH_LONG).show();
//            }
//        });

//        price1 = getArguments().getString("price");
//        selectedItemQuantity++;
//        quantityValue.setText(String.valueOf(selectedItemQuantity));
//        quantity1 = quantityValue.getText().toString();
//        total += Integer.parseInt(price1);
//        price1 = price.getText().toString();
//        Log.d("price",price1);

        holder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String price1 = finalHolder.price.getText().toString();
                quantity[0]++;
                rowItems.get(position).setItemQuantity(String.valueOf(quantity[0]));
                id1 = finalHolder.id.getText().toString();
                finalHolder.textqty.setText(String.valueOf(quantity[0]));
                quantity1 = finalHolder.textqty.getText().toString();
                Log.d("price1111111111",price1);
               // total1 = finalHolder.total.getText().toString();
                quantityprice += totalprice;
               // quantityprice += Integer.parseInt(price1);
                amount = quantityprice + Integer.parseInt(price1);
                Log.d("quantity price",""+quantityprice);
                Log.d(" total",""+amount);

                Toast.makeText(context,"Quantity="+quantity[0]+"price="+amount,Toast.LENGTH_LONG).show();
                Log.d("quantity"+quantity[0],"total price"+amount);
                updateQuantity();
                CartItem fragment = new CartItem();
                FragmentManager manager = ((AppCompatActivity)context).getSupportFragmentManager();
                manager.beginTransaction().replace(R.id.nav_host_fragment,fragment).commit();
            }
        });


//        holder.remove.setOnClickListener(new View.OnClickListener() {
//
//            public void onClick(View v) {
//
//                if (number[0] == 1) {
//                    finalHolder.textqty.setText("" + number[0]);
//                }
//                if (number[0] > 1) {
//                    number[0] = number[0] - 1;
//                    finalHolder.textqty.setText("" + number[0]);
//                    //  quantity1 = finalHolder.textqty.getText().toString();
////                    price1 = finalHolder.price.getText().toString();
////                    totalprice -= Integer.parseInt(price1);
////                    Toast.makeText(context,"Quantity="+number[0]+"price="+totalprice,Toast.LENGTH_LONG).show();
//                }
//            }
//        });
        // Quantity Increment Listener
        holder.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (quantity[0] != 1) {
                    quantity[0]--;
                    //updateQuantity(quantity[0],position); // update in DB
                    rowItems.get(position).setItemQuantity(String.valueOf(quantity[0]));
                    id1 = finalHolder.id.getText().toString();
                    finalHolder.textqty.setText(String.valueOf(quantity[0]));
                    quantity1 = finalHolder.textqty.getText().toString();
                    price1 = finalHolder.price.getText().toString();
                    quantityprice = totalprice;
                    amount=  quantityprice - Integer.parseInt(price1);
                    Log.d("quantity price",""+quantityprice);
                    Log.d(" price",""+Integer.parseInt(price1));
                    Log.d("total ",""+amount);
                    //amount = String.valueOf(totalprice);

                    Toast.makeText(context,"Quantity="+quantity[0]+"price="+amount,Toast.LENGTH_LONG).show();
                    updateQuantity();
                    CartItem fragment = new CartItem();
                    FragmentManager manager = ((AppCompatActivity)context).getSupportFragmentManager();
                    manager.beginTransaction().replace(R.id.nav_host_fragment,fragment).commit();
                }
            }
        });

        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id1 = finalHolder.id.getText().toString();
                deleteproduct();
                CartItem fragment = new CartItem();
                FragmentManager manager = ((AppCompatActivity)context).getSupportFragmentManager();
                manager.beginTransaction().replace(R.id.nav_host_fragment,fragment).commit();
            }
        });

        return convertView;
    }

    public void updateQuantity() {

        String url = Link.link+"updateitemprice.php";

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
                params.put("id",id1);
                params.put("quantity",quantity1);
                params.put("total",""+amount);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }
    public void deleteproduct() {

        String url = Link.link+"deleteproduct.php";

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
                params.put("id",id1);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
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
    @Override
    public int getItemViewType(int position) {
        return position;
    }

}
