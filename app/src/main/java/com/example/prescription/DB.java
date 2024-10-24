package com.example.prescription;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.Random;

//to-do agregar al registro fecha de nacimiento

public class DB extends SQLiteOpenHelper {

    //Creamos la base de datos
    public DB(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, "PrescriptionDB", factory, 1);
    }

    //Creamos una tablas en la base de datos
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table datos_doctores(nombre text, apellido text, telefono text, nss text, curp text, domicilio text, ciudad text, colonia text, cedula text, nombreUsuario text, contrasena text)");
        db.execSQL("create table datos_pacientes(nombre text, apellido text, telefono text, nss text, curp text, domicilio text, ciudad text, colonia text, nombreUsuario text, contrasena text)");
        db.execSQL("create table datos_farmacia(nombre text, telefono text, domicilio text, ciudad text, colonia text, nombreUsuario text, contrasena text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}

    //metodo guardar(doctor) registro
    public String guardar(String nombre, String apellido, String telefono, String nss, String curp, String domicilio, String ciudad, String colonia, String cedula, String nombreUsuario, String contrasena){
        String mensaje = "";

        //permisos de escritura a la base
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contenedor = new ContentValues();

        contenedor.put("nombre", nombre);
        contenedor.put("apellido", apellido);
        contenedor.put("telefono", telefono);
        contenedor.put("nss", nss);
        contenedor.put("curp", curp);
        //contenedor.put("fechaNacimiento", fechaNacimiento);
        contenedor.put("domicilio", domicilio);
        contenedor.put("ciudad", ciudad);
        contenedor.put("colonia",colonia);
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

    //metodo guardar(paciente) registro
    public String guardar(String nombre, String apellido, String telefono, String nss, String curp, String domicilio, String ciudad, String colonia, String nombreUsuario, String contrasena){
        String mensaje = "";

        //permisos de escritura a la base
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contenedor = new ContentValues();

        contenedor.put("nombre", nombre);
        contenedor.put("apellido", apellido);
        contenedor.put("telefono", telefono);
        contenedor.put("nss", nss);
        contenedor.put("curp", curp);
        //contenedor.put("fechaNacimiento", fechaNacimiento);
        contenedor.put("domicilio", domicilio);
        contenedor.put("ciudad", ciudad);
        contenedor.put("colonia",colonia);
        contenedor.put("nombreUsuario",nombreUsuario);
        contenedor.put("contrasena", contrasena);

        //corroborar que se ingreso o no a la base de datos
        try{
            database.insertOrThrow("datos_pacientes", null, contenedor);
            mensaje = "Ingresado correctamente";
        }
        catch(SQLException e){mensaje = "No ingresado: " + e.getMessage();}
        database.close();
        return mensaje;
    }

    //metodo guardar(farmacia) registro
    public String guardar(String nombre, String telefono, String domicilio, String ciudad, String colonia, String nombreUsuario, String contrasena){
        String mensaje = "";

        //permisos de escritura a la base
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contenedor = new ContentValues();

        contenedor.put("nombre", nombre);
        contenedor.put("telefono", telefono);
        //contenedor.put("fechaNacimiento", fechaNacimiento);
        contenedor.put("domicilio", domicilio);
        contenedor.put("ciudad", ciudad);
        contenedor.put("colonia",colonia);
        contenedor.put("nombreUsuario",nombreUsuario);
        contenedor.put("contrasena", contrasena);

        //corroborar que se ingreso o no a la base de datos
        try{
            database.insertOrThrow("datos_farmacia", null, contenedor);
            mensaje = "Ingresado correctamente";
        }
        catch(SQLException e){mensaje = "No ingresado: " + e.getMessage();}
        database.close();
        return mensaje;
    }

    public Boolean validarUsuario(String nombreUsuario) {
        int count = 0;

        SQLiteDatabase database = this.getWritableDatabase();

        //hacer una consulta en varias tablas
        String q = "SELECT COUNT(*) FROM datos_doctores WHERE nombreUsuario = '" + nombreUsuario + "'" +
                "UNION " +
                "SELECT COUNT(*) FROM datos_farmacia WHERE nombreUsuario  = '" + nombreUsuario + "'" +
                "UNION " +
                "SELECT COUNT(*) FROM datos_pacientes WHERE nombreUsuario = '" + nombreUsuario + "'" ;

        Cursor registros = database.rawQuery(q, null);

        // Itera sobre los resultados y suma los valores obtenidos
        while (registros.moveToNext()) {
            count += registros.getInt(0); // Suma el resultado de cada tabla
        }

        registros.close();
        database.close();

        // Retorna true si no hay coincidencias, false si las hay
        return count == 0;
    }

    public String usuarioExistente(String nombreUsuario){
        int count_d = 0;
        int count_f = 0;
        int count_p = 0;
        String tabla = "";

        SQLiteDatabase database = this.getWritableDatabase();

        //hacer una consulta en varias tablas
        String q_doctores = "SELECT COUNT(*) FROM datos_doctores WHERE nombreUsuario = '" + nombreUsuario + "'";
        String q_farmacia = "SELECT COUNT(*) FROM datos_farmacia WHERE nombreUsuario = '" + nombreUsuario + "'";
        String q_pacientes = "SELECT COUNT(*) FROM datos_pacientes WHERE nombreUsuario = '" + nombreUsuario + "'";

        Cursor registros_doctores = database.rawQuery(q_doctores, null);
        Cursor registros_farmacia = database.rawQuery(q_farmacia, null);
        Cursor registros_pacientes = database.rawQuery(q_pacientes, null);

        while (registros_doctores.moveToNext()) {
            count_d += registros_doctores.getInt(0); // Suma el resultado de cada tabla
        }
        while (registros_farmacia.moveToNext()) {
            count_f += registros_farmacia.getInt(0); // Suma el resultado de cada tabla
        }
        while (registros_pacientes.moveToNext()) {
            count_p += registros_pacientes.getInt(0); // Suma el resultado de cada tabla
        }

        if(count_d>0){
            tabla="doctores";
        }else if (count_f>0) {
            tabla="farmacia";
        }else if (count_p>0){
            tabla="pacientes";
        }else{
            tabla="no encontrado";
        }

        registros_doctores.close();
        registros_farmacia.close();
        registros_pacientes.close();
        database.close();

        return tabla;
    }

    public String login(String nombreUsuario){
        String contrasenaEnDB = null;

        SQLiteDatabase database = this.getWritableDatabase();

        //hacer una consulta en varias tablas
        String q = "SELECT contrasena FROM datos_doctores WHERE nombreUsuario = '" + nombreUsuario + "'" +
                "UNION " +
                "SELECT  contrasena FROM datos_farmacia WHERE nombreUsuario  = '" + nombreUsuario + "'" +
                "UNION " +
                "SELECT  contrasena FROM datos_pacientes WHERE nombreUsuario = '" + nombreUsuario + "'" ;

        Cursor registros = database.rawQuery(q, null);

        if (registros.moveToFirst()) { // Verifica si hay resultados
            int indiceContrasena = registros.getColumnIndex("contrasena");
            contrasenaEnDB = registros.getString(indiceContrasena);

        }

        registros.close();
        database.close();

        return contrasenaEnDB;
    }


}
