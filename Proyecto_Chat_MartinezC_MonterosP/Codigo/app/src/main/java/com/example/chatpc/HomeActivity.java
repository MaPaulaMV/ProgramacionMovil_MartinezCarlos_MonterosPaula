/*
 * ESPE - DCC - PROGRAMACIÓN MÓVIL
 * NRC: 6112
 *
 * Sistema: CHATP&C
 * Creado 16/07/2020
 *
 * Los contenidos de este archivo son propiedad privada y estan protegidos por
 * la licencia BSD
 *
 * Se puede utilizar, reproducir o copiar el contenido de este archivo.
 */
package com.example.chatpc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Clase que permite instanciar y manejar todos los elementos de la
 * vista o interfaz HomeActivity que muestra las salas de chat y usuarios.
 *
 * @author Carlos Martínez
 * @author Paula Monteros
 */
public class HomeActivity extends AppCompatActivity{

    private ImageButton btnCerrasSesion;
    private TextView txtNombres;
    private ImageView imgHome;
    RecyclerView usuarios;
    private Presentador Presentador;

    /**
     *  Método que se encarga de inicializar los elementos de la vista.
     *
     * @param savedInstanceState Objecto HomeActivity actual
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        txtNombres = (TextView)findViewById(R.id.txtHomeNombre);
        imgHome = (ImageView)findViewById(R.id.imgHome);
        usuarios =(RecyclerView)findViewById(R.id.lista_usuarios);
        Presentador = new Presentador(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        usuarios.setLayoutManager(linearLayoutManager);
        btnCerrasSesion = (ImageButton)findViewById(R.id.btnCerrarSesion);

        /**
         * Botón para cerrar la sesión del usuario acual conetado.
         */
        btnCerrasSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Presentador.cerrarSesionP();
                startActivity(new Intent(HomeActivity.this,LoginActivity.class));
                finish();
            }
        });

        Presentador.obtenerInfoP(txtNombres,imgHome, getApplicationContext());
        Presentador.leerUsuariosP(getApplicationContext(), usuarios);

    }
}