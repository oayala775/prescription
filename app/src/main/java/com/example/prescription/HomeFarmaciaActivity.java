package com.example.prescription;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class HomeFarmaciaActivity extends AppCompatActivity {

    private ImageView search;
    private EditText searchPill;
    private String searchText;
    private ArrayList<String> informacion;
    private String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home_farmacia);

        // get data form login
        Intent intentInformation = getIntent();
        informacion = intentInformation.getStringArrayListExtra("datos_farmacia");
        userName = informacion.get(6);

        //Reference db
        DB db = new DB(getApplicationContext(), null, null, 1);

        search = findViewById(R.id.searchButton);
        searchPill = findViewById(R.id.search_pill);

        search.setOnClickListener(v -> {
            searchText = searchPill.getText().toString();
            if(!searchText.isEmpty()){
                ArrayList<String> info = db.obtenerDatosPaciente(searchText);
                if(!info.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Usuario " + searchText + " encontrado", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(HomeFarmaciaActivity.this, PerfilFarmaciaActivity.class);
                    intent.putStringArrayListExtra("datos_farmacia", db.obtenerDatosFarmacia(userName));

                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(), "Usuario no encontrado", Toast.LENGTH_SHORT).show();
                    searchPill.setText("");
                }
            }
            Log.d("datosFarmacia","Hola");
        });


        // Buttons
        ImageView exitButton = findViewById(R.id.exitButton);
        exitButton.setOnClickListener(v -> {
            Intent intent = new Intent(HomeFarmaciaActivity.this, LoginActivity.class);
            startActivity(intent);
        });
        ImageView homeButton = findViewById(R.id.homeButton);
        homeButton.setOnClickListener(v -> {
            Intent intent = new Intent(HomeFarmaciaActivity.this, HomeFarmaciaActivity.class);
            intent.putStringArrayListExtra("datos_farmacia", db.obtenerDatosFarmacia(userName));
            startActivity(intent);
        });

        ImageView perfilButton = findViewById(R.id.perfilButton);
        perfilButton.setOnClickListener(v -> {
            Intent intent = new Intent(HomeFarmaciaActivity.this, PerfilFarmaciaActivity.class);
            intent.putStringArrayListExtra("datos_farmacia", db.obtenerDatosFarmacia(userName));
            startActivity(intent);
        });


    }
}
