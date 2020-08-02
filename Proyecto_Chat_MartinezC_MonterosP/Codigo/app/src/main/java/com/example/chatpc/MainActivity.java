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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Clase que permite instanciar y manejar todos los elementos de la
 * vista o interfaz MainActivity que muestra una sala de chat entre 2 usuarios.
 *
 * @author Carlos Martínez
 * @author Paula Monteros
 */
public class MainActivity extends AppCompatActivity {

    CircleImageView fotoPerfil;
    TextView nombreUsuario;
    RecyclerView mensajes;
    EditText txtmensaje;
    ImageButton btnenviar, btnfoto;
    Intent intent;
    private Presentador Presentador;
    private FirebaseAuth firebaseAuth;
    private AdapterMensaje adapterMensajes;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private DatabaseReference databaseReferenceUsuario;
    private DatabaseReference databaseReferenceBuscarUsuario;
    private DatabaseReference databaseReferenceMensaje;
    private FirebaseStorage storage;

    /**
     * Método que se encarga de inicializar los elementos de la vista.
     *
     * @param savedInstanceState Objeto LoginActivity actual
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Presentador = new Presentador(this);
        fotoPerfil = (CircleImageView) findViewById(R.id.perfil);
        nombreUsuario = (TextView) findViewById(R.id.nombre);
        mensajes = (RecyclerView) findViewById(R.id.mensajes);
        txtmensaje = (EditText) findViewById(R.id.txtMensaje);
        btnenviar = (ImageButton) findViewById(R.id.btnEnviar);
        btnfoto = (ImageButton) findViewById(R.id.btnimagen);
        adapterMensajes=new AdapterMensaje(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mensajes.setLayoutManager(linearLayoutManager);
        mensajes.setAdapter(adapterMensajes);

        intent = getIntent();
        Presentador.getModelo().setUser_id(intent.getStringExtra("userid"));
        final String user_id = intent.getStringExtra("userid");//id del usuario con el que se va a chatear

        database = FirebaseDatabase.getInstance();
        firebaseAuth=FirebaseAuth.getInstance();

        databaseReferenceUsuario = database.getReference("Usuarios").child(user_id);
        databaseReferenceMensaje = database.getReference("chat");
        storage=FirebaseStorage.getInstance();

        /**
         * Botón que llama al presentador para enviar los datos del mensaje.
         */
        btnenviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Presentador.enviarMensajeP(txtmensaje.getText().toString(), "1");
            txtmensaje.setText("");
            }
        });

        /**
         * Botón que permite abrir la galería del teléfono para seleccionar una foto.
         */
        btnfoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent i = new Intent(Intent.ACTION_GET_CONTENT);
            i.setType("image/*");
            i.putExtra(Intent.EXTRA_LOCAL_ONLY,true);
            startActivityForResult(Intent.createChooser(i,"Selecciona foto"),1);
            }
        });

        Presentador.infoUsuarioP();
        Presentador.infoContactoP(user_id,nombreUsuario,fotoPerfil,getApplicationContext());
        Presentador.leerMensajesP(getApplicationContext(),mensajes);

    }

    /**
     * Método Scroll que permite movilizarse o desplazarse por la pantalla para visualizar
     * los mensajes.
     */
    private  void  setScrollbar(){
        mensajes.scrollToPosition(adapterMensajes.getItemCount()-1);
    }

    /**
     * Método que obtiene la data de la foto seleccionada y la envia por medio del
     * presentador.
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
       Presentador.enviarFotoP(requestCode,resultCode,data);
    }
}