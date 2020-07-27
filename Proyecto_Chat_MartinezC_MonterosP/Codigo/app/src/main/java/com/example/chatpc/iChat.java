package com.example.chatpc;
import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public interface iChat {

    interface iVistaLogin{
        void cambiarActivityV(boolean confirmacion, String mensaje);
    }

    interface iVistaHome{

    }

    interface iPresentador{
        void cambiarActivityP(boolean confirmacion, String mensaje);
        void registrarUsuarioP(String nombre, String apellido, Uri u, Activity activity);
        void cerrarSesionP();
        void obtenerInfoP(TextView textView, ImageView imageView, Context context);
        void leerUsuariosP(Context context, RecyclerView users);

    }

    interface iModelo{
        void registrarUsuarioM(String nombre, String apellido, Uri u, Activity activity);
        void cerrarSesionM();
        void obtenerInfoM(TextView textView, ImageView imageView, Context context);
        void leerUsuariosM(Context context, RecyclerView users);
    }
}
