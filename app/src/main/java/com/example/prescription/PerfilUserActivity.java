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


public class PerfilUserActivity extends AppCompatActivity {
    public EditText nombre, apellido, telefono, nss, curp, fecha_nacimiento, domicilio, ciudad, colonia;
    public TextView codigo;

    private ImageView HomeButton;
    private ArrayList<String> informacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_perfil_usuario);

        //Obtenemos informacion del intent
        Intent intentInformacion = getIntent();
        informacion = intentInformacion.getStringArrayListExtra("datos_paciente");
        

        String nombre_parametro = informacion.get(1);
        String apellido_parametro = informacion.get(2);
        String telefono_parametro = informacion.get(3);
        String nss_parametro = informacion.get(4);
        String curp_parametro = informacion.get(5);
        LocalDate fecha_nacimiento_parametro = LocalDate.of(1999, 1, 1);
        String domicilio_parametro = informacion.get(6);
        String ciudad_parametro = informacion.get(7);
        String colonia_parametro = informacion.get(8);
        String codigo_parametro = informacion.get(0);

        codigo = findViewById(R.id.user_codigo);
        nombre = findViewById(R.id.user_nombre);
        apellido = findViewById(R.id.user_apellido);
        telefono = findViewById(R.id.user_telefono);
        nss = findViewById(R.id.user_nss);
        curp = findViewById(R.id.user_curp);
        fecha_nacimiento = findViewById(R.id.user_nacimiento);
        domicilio = findViewById(R.id.user_domicilio);
        colonia = findViewById(R.id.user_colonia);
        ciudad = findViewById(R.id.user_ciudad);

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


        // Buttons
        HomeButton = findViewById(R.id.homeButton);
        HomeButton.setOnClickListener(v -> {
            Intent intent = new Intent(PerfilUserActivity.this, HomeUserActivity.class);
            startActivity(intent);
        });

    }


}
