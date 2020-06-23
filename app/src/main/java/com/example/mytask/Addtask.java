package com.example.mytask;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Addtask extends AppCompatActivity {
    TextView addTitle,addDes,addtarget;
    EditText titleDes,desEdit,timeEdit;
    Button createTask, cancelTask;
    DatabaseReference reference;
    FirebaseUser user;
    String uid;

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
        user= FirebaseAuth.getInstance().getCurrentUser();
        uid=user.getUid();
        reference= FirebaseDatabase.getInstance().getReference("Mytask");

        createTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              saveData();
            }

            private void saveData() {
              String title= titleDes.getText().toString().trim();
              String description= desEdit.getText().toString().trim();
              String target= timeEdit.getText().toString().trim();
              String keyTask= reference.push().getKey();
              Mytask mytask =new Mytask(title,description,target,keyTask,uid);
              reference.child(uid).child(keyTask).setValue(mytask);
                Toast.makeText(getApplicationContext(),"Database Saved", Toast.LENGTH_SHORT).show();
                Intent a= new Intent(getApplicationContext(),MainActivity2.class);
                startActivity(a);
            }
        });

        cancelTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent x=new Intent(getApplicationContext(),MainActivity2.class);
                startActivity(x);
            }
        });
    }
}
