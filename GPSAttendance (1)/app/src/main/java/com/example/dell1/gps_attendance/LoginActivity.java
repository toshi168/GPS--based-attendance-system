package com.example.dell1.gps_attendance;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.kosalgeek.android.md5simply.MD5;
import com.kosalgeek.genasync12.AsyncResponse;
import com.kosalgeek.genasync12.ExceptionHandler;
import com.kosalgeek.genasync12.PostResponseAsyncTask;
import java.util.HashMap;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    EditText Enrollment, Password;
    Button LogIn ;
    final String TAG = "LoginActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Enrollment = (EditText) findViewById(R.id.etEnrollmentID);
        Password = (EditText) findViewById(R.id.etPassword);
        LogIn = (Button) findViewById(R.id.login_button);

        LogIn.setOnClickListener(this);


    }

    private boolean emptyValidate(EditText etEnrollmentID, EditText etPassword){
        String enrollmenID = etEnrollmentID.getText().toString();
        String password = etPassword.getText().toString();
        return (enrollmenID.isEmpty() && password.isEmpty());
    }

    String password = "";

    /*@Override
    public void processFinish(String output) {
        if(output.equals("admin success")){
            Intent intent = new Intent(LoginActivity.this, AdminActivity.class);
            startActivity(intent);
        }
        else if(output.equals("student success")){
            Intent intent1 = new Intent(LoginActivity.this, StudentActivity.class);
            startActivity(intent1);
        }

    }*/


    //@Override
    //public void onClick(View view) {
      //  HashMap postData = new HashMap();
        //postData.put("btnLogin","login");
        //postData.put("mobile","android");
        //postData.put("txtUsername", Enrollment.getText().toString());
        //postData.put("txtPassword", Password.getText().toString() );

        //PostResponseAsyncTask loginTask =
          //      new PostResponseAsyncTask(LoginActivity.this, postData,
            //            LoginActivity.this);
        //loginTask.execute("http://192.168.43.6/client/login.php");


    //}

    public void onClick(View v) {

        final String enrollment = Enrollment.getText().toString();
        password =  Password.getText().toString();

        HashMap<String, String> loginData = new HashMap<>();
        loginData.put("txtUsername", enrollment);
        loginData.put("txtPassword", password);

        PostResponseAsyncTask loginTask = new PostResponseAsyncTask(this,
                loginData, new AsyncResponse() {
            @Override
            public void processFinish(String s) {
                Log.d(TAG, s);
                if(s.contains("success")){
                    SharedPreferences pref = getSharedPreferences("loginData", MODE_PRIVATE);
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putString("enrollment", enrollment);
                    editor.putString("password", password);
                    editor.commit();
                    if(s.equals("admin success")) {
                        Intent intent = new Intent(getApplicationContext(), AdminActivity.class);
                        startActivity(intent);
                    }
                    else if(s.equals("student success")){
                        Intent intent = new Intent(getApplicationContext(), StudentActivity.class);
                        intent.putExtra("enrollment", enrollment);
                        startActivity(intent);
                    }
                    else if(s.equals("teacher success")){
                        Intent intent = new Intent(getApplicationContext(), TeacherActivity.class);
                        intent.putExtra("tid", enrollment);
                        startActivity(intent);
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(),
                            "Something went wrong. Cannot login.", Toast.LENGTH_LONG).show();
                }
            }
        });
        loginTask.setExceptionHandler(new ExceptionHandler() {
            @Override
            public void handleException(Exception e) {
                if(e != null && e.getMessage() != null){
                    Log.d(TAG, e.getMessage());
                }
            }
        });
        loginTask.execute("http://192.168.43.6/client/login.php");
    }
}