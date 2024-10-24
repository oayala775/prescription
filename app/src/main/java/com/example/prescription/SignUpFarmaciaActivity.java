package com.example.prescription;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class SignUpFarmaciaActivity extends AppCompatActivity {
    public EditText nombre_p, telefono_p, fecha_nacimiento_p, domicilio_p, ciudad_p, colonia_p, nombreUsuario_p, contrasena_p, confirmarContrasena_p;
    public Button guardar;
    boolean contrasenaValida = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_signup_farmacia);

        nombre_p = (EditText) findViewById(R.id.farmacia_nombre);
        telefono_p = (EditText) findViewById(R.id.farmacia_telefono);
        //fecha_nacimiento = findViewById(R.id.farmacia_fechaNacimiento);
        domicilio_p = (EditText) findViewById(R.id.farmacia_domicilio);
        colonia_p = (EditText) findViewById(R.id.farmacia_colonia);
        ciudad_p = (EditText) findViewById(R.id.farmacia_ciudad);
        nombreUsuario_p = (EditText) findViewById(R.id.farmacia_nombre_usuario);
        contrasena_p = (EditText) findViewById(R.id.farmacia_password);
        confirmarContrasena_p = (EditText) findViewById(R.id.farmacia_confirm_password);
        guardar = (Button) findViewById(R.id.farmacia_guardar);

        guardar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                String nombre = nombre_p.getText().toString();
                String telefono = telefono_p.getText().toString();
                String domicilio = domicilio_p.getText().toString();
                String colonia = colonia_p.getText().toString();
                String ciudad = ciudad_p.getText().toString();
                String nombreUsuario = nombreUsuario_p.getText().toString();
                String contrasena = contrasena_p.getText().toString();
                String confirmarContrasena = confirmarContrasena_p.getText().toString();

                //validar contraseña
                contrasenaValida = contrasena.equals(confirmarContrasena);

                if (contrasenaValida) {
                    DB db = new DB(getApplicationContext(), null, null, 1);
<<<<<<< HEAD
                    if(db.validarUsuario(nombreUsuario)){
                        String mensaje = db.guardar(nombre, telefono, domicilio, ciudad, colonia, nombreUsuario, contrasena);
                        Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(SignUpFarmaciaActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(getApplicationContext(), "Nombre de usuario no disponible", Toast.LENGTH_SHORT).show();
                        nombreUsuario_p.setText("");
                    }
=======
                    String mensaje = db.guardar(nombre, telefono, domicilio, ciudad, colonia, nombreUsuario, contrasena);
                    Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SignUpFarmaciaActivity.this, LoginActivity.class);
                    startActivity(intent);
>>>>>>> 481f7e1b41e3444308041fc88b475d0ac06b85fc
                } else {
                    Toast.makeText(getApplicationContext(), "Las contraseñas NO coinciden", Toast.LENGTH_SHORT).show();
                    contrasena_p.setText("");
                    confirmarContrasena_p.setText("");
                }
            }
        });
    }
}
