package com.example.prescription;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;


public class SignUpActivity extends AppCompatActivity {

    public Button signUpUser;
    public Button signUpDoc;
    public Button signUpPharm;
    public Button signIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_signup);

        signUpUser = findViewById(R.id.buttonUser);
        signUpDoc = findViewById(R.id.buttonDoctor);
        signUpPharm = findViewById(R.id.buttonPharm);
        signIn = findViewById(R.id.buttonSignIn);

        signUpUser.setOnClickListener(v -> {
            Intent intent = new Intent(SignUpActivity.this, SignUpUsuarioActivity.class);
            startActivity(intent);
        });
        signUpDoc.setOnClickListener(v -> {
            Intent intent = new Intent(SignUpActivity.this, SignUpUsuarioActivity.class);
            startActivity(intent);
        });
        signUpPharm.setOnClickListener(v -> {
            Intent intent = new Intent(SignUpActivity.this, SignUpUsuarioActivity.class);
            startActivity(intent);
        });
        signIn.setOnClickListener(v -> {
            Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
            startActivity(intent);
        });
    }

}
