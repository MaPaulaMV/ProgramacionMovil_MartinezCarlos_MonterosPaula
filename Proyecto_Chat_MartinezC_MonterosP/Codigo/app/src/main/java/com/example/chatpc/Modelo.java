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

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

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
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Clase que contiene toda la lógica del programa, la misma realiza todas las operaciones
 * e implementa la interfaz iChat.iModelo.
 *
 * @author Carlos Martínez
 * @author Paula Monteros
 */
public class Modelo implements iChat.iModelo {
    private Presentador Presentador;
    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;
    private DatabaseReference databaseReferenceSala;
    private FirebaseStorage storage;
    private StorageReference storageReference;
    private List<Usuario> usuarioList;
    private AdapterUsuario adapterUsuarios;
    private String nombre_u="", apellid_u="", foto_u="", user_id;
    private DatabaseReference databaseReference2;
    private AdapterMensaje adapterMensajes;
    private DatabaseReference databaseReferenceBuscarUsuario;
    private DatabaseReference databaseReferenceUsuario;
    private DatabaseReference databaseReferenceMensaje;

    /**
     * Constructor vacío de la clase Modelo.
     */
    public Modelo(){

    }

    /**
     * Constructor con parámetros de la clase Modelo.
     *
     * @param Presentador Objecto de la clase Presentador
     */
    public Modelo(Presentador Presentador) {
        this.Presentador = Presentador;
        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReferenceBuscarUsuario = FirebaseDatabase.getInstance().getReference("Usuarios").child(firebaseAuth.getCurrentUser().getUid());
        databaseReferenceMensaje = FirebaseDatabase.getInstance().getReference("chat");
        databaseReferenceSala = FirebaseDatabase.getInstance().getReference("chat");
        storage=FirebaseStorage.getInstance();
        usuarioList= new ArrayList<>();
    }

    /**
     * Método que obtiene el valor del atributo User_id de la clase Modelo
     *
     * @return Id del usuario logeado.
     */
    public String getUser_id() {
        return user_id;
    }

    /**
     * Método que setea el valor del atributo User_id de la clase Modelo.
     *
     * @param user_id Id del usuario
     */
    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    /**
     * Realiza el registro del usuario en la base de datos.
     *
     * @param nombre Nombre del usuario
     * @param apellido Apellido del Usuario
     * @param u Uri de la foto de perfil del Usuario
     * @param activity Activity
     */
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
                                            Presentador.cambiarActivityP(true, "OKEY BOOMER");
                                        }
                                        else {
                                            Presentador.cambiarActivityP(false, "ERROR EN LA BASE");

                                        }
                                    }
                                });
                            }
                            else {
                                Presentador.cambiarActivityP(false, "ERROR EN SUBIR LA FOTO");
                            }
                        }
                    });
                }else {
                    Presentador.cambiarActivityP(false, "ERROR AL CREAR USUARIO");


                }
            }
        });
    }

    /**
     * Método que cierra la sesión del usuario actual.
     */
    @Override
    public void cerrarSesionM() {
        firebaseAuth.signOut();
    }

    /**
     * Método que obtiene la información del usuario logeado, para llenar la vista Home con los
     * datos.
     *
     * @param textView TextView del Activity
     * @param imageView ImageView del activity
     * @param context Context del Activity
     */
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

    }

    /**
     * Método que obtiene los datos de todos los usuarios registrados para mostrar en
     * la vista HomeActivity.
     *
     * @param context Context del Activity
     * @param users RecyclerView del activity.
     */
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

    /**
     * Método que envía un mensaje de texto y lo registra en la base.
     *
     * @param mensaje Contenido del Mensaje
     * @param tipo Tipo de Mensaje
     */
    @Override
    public void enviarMensajeM(String mensaje, String tipo) {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
        String formattedDate = df.format(c.getTime());
        Log.e("Mensaje: ", mensaje);
        databaseReference2.push().setValue(new Mensaje(mensaje, nombre_u +" " + apellid_u,foto_u,tipo,formattedDate));

    }

    /**
     * Método que envía la imagen seleccionada por el usuario.
     *
     * @param requestCode Request Code
     * @param resultCode Result Code
     * @param data Data de la imagen a enviar
     */
    @Override
    public void enviarFotoM(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode==1 && resultCode == -1 && data!=null ){
            Uri u = data.getData();
            storageReference = storage.getReference("imagenes");
            final StorageReference foto = storageReference.child(u.getLastPathSegment());

            foto.putFile(u).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                @Override
                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                    Log.e("FOTO: ", "ENTRA");
                    if(!task.isSuccessful()){
                        throw  new Exception();
                    }
                    return foto.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    if (task.isSuccessful()){
                        Calendar c = Calendar.getInstance();
                        SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
                        String formattedDate = df.format(c.getTime());
                        Mensaje m = new Mensaje(" ", nombre_u +" " + apellid_u,foto_u,"2",formattedDate,task.getResult().toString());
                        databaseReference2.push().setValue(m);
                    }
                }
            });

        }
    }

    /**
     * Método que obtiene la información del usuario logeado.
     */
    @Override
    public void infoUsuarioM() {
        databaseReferenceBuscarUsuario = FirebaseDatabase.getInstance().getReference("Usuarios").child(firebaseAuth.getCurrentUser().getUid());
        databaseReferenceBuscarUsuario.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Usuario usuario = dataSnapshot.getValue(Usuario.class);
                nombre_u = usuario.getNombre();
                apellid_u = usuario.getApellido();
                foto_u = usuario.getFoto();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    /**
     * Método que obtiene la información del usuario con el cual se establecerá la sesión o
     * sala de chat.
     *
     * @param user_id Id del usuario
     * @param textView textView del Activity
     * @param imageView ImageView del Activity
     * @param context Context del Activity
     */
    @Override
    public void infoContactoM(String user_id, final TextView textView, final ImageView imageView, final Context context) {
        this.user_id = user_id;
        databaseReferenceUsuario = FirebaseDatabase.getInstance().getReference("Usuarios").child(user_id);
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

    /**
     * Método que obtienen los mensajes anteriores de una sala de chat, y los
     * muestra en la vista.
     *
     * @param context Context del Activity
     * @param mensajes RecyclerView del Activity
     */
    @Override
    public void leerMensajesM(Context context, final RecyclerView mensajes) {
        final String chat_id =firebaseAuth.getUid() + user_id;
        final String chat_id2 = user_id + firebaseAuth.getUid();
        databaseReferenceMensaje = FirebaseDatabase.getInstance().getReference("chat");
        adapterMensajes=new AdapterMensaje(context);
        adapterMensajes.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                super.onItemRangeInserted(positionStart, itemCount);
                mensajes.scrollToPosition(adapterMensajes.getItemCount()-1);
            }
        });

        databaseReferenceMensaje.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Boolean bandera = false;
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    if(postSnapshot.getKey().equals(chat_id) || postSnapshot.getKey().equals(chat_id2) ){
                        databaseReference2 = FirebaseDatabase.getInstance().getReference("chat").child(postSnapshot.getKey());
                        salaExisente();
                        bandera = true;
                        break;
                    }
                }
                if (!bandera){
                    databaseReference2 = FirebaseDatabase.getInstance().getReference("chat").child(chat_id);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        mensajes.setAdapter(adapterMensajes);
    }

    /**
     * Método que verifica si la sala de chat entre dos usuarios ya existía anteriormente.
     */
    public void salaExisente(){

        databaseReference2.addChildEventListener(new ChildEventListener() {
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
        });
    }

    /**
     * Método que obtiene el valor del atributo FirebaseAuth de la clase Modelo.
     *
     * @return FirebaseAuth
     */
    public FirebaseAuth getFirebaseAuth() {
        return firebaseAuth;
    }

    /**
     * Método que setea el valor del atributo FirebaseAuth de la clase Modelo.
     *
     * @param firebaseAuth
     */
    public void setFirebaseAuth(FirebaseAuth firebaseAuth) {
        this.firebaseAuth = firebaseAuth;
    }
}
