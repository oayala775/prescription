package com.example.prescription;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class HomeDoctorActivity extends AppCompatActivity {
    private ImageView search;
    private EditText searchPill;
    private String searchText;
    private ImageView perfilButton;
    private ArrayList<String> informacion;
    private String userName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home_doctor);

        // get data from login
        Intent intentInformacion = getIntent();
        informacion = intentInformacion.getStringArrayListExtra("datos_doctor");
        userName = informacion.get(10);

        // Reference db
        DB db = new DB(getApplicationContext(), null, null, 1);

        // Search button and text retrieval
        search = findViewById(R.id.searchButton);
        searchPill = findViewById(R.id.search_pill);

        search.setOnClickListener(v -> {
            searchText = searchPill.getText().toString();
            if (!searchText.isEmpty()){
                ArrayList<String> info = db.obtenerDatosPaciente(searchText);
                if (!info.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Usuario " + searchText + " encontrado", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(HomeDoctorActivity.this, CreateRecipeActivity.class);
                    intent.putStringArrayListExtra("datos_paciente", info);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Usuario no encontrado", Toast.LENGTH_SHORT).show();
                    searchPill.setText("");
                }
            }
        });


        // Navbar
        perfilButton = findViewById(R.id.perfilButton);
        perfilButton.setOnClickListener(v -> {
            Intent intent = new Intent(HomeDoctorActivity.this, PerfilDoctorActivity.class);
            intent.putStringArrayListExtra("datos_doctor", db.obtenerDatosDoctor(userName));
            startActivity(intent);
        });

    }
}
