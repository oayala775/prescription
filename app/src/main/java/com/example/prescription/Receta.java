package com.example.prescription;

public class Receta {

    //Atributos
    private String description;
    private String fecha;


    //Metodos
    public Receta(){};

    public Receta(
            String fecha,
            String description
    ){
        this.fecha = fecha;
        this.description = description;
    }


    //Setters
    public void setFecha(String f){
        this.fecha = f;
    }
    public void setDescription(String des){
        this.description = des;
    }


    //Getters
    public String getFecha(){
        return this.fecha;
    }
    public String getDescription(){
        return this.description;
    }


}
