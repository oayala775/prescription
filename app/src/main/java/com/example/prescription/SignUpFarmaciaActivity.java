package com.example.prescription;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;


public class SignUpFarmaciaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_signup_farmacia);
    }

    public void goToLogin(View view){
        Intent intent = new Intent(SignUpFarmaciaActivity.this, LoginActivity.class);
        startActivity(intent);
    }
}
