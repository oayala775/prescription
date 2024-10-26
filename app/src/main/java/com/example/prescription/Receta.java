package com.example.prescription;

import com.example.prescription.DB;

import android.content.Context;
import android.content.Intent;

import java.time.LocalDate;
import java.util.ArrayList;

public class Receta {

    //Atributos
    private String userName;
    private String name;
    private String idPatient;
    private String age;
    private String gender;
    private String stature;
    private String weight;
    private String diagnostic;

    private String treatment;
    private String date;
    private boolean status = false; // True -> Active, False -> Expired
    private ArrayList<String> informacion;



    //Metodos
    public Receta(){};

    public Receta(Context context, String userName){
        //Obtenemos informacion del intent
        DB db = new DB(context, null, null, 1);
        informacion = db.obtenerDatosPaciente(userName);
        this.name = informacion.get(1) + " " + informacion.get(2);
        this.idPatient = informacion.get(0);
    }


    //Setters
    public void setName(String n){ this.name = n;}
    public void setIdPatient(String id){ this.idPatient = id;}
    public void setAge(String age){ this.age = age; }
    public void setGender(String g) { this.gender = g; }
    public void setStature(String s){ this.stature = s; }
    public void setWeight(String w){ this.weight = w; }
    public void setDate(String f){
        this.date = f;
    }
    public void setDiagnostic(String diag){
        this.diagnostic = diag;
    }
    public void setTreatment(String T) { this.treatment = T; }
    public void setStatus(boolean S) { this.status = S; }


    //Getters
    public String getName() { return this.name; }
    public String getIdPatient(){ return this.idPatient; }
    public String getAge(){ return this.age; }
    public String getGender(){ return this.gender; }
    public String getStature(){ return this.stature; }
    public String getWeight(){ return this.weight; }
    public String getDate(){
        return this.date;
    }
    public String getDiagnostic(){
        return this.diagnostic;
    }
    public String getTreatment(){ return this.treatment; }
    public boolean getStatus(){ return this.status; }



}
