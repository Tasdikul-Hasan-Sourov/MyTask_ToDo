package com.example.mytask;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EditTask extends AppCompatActivity {
    EditText titleDes,desEdit,timeEdit;
    Button saveTask, deleteTask;
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_task);
        ActionBar actionBar=getSupportActionBar();
        actionBar.hide();

        titleDes=(EditText) findViewById(R.id.desT);
        desEdit=(EditText) findViewById(R.id.desEdit);
        timeEdit=(EditText) findViewById(R.id.timeEdit);

        saveTask=(Button) findViewById(R.id.save);
        deleteTask=(Button) findViewById(R.id.delete);

        titleDes.setText(getIntent().getStringExtra("taskTitle"));
        desEdit.setText(getIntent().getStringExtra("taskDes"));
        timeEdit.setText(getIntent().getStringExtra("taskDate"));
        final String keykeyTask=getIntent().getStringExtra("keyTask");
        ref= FirebaseDatabase.getInstance().getReference().child("Mytask").child(keykeyTask);

        deleteTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ref.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful ()){
                            Intent s= new Intent(getApplicationContext(),MainActivity.class);
                            startActivity(s);
                        }
                        else{
                            Toast.makeText(getApplicationContext(),"Not deleted",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        saveTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               ref.addValueEventListener(new ValueEventListener() {
                   @Override
                   public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                       dataSnapshot.getRef().child("taskTitle").setValue(titleDes.getText().toString());
                       dataSnapshot.getRef().child("taskDes").setValue(desEdit.getText().toString());
                       dataSnapshot.getRef().child("taskDate").setValue(timeEdit.getText().toString());
                       dataSnapshot.getRef().child("keyTask").setValue(keykeyTask);

                       Intent a=new Intent(getApplicationContext(),MainActivity.class);
                       startActivity(a);

                   }

                   @Override
                   public void onCancelled(@NonNull DatabaseError error) {

                   }
               });
            }
        });
    }
}