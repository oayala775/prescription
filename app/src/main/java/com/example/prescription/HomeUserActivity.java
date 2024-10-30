package com.example.prescription;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public class HomeUserActivity extends AppCompatActivity {

    //Atributos
    private RecyclerView recyclerView;
    private RecyclerView.Adapter myAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ImageView PerfilButton;
    private String userName;


    private List<Receta> inventarioRecetas = new ArrayList<>();
    private ArrayList<String> informacion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home_usuario);

        //Log.d("HomeUserActivity", "OnCreate Creado: ");

        Intent intentInformacion = getIntent();
        informacion = intentInformacion.getStringArrayListExtra("datos_paciente");

        userName = informacion.get(9);

        Log.d("HomeUserActivity", "informacion: " + informacion);
        //nombre, edad, estatura, peso, diagnostico, tratamiento, idPaciente


        //Se agregan recetas de prueba
        //Receta receta = new Receta(this, userName);
        //inventarioRecetas.add(receta);

        DB db = new DB(getApplicationContext(), null, null, 1);
        this.inventarioRecetas = new ArrayList<>(db.obtenerRecetasPaciente("1"));




        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        myAdapter = new ItemRecetaAdapter(new ArrayList<>(inventarioRecetas));
        recyclerView.setAdapter(myAdapter);




        // Buttons
        ImageView exitButton = findViewById(R.id.exitButton);
        exitButton.setOnClickListener(v -> {
            Intent intent = new Intent(HomeUserActivity.this, LoginActivity.class);
            startActivity(intent);
        });
        ImageView homeButton = findViewById(R.id.homeButton);
        homeButton.setOnClickListener(v -> {
            Intent intent = new Intent(HomeUserActivity.this, HomeUserActivity.class);
            intent.putStringArrayListExtra("datos_paciente", db.obtenerDatosPaciente(userName));
            startActivity(intent);
        });

        ImageView perfilButton = findViewById(R.id.perfilButton);
        perfilButton.setOnClickListener(v -> {
            Intent intent = new Intent(HomeUserActivity.this, PerfilUserActivity.class);
            intent.putStringArrayListExtra("datos_paciente", db.obtenerDatosPaciente(userName));
            startActivity(intent);
        });

    }

}
