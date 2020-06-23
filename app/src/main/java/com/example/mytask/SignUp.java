package com.example.mytask;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class SignUp extends AppCompatActivity implements View.OnClickListener {

    private EditText signupEmail, signupPassword;
    private TextView signupText;
    private Button signupButton;
    private FirebaseAuth mAuth ;
    private ProgressBar progressbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        this.setTitle("Sign up Activity");
        signupEmail =(EditText) findViewById(R.id.email);
        signupPassword=(EditText) findViewById(R.id.password);
        signupText=(TextView) findViewById(R.id.textViewup);
        signupButton=(Button) findViewById(R.id.buttonup);
        signupText.setOnClickListener(this);
        signupButton.setOnClickListener(this);
        mAuth = FirebaseAuth.getInstance();
        progressbar=(ProgressBar) findViewById(R.id.progressBar);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonup:
                userRegister();
                break;
            case  R.id.textViewup:
                Intent intent=new Intent(getApplicationContext(),MainActivity2.class);
                startActivity(intent);
                break;
        }
    }

    private void userRegister() {
        String email=signupEmail.getText().toString().trim();
        String password=signupPassword.getText().toString().trim();

        if(email.isEmpty())
        {
            signupEmail.setError("Enter an email address");
            signupEmail.requestFocus();
            return;
        }

        if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            signupEmail.setError("Enter a valid email address");
            signupEmail.requestFocus();
            return;
        }

        //checking the validity of the password
        if(email.isEmpty())
        {
            signupPassword.setError("Enter a password");
            signupPassword.requestFocus();
            return;
        }
        if(password.length()<6){
            signupPassword.setError("Minimum password length should be 6");
            signupPassword.requestFocus();
            return;
        }
        progressbar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressbar.setVisibility(View.INVISIBLE);
                if (task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(),"Sign Up is Successful", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                } else {
                    if(task.getException()instanceof FirebaseAuthUserCollisionException){
                        Toast.makeText(getApplicationContext(),"User already exists",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"Error"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


    }
}