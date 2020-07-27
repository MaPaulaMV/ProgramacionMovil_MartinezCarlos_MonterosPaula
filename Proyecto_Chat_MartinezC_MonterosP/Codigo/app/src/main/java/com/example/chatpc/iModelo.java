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
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;

public class iModelo implements iChat.iModelo {
    private iPresentador iPresentador;
    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;
    private DatabaseReference databaseReferenceBuscarUsuario;
    private DatabaseReference databaseReferenceMensaje;
    private DatabaseReference databaseReferenceSala;
    private FirebaseStorage storage;
    private StorageReference storageReference;
    private List<Usuario> usuarioList;
    private AdapterUsuario adapterUsuarios;
    private String nombre_u,apellido_u,foto_u;
    private String user_id;
    private String chat_id;
    private String chat_id2;

    public iModelo(){

    }
    public iModelo(iPresentador iPresentador) {
        this.iPresentador = iPresentador;
        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReferenceBuscarUsuario = FirebaseDatabase.getInstance().getReference("Usuarios").child(firebaseAuth.getCurrentUser().getUid());
        databaseReferenceMensaje = FirebaseDatabase.getInstance().getReference("chat");
        databaseReferenceSala = FirebaseDatabase.getInstance().getReference("chat");
        storage=FirebaseStorage.getInstance();
        usuarioList= new ArrayList<>();
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
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

    @Override
    public void enviarMensajeM(String mensaje, String tipo) {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
        String formattedDate = df.format(c.getTime());
        databaseReferenceMensaje.push().setValue(new Mensaje(mensaje, nombre_u+" "+apellido_u,foto_u,tipo,formattedDate));
    }

    @Override
    public void perfilusuarioM() {
        databaseReferenceBuscarUsuario.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Usuario usuario = dataSnapshot.getValue(Usuario.class);
                nombre_u = usuario.getNombre();
                apellido_u = usuario.getApellido();
                foto_u = usuario.getFoto();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void receptorInfoM(final TextView textView, final ImageView imageView, final Context context) {
        DatabaseReference databaseReferenceUsuario = FirebaseDatabase.getInstance().getReference("Usuarios").child(user_id);
        databaseReferenceUsuario.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Usuario usuario = dataSnapshot.getValue(Usuario.class);
                textView.setText(usuario.getNombre() + " " + usuario.getApellido());
                Glide.with(context).load(usuario.getFoto()).into(imageView);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void crearSesionSalaM(final AdapterMensaje adapterMensaje) {
        chat_id =firebaseAuth.getUid() + user_id;
        chat_id2 = user_id + firebaseAuth.getUid();
        Log.e("chat1: ",chat_id);
        Log.e("chat2: ",chat_id2);
        databaseReferenceSala.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Boolean bandera = false;
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    if(postSnapshot.getKey().equals(chat_id) || postSnapshot.getKey().equals(chat_id2) ){
                        databaseReferenceMensaje.child(postSnapshot.getKey());
                        salaExisente(adapterMensaje);
                        bandera = true;
                        break;
                    }
                }
                if (!bandera){
                    databaseReference =  FirebaseDatabase.getInstance().getReference("chat").child(chat_id);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void salaExisente(final AdapterMensaje adapterMensaje){
        databaseReferenceMensaje.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Mensaje m = dataSnapshot.getValue(Mensaje.class);
                adapterMensaje.addMensaje(m);
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
