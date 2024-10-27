package com.example.prescription;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


public class PerfilDoctorActivity extends AppCompatActivity {
    public EditText nombre, apellido, telefono, nss, curp, fecha_nacimiento, domicilio, ciudad, colonia;
    public TextView codigo, cedula;
    private ArrayList<String> informacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_perfil_doctor);

        //Obtenemos informacion del intent
        Intent intentInformacion = getIntent();
        informacion = intentInformacion.getStringArrayListExtra("datos_doctor");


        //String nombre_parametro = "Juan";
        //String apellido_parametro = "Pérez";
        //long telefono_parametro = 9999999999L;
        //long nss_parametro = 99999999999L;
        //String curp_parametro = "OPDE129867RJCURXA9";
        //LocalDate fecha_nacimiento_parametro = LocalDate.of(1999, 1, 1);
        //String domicilio_parametro = "Calle 1 numero #1";
        //String colonia_parametro = "Colonia 1";
        //String ciudad_parametro = "Ciudad";
        //long codigo_parametro = 21212121212L;
        //long cedula_parametro = 88888888L;

        String nombre_parametro = informacion.get(1);
        String apellido_parametro = informacion.get(2);
        String telefono_parametro = informacion.get(3);
        String nss_parametro = informacion.get(4);
        String curp_parametro = informacion.get(5);
        LocalDate fecha_nacimiento_parametro = LocalDate.of(1999, 1, 1);
        String domicilio_parametro = informacion.get(6);
        String ciudad_parametro = informacion.get(7);
        String colonia_parametro = informacion.get(8);
        String cedula_parametro = informacion.get(9);
        String codigo_parametro = informacion.get(0);

        codigo = findViewById(R.id.doctor_codigo);
        nombre = findViewById(R.id.doctor_nombre);
        apellido = findViewById(R.id.doctor_apellido);
        telefono = findViewById(R.id.doctor_telefono);
        nss = findViewById(R.id.doctor_nss);
        curp = findViewById(R.id.doctor_curp);
        fecha_nacimiento = findViewById(R.id.doctor_nacimiento);
        domicilio = findViewById(R.id.doctor_domicilio);
        colonia = findViewById(R.id.doctor_colonia);
        ciudad = findViewById(R.id.doctor_ciudad);
        cedula = findViewById(R.id.doctor_cedula);

        codigo.setText(String.valueOf(codigo_parametro));
        nombre.setText(nombre_parametro);
        apellido.setText(apellido_parametro);
        telefono.setText(String.valueOf(telefono_parametro));
        nss.setText(String.valueOf(nss_parametro));
        curp.setText(curp_parametro);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        fecha_nacimiento.setText(fecha_nacimiento_parametro.format(formatter));
        domicilio.setText(domicilio_parametro);
        colonia.setText(colonia_parametro);
        ciudad.setText(ciudad_parametro);
        cedula.setText(String.valueOf(cedula_parametro));

        DB db = new DB(getApplicationContext(), null, null, 1);
        String userName = informacion.get(9);

        // Buttons
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
}
