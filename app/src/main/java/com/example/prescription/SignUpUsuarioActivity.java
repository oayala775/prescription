package com.example.prescription;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;


public class SignUpUsuarioActivity extends AppCompatActivity {
    public EditText nombre_p, apellido_p, telefono_p, nss_p, curp_p, fecha_nacimiento_p, domicilio_p, ciudad_p, colonia_p, nombreUsuario_p, contrasena_p, confirmarContrasena_p;
    public Button guardar;
    boolean contrasenaValida = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_signup_usuario);
        nombre_p = (EditText) findViewById(R.id.paciente_nombre);
        apellido_p = (EditText) findViewById(R.id.paciente_apellido);
        telefono_p = (EditText) findViewById(R.id.paciente_telefono);
        nss_p = (EditText) findViewById(R.id.paciente_nss);
        curp_p = (EditText) findViewById(R.id.paciente_curp);
        //fecha_nacimiento = findViewById(R.id.paciente_fechaNacimiento);
        domicilio_p = (EditText) findViewById(R.id.paciente_domicilio);
        colonia_p = (EditText) findViewById(R.id.paciente_colonia);
        ciudad_p = (EditText) findViewById(R.id.paciente_ciudad);
        nombreUsuario_p = (EditText) findViewById(R.id.paciente_nombre_usuario);
        contrasena_p = (EditText) findViewById(R.id.paciente_password);
        confirmarContrasena_p = (EditText) findViewById(R.id.paciente_confirm_password);
        guardar = (Button) findViewById(R.id.paciente_guardar);

        guardar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                String nombre = nombre_p.getText().toString();
                String apellido = apellido_p.getText().toString();
                String telefono = telefono_p.getText().toString();
                String nss = nss_p.getText().toString();
                String curp = curp_p.getText().toString();
                String domicilio = domicilio_p.getText().toString();
                String colonia = colonia_p.getText().toString();
                String ciudad = ciudad_p.getText().toString();
                String nombreUsuario = nombreUsuario_p.getText().toString();
                String contrasena = contrasena_p.getText().toString();
                String confirmarContrasena = confirmarContrasena_p.getText().toString();

                //validar contraseña y cedula
                contrasenaValida = contrasena.equals(confirmarContrasena);

                if (contrasenaValida) {
                    DB db = new DB(getApplicationContext(), null, null, 1);
                    String mensaje = db.guardar(nombre, apellido, telefono, nss, curp, domicilio, ciudad, colonia, nombreUsuario, contrasena);
                    Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SignUpUsuarioActivity.this, LoginActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Las contraseñas NO coinciden", Toast.LENGTH_SHORT).show();
                    contrasena_p.setText("");
                    confirmarContrasena_p.setText("");
                }
            }
        });
    }
}
