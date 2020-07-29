package com.example.chatpc;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;


import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {

    CircleImageView fotoPerfil;
    TextView nombreUsuario;
    RecyclerView mensajes;
    EditText txtmensaje;
    ImageButton btnenviar, btnfoto;
    Intent intent;
    private iPresentador iPresentador;
    private FirebaseAuth firebaseAuth;
    private AdapterMensaje adapterMensajes;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private DatabaseReference databaseReferenceUsuario;
    private DatabaseReference databaseReferenceBuscarUsuario;
    private DatabaseReference databaseReferenceMensaje;
    private FirebaseStorage storage;
    String msj;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iPresentador = new iPresentador(this);
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
        iPresentador.getiModelo().setUser_id(intent.getStringExtra("userid"));
        final String user_id = intent.getStringExtra("userid");//id del usuario con el que se va a chatear

        database = FirebaseDatabase.getInstance();
        firebaseAuth=FirebaseAuth.getInstance();


        databaseReferenceUsuario = database.getReference("Usuarios").child(user_id);

        databaseReferenceMensaje = database.getReference("chat");
        storage=FirebaseStorage.getInstance();


        btnenviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iPresentador.enviarMensajeP(txtmensaje.getText().toString(), "1");
                txtmensaje.setText("");
            }
        });

        btnfoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_GET_CONTENT);
                i.setType("image/*");
                i.putExtra(Intent.EXTRA_LOCAL_ONLY,true);
                startActivityForResult(Intent.createChooser(i,"Selecciona foto"),1);
            }
        });

        /*adapterMensajes.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                super.onItemRangeInserted(positionStart, itemCount);
                setScrollbar();
            }
        });*/


        iPresentador.infoUsuarioP();
        iPresentador.infoContactoP(user_id,nombreUsuario,fotoPerfil,getApplicationContext());
        iPresentador.leerMensajesP(getApplicationContext(),mensajes);
        

    }
    private  void  setScrollbar(){
        mensajes.scrollToPosition(adapterMensajes.getItemCount()-1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


       iPresentador.enviarFotoP(requestCode,resultCode,data);
    }

}