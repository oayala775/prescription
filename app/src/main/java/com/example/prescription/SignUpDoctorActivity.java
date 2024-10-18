package com.example.prescription;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class SignUpDoctorActivity extends AppCompatActivity {

    public EditText nombre_p, apellido_p, telefono_p, nss_p, curp_p, fecha_nacimiento_p, domicilio_p, ciudad_p, colonia_p, nombreUsuario_p, contrasena_p, confirmarContrasena_p;
    public TextView cedula_p;
    public Button guardar;
    String[] cedulasValidas = {"111111", "222222", "333333"};
    boolean contrasenaValida = false;
    boolean cedulaValida = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_signup_doctor);

        nombre_p = (EditText) findViewById(R.id.doctor_nombre);
        apellido_p = (EditText) findViewById(R.id.doctor_apellido);
        telefono_p = (EditText) findViewById(R.id.doctor_telefono);
        nss_p = (EditText) findViewById(R.id.doctor_nss);
        curp_p = (EditText) findViewById(R.id.doctor_curp);
        //fecha_nacimiento = findViewById(R.id.doctor_fechaNacimiento);
        domicilio_p = (EditText) findViewById(R.id.doctor_domicilio);
        colonia_p = (EditText) findViewById(R.id.doctor_colonia);
        ciudad_p = (EditText) findViewById(R.id.doctor_ciudad);
        cedula_p = (EditText) findViewById(R.id.doctor_cedula);
        nombreUsuario_p = (EditText) findViewById(R.id.doctor_nombre_usuario);
        contrasena_p = (EditText) findViewById(R.id.doctor_password);
        confirmarContrasena_p = (EditText) findViewById(R.id.doctor_confirm_password);
        guardar = (Button) findViewById(R.id.doctor_guardar);

        guardar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                String nombre = nombre_p.getText().toString();
                String apellido = apellido_p.getText().toString();
                String telefono = telefono_p.getText().toString();
                String nss = nss_p.getText().toString();
                String curp = curp_p.getText().toString();
                String domicilio = domicilio_p.getText().toString();
                String colonia = colonia_p.getText().toString();
                String ciudad = ciudad_p.getText().toString();
                String cedula = cedula_p.getText().toString();
                String nombreUsuario = nombreUsuario_p.getText().toString();
                String contrasena = contrasena_p.getText().toString();
                String confirmarContrasena = confirmarContrasena_p.getText().toString();

                //validar contraseña y cedula
                contrasenaValida = contrasena.equals(confirmarContrasena);
                // Validar cédula
                cedulaValida = false; // Reiniciar la variable antes de la validación
                for (String cedulaValidaArray : cedulasValidas) {
                    if (cedula.equals(cedulaValidaArray)) {
                        cedulaValida = true; // Cédula válida
                        break; // Salir del bucle si se encuentra una coincidencia
                    }
                }

                if(contrasenaValida && cedulaValida){
                    DB db = new DB(getApplicationContext(), null, null, 1);
                    String mensaje = db.guardar(nombre,apellido,telefono, nss, curp, domicilio, ciudad, colonia, cedula, nombreUsuario, contrasena);
                    Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SignUpDoctorActivity.this, LoginActivity.class);
                    startActivity(intent);
                }else{
                    if(!contrasenaValida && cedulaValida){
                        Toast.makeText(getApplicationContext(), "Las contraseñas NO coinciden", Toast.LENGTH_SHORT).show();
                        contrasena_p.setText("");
                        confirmarContrasena_p.setText("");
                    } else if(!cedulaValida && contrasenaValida) {
                        Toast.makeText(getApplicationContext(), "Cedula profesional NO valida", Toast.LENGTH_SHORT).show();
                        cedula_p.setText("");
                    }else{
                        Toast.makeText(getApplicationContext(), "Las contraseñas NO coinciden y la cedula no es valida", Toast.LENGTH_SHORT).show();
                        contrasena_p.setText("");
                        confirmarContrasena_p.setText("");
                        cedula_p.setText("");
                    }
                }
            }
        });

    }


    public void goToLogin(View view){
        Intent intent = new Intent(SignUpDoctorActivity.this, LoginActivity.class);
        startActivity(intent);
    }
}
