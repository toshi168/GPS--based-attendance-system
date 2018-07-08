package com.example.dell1.gps_attendance;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.kosalgeek.genasync12.AsyncResponse;
import com.kosalgeek.genasync12.PostResponseAsyncTask;

import java.util.HashMap;


public class StudentActivity extends AppCompatActivity implements View.OnClickListener {

    String enrollment, name, sem, branch, sid;
    TextView receive;
    Button btnMarkAttendance;
    String data[];
    final String TAG = "StudentActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        btnMarkAttendance = findViewById(R.id.btnMarkAttendance);

        enrollment = getIntent().getStringExtra("enrollment");

        receive = (TextView)findViewById(R.id.textView1);

        HashMap<String, String> studentData = new HashMap<>();
        studentData.put("txtEnrollmentID", enrollment);

        PostResponseAsyncTask studentTask = new PostResponseAsyncTask(this,
                studentData, new AsyncResponse() {
            @Override
            public void processFinish(String s) {
                Log.d(TAG, s);
                data = s.split(",");
                String qname = data[0].split(":")[1];
                name = qname.substring(1, qname.length()-1);
                String qsem = data[1].split(":")[1];;
                sem = qsem.substring(1, qsem.length()-1);
                String qbranch = data[2].split(":")[1];
                branch = qbranch.substring(1, qbranch.length()-2);
            }
        });

        studentTask.execute("http://192.168.43.6/client/stud.php");

        HashMap<String, String> subjectData = new HashMap<>();
        subjectData.put("sem", sem);
        subjectData.put("branch", branch);
        PostResponseAsyncTask subjectTask = new PostResponseAsyncTask(this,
                subjectData, new AsyncResponse() {
            @Override
            public void processFinish(String s) {
                Log.d(TAG, s);
                String msg = "Welcome, "+name+"\n Ongoing class - "+s;
                sid = s.split(":")[0];
                receive.setText(msg);
                    }
        });

        subjectTask.execute("http://192.168.43.6/client/fetchsubject.php");


        btnMarkAttendance.setOnClickListener(this);
    }

    @Override
   public void onClick(View view) {


        Intent intent = new Intent(getApplicationContext(), LocationActivity.class);
        intent.putExtra("sem",sem);
        intent.putExtra("dept",branch);
        intent.putExtra("eid", enrollment);
        intent.putExtra("sid", sid);
        startActivity(intent);



    }

}
