package com.example.prescription;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecipesPatientFromFarmacia extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter myAdapter;

    private TextView userName;
    private TextView code;

    private List<Receta> inventarioRecetas = new ArrayList<>();
    private ArrayList<String> informacion;
    private ArrayList<String> informacionFarmacia;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_recipes_patient_from_farmacia);

        Intent intentInformacion = getIntent();
        informacion = intentInformacion.getStringArrayListExtra("datos_paciente");
        informacionFarmacia = intentInformacion.getStringArrayListExtra("datos_farmacia");

        Log.d("RecipesFromFarmacia", "informacionFarmacia: " + informacionFarmacia);

        userName = findViewById(R.id.usuario);
        code = findViewById(R.id.codigo);

        userName.setText(informacion.get(9));
        code.setText(informacion.get(0));

        // Databases
        DB db = new DB(getApplicationContext(), null, null, 1);
        inventarioRecetas = new ArrayList<>(db.obtenerRecetasPaciente(informacion.get(0)));

        // Adapter
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        myAdapter = new ItemRecetaAdapter(new ArrayList<>(inventarioRecetas));
        recyclerView.setAdapter(myAdapter);



        // Buttons
        ImageView exitButton = findViewById(R.id.exitButton);
        exitButton.setOnClickListener(v -> {
            Intent intent = new Intent(RecipesPatientFromFarmacia.this, LoginActivity.class);
            startActivity(intent);
        });
        ImageView homeButton = findViewById(R.id.homeButton);
        homeButton.setOnClickListener(v -> {
            Intent intent = new Intent(RecipesPatientFromFarmacia.this, HomeFarmaciaActivity.class);
            intent.putStringArrayListExtra("datos_farmacia", db.obtenerDatosFarmacia(informacionFarmacia.get(6)));
            startActivity(intent);
        });

        ImageView perfilButton = findViewById(R.id.perfilButton);
        perfilButton.setOnClickListener(v -> {
            Intent intent = new Intent(RecipesPatientFromFarmacia.this, PerfilFarmaciaActivity.class);
            intent.putStringArrayListExtra("datos_farmacia", db.obtenerDatosFarmacia(informacionFarmacia.get(6)));
            startActivity(intent);
        });
    }
}
