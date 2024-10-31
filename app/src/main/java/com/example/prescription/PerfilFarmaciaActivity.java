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


public class PerfilFarmaciaActivity extends AppCompatActivity {
    public EditText nombre, telefono, domicilio, colonia, ciudad;
    private ArrayList<String> informacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_perfil_farmacia);

        //Obtenemos informacion del intent
        Intent intentInformacion = getIntent();
        informacion = intentInformacion.getStringArrayListExtra("datos_farmacia");

        String nombre_parametro = informacion.get(1);
        String telefono_parametro = informacion.get(2);
        String domicilio_parametro = informacion.get(3);
        String ciudad_parametro = informacion.get(4);
        String colonia_parametro = informacion.get(5);

        nombre = findViewById(R.id.farmacia_nombre);
        telefono = findViewById(R.id.farmacia_telefono);
        domicilio = findViewById(R.id.farmacia_domicilio);
        colonia = findViewById(R.id.farmacia_colonia);
        ciudad = findViewById(R.id.farmacia_ciudad);

        nombre.setText(nombre_parametro);
        telefono.setText(String.valueOf(telefono_parametro));
        domicilio.setText(domicilio_parametro);
        colonia.setText(colonia_parametro);
        ciudad.setText(ciudad_parametro);

        DB db = new DB(getApplicationContext(), null, null, 1);
        String userName = informacion.get(6);

        nombre.setEnabled(false);
        telefono.setEnabled(false);
        domicilio.setEnabled(false);
        colonia.setEnabled(false);
        ciudad.setEnabled(false);

        Button editButton = findViewById(R.id.buttonEdit);
        editButton.setOnClickListener(v->{
            edit(telefono,domicilio,colonia,ciudad,editButton);
        });


        // Buttons
        ImageView exitButton = findViewById(R.id.exitButton);
        exitButton.setOnClickListener(v -> {
            Intent intent = new Intent(PerfilFarmaciaActivity.this, LoginActivity.class);
            startActivity(intent);
        });
//        ImageView homeButton = findViewById(R.id.homeButton);
//        homeButton.setOnClickListener(v -> {
//            Intent intent = new Intent(PerfilFarmaciaActivity.this, HomeDoctorActivity.class);
//            intent.putStringArrayListExtra("datos_doctor", db.obtenerDatosDoctor(userName));
//            startActivity(intent);
//        });

        ImageView perfilButton = findViewById(R.id.perfilButton);
        perfilButton.setOnClickListener(v -> {
            Intent intent = new Intent(PerfilFarmaciaActivity.this, PerfilFarmaciaActivity.class);
            intent.putStringArrayListExtra("datos_farmacia", db.obtenerDatosFarmacia(userName));
            Log.d("Usuario",userName);
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
