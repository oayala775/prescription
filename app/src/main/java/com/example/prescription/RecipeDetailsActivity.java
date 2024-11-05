package com.example.prescription;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class RecipeDetailsActivity extends AppCompatActivity {

    private EditText fullName, day, month, year, age, height, weight, diagnostic, treatment;
    private TextView status;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_recipe_details);

        fullName = (EditText) findViewById(R.id.user_name);
        day = (EditText) findViewById(R.id.user_day_recipe);
        month = (EditText) findViewById(R.id.user_month_recipe);
        year = (EditText) findViewById(R.id.user_year_recipe);
        age = (EditText) findViewById(R.id.user_age);
        height = (EditText) findViewById(R.id.user_height);
        weight = (EditText) findViewById(R.id.user_weight);
        diagnostic = (EditText) findViewById(R.id.user_diagnostic);
        treatment = (EditText) findViewById(R.id.user_treatment);

        status = (TextView) findViewById(R.id.status);

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

        if(detailsRecipe.get(6).equals("Expired")){
            status.setVisibility(View.VISIBLE);
        }
        else{
            status.setVisibility(View.GONE);
        }

        DB db = new DB(getApplicationContext(), null, null, 1);

        ImageView exitButton = findViewById(R.id.exitButton);
        exitButton.setOnClickListener(v -> {
            Intent intent2 = new Intent(RecipeDetailsActivity.this, LoginActivity.class);
            startActivity(intent2);
        });

    }


}
