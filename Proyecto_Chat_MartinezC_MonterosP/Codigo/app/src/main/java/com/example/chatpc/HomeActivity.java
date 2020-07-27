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

public class HomeActivity extends AppCompatActivity{

    private ImageButton btnCerrasSesion;
    private TextView txtNombres;
    private ImageView imgHome;
    RecyclerView usuarios;

    private iPresentador iPresentador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        txtNombres = (TextView)findViewById(R.id.txtHomeNombre);
        imgHome = (ImageView)findViewById(R.id.imgHome);
        usuarios =(RecyclerView)findViewById(R.id.lista_usuarios);
        iPresentador = new iPresentador(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        usuarios.setLayoutManager(linearLayoutManager);


        btnCerrasSesion = (ImageButton)findViewById(R.id.btnCerrarSesion);
        btnCerrasSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iPresentador.cerrarSesionP();
                startActivity(new Intent(HomeActivity.this,LoginActivity.class));
                finish();
            }
        });

        iPresentador.obtenerInfoP(txtNombres,imgHome, getApplicationContext());
        iPresentador.leerUsuariosP(getApplicationContext(), usuarios);

    }
}