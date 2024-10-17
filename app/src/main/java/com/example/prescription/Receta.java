package com.example.prescription;

public class Receta {

    //Atributos
    private String name = "Daniel";
    private String idPatient = "17102024";
    private String age = "21";
    private String gender = "d";
    private String stature = "1.80 m";
    private String weight = "80 kg";
    private String diagnostic;

    private String treatment = "paracetamol cada 8 horas";
    private String date;
    private boolean status = false; // True -> Active, False -> Expired




    //Metodos
    public Receta(){};

    public Receta(
            String date,
            String diagnostic
    ){
        this.date = date;
        this.diagnostic = diagnostic;
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
