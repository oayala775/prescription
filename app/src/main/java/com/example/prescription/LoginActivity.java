package com.example.prescription;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;


public class LoginActivity extends AppCompatActivity{

    public Button registerButton;
    public Button logInButton;

    public EditText user, password;
    public String userString, passwordString;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        logInButton = findViewById(R.id.buttonLogIn);
        registerButton = findViewById(R.id.buttonRegister);

        // user, password
        user = findViewById(R.id.user);
        password = findViewById(R.id.password);

        logInButton.setOnClickListener(v -> {
            userString = user.getText().toString();
            passwordString = password.getText().toString();

            if(userString.equalsIgnoreCase("usuario") && passwordString.equalsIgnoreCase("usuario")){
                Intent intent = new Intent(LoginActivity.this, HomeUserActivity.class);
                startActivity(intent);
            }else{
                Toast.makeText(getApplicationContext(), "Cerdenciales invalidas",Toast.LENGTH_SHORT).show();

            }
        });
        registerButton.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
            startActivity(intent);
        });
    }
    

}
