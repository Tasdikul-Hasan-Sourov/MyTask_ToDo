package com.example.mytask;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText signinEmail, signinPassword;
    private TextView signinText;
    private Button signinButton;
    //private ProgressBar progressbare;
    private FirebaseAuth mAuth;
    FirebaseUser user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //ActionBar actionBar= getSupportActionBar();
        //actionBar.hide();
        this.setTitle("Sign in Activity");
        signinEmail =(EditText) findViewById(R.id.email);
        signinPassword=(EditText) findViewById(R.id.password);
        signinText=(TextView) findViewById(R.id.textViewin);
        signinButton=(Button) findViewById(R.id.buttonin);
        signinText.setOnClickListener(this);
        signinButton.setOnClickListener(this);
        //progressbare=(ProgressBar) findViewById(R.id.progressBar2);
        mAuth = FirebaseAuth.getInstance();
        user= FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // User is signed in
            Intent i = new Intent(MainActivity.this, MainActivity2.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
        } else {
            Intent l=new Intent(getApplicationContext(),MainActivity.class);
            startActivity(l);
        }


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonin:
                userLogin();
                break;
            case  R.id.textViewin:
                Intent intent=new Intent(getApplicationContext(),SignUp.class);
                startActivity(intent);
                break;
        }
    }

    private void userLogin() {
        String email=signinEmail.getText().toString().trim();
        String password=signinPassword.getText().toString().trim();

        if(email.isEmpty())
        {
            signinEmail.setError("Enter an email address");
            signinEmail.requestFocus();
            return;
        }

        if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            signinEmail.setError("Enter a valid email address");
            signinEmail.requestFocus();
            return;
        }

        //checking the validity of the password
        if(email.isEmpty())
        {
            signinPassword.setError("Enter a password");
            signinPassword.requestFocus();
            return;
        }
        if(password.length()<6){
            signinPassword.setError("Minimum password length should be 6");
            signinPassword.requestFocus();
            return;
        }
        //  progressbare.setVisibility(View.VISIBLE);
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                //  progressbare.setVisibility(View.INVISIBLE);
                if(task.isSuccessful()){
                    Intent intent=new Intent(getApplicationContext(),MainActivity2.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(getApplicationContext(),"Login Un Successful",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}