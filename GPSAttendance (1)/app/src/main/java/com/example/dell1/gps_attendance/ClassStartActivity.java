package com.example.dell1.gps_attendance;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.kosalgeek.genasync12.AsyncResponse;
import com.kosalgeek.genasync12.PostResponseAsyncTask;

import java.util.HashMap;

public class ClassStartActivity extends AppCompatActivity {

    TextView textView1;
    int counter=0;
    String sem = "";
    String sid = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_start);

        textView1 = findViewById(R.id.textView1);
        sem = getIntent().getStringExtra("sem");
        sid = getIntent().getStringExtra("sid");

        HashMap<String, String> postData = new HashMap<>();
        postData.put("sem", sem);
        postData.put("sid", sid);

        final PostResponseAsyncTask task2 = new PostResponseAsyncTask(this,
                postData, new AsyncResponse() {
            @Override
            public void processFinish(String s) {
                Intent intent = new Intent(getApplicationContext(), TeacherActivity.class);
                intent.putExtra("osem",sem);
                intent.putExtra("osid",sid);
                startActivity(intent);
            }
        });

        new CountDownTimer(60000, 1000){
            public void onTick(long millisUntilFinished){
                textView1.setText("Time elapsed (in minutes): "+String.valueOf(counter));
                counter++;
            }
            public  void onFinish(){
                task2.execute("http://192.168.43.6/client/stopclass.php");
            }
        }.start();

    }

}
