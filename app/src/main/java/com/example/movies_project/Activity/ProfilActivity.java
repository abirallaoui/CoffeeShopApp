package com.example.movies_project.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.movies_project.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfilActivity extends AppCompatActivity {

    private TextView email;
    private FirebaseAuth auth;
    private FirebaseUser user;
    private Button sendCommentBtn;

    private ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        email = findViewById(R.id.email);
        sendCommentBtn = findViewById(R.id.sendCommentBtn);
        back=findViewById(R.id.back);


        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        email.setText(user.getEmail());

        sendCommentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ProfilActivity.this, "Thanks for sending", Toast.LENGTH_SHORT).show();

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfilActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}