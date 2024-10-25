package com.example.prescription;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;


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
            ArrayList<String> tuple = new ArrayList<>(2);

            userString = user.getText().toString();
            passwordString = password.getText().toString();

            DB db = new DB(getApplicationContext(), null, null, 1);
            if (db.usuarioExistente(userString)){
                tuple = db.contrasenaExistente(userString);
                if(passwordString.equals(tuple.get(0))){
                    if(tuple.get(1).equals("doctor")){
                        Intent intent = new Intent(LoginActivity.this, PerfilDoctorActivity.class);
                        intent.putStringArrayListExtra("datos_doctor", db.obtenerDatosDoctor(userString));
                        startActivity(intent);
                    } else if(tuple.get(1).equals("paciente")){
                        Intent intent = new Intent(LoginActivity.this, PerfilUserActivity.class);
                        intent.putStringArrayListExtra("datos_paciente", db.obtenerDatosPaciente(userString));
                        startActivity(intent);
                    } else if(tuple.get(1).equals("farmacia")){
                        Intent intent = new Intent(LoginActivity.this, PerfilFarmaciaActivity.class);
                        intent.putStringArrayListExtra("datos_farmacia", db.obtenerDatosFarmacia(userString));
                        startActivity(intent);
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "Contraseña incorrecta", Toast.LENGTH_SHORT).show();
                    password.setText("");
                }
            }
            else {
                Toast.makeText(LoginActivity.this, "Usuario no encontrado", Toast.LENGTH_SHORT).show();
                user.setText("");
            }

        });
        registerButton.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
            startActivity(intent);
        });
    }


}
