package com.example.prescription;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ItemRecetaAdapter extends RecyclerView.Adapter<ItemRecetaAdapter.MyViewHolder> {

    private List<Receta> data;


    //Metodos
    public static class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView description;
        public ImageView sig;


        public MyViewHolder(View v){
            super(v);
            description = v.findViewById(R.id.description);
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
        Log.d("ItemRecetaAdapter", "Position: " + position + " - " + data.get(position).getDate() + " " + data.get(position).getDescription());

        holder.description.setText(data.get(position).getDate() + " " + data.get(position).getDescription());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }




}
