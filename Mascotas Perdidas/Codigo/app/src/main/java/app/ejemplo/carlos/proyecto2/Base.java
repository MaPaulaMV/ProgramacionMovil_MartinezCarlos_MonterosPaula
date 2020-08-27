package app.ejemplo.carlos.proyecto2;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class Base extends AppCompatActivity {


    EditText nombre;
    EditText lugar;
    EditText desc;
    EditText nomPersona;
    EditText numTel;
    EditText eMail;
    RadioButton perdido;
    RadioButton herido;
    RadioButton adopcion;
    ImageView foto;
    Button subir,btnFoto;
    private Uri filePath;
    private final int PICK_PHOTO = 1;

    DatabaseReference databaseReference;
    StorageReference storageReference;
    FirebaseAuth firebaseAuth;
    ProfileTracker profileTracker;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        getSupportActionBar().setTitle("PUBLICAR");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        storageReference=FirebaseStorage.getInstance().getReference();
        firebaseAuth = FirebaseAuth.getInstance();

        nombre=(EditText)findViewById(R.id.nombre);
        lugar = (EditText) findViewById(R.id.lugar);
        desc = (EditText) findViewById(R.id.desc);
        nomPersona=(EditText)findViewById(R.id.nombrePersona);
        numTel=(EditText)findViewById(R.id.telefono);
        eMail=(EditText)findViewById(R.id.email);
        perdido = (RadioButton)findViewById(R.id.Perdido);
        herido = (RadioButton)findViewById(R.id.Herido);
        adopcion = (RadioButton)findViewById(R.id.Adopcion);
        subir = (Button)findViewById(R.id.subir);
        foto = (ImageView)findViewById(R.id.imagenObjeto);

        foto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"SELECCIONE IMAGEN"),PICK_PHOTO);
            }
        });
        profileTracker=new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile oldProfile, Profile currentProfile) {
                if(currentProfile!=null){
                    displayProfileInfo(currentProfile);
                }
            }
        };
        if(AccessToken.getCurrentAccessToken()!=null) {
            requestEmail(AccessToken.getCurrentAccessToken());
            Profile profile = Profile.getCurrentProfile();
            if (profile != null) {
                displayProfileInfo(profile);
            } else {
                Profile.fetchProfileForCurrentAccessToken();
            }
        }
       subir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validarCampos()==true){
                    subir.setEnabled(false);
                    if(filePath!=null){
                        final StorageReference mStorage = storageReference.child("Perdidos").child(filePath.getLastPathSegment());
                        mStorage.putFile(filePath).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                            @Override
                            public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                                if(!task.isSuccessful()){
                                    throw new Exception();
                                }
                                return mStorage.getDownloadUrl();
                            }
                        }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                            @Override
                            public void onComplete(@NonNull Task<Uri> task) {
                                if(task.isSuccessful()){
                                    Uri link = task.getResult();
                                    String nomE = nombre.getText().toString();
                                    String lugE = lugar.getText().toString();
                                    String descE=desc.getText().toString();
                                    String nomP=nomPersona.getText().toString();
                                    String telP=numTel.getText().toString();
                                    String emailP=eMail.getText().toString();
                                    String id = databaseReference.push().getKey();//retorna id q asigna
                                    String tipo = validar();
                                    String u_id = firebaseAuth.getUid();
                                    Objeto obj = new Objeto(nomE, lugE,descE,nomP,telP,emailP,id, link.toString(),tipo, u_id);
                                    databaseReference.child("perdidos").child(id).setValue(obj);//Se pueded cambiar el ID para que sea otro identificador y no se repita
                                    Toast.makeText(getApplicationContext(),"OBJETO SUBIDO",Toast.LENGTH_SHORT).show();
                                    goPublicados();

                                }
                            }
                        });
                    }
                }
            }
        });

    }

    public  String validar(){
        String cad = "";

        if(perdido.isChecked()==true){
            cad = "Perdid@";
        }
        else if(herido.isChecked()==true){
            cad = "Herid@";
        }
        else if(adopcion.isChecked()==true){
            cad = "Adopción";
        }
        return  cad;
    }
    void  goPublicados(){
        Intent intent = new Intent(getApplicationContext(), menumain.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    private  void abrirFoto(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"SELECCIONE IMAGEN"),PICK_PHOTO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==PICK_PHOTO && resultCode==RESULT_OK && data != null &&data.getData()!=null){
             filePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),filePath);
                foto.setImageBitmap(bitmap);

            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }
    private void requestEmail(AccessToken currentAccessToken) {
        GraphRequest request = GraphRequest.newMeRequest(currentAccessToken, new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                if (response.getError() != null) {
                    Toast.makeText(getApplicationContext(), response.getError().getErrorMessage(), Toast.LENGTH_LONG).show();
                    return;
                }
                try {
                    String email = object.getString("email");
                    eMail.setText(email);
                } catch (JSONException e) {
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id, first_name, last_name, email, gender, birthday, location");
        request.setParameters(parameters);
        request.executeAsync();
    }
    private void displayProfileInfo(Profile currentProfile) {
        String name = currentProfile.getName();
        nomPersona.setText(name);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent miIntenr = new Intent(this,menumain.class);
        miIntenr.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(miIntenr);
        finish();
    }

    private Boolean validarCampos(){
        Boolean validar = true;
        if(TextUtils.isEmpty(nombre.getText().toString())){
            Toast.makeText(getApplicationContext(), "Campo OBJETO vacío", Toast.LENGTH_LONG).show();
            validar=false;
        }
        else if(TextUtils.isEmpty(lugar.getText().toString())){
            Toast.makeText(getApplicationContext(), "Campo LUGAR vacío", Toast.LENGTH_LONG).show();
            validar=false;
        }
        else if(TextUtils.isEmpty(desc.getText().toString())){
            Toast.makeText(getApplicationContext(), "Campo DESCRIPCIÓN vacío", Toast.LENGTH_LONG).show();
            validar=false;
        }
        else if(TextUtils.isEmpty(nomPersona.getText().toString())){
            Toast.makeText(getApplicationContext(), "Campo NOMBRE DE PERSONA vacío", Toast.LENGTH_LONG).show();
            validar=false;
        }
        else if(TextUtils.isEmpty(numTel.getText().toString())){
            Toast.makeText(getApplicationContext(), "Campo # DE TELEFONO vacío", Toast.LENGTH_LONG).show();
            validar=false;
        }
        else if(TextUtils.isEmpty(eMail.getText().toString())) {
            Toast.makeText(getApplicationContext(), "Campo EMAIL vacío", Toast.LENGTH_LONG).show();
            validar = false;
        }
        return validar;
    }

}
