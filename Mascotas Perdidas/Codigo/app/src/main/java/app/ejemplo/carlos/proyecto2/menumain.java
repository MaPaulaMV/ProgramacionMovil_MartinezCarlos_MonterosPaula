/*
 * ESPE - DCC - PROGRAMACIÓN MÓVIL
 * NRC: 6112
 *
 * Sistema: Mascotas Perdidas
 * Creado 01/09/2020
 *
 * Los contenidos de este archivo son propiedad privada y estan protegidos por
 * la licencia BSD
 *
 * Se puede utilizar, reproducir o copiar el contenido de este archivo.
 */
package app.ejemplo.carlos.proyecto2;

/**
 * Clase que representa al menu principal
 *
 * @author Carlos Martínez
 * @author Paula Monteros
 */
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.widget.SearchView;
import app.ejemplo.carlos.proyecto2.Fragments.InfoActivity;
import app.ejemplo.carlos.proyecto2.Intros.Intro;

import com.facebook.AccessToken;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginManager;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class menumain extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener ,android.support.v7.widget.SearchView.OnQueryTextListener {

    String name;
    ProfileTracker profileTracker;
    DatabaseReference databaseReference;

    TextView nom;
    ArrayList<Objeto> list = new ArrayList<Objeto>();
    private List<String> listnames = new ArrayList<>();
    private List<Objeto> listnewO = new ArrayList<Objeto>();
    private AdapterDatos adapterDatos;
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_menumain);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        recyclerView = (RecyclerView)findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));
        databaseReference = FirebaseDatabase.getInstance().getReference().child("perdidos");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (final DataSnapshot snapshot : dataSnapshot.getChildren()){

                    Objeto objeto = snapshot.getValue(Objeto.class);
                    list.add(objeto);
                    listnames.add(objeto.getNombre_mascota());
                    listnewO.add(objeto);
                }
                Collections.reverse(list);
                adapterDatos = new AdapterDatos(list,getApplicationContext());
                recyclerView.setAdapter(adapterDatos);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("error", databaseError.getDetails());
                Intent intent = new Intent(getApplicationContext(),LogInActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | intent.FLAG_ACTIVITY_CLEAR_TASK|intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                Toast.makeText(getApplicationContext(),"Ups hubo un problema enseguida lo arreglamos",Toast.LENGTH_SHORT).show();

            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goMainScreen();
            }
        });


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View hview=navigationView.getHeaderView(0);
        nom = hview.findViewById(R.id.textNombreP);

        if(AccessToken.getCurrentAccessToken()==null){
            goIntro();
        }
        else if (AccessToken.getCurrentAccessToken()!=null) {
            Profile profile = Profile.getCurrentProfile();
            if (profile != null) {
                displayProfileInfo(profile);
            } else {
                Profile.fetchProfileForCurrentAccessToken();
            }
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        MenuItem menuItem= menu.findItem(R.id.action_buscar);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(this);

        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            goMainScreen();
        } else if (id == R.id.nav_gallery) {
            goPublicados();
        } else if (id == R.id.nav_slideshow) {
            goInfo();
        }/* else if (id == R.id.nav_share) {

        } */else if (id == R.id.nav_send) {
            LoginManager.getInstance().logOut();
            goCerrarSesion();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    private void goLogInScreen() {
        Intent intent = new Intent(getApplicationContext(), LogInActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        //Toast.makeText(this, "HA CERRADDO SESION", Toast.LENGTH_SHORT).show();
    }

    private void goCerrarSesion() {
        Intent intent = new Intent(getApplicationContext(), LogInActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        Toast.makeText(this, "HA CERRADDO SESION", Toast.LENGTH_SHORT).show();
    }
    private void goMainScreen() {
        Intent intent = new Intent(this,Base.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | intent.FLAG_ACTIVITY_CLEAR_TASK|intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
    private void goPublicados() {
        Intent intent = new Intent(this,SearchActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | intent.FLAG_ACTIVITY_CLEAR_TASK|intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
    private void goIntro() {
        Intent intent = new Intent(this, Intro.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | intent.FLAG_ACTIVITY_CLEAR_TASK|intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
    private void goInfo() {
        Intent intent = new Intent(this,InfoActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | intent.FLAG_ACTIVITY_CLEAR_TASK|intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {

        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {

        String userInput = newText.toLowerCase();
        ArrayList<Objeto> newList= new ArrayList<>();

        for(Objeto objeto:list){
            if(objeto.getNombre_mascota().toLowerCase().contains(userInput))
            {
                newList.add(objeto);
            }
        }



        adapterDatos.updateList(newList);
        return true;
    }

    public static class PublicadosViewHolder extends  RecyclerView.ViewHolder{
        View myView ;

        public PublicadosViewHolder (View itemView){
            super(itemView);
            myView=itemView;
        }
    }
    private void displayProfileInfo(Profile currentProfile) {
        String name = currentProfile.getName();

        nom.setText("Bienvenido "+ name);
    }

}
