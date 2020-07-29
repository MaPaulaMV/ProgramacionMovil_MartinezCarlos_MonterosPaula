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
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * Interfaz principal del programa, donde se declara todos los métodos
 * que se van a usar para que puedan ser implementados por las diferentes clases.
 *
 * @author Carlos Martínez
 * @author Paula Monteros
 */
public interface iChat {

    /**
     * Interfaz iVistaLogin que declara los métodos que van a ser utilizados por las clases
     * que implementen esta interfaz.
     */
    interface iVistaLogin{
        void cambiarActivityV(boolean confirmacion, String mensaje);
    }

    /**
     * Interfaz iPresentador que declara los métodos que van a ser utilizados por las clases
     * que implementen esta interfaz.
     */
    interface iPresentador{
        void cambiarActivityP(boolean confirmacion, String mensaje);
        void registrarUsuarioP(String nombre, String apellido, Uri u, Activity activity);
        void cerrarSesionP();
        void obtenerInfoP(TextView textView, ImageView imageView, Context context);
        void leerUsuariosP(Context context, RecyclerView users);
        void enviarMensajeP(String mensaje, String tipo);
        void enviarFotoP(int requestCode, int resultCode, @Nullable Intent data);
        void infoUsuarioP();
        void infoContactoP(String user_id,TextView textView, ImageView imageView, Context context);
        void leerMensajesP(Context context, RecyclerView mensajes);

    }

    /**
     * Interfaz iModelo que declara los métodos que van a ser utilizados por las clases
     * que implementen esta interfaz.
     */
    interface iModelo{
        void registrarUsuarioM(String nombre, String apellido, Uri u, Activity activity);
        void cerrarSesionM();
        void obtenerInfoM(TextView textView, ImageView imageView, Context context);
        void leerUsuariosM(Context context, RecyclerView users);
        void enviarMensajeM(String mensaje, String tipo);
        void enviarFotoM(int requestCode, int resultCode, @Nullable Intent data);
        void infoUsuarioM();
        void infoContactoM(String user_id,TextView textView, ImageView imageView, Context context);
        void leerMensajesM(Context context, RecyclerView mensajes);
    }
}
