package com.example.chatpc;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class iPresentador implements  iChat.iPresentador {
    private LoginActivity iVistaLogin;
    private HomeActivity iVistaHome;
    private MainActivity iVistaMain;
    private iModelo iModelo;

    public iPresentador(){

    }

    public iPresentador(LoginActivity iVistaLogin){
        this.iVistaLogin = iVistaLogin;
        this.iModelo=new iModelo(this);
    }
    public iPresentador(HomeActivity iVistaHome){
        this.iVistaHome = iVistaHome;
        this.iModelo=new iModelo(this);
    }

    public iPresentador(MainActivity iVistaMain){
        this.iVistaMain = iVistaMain;
        this.iModelo=new iModelo(this);
    }

    public LoginActivity getiVistaLogin() {
        return iVistaLogin;
    }

    public void setiVistaLogin(LoginActivity iVistaLogin) {
        this.iVistaLogin = iVistaLogin;
    }

    public com.example.chatpc.iModelo getiModelo() {
        return iModelo;
    }

    public void setiModelo(com.example.chatpc.iModelo iModelo) {
        this.iModelo = iModelo;
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
                iModelo.registrarUsuarioM( nombre,  apellido,  u, activity);
            }
        }catch (Exception e){
            Log.e("ERROR", e.getMessage());
        }
    }

    @Override
    public void cerrarSesionP() {
        try{
            if(iVistaHome!=null){
                iModelo.cerrarSesionM();
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
                iModelo.obtenerInfoM(textView, imageView, context);

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
               iModelo.leerUsuariosM(context, users);
            }
        }catch (Exception e){
            Log.e("ERROR", e.getMessage());
        }
    }

    @Override
    public void enviarMensajeP(String mensaje, String tipo) {
        try {
            if(iVistaMain!=null){
                iModelo.enviarMensajeM(mensaje, tipo);
            }
        }catch(Exception e){
            Log.e("ERROR", e.getMessage());
        }
    }

    @Override
    public void perfilusuarioP() {
        try {
            if(iVistaMain!=null){
                iModelo.perfilusuarioM();
            }
        }catch(Exception e){
            Log.e("ERROR", e.getMessage());
        }
    }

    @Override
    public void receptorInfoP(TextView textView, ImageView imageView, Context context) {
        try {
            if(iVistaMain!=null){
                iModelo.receptorInfoM(textView,imageView,context);
            }
        }catch(Exception e){
            Log.e("ERROR", e.getMessage());
        }
    }

    @Override
    public void crearSesionSalaP(AdapterMensaje adapterMensaje) {
        try {
            if(iVistaMain!=null){
                iModelo.crearSesionSalaM(adapterMensaje);
            }
        }catch(Exception e){
            Log.e("ERROR", e.getMessage());
        }
    }
}
