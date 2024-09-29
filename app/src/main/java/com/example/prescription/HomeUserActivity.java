package com.example.prescription;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public class HomeUserActivity extends AppCompatActivity {

    //Atributos
    private RecyclerView recyclerView;
    private RecyclerView.Adapter myAdapter;
    private RecyclerView.LayoutManager layoutManager;


    private List<Receta> inventarioRecetas = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home_usuario);

        //Se agregan recetas de prueba
        this.inventarioRecetas.add(new Receta("27-07-2024","Pruebakokokokokokokokokokokokokokokokokokokokokokokokokokokokoklpllplplplplplplplplplplplpplpllplplplplplplplp"));
        this.inventarioRecetas.add(new Receta("28-07-2024","Prueba"));
        this.inventarioRecetas.add(new Receta("29-07-2024","Prueba"));
        this.inventarioRecetas.add(new Receta("30-07-2024","Prueba"));
        this.inventarioRecetas.add(new Receta("31-07-2024","Prueba"));
        this.inventarioRecetas.add(new Receta("28-07-2024","Prueba"));
        this.inventarioRecetas.add(new Receta("29-07-2024","Prueba"));
        this.inventarioRecetas.add(new Receta("30-07-2024","Prueba"));
        this.inventarioRecetas.add(new Receta("31-07-2024","Prueba"));
        this.inventarioRecetas.add(new Receta("28-07-2024","Prueba"));
        this.inventarioRecetas.add(new Receta("29-07-2024","Prueba"));
        this.inventarioRecetas.add(new Receta("30-07-2024","Prueba"));
        this.inventarioRecetas.add(new Receta("31-07-2024","Prueba"));
        this.inventarioRecetas.add(new Receta("28-07-2024","Prueba"));
        this.inventarioRecetas.add(new Receta("29-07-2024","Prueba"));
        this.inventarioRecetas.add(new Receta("30-07-2024","Prueba"));
        this.inventarioRecetas.add(new Receta("31-07-2024","Prueba"));
        this.inventarioRecetas.add(new Receta("28-07-2024","Prueba"));
        this.inventarioRecetas.add(new Receta("29-07-2024","Prueba"));
        this.inventarioRecetas.add(new Receta("30-07-2024","Prueba"));
        this.inventarioRecetas.add(new Receta("31-07-2024","Prueba"));



        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        myAdapter = new ItemRecetaAdapter(new ArrayList<>(inventarioRecetas));
        recyclerView.setAdapter(myAdapter);

        ImageView PerfilButton;
        PerfilButton = findViewById(R.id.imageView3);

        PerfilButton.setOnClickListener(v -> {
            Intent intent = new Intent(HomeUserActivity.this, PerfilUserActivity.class);
            startActivity(intent);
        });


    }


    public void goHome(View v){
        Intent intent = new Intent(HomeUserActivity.this, HomeUserActivity.class);
        startActivity(intent);
    }


}
