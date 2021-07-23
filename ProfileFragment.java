package com.example.saree360.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.saree360.Activity.MainActivity;
import com.example.saree360.R;
import com.example.saree360.utils.Link;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ProfileFragment extends Fragment {


    TextView username1,email1;
    Button btn;

    JSONArray users;
    EditText username,email,phone,password;
    String pid;
    Button button;
    public ProfileFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);
        pid = this.getArguments().getString("data");
        Log.d("this data",""+pid);
        username1 = rootView.findViewById(R.id.textView14);
        email1 = rootView.findViewById(R.id.textView15);
        username = rootView.findViewById(R.id.edit1);
        email = rootView.findViewById(R.id.edit2);
        phone = rootView.findViewById(R.id.edit3);
        password = rootView.findViewById(R.id.edit4);
        button = rootView.findViewById(R.id.button7);
        profiledata();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateprofile();
              Toast.makeText(getContext(),"Profile Updated",Toast.LENGTH_SHORT).show();
            }
        });
        return rootView;
    }
    private void profiledata(){

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Link.link+"profiledata.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("ListStudents11111: ",response );
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            users = jsonObject.getJSONArray("result");

                            for(int i=0;i<users.length();i++){
                                JSONObject jo = users.getJSONObject(i);
                                username1.setText(jo.optString("username"));
                                email1.setText(jo.optString("email"));
                                username.setText(jo.optString("username"));
                                email.setText(jo.optString("email"));
                                phone.setText(jo.optString("phoneno"));
                                password.setText(jo.optString("password"));

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Log.d("error.toString(): ",error.toString());
                    }
                }){
            @Override
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap<>();
                params.put("logid",pid);
                Log.d("Params",""+params);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }

    public void updateprofile() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Link.link+"updateprofile.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("response:", "response:-" + response);
                        if (response.equals("Success"))
                        {
                            //    Toast.makeText(getContext(), "Success", Toast.LENGTH_LONG).show();

                        } else {
                            //   Toast.makeText(getContext(), "Failed", Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getContext(), error.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("id",pid);
                params.put("username", username.getText().toString().trim());
                params.put("email", email.getText().toString().trim());
                params.put("phoneno", phone.getText().toString().trim());
                params.put("password", password.getText().toString().trim());
                Log.d("params", params.toString());
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);

    }

}
