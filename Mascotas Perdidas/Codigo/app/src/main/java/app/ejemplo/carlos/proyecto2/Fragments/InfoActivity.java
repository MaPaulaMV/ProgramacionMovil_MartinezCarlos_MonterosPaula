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
package app.ejemplo.carlos.proyecto2.Fragments;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import app.ejemplo.carlos.proyecto2.R;
import app.ejemplo.carlos.proyecto2.menumain;

import app.ejemplo.carlos.proyecto2.menumain;

public class InfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
}
