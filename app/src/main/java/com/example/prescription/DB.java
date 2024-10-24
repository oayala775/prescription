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

    public boolean usuarioExistente(String nombreUsuario){
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
        return count == 1;
    }

    public String[] login(String nombreUsuario){
        String contrasenaEnDB = null;
        String tabla = "";

        SQLiteDatabase database = this.getWritableDatabase();

        //hacer una consulta en varias tablas
        String q = "SELECT nombreUsuario, 'datos_doctores' AS origen FROM datos_doctores WHERE nombreUsuario = '" + nombreUsuario + "'" +
                "UNION " +
                "SELECT  nombreUsuario, 'datos_farmacia' AS origen FROM datos_farmacia WHERE nombreUsuario  = '" + nombreUsuario + "'" +
                "UNION " +
                "SELECT  nombreUsuario, 'datos_pacientes' AS origen FROM datos_pacientes WHERE nombreUsuario = '" + nombreUsuario + "'" ;

        Cursor registros = database.rawQuery(q, null);

        if (registros.moveToFirst()) { // Verifica si hay resultados
            int indiceContrasena = registros.getColumnIndex("contrasena");
            int indiceOrigen = registros.getColumnIndex("origen");

            contrasenaEnDB = registros.getString(indiceContrasena);
            tabla = registros.getString(indiceOrigen);
        }

        registros.close();
        database.close();

        return new String[]{contrasenaEnDB, tabla};
    }


}
