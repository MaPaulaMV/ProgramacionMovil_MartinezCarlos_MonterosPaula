package com.example.chatpc;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    private EditText txtNombre;
    private EditText txtApellido;
    private Button btnIngresar;
    private ImageButton btnFoto;
    private ImageView foto;

    private  String nombre="", apellido="", fotoperfil="";
    private Uri u;

    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;
    private FirebaseStorage storage;
    private StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        txtNombre = (EditText)findViewById(R.id.txtRegNombre);
        txtApellido = (EditText)findViewById(R.id.txtRegApellido);
        btnIngresar = (Button) findViewById(R.id.btnRegIngresar);
        btnFoto = (ImageButton) findViewById(R.id.btnRegImagen);
        foto = (ImageView) findViewById(R.id.imagenregistro);
        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        storage=FirebaseStorage.getInstance();

        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nombre=txtNombre.getText().toString();
                apellido=txtApellido.getText().toString();
                registrarUsuario();
            }
        });

        btnFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subirFoto();
            }
        });
    }

    public  void registrarUsuario(){
        btnIngresar.setEnabled(false);
        firebaseAuth.signInAnonymously().addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
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
                                fotoperfil = task.getResult().toString();
                                String id = firebaseAuth.getCurrentUser().getUid();
                                Map<String,Object> map = new HashMap<>();
                                map.put("nombre",nombre);
                                map.put("apellido",apellido);
                                map.put("foto",fotoperfil);

                                databaseReference.child("Usuarios").child(id).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task2) {
                                        if(task2.isSuccessful()){
                                            startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                                            finish();
                                        }
                                        else {
                                            Toast.makeText(LoginActivity.this,"ERROR AL GUARDAR EN LA BASE", Toast.LENGTH_LONG).show();
                                            btnIngresar.setEnabled(true);
                                        }
                                    }
                                });
                            }
                            else {
                                Toast.makeText(LoginActivity.this,"ERROR AL SUBIR FOTO", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }else {
                    Toast.makeText(LoginActivity.this,"ERROR AL INGRESAR", Toast.LENGTH_LONG).show();
                    btnIngresar.setEnabled(true);
                }
            }
        });
    }

    public void subirFoto(){
        Intent i = new Intent(Intent.ACTION_GET_CONTENT);
        i.setType("image/*");

        i.putExtra(Intent.EXTRA_LOCAL_ONLY,true);
        startActivityForResult(Intent.createChooser(i,"Selecciona foto"),1);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (firebaseAuth.getCurrentUser()!=null){
            startActivity(new Intent(LoginActivity .this, HomeActivity.class));
            finish();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1 && resultCode == RESULT_OK && data!=null ){
            u = data.getData();
            foto.setImageURI(u);
        }
    }

}