package com.example.chatpc;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;


import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {

    CircleImageView fotoPerfil;
    TextView nombreUsuario;
    RecyclerView mensajes;
    EditText txtmensaje;
    ImageButton btnenviar, btnfoto;
    Intent intent;

    private FirebaseAuth firebaseAuth;
    private AdapterMensaje adapterMensajes;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private DatabaseReference databaseReferenceUsuario;
    private DatabaseReference databaseReferenceBuscarUsuario;
    private DatabaseReference databaseReferenceMensaje;
    private FirebaseStorage storage;

    private iPresentador iPresentador;



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

        /*databaseReferenceMensaje.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Boolean bandera = false;
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    if(postSnapshot.getKey().equals(chat_id) || postSnapshot.getKey().equals(chat_id2) ){
                        databaseReference = database.getReference("chat").child(postSnapshot.getKey());
                        salaExisente();
                        bandera = true;
                        break;
                    }
                }
                if (!bandera){
                    databaseReference = database.getReference("chat").child(chat_id);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/

    }
    private  void  setScrollbar(){
        mensajes.scrollToPosition(adapterMensajes.getItemCount()-1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


       iPresentador.enviarFotoP(requestCode,resultCode,data);
    }

    public void salaExisente(){

        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Mensaje m = dataSnapshot.getValue(Mensaje.class);
                adapterMensajes.addMensaje(m);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });//PARA LOS MENSAJES
    }
}