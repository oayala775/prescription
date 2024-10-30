package com.example.prescription;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
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
        db.execSQL("CREATE TABLE datos_doctores(id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, apellido TEXT, telefono TEXT, nss TEXT, curp TEXT, domicilio TEXT, ciudad TEXT, colonia TEXT, cedula TEXT, nombreUsuario TEXT, contrasena TEXT, rol TEXT)");
        db.execSQL("CREATE TABLE datos_pacientes(id INTEGER PRIMARY KEY AUTOINCREMENT,nombre TEXT, apellido TEXT, telefono TEXT, nss TEXT, curp TEXT, domicilio TEXT, ciudad TEXT, colonia TEXT, nombreUsuario TEXT, contrasena TEXT, rol TEXT)");
        db.execSQL("CREATE TABLE datos_farmacia(id INTEGER PRIMARY KEY AUTOINCREMENT,nombre TEXT, telefono TEXT, domicilio TEXT, ciudad TEXT, colonia TEXT, nombreUsuario TEXT, contrasena TEXT, rol TEXT)");
        db.execSQL("CREATE TABLE recetas(id_receta INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, edad TEXT, estatura TEXT, peso TEXT, diagnostico TEXT, tratamiento TEXT, id_paciente INTEGER, FOREIGN KEY (id_paciente) REFERENCES datos_pacientes(id))");

        // Agregar un paciente predefinido
        db.execSQL("INSERT INTO datos_pacientes (nombre, apellido, telefono, nss, curp, domicilio, ciudad, colonia, nombreUsuario, contrasena, rol)" +
                "VALUES ('paciente', 'paciente', '5551234567', '1234567890', 'JUAP', 'Guadalajara', 'Guadalajara', 'Centro', 'paciente', '12345', 'paciente')");

        db.execSQL("INSERT INTO recetas (nombre, edad, estatura, peso, diagnostico, tratamiento, id_paciente) VALUES ('Receta Inicial', '30', '175', '70', 'Diagnóstico de prueba', 'Tratamiento de prueba', 1)");

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
        contenedor.put("rol", "doctor");
        contenedor.put("cedula", cedula);

        //corroborar que se ingreso o no a la base de datos
        try{
            database.insertOrThrow("datos_doctores", null, contenedor);
            mensaje = "Ingresado correctamente";
        }
        catch(SQLException e){mensaje = "No ingresado: " + e.getMessage();}
        database.close();
        return mensaje;
    }

//    public String actualizar(String nombre, String apellido, String telefono, String nss, String curp, String domicilio, String ciudad, String colonia, String cedula, String nombreUsuario){
//
//
//    }

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
        contenedor.put("rol", "paciente");


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
        contenedor.put("rol", "farmacia");


        //corroborar que se ingreso o no a la base de datos
        try{
            database.insertOrThrow("datos_farmacia", null, contenedor);
            mensaje = "Ingresado correctamente";
        }
        catch(SQLException e){mensaje = "No ingresado: " + e.getMessage();}
        database.close();
        return mensaje;
    }

    // Guardar recetas
    public String guardar(String nombre, int id_paciente, String edad, String estatura, String peso, String diagnostico, String tratamiento){
        String mensaje = "";

        //permisos de escritura a la base
        SQLiteDatabase database;
        ContentValues contenedor;

        if (usuarioExistente(id_paciente)){
            database = this.getWritableDatabase();
            contenedor = new ContentValues();
            contenedor.put("nombre",nombre);
            contenedor.put("edad",edad);
            contenedor.put("estatura",estatura);
            contenedor.put("peso",peso);
            contenedor.put("diagnostico",diagnostico);
            contenedor.put("tratamiento",tratamiento);
            contenedor.put("id_paciente",id_paciente);
            //corroborar que se ingreso o no a la base de datos
            try{
                database.insertOrThrow("recetas", null, contenedor);
                mensaje = "Receta correctamente creada";
            }
            catch(SQLException e){mensaje = "No se pudo crear la receta: " + e.getMessage();}
            database.close();
            return mensaje;
        } else {
            mensaje = "No se encontró el paciente";
            return mensaje;
        }


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



    public Boolean usuarioExistente(String nombreUsuario){
        SQLiteDatabase database = this.getReadableDatabase();

        //hacer una consulta en varias tablas
        String query = String.format("SELECT nombreUsuario, rol FROM (" +
                "SELECT nombreUsuario, rol FROM datos_pacientes WHERE nombreUsuario = ? UNION " +
                "SELECT nombreUsuario, rol FROM datos_doctores WHERE nombreUsuario = ? UNION " +
                "SELECT nombreUsuario, rol FROM datos_farmacia WHERE nombreUsuario = ?) LIMIT 1;");

        Cursor cursor = database.rawQuery(query, new String[]{nombreUsuario,nombreUsuario,nombreUsuario});


        if (cursor.moveToFirst()) {
            database.close();   // Successful login
            return true;
        } else {
            cursor.close();
            database.close();
            return false; // Login failed
        }
    }

    public Boolean usuarioExistente(int id){
        Integer idParse = (Integer) id;
        String idString = idParse.toString();
        SQLiteDatabase database = this.getReadableDatabase();

        //hacer una consulta en varias tablas
        String query = String.format("SELECT id FROM datos_pacientes WHERE id = ? ");

        Cursor cursor = database.rawQuery(query, new String[]{idString});

        if (cursor.moveToFirst()) {
            database.close();   // Successful login
            return true;
        } else {
            cursor.close();
            database.close();
            return false; // Login failed
        }
    }

    public ArrayList<String> contrasenaExistente(String nombreUsuario){
        ArrayList<String> tuple = new ArrayList<>(2);
        SQLiteDatabase database = this.getReadableDatabase();

        //hacer una consulta en varias tablas
        String query = String.format("SELECT contrasena, rol FROM (" +
                "SELECT contrasena, rol FROM datos_pacientes WHERE nombreUsuario = ? UNION " +
                "SELECT contrasena, rol FROM datos_doctores WHERE nombreUsuario = ? UNION " +
                "SELECT contrasena, rol FROM datos_farmacia WHERE nombreUsuario = ?) LIMIT 1;");

        Cursor cursor = database.rawQuery(query, new String[]{nombreUsuario,nombreUsuario,nombreUsuario});


        if (cursor.moveToFirst()) {
            tuple.add(cursor.getString(0));
            tuple.add(cursor.getString(1));
            database.close();   // Successful login
            return tuple;
        } else {
            cursor.close();
            database.close();
            return null; // Login failed
        }
    }


    public ArrayList<String> obtenerDatosPaciente(String nombreUsuario){
        ArrayList<String> datos = new ArrayList<>();
        SQLiteDatabase database = this.getReadableDatabase();

        String query = "SELECT * FROM datos_pacientes WHERE nombreUsuario = ?";
        Cursor cursor = database.rawQuery(query, new String[]{nombreUsuario});

        // Verificamos si hay resultados
        if (cursor.moveToFirst()) {
            int numColumns = cursor.getColumnCount();

            for(int i = 0; i < numColumns; i++){
                datos.add(cursor.getString(i));
            }

        }
        cursor.close();
        database.close();

        return datos;
    }

    public ArrayList<String> obtenerDatosDoctor(String nombreUsuario){
        ArrayList<String> datos = new ArrayList<>();
        SQLiteDatabase database = this.getReadableDatabase();

        String query = "SELECT * FROM datos_doctores WHERE nombreUsuario = ?";
        Cursor cursor = database.rawQuery(query, new String[]{nombreUsuario});

        //Verificamos si hay resultados
        if(cursor.moveToFirst()){
            int numColumns = cursor.getColumnCount();

            for(int i = 0; i < numColumns; i++){
                datos.add(cursor.getString(i));
            }
        }
        cursor.close();
        database.close();
        return datos;
    }

    public ArrayList<String> obtenerDatosFarmacia(String nombreUsuario){
        ArrayList<String> datos = new ArrayList<>();
        SQLiteDatabase database = this.getReadableDatabase();

        String query = "SELECT * FROM datos_farmacia WHERE nombreUsuario = ?";
        Cursor cursor = database.rawQuery(query, new String[]{nombreUsuario});

        //Verificamos si hay resultados
        if(cursor.moveToFirst()){
            int numColumns = cursor.getColumnCount();

            for(int i = 0; i < numColumns; i++){
                datos.add(cursor.getString(i));
            }
        }
        cursor.close();
        database.close();
        return datos;
    }

    public ArrayList<Receta> obtenerRecetasPaciente(String idPaciente){
        ArrayList<Receta> datos = new ArrayList<>();

        SQLiteDatabase database = this.getReadableDatabase();

        String query = "SELECT * FROM recetas WHERE id_paciente = ?";
        Cursor cursor = database.rawQuery(query, new String[]{idPaciente});



        if(cursor != null){
            try{
                cursor.moveToFirst();
                do {
                    //@SuppressLint("Range") int idReceta = cursor.getInt(cursor.getColumnIndex("id_receta"));
                    @SuppressLint("Range") String nombre = cursor.getString(cursor.getColumnIndex("nombre"));
                    @SuppressLint("Range") String edad = cursor.getString(cursor.getColumnIndex("edad"));
                    @SuppressLint("Range") String estatura = cursor.getString(cursor.getColumnIndex("estatura"));
                    @SuppressLint("Range") String peso = cursor.getString(cursor.getColumnIndex("peso"));
                    @SuppressLint("Range") String diagnostico = cursor.getString(cursor.getColumnIndex("diagnostico"));
                    @SuppressLint("Range") String tratamiento = cursor.getString(cursor.getColumnIndex("tratamiento"));

                    // Crea un objeto Receta (asegúrate de tener la clase Receta definida)
                    Receta receta = new Receta(nombre, edad, estatura, peso, diagnostico, tratamiento, idPaciente);
                    datos.add(receta);
                } while (cursor.moveToNext());
            }
            catch (Exception e){
                Log.e("DB", "Error al obtener recetas: ", e);
            }
            finally {
                cursor.close();
            }
        }
        database.close();
        return datos;
    }
}
