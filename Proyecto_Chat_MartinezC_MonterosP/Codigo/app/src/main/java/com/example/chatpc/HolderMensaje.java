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

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import de.hdodenhof.circleimageview.CircleImageView;

public class HolderMensaje extends RecyclerView.ViewHolder {

    private TextView mensaje;
    private TextView nombre;
    private TextView hora;
    private CircleImageView fotomensaje;
    private ImageView imagenmensaje;


    public TextView getMensaje() {
        return mensaje;
    }

    public void setMensaje(TextView mensaje) {
        this.mensaje = mensaje;
    }

    public TextView getNombre() {
        return nombre;
    }

    public void setNombre(TextView nombre) {
        this.nombre = nombre;
    }

    public TextView getHora() {
        return hora;
    }

    public void setHora(TextView hora) {
        this.hora = hora;
    }

    public CircleImageView getFotomensaje() {
        return fotomensaje;
    }

    public void setFotomensaje(CircleImageView fotomensaje) {
        this.fotomensaje = fotomensaje;
    }

    public HolderMensaje(@NonNull View itemView) {
        super(itemView);
        mensaje = (TextView) itemView.findViewById(R.id.mensajemensaje);
        nombre = (TextView) itemView.findViewById(R.id.nombremensaje);
        hora = (TextView) itemView.findViewById(R.id.fechamensaje);
        fotomensaje = (CircleImageView) itemView.findViewById(R.id.fotomensaje);
        imagenmensaje =(ImageView)itemView.findViewById(R.id.imagenmensaje);

    }

    public ImageView getImagenmensaje() {
        return imagenmensaje;
    }

    public void setImagenmensaje(ImageView imagenmensaje) {
        this.imagenmensaje = imagenmensaje;
    }
}
