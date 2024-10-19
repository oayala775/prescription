package com.example.prescription;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.Random;

public class DB extends SQLiteOpenHelper {

    //Creamos la base de datos
    public DB(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, "PrescriptionDB", factory, 1);
    }

    //Creamos una tablas en la base de datos
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table datos_doctores(codigo text, nombre text, apellido text, telefono text, nss text, curp text, domicilio text, ciudad text, colonia text, cedula text, nombreUsuario text, contrasena text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}

    //metodo guardar registro
    //agregar al registro fecha de nacimiento, contraseña, y nombre de usuario
    //vamos a tener que comprobar los nombres de usuario y que las ocntraseñas coincidan
    public String guardar(String nombre, String apellido, String telefono, String nss, String curp, String domicilio, String ciudad, String colonia, String cedula, String nombreUsuario, String contrasena){
        String mensaje = "";

        //permisos de escritura a la base
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contenedor = new ContentValues();

        //generar código
        Random random = new Random();
        int minimo = 111111111;
        int maximo = 999999999;
        int codigo = random.nextInt((maximo - minimo) + 1) + minimo;
        //TO-DO checar que el código no este en uso

        contenedor.put("codigo", codigo);
        contenedor.put("nombre", nombre);
        contenedor.put("apellido", apellido);
        contenedor.put("telefono", telefono);
        contenedor.put("nss", nss);
        contenedor.put("curp", curp);
        //contenedor.put("fechaNacimiento", fechaNacimiento);
        contenedor.put("domicilio", domicilio);
        contenedor.put("ciudad", ciudad);
        contenedor.put("colonia",colonia);
        contenedor.put("cedula", cedula);
        contenedor.put("nombreUsuario",nombreUsuario);
        contenedor.put("contrasena", contrasena);

        //corroborar que se ingreso o no a la base de datos
        try{
            database.insertOrThrow("datos_doctores", null, contenedor);
            mensaje = "Ingresado correctamente";
        }
        catch(SQLException e){mensaje = "No ingresado: " + e.getMessage();}
        database.close();
        return mensaje;
    }

}
