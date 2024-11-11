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

import java.util.ArrayList;

public class RecipeDetailsActivityFarmacia extends AppCompatActivity {

    private EditText fullName, day, month, year, age, height, weight, diagnostic, treatment, medications;
    private Button editButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_recipe_details_farmacia);

        fullName = (EditText) findViewById(R.id.user_name);
        day = (EditText) findViewById(R.id.user_day_recipe);
        month = (EditText) findViewById(R.id.user_month_recipe);
        year = (EditText) findViewById(R.id.user_year_recipe);
        age = (EditText) findViewById(R.id.user_age);
        height = (EditText) findViewById(R.id.user_height);
        weight = (EditText) findViewById(R.id.user_weight);
        diagnostic = (EditText) findViewById(R.id.user_diagnostic);
        treatment = (EditText) findViewById(R.id.user_treatment);

        medications = (EditText) findViewById(R.id.user_med);

        medications.setEnabled(false);

        // Details recipe
        Intent intent = getIntent();

        // ArrayList
        ArrayList<String> detailsRecipe = intent.getStringArrayListExtra("description_recipe");

        fullName.setText(detailsRecipe.get(0));
        age.setText(detailsRecipe.get(1));
        height.setText(detailsRecipe.get(2));
        weight.setText(detailsRecipe.get(3));
        diagnostic.setText(detailsRecipe.get(4));
        treatment.setText(detailsRecipe.get(5));
        medications.setText(detailsRecipe.get(6));
        String idPaciente = detailsRecipe.get(7);
        String idReceta = detailsRecipe.get(8);


        DB db = new DB(getApplicationContext(), null, null, 1);


        Button editButton = findViewById(R.id.buttonEdit);
        editButton.setOnClickListener(v->{
            edit(editButton,medications,idPaciente,idReceta);
        });


        ImageView exitButton = findViewById(R.id.exitButton);
        exitButton.setOnClickListener(v -> {
            Intent intent2 = new Intent(RecipeDetailsActivityFarmacia.this, LoginActivity.class);
            startActivity(intent2);
        });

    }

    private void edit(Button editButton, EditText medications, String idPaciente, String idReceta){
        if (editButton.getText().toString().equals("Editar")){
            editButton.setText("Guardar");
            medications.setEnabled(true);
        } else if(editButton.getText().toString().equals("Guardar")){
            DB db = new DB(getApplicationContext(), null, null, 1);
            String medication = medications.getText().toString();

            if(db.actualizar_receta(medication,idPaciente,idReceta)){
                editButton.setText("Editar");
                medications.setEnabled(false);
                Toast.makeText(getApplicationContext(),"Editado correctamente", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(),"No se pudo editar", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
