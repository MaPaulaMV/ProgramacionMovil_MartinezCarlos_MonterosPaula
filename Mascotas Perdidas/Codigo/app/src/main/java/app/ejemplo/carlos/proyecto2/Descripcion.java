package app.ejemplo.carlos.proyecto2;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Descripcion extends AppCompatActivity {

    TextView nomO, lugO,descO;
    TextView nomP,telP,emailP, tipo;
    DatabaseReference databaseReference;
    ImageView imageView;
    String id;
    Button btnWhats;
    String url;
    boolean isImageFitToScreen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descripcion);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        FloatingActionButton fab1=findViewById(R.id.cllamar);
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showToast("Llamar");
                onClickTelef(v);

            }
        });
        FloatingActionButton fab2=findViewById(R.id.cwhatsapp);
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("Whatsapp");
                onClickWhats(v);
            }
        });
        nomO = (TextView)findViewById(R.id.nombre);
        lugO = (TextView)findViewById(R.id.lugar);
        descO = (TextView)findViewById(R.id.desc);
        tipo = (TextView)findViewById(R.id.tipo);

        nomP = (TextView)findViewById(R.id.nombrePersona);
        telP = (TextView)findViewById(R.id.telefono);
        emailP = (TextView)findViewById(R.id.email);


        imageView=(ImageView)findViewById(R.id.imgDesc);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isImageFitToScreen) {
                    isImageFitToScreen=false;
                    imageView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    imageView.setAdjustViewBounds(true);
                }else{
                    isImageFitToScreen=true;
                    imageView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                }
            }
        });
        getIncomingIntent();

    }

    public void showToast(String message){

        Toast.makeText(this, message,Toast.LENGTH_SHORT).show();
    }
    private  void  getIncomingIntent(){
        if(getIntent().hasExtra("idObjeto")){
             id = getIntent().getStringExtra("idObjeto");
            setInfo(id);
        }
    }
    private void setInfo(final String id){
        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("perdidos").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (final DataSnapshot snapshot : dataSnapshot.getChildren()){

                    if(id.equals(snapshot.getValue(Objeto.class).getId_mascota())){

                        Objeto objeto = snapshot.getValue(Objeto.class);
                        nomO.setText("MASCOTA: "+objeto.getNombre_mascota());
                        lugO.setText("LUGAR: "+objeto.getMapa());
                        descO.setText("DESCRIPCION: "+ objeto.getDesc());
                        nomP.setText("NOMBRE: "+objeto.getNomPersona());
                        telP.setText("CELULAR: "+objeto.getNumCelular());
                        emailP.setText("EMAIL: "+objeto.getEmail());
                        url=objeto.getNumCelular();
                        tipo.setText("TIPO: " + objeto.getTipo());

                        Glide.with(getApplicationContext()).load(objeto.getUrl()).listener(new RequestListener<Drawable>() {
                            @Override
                            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                                imageView.setImageResource(R.drawable.ic_error_black_24dp);
                                return false;
                            }
                            @Override
                            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                                imageView.setVisibility(View.VISIBLE);
                                return false;
                            }
                        }).into(imageView);

                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
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
    public void onClickWhats(View view) {

        try {
            String link=generarUrl(url);
            Uri uri = Uri.parse(link);
            Intent intent = new Intent(Intent.ACTION_VIEW,uri);
            startActivity(intent);
        }
        catch (Exception e){
            Toast.makeText(getApplicationContext(),"ERROR AL ABRIR WHATSAPP",Toast.LENGTH_SHORT).show();
        }

    }
    private String generarUrl(String numero){
        return "https://wa.me/593"+numero;
    }

    public void onClickTelef(View view) {
        try {
            String telef="tel:" + url;
            Log.e("NUMERO: ",telef);
            startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse(telef)));

        }
        catch (Exception e ){
            Log.e("ERROR","");
            Toast.makeText(getApplicationContext(),"NO ENTRO",Toast.LENGTH_SHORT).show();
        }
    }
}
