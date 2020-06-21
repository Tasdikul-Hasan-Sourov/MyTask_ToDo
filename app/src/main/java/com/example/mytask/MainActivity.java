package com.example.mytask;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView pageTitle,subTitle,endLine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pageTitle=(TextView)findViewById(R.id.pageTitle);
        subTitle=(TextView) findViewById(R.id.subTitle);
        endLine=(TextView) findViewById(R.id.endLine);
    }
}