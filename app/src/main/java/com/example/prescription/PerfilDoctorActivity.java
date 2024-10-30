package com.example.prescription;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


public class PerfilDoctorActivity extends AppCompatActivity {
    public EditText nombre, apellido, telefono, nss, curp, fecha_nacimiento, domicilio, ciudad, colonia, dia, mes, anio;
    public TextView cedula;
    private ArrayList<String> informacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_perfil_doctor);

        //Obtenemos informacion del intent
        Intent intentInformacion = getIntent();
        informacion = intentInformacion.getStringArrayListExtra("datos_doctor");

        String nombre_parametro = informacion.get(1);
        String apellido_parametro = informacion.get(2);
        String telefono_parametro = informacion.get(3);
        String nss_parametro = informacion.get(4);
        String curp_parametro = informacion.get(5);
        String domicilio_parametro = informacion.get(6);
        String ciudad_parametro = informacion.get(7);
        String colonia_parametro = informacion.get(8);
        String cedula_parametro = informacion.get(9);
        String dia_parametro = informacion.get(13);
        String mes_parametro = informacion.get(14);
        String anio_parametro = informacion.get(15);

        nombre = findViewById(R.id.doctor_nombre);
        apellido = findViewById(R.id.doctor_apellido);
        telefono = findViewById(R.id.doctor_telefono);
        nss = findViewById(R.id.doctor_nss);
        curp = findViewById(R.id.doctor_curp);
        domicilio = findViewById(R.id.doctor_domicilio);
        colonia = findViewById(R.id.doctor_colonia);
        ciudad = findViewById(R.id.doctor_ciudad);
        cedula = findViewById(R.id.doctor_cedula);
        dia = findViewById(R.id.doctor_day);
        mes = findViewById(R.id.doctor_month);
        anio = findViewById(R.id.doctor_year);

        nombre.setText(nombre_parametro);
        apellido.setText(apellido_parametro);
        telefono.setText(String.valueOf(telefono_parametro));
        nss.setText(String.valueOf(nss_parametro));
        curp.setText(curp_parametro);
        domicilio.setText(domicilio_parametro);
        colonia.setText(colonia_parametro);
        ciudad.setText(ciudad_parametro);
        cedula.setText(String.valueOf(cedula_parametro));
        dia.setText(dia_parametro);
        mes.setText(mes_parametro);
        anio.setText(anio_parametro);

        nombre.setEnabled(false);
        apellido.setEnabled(false);
        telefono.setEnabled(false);
        nss.setEnabled(false);
        curp.setEnabled(false);
        domicilio.setEnabled(false);
        colonia.setEnabled(false);
        ciudad.setEnabled(false);
        cedula.setEnabled(false);
        dia.setEnabled(false);
        mes.setEnabled(false);
        anio.setEnabled(false);

        DB db = new DB(getApplicationContext(), null, null, 1);
        String userName = informacion.get(9);

        Button editButton = findViewById(R.id.buttonEdit);
        editButton.setOnClickListener(v->{
            edit(telefono,domicilio,colonia,ciudad,editButton);
        });


        // Buttons
        ImageView exitButton = findViewById(R.id.exitButton);
        exitButton.setOnClickListener(v -> {
            Intent intent = new Intent(PerfilDoctorActivity.this, LoginActivity.class);
            startActivity(intent);
        });
        ImageView homeButton = findViewById(R.id.homeButton);
        homeButton.setOnClickListener(v -> {
            Intent intent = new Intent(PerfilDoctorActivity.this, HomeDoctorActivity.class);
            intent.putStringArrayListExtra("datos_doctor", db.obtenerDatosDoctor(userName));
            startActivity(intent);
        });

        ImageView perfilButton = findViewById(R.id.perfilButton);
        perfilButton.setOnClickListener(v -> {
            Intent intent = new Intent(PerfilDoctorActivity.this, PerfilDoctorActivity.class);
            intent.putStringArrayListExtra("datos_doctor", db.obtenerDatosDoctor(userName));
            startActivity(intent);
        });

    }

    private void edit(EditText telefono, EditText domicilio, EditText colonia, EditText ciudad, Button editButton){
        if (editButton.getText().toString().equals("Editar")){
            editButton.setText("Guardar");
            telefono.setEnabled(true);
            domicilio.setEnabled(true);
            colonia.setEnabled(true);
            ciudad.setEnabled(true);
        } else if(editButton.getText().toString().equals("Guardar")){
            editButton.setText("Editar");
            telefono.setEnabled(false);
            domicilio.setEnabled(false);
            colonia.setEnabled(false);
            ciudad.setEnabled(false);
            Toast.makeText(getApplicationContext(),"Editado correctamente", Toast.LENGTH_SHORT).show();
        }
    }
}
