package com.example.movies_project.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.movies_project.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class RegisterActivity extends AppCompatActivity {

    private EditText nameEdt,passwordEdt,emailEdt,adressEdt,phoneEdt;
    private Button signinBtn;
    private ProgressBar progressBar;
    private TextView Already,loginNow;

    FirebaseAuth mAuth;

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent = new Intent(RegisterActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth=FirebaseAuth.getInstance();

        nameEdt=findViewById(R.id.editTextName);
        passwordEdt=findViewById(R.id.editTextPasswordSign);
        emailEdt=findViewById(R.id.editTextEmailAddress);
        adressEdt=findViewById(R.id.editTextDeliveryAddress);
        phoneEdt=findViewById(R.id.editTextPhoneNumber);
        progressBar=findViewById(R.id.progressBarSignIn);

        signinBtn=findViewById(R.id.signinBtn);

        Already=findViewById(R.id.already);
        loginNow=findViewById(R.id.loginNow);

        Already.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        loginNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });


        signinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                String name,email,password,date,adress,phone;

                name=String.valueOf(nameEdt.getText());
                email=String.valueOf(emailEdt.getText());
                password=String.valueOf(passwordEdt.getText());
                adress=String.valueOf(adressEdt.getText());
                phone=String.valueOf(phoneEdt.getText());

                if (TextUtils.isEmpty(name)){
                    Toast.makeText(RegisterActivity.this, "Enter name", Toast.LENGTH_SHORT).show();return;
                }if (TextUtils.isEmpty(email)){
                    Toast.makeText(RegisterActivity.this, "Enter email", Toast.LENGTH_SHORT).show();return;
                }if (TextUtils.isEmpty(password)){
                    Toast.makeText(RegisterActivity.this, "Enter password", Toast.LENGTH_SHORT).show();return;
                }if (TextUtils.isEmpty(phone)){
                    Toast.makeText(RegisterActivity.this, "Enter Phone Number", Toast.LENGTH_SHORT).show();return;
                }if (TextUtils.isEmpty(adress)){
                    Toast.makeText(RegisterActivity.this, "Enter Delivery Address", Toast.LENGTH_SHORT).show();return;
                }


                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressBar.setVisibility(View.GONE);
                                if (task.isSuccessful()) {
                                    Toast.makeText(RegisterActivity.this, "Account Created.",
                                            Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(RegisterActivity.this,MainActivity.class);
                                    startActivity(intent);
                                    finish();

                                } else {
                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(RegisterActivity.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

            }
        });
    }
}