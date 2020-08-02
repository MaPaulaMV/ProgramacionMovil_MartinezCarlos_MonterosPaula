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

    /**
     * Constructor vacío de la clase Presentador.
     */
    public Presentador(){

    }

    /**
     * Constructor con parámetros de la clase Presentador.
     *
     * @param iVistaLogin Vista que implementa la interfaz iChat.iVistaLogin
     */
    public Presentador(LoginActivity iVistaLogin){
        this.iVistaLogin = iVistaLogin;
        this.Modelo =new Modelo(this);
    }

    /**
     * Constructor con parámetros de la clase Presentador.
     *
     * @param iVistaHome Vista que implementa la interfaz iChat.iVistaHome
     */
    public Presentador(HomeActivity iVistaHome){
        this.iVistaHome = iVistaHome;
        this.Modelo =new Modelo(this);
    }

    /**
     * Constructor con parámetros de la clase Presentador.
     *
     * @param iVistaMain Vista que implementa la interfaz iChat.iVistaMain
     */
    public Presentador(MainActivity iVistaMain){
        this.iVistaMain = iVistaMain;
        this.Modelo =new Modelo(this);
    }

    /**
     * Método que obtiene el valor del atributo iVistaLogin de la clase Presentador
     *
     * @return iVistaLogin
     */
    public LoginActivity getiVistaLogin() {
        return iVistaLogin;
    }

    /**
     * Método que setea el valor del atributo iVistaLogin de la clase Presentador
     *
     * @param iVistaLogin
     */
    public void setiVistaLogin(LoginActivity iVistaLogin) {
        this.iVistaLogin = iVistaLogin;
    }

    /**
     * Método que obtiene el valor del atributo Modelo de la clase Presentador
     *
     * @return Modelo
     */
    public Modelo getModelo() {
        return Modelo;
    }

    /**
     * Método que setea el valor del atributo Modelo de la clase Presentador
     *
     * @param modelo
     */
    public void setModelo(Modelo modelo) {
        this.Modelo = modelo;
    }

    /**
     * Método que permite cambiar de la activity de registro al activityHome si es que
     * el registro del usuario fue exitoso.
     *
     * @param confirmacion Confirmación del registro de usuario
     * @param mensaje mensaje de error o exito del registro
     */
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

    /**
     * Método que envia los datos del usuario al modelo para realizar su registro
     * en la base de datos.
     *
     * @param nombre Nombre del Usuario
     * @param apellido Apellido del Usuario
     * @param u Uri de la foro de perfil del usuario
     * @param activity Activity de registro
     */
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

    /**
     * Método que para cerrar sesión del usuario.
     */
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

    /**
     * Método que llama al modelo para obtener información del usuario que logeado.
     *
     * @param textView TextView del activity
     * @param imageView ImageView del activity
     * @param context Context del Activity
     */
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

    /**
     * Método que llama al modelo para obtener a los usuarios registrados en la base.
     *
     * @param context Context del Activity
     * @param users RecyclerView del Activity
     */
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

    /**
     * Método que envia el contenido del mensaje al modelo.
     *
     * @param mensaje Contenido del mensaje
     * @param tipo Tipo del mensaje
     */
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

    /**
     * Método que envía la data de la imagen al modelo para enviar como mensaje.
     *
     * @param requestCode Request Code
     * @param resultCode result Code
     * @param data Data de la imagen
     */
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

    /**
     * Método que llama al modelo para obtener información del usuario.
     */
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

    /**
     * Método que llama al modelo para obtener información del usuario con el cual se interactua en el chat.
     *
     * @param user_id Id del usuario
     * @param textView TextView del Activity
     * @param imageView ImageView del Activity
     * @param context Context del Activity
     */
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

    /**
     * Método que llama al modelo para obtener los mensajes enviados anteriormente en un chat específico.
     *
     * @param context Context del Activity
     * @param mensajes recycleView del Activity
     */
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
