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
    public EditText nombreUsuarioET, contrasenaET;
    String tablaenDB = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        logInButton = findViewById(R.id.buttonLogIn);
        registerButton = findViewById(R.id.buttonRegister);
        nombreUsuarioET = findViewById(R.id.user);
        contrasenaET = findViewById(R.id.password);

        String nombreUsuario = nombreUsuarioET.getText().toString();
        String contrasena = contrasenaET.getText().toString();

        logInButton.setOnClickListener(v -> {
            DB db = new DB(getApplicationContext(), null, null, 1);
            tablaenDB = db.usuarioExistente(nombreUsuario);
            Toast.makeText(getApplicationContext(), tablaenDB, Toast.LENGTH_SHORT).show();
//            System.out.println(tablaenDB);
//            if(contrasena.equals(db.login(nombreUsuario))){
//                Toast.makeText(getApplicationContext(), "contraseña correcta", Toast.LENGTH_SHORT).show();
//            }else{
//                Toast.makeText(getApplicationContext(), "contraseña incorrecta", Toast.LENGTH_SHORT).show();
//            }

//            if(tablaenDB.equals("doctores")){
//                String[] contrasenaYTabla = db.login(nombreUsuario);
//                Intent intent = new Intent(LoginActivity.this, HomeUserActivity.class);
//                startActivity(intent);
//            }else{
//                Toast.makeText(getApplicationContext(), "Usuario NO valido", Toast.LENGTH_SHORT).show();
//                nombreUsuarioET.setText("");
//            }

        });
        registerButton.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
            startActivity(intent);
        });
    }
    

}
