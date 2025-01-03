package com.example.prescription;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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


public class PerfilUserActivity extends AppCompatActivity {
    public EditText nombre, apellido, telefono, nss, curp, fecha_nacimiento, domicilio, ciudad, colonia, dia, mes, anio;

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
        //Log.d("Informacion usuario",""+informacion);

        String nombre_parametro = informacion.get(1);
        String apellido_parametro = informacion.get(2);
        String telefono_parametro = informacion.get(3);
        String nss_parametro = informacion.get(4);
        String curp_parametro = informacion.get(5);
        String domicilio_parametro = informacion.get(6);
        String ciudad_parametro = informacion.get(7);
        String colonia_parametro = informacion.get(8);
        String dia_parametro = informacion.get(12);
        String mes_parametro = informacion.get(13);
        String anio_parametro = informacion.get(14);

        nombre = findViewById(R.id.user_nombre);
        apellido = findViewById(R.id.user_apellido);
        telefono = findViewById(R.id.user_telefono);
        nss = findViewById(R.id.user_nss);
        curp = findViewById(R.id.user_curp);
        domicilio = findViewById(R.id.user_domicilio);
        colonia = findViewById(R.id.user_colonia);
        ciudad = findViewById(R.id.user_ciudad);
        dia = findViewById(R.id.user_day);
        mes = findViewById(R.id.user_month);
        anio = findViewById(R.id.user_year);

        nombre.setText(nombre_parametro);
        apellido.setText(apellido_parametro);
        telefono.setText(String.valueOf(telefono_parametro));
        nss.setText(String.valueOf(nss_parametro));
        curp.setText(curp_parametro);
        domicilio.setText(domicilio_parametro);
        colonia.setText(colonia_parametro);
        ciudad.setText(ciudad_parametro);
        dia.setText(dia_parametro);
        mes.setText(mes_parametro);
        anio.setText(anio_parametro);


        DB db = new DB(getApplicationContext(), null, null, 1);
        String userName = informacion.get(9);

        nombre.setEnabled(false);
        apellido.setEnabled(false);
        telefono.setEnabled(false);
        nss.setEnabled(false);
        curp.setEnabled(false);
        domicilio.setEnabled(false);
        colonia.setEnabled(false);
        ciudad.setEnabled(false);
        dia.setEnabled(false);
        mes.setEnabled(false);
        anio.setEnabled(false);

        Button editButton = findViewById(R.id.buttonEdit);
        editButton.setOnClickListener(v->{
            edit(telefono,domicilio,colonia,ciudad,editButton,userName);
        });

        // Buttons
        ImageView exitButton = findViewById(R.id.exitButton);
        exitButton.setOnClickListener(v -> {
            Intent intent = new Intent(PerfilUserActivity.this, LoginActivity.class);
            startActivity(intent);
        });
        ImageView homeButton = findViewById(R.id.homeButton);
        homeButton.setOnClickListener(v -> {
            Intent intent = new Intent(PerfilUserActivity.this, HomeUserActivity.class);
            intent.putStringArrayListExtra("datos_paciente", db.obtenerDatosPaciente(userName));
            startActivity(intent);
        });

        ImageView perfilButton = findViewById(R.id.perfilButton);
        perfilButton.setOnClickListener(v -> {
            Intent intent = new Intent(PerfilUserActivity.this, PerfilUserActivity.class);
            intent.putStringArrayListExtra("datos_paciente", db.obtenerDatosPaciente(userName));
            startActivity(intent);
        });

    }

    private void edit(EditText telefono, EditText domicilio, EditText colonia, EditText ciudad, Button editButton, String userName){
        if (editButton.getText().toString().equals("Editar")){
            editButton.setText("Guardar");
            telefono.setEnabled(true);
            domicilio.setEnabled(true);
            colonia.setEnabled(true);
            ciudad.setEnabled(true);
        } else if(editButton.getText().toString().equals("Guardar")){
            DB db = new DB(getApplicationContext(), null, null, 1);
            String telefonoS = telefono.getText().toString();
            String domicilioS = domicilio.getText().toString();
            String coloniaS = colonia.getText().toString();
            String ciudadS = ciudad.getText().toString();

            if(db.actualizar(1,telefonoS,domicilioS,coloniaS,ciudadS,userName)){
                editButton.setText("Editar");
                telefono.setEnabled(false);
                domicilio.setEnabled(false);
                colonia.setEnabled(false);
                ciudad.setEnabled(false);
                Toast.makeText(getApplicationContext(),"Editado correctamente", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(),"No se pudo editar", Toast.LENGTH_SHORT).show();
            }
        }
    }


}
