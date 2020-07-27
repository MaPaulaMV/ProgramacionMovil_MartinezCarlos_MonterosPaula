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

public class LoginActivity extends AppCompatActivity implements iChat.iVistaLogin{

    private EditText txtNombre;
    private EditText txtApellido;
    private Button btnIngresar;
    private ImageButton btnFoto;
    private ImageView foto;

    private  String nombre="", apellido="", fotoperfil="";
    private Uri u;
    private iPresentador iPresentador;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        iPresentador = new iPresentador(this);
        txtNombre = (EditText)findViewById(R.id.txtRegNombre);
        txtApellido = (EditText)findViewById(R.id.txtRegApellido);
        btnIngresar = (Button) findViewById(R.id.btnRegIngresar);
        btnFoto = (ImageButton) findViewById(R.id.btnRegImagen);
        foto = (ImageView) findViewById(R.id.imagenregistro);


            btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nombre=txtNombre.getText().toString();
                apellido=txtApellido.getText().toString();
                iPresentador.registrarUsuarioP(nombre,apellido,u,new LoginActivity());
                btnIngresar.setEnabled(false);
            }
        });

        btnFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subirFoto();
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
        if (iPresentador.getiModelo().firebaseAuth.getCurrentUser() !=null){
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