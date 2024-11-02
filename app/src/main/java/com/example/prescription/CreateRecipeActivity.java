package com.example.prescription;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class CreateRecipeActivity extends AppCompatActivity {

    private EditText name;
    private EditText day;
    private EditText month;
    private EditText year;
    private EditText age;
    private EditText height;
    private EditText weight;
    private EditText diagnostic;
    private EditText treatment;
    private ArrayList<String> informacion;
    private String userName;
    private Integer idPaciente;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_recipe_write);

        Intent intentInformacion = getIntent();
        informacion = intentInformacion.getStringArrayListExtra("datos_paciente");
        idPaciente = Integer.parseInt(informacion.get(0));
        userName = informacion.get(9);

        DB db = new DB(getApplicationContext(), null, null, 1);

        name = findViewById(R.id.user_name);
        day = findViewById(R.id.user_day_recipe);
        month = findViewById(R.id.user_month_recipe);
        year = findViewById(R.id.user_year_recipe);
        age = findViewById(R.id.user_age);
        height = findViewById(R.id.user_height);
        weight = findViewById(R.id.user_weight);
        diagnostic = findViewById(R.id.user_diagnostic);
        treatment = findViewById(R.id.user_treatment);
        button = findViewById(R.id.buttonSendRecipe);

        name.setText(informacion.get(1) + " " + informacion.get(2));

        button.setOnClickListener(v -> {
            String ageString;
            String heightString;
            String weightString;
            String diagnosticString;
            String treatmentString;

            ageString = age.getText().toString();
            heightString = height.getText().toString();
            weightString = weight.getText().toString();
            diagnosticString = diagnostic.getText().toString();
            treatmentString = treatment.getText().toString();
            String mensaje = db.guardar(informacion.get(1), idPaciente, ageString,heightString,weightString,diagnosticString,treatmentString);

            if (mensaje.equals("Receta correctamente creada")){
                Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(CreateRecipeActivity.this, HomeDoctorActivity.class);
                intent.putStringArrayListExtra("datos_doctor", db.obtenerDatosDoctor(userName));
                startActivity(intent);
            } else {
                Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_SHORT).show();
            }
        });

        // Buttons
        ImageView homeButton = findViewById(R.id.homeButton);
        homeButton.setOnClickListener(v -> {
            Intent intent = new Intent(CreateRecipeActivity.this, HomeDoctorActivity.class);
            intent.putStringArrayListExtra("datos_doctor", db.obtenerDatosDoctor(userName));
            startActivity(intent);
        });

        ImageView perfilButton = findViewById(R.id.perfilButton);
        perfilButton.setOnClickListener(v -> {
            Intent intent = new Intent(CreateRecipeActivity.this, PerfilDoctorActivity.class);
            intent.putStringArrayListExtra("datos_doctor", db.obtenerDatosDoctor(userName));
            startActivity(intent);
        });

    }
}
