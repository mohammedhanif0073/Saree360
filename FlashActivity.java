package com.example.saree360.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.saree360.R;

public class FlashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flashactivity);

        Thread Timer=new Thread(){
            public void run(){
                try {
                    sleep(3000);
                    Intent intent=new Intent(FlashActivity.this,SigninActivity.class);
                    startActivity(intent);
                }
                catch(InterruptedException e){
                    e.printStackTrace();
                }
                finally {
                    finish();
                }
            }
        };
        Timer.start();
    }
}
