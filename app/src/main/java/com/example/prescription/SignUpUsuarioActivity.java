package com.example.prescription;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;


public class SignUpUsuarioActivity extends AppCompatActivity {

    public ImageView btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_signup_usuario);


//        btnBack = (ImageView) findViewById(R.id.flecha_izq);
//        btnBack.setOnClickListener(v -> {
//            Intent intent = new Intent(SignUpUsuarioActivity.this, SignUpActivity.class);
//            startActivity(intent);
//        });
    }

    //Go to Login
    public void  goToLogin(View view){
        Intent intent = new Intent(SignUpUsuarioActivity.this, LoginActivity.class);
        startActivity(intent);
    }


}
