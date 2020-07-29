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

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Clase que permite instanciar y manejar todos los elementos de la
 * vista o interfaz de para el registro de usuarios, implementa la interfaz iChat.iVistaLogin.
 *
 * @author Carlos Martínez
 * @author Paula Monteros
 */
public class LoginActivity extends AppCompatActivity implements iChat.iVistaLogin{

    private EditText txtNombre;
    private EditText txtApellido;
    private Button btnIngresar;
    private ImageButton btnFoto;
    private ImageView foto;

    private  String nombre="", apellido="";
    private Uri u;
    private Presentador Presentador;

    /**
     * Método que se encarga de inicializar los elemnetos de la vista.
     *
     * @param savedInstanceState Objeto LoginActivity actual
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Presentador = new Presentador(this);
        txtNombre = (EditText)findViewById(R.id.txtRegNombre);
        txtApellido = (EditText)findViewById(R.id.txtRegApellido);
        btnIngresar = (Button) findViewById(R.id.btnRegIngresar);
        btnFoto = (ImageButton) findViewById(R.id.btnRegImagen);
        foto = (ImageView) findViewById(R.id.imagenregistro);

        /**
         * Botón que llama al presentador para enviar los datos del usuario para que se registre en la base.
         */
        btnIngresar.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            nombre=txtNombre.getText().toString();
            apellido=txtApellido.getText().toString();
            Presentador.registrarUsuarioP(nombre,apellido,u,new LoginActivity());
            btnIngresar.setEnabled(false);
            }
        });

        /**
         * Botón que abre la galería del teléfono pasa seleccionar una foto del mismo.
         */
        btnFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subirFoto();
            }
        });
    }


    /**
     * Método que permite abrir la galería del teléfono y seleccionar una foto.
     */
    public void subirFoto(){
        Intent i = new Intent(Intent.ACTION_GET_CONTENT);
        i.setType("image/*");
        i.putExtra(Intent.EXTRA_LOCAL_ONLY,true);
        startActivityForResult(Intent.createChooser(i,"Selecciona foto"),1);
    }

    /**
     * Método que determina si el usuario actual se encuentra registrado en la base.
     */
    @Override
    protected void onStart() {
        super.onStart();
        if (Presentador.getModelo().firebaseAuth.getCurrentUser() !=null){
            startActivity(new Intent(LoginActivity .this, HomeActivity.class));
            finish();
        }
    }

    /**
     * Método que muestra en pantalla la foto seleccionada por el usuario.
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1 && resultCode == RESULT_OK && data!=null ){
            u = data.getData();
            foto.setImageURI(u);
        }
    }

    /**
     * Método que permite cambiar del LoginActivity al HomeActivity.
     *
     * @param confirmacion Confirmación de que el usuario se registró e la base.
     * @param mensaje Mensaje de éxito u error en el registro.
     */
    @Override
    public void cambiarActivityV(boolean confirmacion, String mensaje) {
        if (confirmacion){
            startActivity(new Intent(this, HomeActivity.class));
            finish();
        }else {
            btnIngresar.setEnabled(true);
            Toast.makeText(getApplicationContext(),mensaje,Toast.LENGTH_LONG).show();
        }

    }
}