package com.example.dell1.gps_attendance;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.kosalgeek.genasync12.AsyncResponse;
import com.kosalgeek.genasync12.PostResponseAsyncTask;

import java.util.HashMap;

public class TeacherActivity extends AppCompatActivity implements View.OnClickListener {
    String tid;
    Button StartSession;
    Spinner spnSem, spnSub;
    final String TAG = "TeacherActivity";
    TextView txtClassStatus;
    String osem="",osid="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);
        tid= getIntent().getStringExtra("tid");
        txtClassStatus = findViewById(R.id.txtClassStatus);
        StartSession = findViewById(R.id.btnStartSession);
        spnSem = findViewById(R.id.spnSem);
        spnSub = findViewById(R.id.spnSubject);
        StartSession.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        final String sem = spnSem.getSelectedItem().toString();
        final String sid = spnSub.getSelectedItem().toString().split(":")[1];

        HashMap<String, String> postData = new HashMap<>();
        postData.put("sem", sem);
        postData.put("sid", sid);
        postData.put("tid", tid);

        PostResponseAsyncTask task1 = new PostResponseAsyncTask(this,
                postData, new AsyncResponse() {
            @Override
            public void processFinish(String s) {
                Intent intent = new Intent(TeacherActivity.this, ClassStartActivity.class);
                intent.putExtra("sem",sem);
                intent.putExtra("sid",sid);
                startActivity(intent);
            }
        });
        task1.execute("http://192.168.43.6/client/teacher.php");


    }

}
