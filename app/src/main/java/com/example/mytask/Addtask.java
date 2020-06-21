package com.example.mytask;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Addtask extends AppCompatActivity {
    TextView addTitle,addDes,addtarget;
    EditText titleDes,desEdit,timeEdit;
    Button createTask, cancelTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addtask);
        ActionBar actionBar= getSupportActionBar();
        actionBar.hide();
        addTitle=(TextView) findViewById(R.id.titleText);
        titleDes=(EditText) findViewById(R.id.desT);
        addDes=(TextView) findViewById(R.id.desDes);
        desEdit=(EditText) findViewById(R.id.desEdit);
        addtarget=(TextView) findViewById(R.id.timeline);
        timeEdit=(EditText) findViewById(R.id.timeEdit);
        createTask=(Button) findViewById(R.id.create);
        cancelTask=(Button) findViewById(R.id.cancel);

        createTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
