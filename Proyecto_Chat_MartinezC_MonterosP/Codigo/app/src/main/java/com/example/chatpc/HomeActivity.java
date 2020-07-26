package com.example.chatpc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private ImageButton btnCerrasSesion;
    private TextView txtNombres;
    private ImageView imgHome;
    RecyclerView usuarios;

    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    private AdapterUsuario adapterUsuarios;
    private List<Usuario> usuarioList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        txtNombres = (TextView)findViewById(R.id.txtHomeNombre);
        imgHome = (ImageView)findViewById(R.id.imgHome);
        usuarios =(RecyclerView)findViewById(R.id.lista_usuarios);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        usuarios.setLayoutManager(linearLayoutManager);
        usuarioList= new ArrayList<>();

        firebaseAuth=FirebaseAuth.getInstance();
        databaseReference=FirebaseDatabase.getInstance().getReference();
        btnCerrasSesion = (ImageButton)findViewById(R.id.btnCerrarSesion);
        btnCerrasSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();
                startActivity(new Intent(HomeActivity.this,LoginActivity.class));
                finish();//para que no pueda vovler a esta pestaña si da click al boton atras del cell
            }
        });

        obtenerInfo();
        leerUsuarios();
    }

    private void leerUsuarios() {
        databaseReference.child("Usuarios").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                usuarioList.clear();
                for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                    Usuario usuario = snapshot.getValue(Usuario.class);
                    usuario.setId(snapshot.getKey());

                    if(!usuario.getId().equals(firebaseAuth.getUid())){
                        usuarioList.add(usuario);
                    }

                }
                adapterUsuarios = new AdapterUsuario(getApplicationContext(),usuarioList);
                usuarios.setAdapter(adapterUsuarios);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void obtenerInfo(){
        String id = firebaseAuth.getCurrentUser().getUid();
        databaseReference.child("Usuarios").child(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    String nombre = dataSnapshot.child("nombre").getValue().toString();
                    String apellido = dataSnapshot.child("apellido").getValue().toString();
                    String foto = dataSnapshot.child("foto").getValue().toString();
                    Glide.with(getApplicationContext()).load(foto).into(imgHome);
                    txtNombres.setText(nombre + " " + apellido);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}