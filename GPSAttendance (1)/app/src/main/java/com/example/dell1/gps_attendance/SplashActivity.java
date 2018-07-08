package com.example.dell1.gps_attendance;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {
    String enrollStored = "", passwordStored = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                SharedPreferences pref = getSharedPreferences("loginData", MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                //editor.clear();  //for bebugging
                //editor.commit(); //for bebugging
                enrollStored = pref.getString("enrollment", null);
                passwordStored = pref.getString("password", null);

                if(enrollStored == null){
                    Intent in = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(in);
                }
                else if(enrollStored.equals("admin")){
                    Intent in = new Intent(getApplicationContext(), AdminActivity.class);
                    startActivity(in);
                }
                else{
                    Intent in = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(in);
                }
                /*else if(enrollStored.length()==12){
                    Intent in = new Intent(getApplicationContext(), StudentActivity.class);
                    startActivity(in);
                }*/

                SplashActivity.this.finish();
            }
        }, 3000);
    }

}
