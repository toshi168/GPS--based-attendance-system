package com.example.dell1.gps_attendance;

import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.kosalgeek.android.md5simply.MD5;
import com.kosalgeek.genasync12.AsyncResponse;
import com.kosalgeek.genasync12.PostResponseAsyncTask;

import java.util.HashMap;

public class AddStudentActivity extends AppCompatActivity implements View.OnClickListener {
    final String TAG = "RegisterActivity";
    EditText etEnrollmentID, etName, etBranch, etPhone ;
    Spinner spinSem;
    Button btnAddStudent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        etEnrollmentID = (EditText)findViewById(R.id.etAddEnrollID);
        etName = (EditText)findViewById(R.id.etAddStuName);
        spinSem = findViewById(R.id.spinSem);
        etBranch = (EditText)findViewById(R.id.etAddBranch);
        etPhone = (EditText)findViewById(R.id.phAddStu);
        btnAddStudent = (Button)findViewById(R.id.btnRegStudent);
        btnAddStudent.setOnClickListener(this);


    }
    @Override
    public void onClick(View v) {
        if(!emptyValidate(etEnrollmentID, etName, etBranch, etPhone, spinSem)){

            String enrollment = etEnrollmentID.getText().toString();
            String name = etName.getText().toString();
            String branch = etBranch.getText().toString();
            String phone = etPhone.getText().toString();
            String sem = spinSem.getSelectedItem().toString();

                HashMap<String, String> postData = new HashMap<>();
                postData.put("enroll", enrollment);
                postData.put("name", name);
                postData.put("sem", sem);
                postData.put("branch", branch);
                postData.put("phone", phone);

                PostResponseAsyncTask task1 = new PostResponseAsyncTask(this,
                        postData, new AsyncResponse() {
                    @Override
                    public void processFinish(String s) {
                        Log.d(TAG, s);
                        if(s.contains("ErrorInsert")){
                            Toast.makeText(AddStudentActivity.this,
                                    "Something went wrong. Data was not inserted.",
                                    Toast.LENGTH_LONG).show();
                        }else if(s.contains("Yayy!")) {
                            Toast.makeText(AddStudentActivity.this,
                                    "Student Added Successfully.",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                });
                task1.execute("http://192.168.43.6/client/register.php");


        } else{
            Toast.makeText(getApplicationContext(), "Fill out all the fields",
                    Toast.LENGTH_LONG).show();
        }
    }

    private boolean emptyValidate(EditText etEnrollmentID,EditText etName,EditText etBranch,EditText etPhone,Spinner spinSem){
        String enrollment = etEnrollmentID.getText().toString();
        String name = etName.getText().toString();
        String branch = etBranch.getText().toString();
        String phone = etPhone.getText().toString();
        String sem = spinSem.getSelectedItem().toString();
        return (enrollment.isEmpty() && name.isEmpty() && branch.isEmpty()&& phone.isEmpty() && sem.isEmpty());
    }

}