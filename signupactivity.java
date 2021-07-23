package com.example.saree360.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.example.saree360.R;
import com.example.saree360.utils.Link;

import java.util.HashMap;
import java.util.Map;

import static com.example.saree360.utils.Link.link;

public class signupactivity extends AppCompatActivity implements View.OnClickListener{
Button reg;
TextView log;
EditText username,email,password,phone;
    private AwesomeValidation awesomeValidation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signupactivity);
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        email = (EditText) findViewById(R.id.email);
        phone = (EditText) findViewById(R.id.phone);

        reg = (Button) findViewById(R.id.reg);
        String regexPassword = "(?=.*[a-z])(?=.*[A-Z])(?=.*[\\d])(?=.*[~`!@#\\$%\\^&\\*\\(\\)\\-_\\+=\\{\\}\\[\\]\\|\\;:\"<>,./\\?]).{8,}";
        awesomeValidation.addValidation(this, R.id.username, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", R.string.usernameerror);
        awesomeValidation.addValidation(this, R.id.email, Patterns.EMAIL_ADDRESS, R.string.emailerror);
        awesomeValidation.addValidation(this, R.id.phone, "^[1-9]{2}[0-9]{8}$", R.string.mobileerror);
        awesomeValidation.addValidation(this, R.id.password, regexPassword, R.string.passworderror);
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == reg) {
                    Register();
                }
            }
        });
        log = (TextView) findViewById(R.id.log);
        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View V) {
                Intent intent1 = new Intent(signupactivity.this, SigninActivity.class);
                startActivity(intent1);
            }
        });
    }
        private void submitForm() {
            //first validate the form then move ahead
            //if this becomes true that means validation is successfull
            if (awesomeValidation.validate()) {
                Toast.makeText(this, "Registration Successful", Toast.LENGTH_LONG).show();
                //process the data further
            }
        }
    public void onClick(View view) {
        if (view == reg) {
            submitForm();
        }
    }

    public void Register() {
        if (awesomeValidation.validate()) {
            Log.d("responseee", "inside");
            StringRequest stringRequest = new StringRequest(Request.Method.POST, Link.link+"signup.php",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.d("responseee", response);
                            String[] arr = response.trim().split("-");
                            Log.d("valid_respnse", arr[0]);
                            try {
                                if (arr[0].equalsIgnoreCase("invalid")) {
                                   // warning.setVisibility(View.VISIBLE);
                                    Toast.makeText(signupactivity.this,"Registeration not successfull",Toast.LENGTH_SHORT).show();
                                } else if (arr[0].equals("valid")) {
                                    Toast.makeText(signupactivity.this, "Registration Successful", Toast.LENGTH_LONG).show();
                                    Log.d("register",arr[0]);
                                    startActivity(new Intent(getApplicationContext(), SigninActivity.class));
                                    //sessionManager.createLoginSession(arr[1], arr[2], arr[3], arr[4]);
                                    for (String anArr : arr) {
                                        Log.d("SessionVlaue", anArr);
                                    }
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    //Server Error Handler
                    //new ErrorHandler(error,LoginActivity.this, findViewById(R.id.login_activity));
                }
            }) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<>();
                    params.put("username", username.getText().toString().trim());
                    params.put("email", email.getText().toString().trim());
                    params.put("phoneno", phone.getText().toString().trim());
                    params.put("password", password.getText().toString().trim());
                    Log.d("params", params.toString());
                    return params;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(signupactivity.this);
            requestQueue.add(stringRequest);

        }

    }

}

