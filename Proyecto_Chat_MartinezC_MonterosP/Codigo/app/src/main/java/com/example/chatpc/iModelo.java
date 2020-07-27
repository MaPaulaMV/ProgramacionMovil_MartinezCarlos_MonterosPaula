package com.example.chatpc;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;

public class iModelo implements iChat.iModelo {
    private iPresentador iPresentador;
    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;
    private FirebaseStorage storage;
    private StorageReference storageReference;
    private List<Usuario> usuarioList;
    private AdapterUsuario adapterUsuarios;

    public iModelo(){

    }
    public iModelo(iPresentador iPresentador) {
        this.iPresentador = iPresentador;
        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        storage=FirebaseStorage.getInstance();
        usuarioList= new ArrayList<>();
    }

    @Override
    public void registrarUsuarioM(final String nombre, final String apellido, final Uri u, Activity activity) {


        firebaseAuth.signInAnonymously().addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    storageReference = storage.getReference("perfiles");
                    final StorageReference foto = storageReference.child(u.getLastPathSegment() + Math.random()*10);
                    foto.putFile(u).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                        @Override
                        public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                            if(!task.isSuccessful()){
                                throw  new Exception();
                            }
                            return foto.getDownloadUrl();
                        }
                    }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                        @Override
                        public void onComplete(@NonNull Task<Uri> task) {
                            if (task.isSuccessful()){
                                String fotoperfil = task.getResult().toString();
                                String id = firebaseAuth.getCurrentUser().getUid();
                                Map<String,Object> map = new HashMap<>();
                                map.put("nombre",nombre);
                                map.put("apellido",apellido);
                                map.put("foto",fotoperfil);

                                databaseReference.child("Usuarios").child(id).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task2) {
                                        if(task2.isSuccessful()){
                                            iPresentador.cambiarActivityP(true, "OKEY BOOMER");
                                        }
                                        else {
                                            iPresentador.cambiarActivityP(false, "ERROR EN LA BASE");

                                        }
                                    }
                                });
                            }
                            else {
                                iPresentador.cambiarActivityP(false, "ERROR EN SUBIR LA FOTO");
                            }
                        }
                    });
                }else {
                    iPresentador.cambiarActivityP(false, "ERROR AL CREAR USUARIO");


                }
            }
        });
    }

    @Override
    public void cerrarSesionM() {
        firebaseAuth.signOut();
    }

    @Override
    public void obtenerInfoM(final TextView textView, final ImageView imageView, final Context context) {


        String id = firebaseAuth.getCurrentUser().getUid();

        databaseReference.child("Usuarios").child(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //final List<String> informacion = new ArrayList<String>();
                String nombre = dataSnapshot.child("nombre").getValue().toString();
                String apellido = dataSnapshot.child("apellido").getValue().toString();
                String foto = dataSnapshot.child("foto").getValue().toString();

                textView.setText(nombre + " " + apellido);
                Glide.with(context).load(foto).into(imageView);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        /*databaseReference.child("Usuarios").child(id).addValueEventListener( new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()){
                    String nombre = dataSnapshot.child("nombre").getValue().toString();
                    String apellido = dataSnapshot.child("apellido").getValue().toString();
                    String foto = dataSnapshot.child("foto").getValue().toString();
                    informacion.add(nombre);
                    informacion.add(apellido);
                    informacion.add(foto);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/

    }

    @Override
    public void leerUsuariosM(final Context context, final RecyclerView users) {
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
                adapterUsuarios = new AdapterUsuario(context,usuarioList);
                users.setAdapter(adapterUsuarios);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

   /* @Override
    public AdapterUsuario leerUsuariosM(final Context context) {
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
                adapterUsuarios = new AdapterUsuario(context,usuarioList);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return adapterUsuarios;
    }*/


    public FirebaseAuth getFirebaseAuth() {
        return firebaseAuth;
    }

    public void setFirebaseAuth(FirebaseAuth firebaseAuth) {
        this.firebaseAuth = firebaseAuth;
    }
}
