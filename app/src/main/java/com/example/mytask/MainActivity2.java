package com.example.mytask;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {

    private TextView pageTitle,subTitle,endLine;
    Button  addButton;
    DatabaseReference reference;
    FirebaseUser user;
    RecyclerView resView;
    ArrayList<Mytask> list;
    taskAdapter taskAdapter;
    String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ActionBar actionBar= getSupportActionBar();
        actionBar.hide();
        pageTitle=(TextView)findViewById(R.id.pageTitle);
        subTitle=(TextView) findViewById(R.id.subTitle);
        endLine=(TextView) findViewById(R.id.endLine);
        addButton=(Button) findViewById(R.id.buttonAdd);
        resView=(RecyclerView) findViewById(R.id.resId);
        user= FirebaseAuth.getInstance().getCurrentUser();
        uid=user.getUid();

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),Addtask.class);
                startActivity(intent);
                finish();
            }
        });
        resView.setLayoutManager(new LinearLayoutManager(this));
        list= new ArrayList<Mytask>();
        reference= FirebaseDatabase.getInstance().getReference("Mytask").child(uid);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Mytask p = dataSnapshot1.getValue(Mytask.class);
                    list.add(p);
                }
                taskAdapter = new taskAdapter(MainActivity2.this, list);
                resView.setAdapter(taskAdapter);
                taskAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(),"No Data", Toast.LENGTH_SHORT).show();
            }
        });
    }
}