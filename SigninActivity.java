package com.example.saree360.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
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
import com.example.saree360.R;
import com.example.saree360.utils.Link;

import java.util.HashMap;
import java.util.Map;

public class SigninActivity extends AppCompatActivity {
Button log;
TextView txt;
EditText username,password;
    SharedPreferences pref;
    String user, pass;
    ProgressDialog Dialog1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signinactivity);
        pref = getSharedPreferences("id",MODE_PRIVATE);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        log = (Button) findViewById(R.id.log);
        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // if(pref.contains("username") && pref.contains("password")) {
                    validate();

              //  }
            }
        });

        txt = (TextView) findViewById(R.id.reg);
        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inte =new Intent(SigninActivity.this,signupactivity.class);
                startActivity(inte);
            }
        });
    }


    public void Login() {
        Log.d("responseee", "inside111");
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Link.link+"signin.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Dialog1.dismiss();
                        Log.d("responseee", response);
                        String[] arr = response.trim().split("_");
                        Log.d("valid_respnse", arr[1]);

                        try {
                            if (arr[0].equalsIgnoreCase("invalid")) {
                                //warning.setVisibility(View.VISIBLE);
                                Toast.makeText(SigninActivity.this,"Invalid Login details",Toast.LENGTH_SHORT).show();


                            } else if (arr[0].equalsIgnoreCase("not_register")) {

                                AlertDialog alertDialog = new AlertDialog.Builder(SigninActivity.this, AlertDialog.THEME_HOLO_LIGHT).create();
                                alertDialog.setTitle("Warning..!");
                                alertDialog.setMessage("User not register");
                                alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "OK",
                                        new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                Dialog1.dismiss();
                                                dialog.dismiss();
                                                username.setText("");
                                                password.setText("");

                                            }
                                        });
                                alertDialog.show();
                            } else if (arr[0].equals("valid")) {

                                Dialog1.dismiss();
                                //startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                //sessionManager.createLoginSession(arr[1], arr[2], arr[3], arr[4]);
                                Log.d("inn","Valid");
                                /////////////////////
                                Intent intent= new Intent(SigninActivity.this, MainActivity.class);
                                intent.putExtra("login",arr[1]+"");
                                Toast.makeText(SigninActivity.this,"login id"+arr[1]+"",Toast.LENGTH_SHORT).show();
                                Log.d("Login",arr[1]);
                                startActivity(intent);
                                //////////////////////
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
                params.put("password", password.getText().toString().trim());
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(SigninActivity.this);
        requestQueue.add(stringRequest);
    }
    private void validate() {
        user = username.getText().toString().trim();
        pass = password.getText().toString().trim();
        if (!validation()) {
            Dialog1 = new ProgressDialog(SigninActivity.this, R.style.AppTheme_Dark_Dialog);
            Dialog1.setIndeterminate(true);
            Dialog1.setMessage("Authenticating...");
            Dialog1.show();
            //Network Check
            //ConnectivityReceiver.isConnectedProgress(LoginActivity.this,findViewById(R.id.login_activity),Dialog1);
            Login();
        }
    }

    public Boolean validation() {
        boolean val = false;
        if (username.getText().toString().trim().length() == 0) {
            val = true;
            username.setError("Username is not entered");
            username.requestFocus();
        }
        if (password.getText().toString().trim().length() == 0) {
            val = true;
            password.setError("Password is not entered");
            password.requestFocus();
        }
        if (username.getText().toString().trim().length() < 1) {
            val = true;
            username.setError("Username should contain 5 digits");
            username.requestFocus();
        }
        return val;
    }
}
