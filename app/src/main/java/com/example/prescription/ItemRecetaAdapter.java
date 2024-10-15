package com.example.prescription;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ItemRecetaAdapter extends RecyclerView.Adapter<ItemRecetaAdapter.MyViewHolder> {

    private List<Receta> data;


    //Metodos
    public static class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView diagnostic;
        public LinearLayout sig;


        public MyViewHolder(View v){
            super(v);
            diagnostic = v.findViewById(R.id.diagnostic);
            sig = v.findViewById(R.id.linearLayout);
        }
    }

    public ItemRecetaAdapter(ArrayList<Receta> recetas){data = new ArrayList<>(recetas);}

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_receta, parent, false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position){
        Log.d("ItemRecetaAdapter", "Position: " + position + " - " + data.get(position).getDate() + " " + data.get(position).getDiagnostic());

        holder.diagnostic.setText(data.get(position).getDate() + " " + data.get(position).getDiagnostic());

        // Click Methods
        holder.sig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), RecipeDetailsActivity.class);

                ArrayList<String> details_recipe = new ArrayList<>();

                /*
                * name
                * id
                * day
                * month
                * year
                * age
                * height
                * weight
                * Diagnostic
                * Treatment
                * */

                details_recipe.add(data.get(position).getName());
                details_recipe.add(data.get(position).getIdPatient());

                String[] date = data.get(position).getDate().split("-");
                Collections.addAll(details_recipe, date);

                details_recipe.add(data.get(position).getAge());
                details_recipe.add(data.get(position).getStature());
                details_recipe.add(data.get(position).getWeight());
                details_recipe.add(data.get(position).getDiagnostic());
                details_recipe.add(data.get(position).getTreatment());


                intent.putStringArrayListExtra("description_recipe", details_recipe);
                v.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }




}
