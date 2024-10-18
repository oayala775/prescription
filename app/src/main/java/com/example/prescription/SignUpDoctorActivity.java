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

    public EditText nombre_p, apellido_p, telefono_p, nss_p, curp_p, fecha_nacimiento_p, domicilio_p, ciudad_p, colonia_p;
    public TextView cedula_p;
    public Button guardar;

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
        guardar = (Button) findViewById(R.id.doctor_guardar);

        guardar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                DB db = new DB(getApplicationContext(), null, null, 1);
                String nombre = nombre_p.getText().toString();
                String apellido = apellido_p.getText().toString();
                String telefono = telefono_p.getText().toString();
                String nss = nss_p.getText().toString();
                String curp = curp_p.getText().toString();
                String domicilio = domicilio_p.getText().toString();
                String colonia = colonia_p.getText().toString();
                String ciudad = ciudad_p.getText().toString();
                String cedula = cedula_p.getText().toString();
                String mensaje = db.guardar(nombre,apellido,telefono, nss, curp, domicilio, ciudad, colonia, cedula);
                Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_SHORT).show();
            }
        });

    }


    public void goToLogin(View view){
        Intent intent = new Intent(SignUpDoctorActivity.this, LoginActivity.class);
        startActivity(intent);
    }
}
