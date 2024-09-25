package com.example.prescription;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class PerfilUserActivity extends AppCompatActivity {
    public EditText nombre, apellido, telefono, nss, curp, fecha_nacimiento, calle1, calle2, colonia;
    public TextView codigo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_perfil_usuario);

        String nombre_parametro = "Juan";
        String apellido_parametro = "Pérez";
        long telefono_parametro = 9999999999L;
        long nss_parametro = 99999999999L;
        String curp_parametro = "OPDE129867RJCURXA9";
        LocalDate fecha_nacimiento_parametro = LocalDate.of(1999, 1, 1);
        String calle1_parametro = "Calle 1";
        String calle2_parametro = "Calle 2";
        String colonia_parametro = "America";
        long codigo_parametro = 21212121212L;

        codigo = findViewById(R.id.user_codigo);
        nombre = findViewById(R.id.user_nombre);
        apellido = findViewById(R.id.user_apellido);
        telefono = findViewById(R.id.user_telefono);
        nss = findViewById(R.id.user_nss);
        curp = findViewById(R.id.user_curp);
        fecha_nacimiento = findViewById(R.id.user_nacimiento);
        calle1 = findViewById(R.id.user_calle1);
        calle2 = findViewById(R.id.user_calle2);
        colonia = findViewById(R.id.user_colonia);

        codigo.setText(String.valueOf(codigo_parametro));
        nombre.setText(nombre_parametro);
        apellido.setText(apellido_parametro);
        telefono.setText(String.valueOf(telefono_parametro));
        nss.setText(String.valueOf(nss_parametro));
        curp.setText(curp_parametro);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        fecha_nacimiento.setText(fecha_nacimiento_parametro.format(formatter));
        calle1.setText(calle1_parametro);
        calle2.setText(calle2_parametro);
        colonia.setText(colonia_parametro);

    }
}
