package com.example.prescription;

public class Receta {

    //Atributos
    private String name = "";
    private String idPatient = "";
    private String age = "";
    private String gender = "";
    private String stature = "";
    private String weight = "";
    private String description;
    private String date;



    //Metodos
    public Receta(){};

    public Receta(
            String date,
            String description
    ){
        this.date = date;
        this.description = description;
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
    public void setDescription(String des){
        this.description = des;
    }


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
    public String getDescription(){
        return this.description;
    }


}
