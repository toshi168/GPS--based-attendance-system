package com.example.dell1.gps_attendance;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminActivity extends AppCompatActivity  {
    Button btnAddStudent;
    Button btnDelStudent;
    Button btnAddTeacher;
    Button btnDelTeacher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_succ);
        btnAddStudent = findViewById(R.id.btnAddStudent);
        btnDelStudent = findViewById(R.id.btnDelStudent);
        btnAddTeacher = findViewById(R.id.btnAddTeacher);
        btnDelTeacher = findViewById(R.id.btnDelTeacher);

        btnAddStudent.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminActivity.this, AddStudentActivity.class);
                startActivity(intent);
            }
        });
        /*btnDelStudent.setOnClickListener(this);
        btnAddTeacher.setOnClickListener(this);
        btnDelTeacher.setOnClickListener(this);
*/

    }

    //@Override
    /*public void onClick(View v) {
        /*switch (v.getId()) {

            case R.id.btnAddStudent:
                Intent intent1 = new Intent(AdminActivity.this,AddStudentActivity.class);
                startActivity(intent1);
                break;

            case R.id.btnDelStudent:
                Intent intent2 = new Intent(AdminActivity.this,DelStudentActivity.class);
                startActivity(intent2);
                break;

            case R.id.btnAddTeacher:
                Intent intent3 = new Intent(AdminActivity.this,AddTeacherActivity.class);
                startActivity(intent3);
                break;

            case R.id.btnDelTeacher:
                Intent intent4 = new Intent(AdminActivity.this,DelTeacherActivity.class);
                startActivity(intent4);
                break;


            default:
                break;
        }*/
        /*Intent intent1 = new Intent(AdminActivity.this,AddStudentActivity.class);
        startActivity(intent1);


    }*/
}
