package com.example.prescription;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class RecipeDetailsActivity extends AppCompatActivity {

    private EditText fullName, id, day, month, year, age, height, weight, diagnostic, treatment;
    private TextView status;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_recipe_details);

        fullName = (EditText) findViewById(R.id.user_name);
        id = (EditText) findViewById(R.id.user_id);
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
        id.setText(detailsRecipe.get(1));
        day.setText(detailsRecipe.get(2));
        month.setText(detailsRecipe.get(3));
        year.setText(detailsRecipe.get(4));
        age.setText(detailsRecipe.get(5));
        height.setText(detailsRecipe.get(6));
        weight.setText(detailsRecipe.get(7));
        diagnostic.setText(detailsRecipe.get(8));
        treatment.setText(detailsRecipe.get(9));

        if(detailsRecipe.get(10).equals("Expired")){
            status.setVisibility(View.VISIBLE);
        }
        else{
            status.setVisibility(View.GONE);
        }
    }
}
