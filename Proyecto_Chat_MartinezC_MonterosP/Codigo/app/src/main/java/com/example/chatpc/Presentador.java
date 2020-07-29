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

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que actúa como intermediario entre la Vista y
 * el Modelo de la aplicación, implementa la interfaz iChat.iPresentador.
 *
 * @author Carlos Martínez
 * @author Paula Monteros
 */
public class Presentador implements  iChat.iPresentador {
    private LoginActivity iVistaLogin;
    private HomeActivity iVistaHome;
    private MainActivity iVistaMain;
    private Modelo Modelo;

    public Presentador(){

    }

    public Presentador(LoginActivity iVistaLogin){
        this.iVistaLogin = iVistaLogin;
        this.Modelo =new Modelo(this);
    }

    public Presentador(HomeActivity iVistaHome){
        this.iVistaHome = iVistaHome;
        this.Modelo =new Modelo(this);
    }
    public Presentador(MainActivity iVistaMain){
        this.iVistaMain = iVistaMain;
        this.Modelo =new Modelo(this);
    }

    public LoginActivity getiVistaLogin() {
        return iVistaLogin;
    }

    public void setiVistaLogin(LoginActivity iVistaLogin) {
        this.iVistaLogin = iVistaLogin;
    }

    public Modelo getModelo() {
        return Modelo;
    }

    public void setModelo(Modelo modelo) {
        this.Modelo = modelo;
    }

    @Override
    public void cambiarActivityP(boolean confirmacion, String mensaje) {
        try{
            if(iVistaLogin!=null){
                iVistaLogin.cambiarActivityV(confirmacion,mensaje);
            }
        }catch (Exception e){
            Log.e("ERROR", e.getMessage());
        }
    }

    @Override
    public void registrarUsuarioP(String nombre, String apellido, Uri u, Activity activity) {
        try{
            if(iVistaLogin!=null){
                Modelo.registrarUsuarioM( nombre,  apellido,  u, activity);
            }
        }catch (Exception e){
            Log.e("ERROR", e.getMessage());
        }
    }

    @Override
    public void cerrarSesionP() {
        try{
            if(iVistaHome!=null){
                Modelo.cerrarSesionM();
            }
        }catch (Exception e){
            Log.e("ERROR", e.getMessage());
        }
    }

    @Override
    public void obtenerInfoP(TextView textView, ImageView imageView, Context context) {
        List<String> adapterUsuario=new ArrayList<String>();
        try{
            if(iVistaHome!=null){
                Modelo.obtenerInfoM(textView, imageView, context);

            }

        }catch (Exception e){
            Log.e("ERROR", e.getMessage());
        }

    }

    @Override
    public void leerUsuariosP(Context context, RecyclerView users) {
        AdapterUsuario adapterUsuario = null;
        try{
            if(iVistaHome!=null){
               Modelo.leerUsuariosM(context, users);
            }
        }catch (Exception e){
            Log.e("ERROR", e.getMessage());
        }
    }

    @Override
    public void enviarMensajeP(String mensaje, String tipo) {
        try{
            if(iVistaMain!=null){
                Modelo.enviarMensajeM(mensaje, tipo);

            }

        }catch (Exception e){
            Log.e("ERROR", e.getMessage());
        }
    }

    @Override
    public void enviarFotoP(int requestCode, int resultCode, @Nullable Intent data) {
        try{
            if(iVistaMain!=null){
                Modelo.enviarFotoM(requestCode, resultCode,data);

            }

        }catch (Exception e){
            Log.e("ERROR", e.getMessage());
        }
    }

    @Override
    public void infoUsuarioP() {
        try{
            if(iVistaMain!=null){
                Modelo.infoUsuarioM();

            }

        }catch (Exception e){
            Log.e("ERROR", e.getMessage());
        }
    }

    @Override
    public void infoContactoP(String user_id,TextView textView, ImageView imageView, Context context) {
        try{
            if(iVistaMain!=null){
                Modelo.infoContactoM(user_id,textView,imageView,context);

            }

        }catch (Exception e){
            Log.e("ERROR", e.getMessage());
        }
    }

    @Override
    public void leerMensajesP(Context context, RecyclerView mensajes) {
        try{
            if(iVistaMain!=null){
                Modelo.leerMensajesM(context,mensajes);
            }
        }catch (Exception e){
            Log.e("ERROR", e.getMessage());
        }
    }
}
