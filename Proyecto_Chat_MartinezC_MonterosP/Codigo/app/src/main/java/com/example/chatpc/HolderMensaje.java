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

/**
 * Clase que actua como modelo del Card View Mensaje, representa
 * al card view.
 *
 * @author Carlos Martínez
 * @author Paula Monteros
 */
public class HolderMensaje extends RecyclerView.ViewHolder {

    private TextView mensaje;
    private TextView nombre;
    private TextView hora;
    private CircleImageView fotoMensaje;
    private ImageView imagenMensaje;

    /**
     * Método que obtiene el valor del atributo Mensaje de la clase HolderMensaje.
     *
     * @return Mensaje
     */
    public TextView getMensaje() {
        return mensaje;
    }

    /**
     * Método que setea el valor del atributo Mensaje de la clase HolderMensaje
     *
     * @param mensaje
     */
    public void setMensaje(TextView mensaje) {
        this.mensaje = mensaje;
    }

    /**
     * Método que obtiene el valor del atributo Nombre de la clase HolderMensaje.
     *
     * @return Nombre del remitente del mensaje.
     */
    public TextView getNombre() {
        return nombre;
    }

    /**
     * Método que setea el valor del atributo Nombre de la clase HolderMensaje.
     *
     * @param nombre Nombre del usuario remitente del mensaje
     */
    public void setNombre(TextView nombre) {
        this.nombre = nombre;
    }

    /**
     * Método que obtiene el valor del atributo Hora de la clase HolderMensaje
     *
     * @return Hora de envío del mensaje.
     */
    public TextView getHora() {
        return hora;
    }

    /**
     * Método que setea el valor del atributo Hora de la clase HolderMensaje.
     *
     * @param hora Hora de envío del mensaje.
     */
    public void setHora(TextView hora) {
        this.hora = hora;
    }

    /**
     * Método que obtiene el valor del atributo FotoMensaje de la clase HolderMensaje.
     *
     * @return Foto de perfil del usuario que envía el mensaje.
     */
    public CircleImageView getFotoMensaje() {
        return fotoMensaje;
    }

    /**
     * Método que setea el valor del atributo FotoMensaje de la clase HolderMensaje.
     *
     * @param fotoMensaje Foto de perfil del usuario que envía el mensaje.
     */
    public void setFotoMensaje(CircleImageView fotoMensaje) {
        this.fotoMensaje = fotoMensaje;
    }

    /**
     * Método que setea cada elemento del HolderMensaje igualandolo a los elementos del card View mensaje
     * por medio de IDs.
     *
     * @param itemView itemView
     */
    public HolderMensaje(@NonNull View itemView) {
        super(itemView);
        mensaje = (TextView) itemView.findViewById(R.id.mensajemensaje);
        nombre = (TextView) itemView.findViewById(R.id.nombremensaje);
        hora = (TextView) itemView.findViewById(R.id.fechamensaje);
        fotoMensaje = (CircleImageView) itemView.findViewById(R.id.fotomensaje);
        imagenMensaje =(ImageView)itemView.findViewById(R.id.imagenmensaje);

    }

    /**
     * Método que obtiene el valor del atributo ImagenMensaje de la clase HolderMensaje.
     *
     * @return Imagen enviada por el usuario como mensaje.
     */
    public ImageView getImagenMensaje() {
        return imagenMensaje;
    }

    /**
     * Método que setea el valor del atributo ImagenMensaje de la clase HolderMensaje.
     *
     * @param imagenMensaje Imagen enviada por el usuario.
     */
    public void setImagenMensaje(ImageView imagenMensaje) {
        this.imagenMensaje = imagenMensaje;
    }
}
