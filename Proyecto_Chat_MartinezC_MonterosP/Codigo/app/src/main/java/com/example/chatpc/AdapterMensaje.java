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

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que se encarga de llenar con los datos de los mensajes enviados
 * los Card View Mensaje.
 *
 * @author Carlos Martínez
 * @author Paula Monteros
 */
public class AdapterMensaje extends RecyclerView.Adapter<HolderMensaje> {
    private  List<Mensaje> list = new ArrayList<>();
    private Context context;

    /**
     * Constructor con parámetros de la clase AdpaterMensaje.
     *
     * @param context Context de un Activity
     */
    public AdapterMensaje(Context context) {
        this.context = context;
    }

    /**
     * Método que añade elementos a la lista, añade mensajes.
     *
     * @param mensaje Mensaje
     */
    public void addMensaje(Mensaje mensaje){
        list.add(mensaje);
        notifyItemInserted(list.size());
    }

    /**
     * Método que se encarga de inicializar los elementos del HolderMensaje.
     *
     * @param parent ViewGroup
     * @param viewType Tipo de Vista
     * @return HolderMensaje
     */
    @NonNull
    @Override
    public HolderMensaje onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_view_mensajes,parent,false);
        return new HolderMensaje(view);
    }

    /**
     * Método que infla o llena de datos el card view mensaje.
     *
     * @param holder HolderMensaje
     * @param position Posición en la lista de mensajes
     */
    @Override
    public void onBindViewHolder(@NonNull HolderMensaje holder, int position) {
        holder.getNombre().setText(list.get(position).getNombre());
        holder.getMensaje().setText(list.get(position).getMensaje());
        holder.getHora().setText(list.get(position).getHora());
        Glide.with(context).load(list.get(position).getFotoPerfil()).into(holder.getFotoMensaje());

        if("2".equals(list.get(position).getTipoMensaje())){
            holder.getImagenMensaje().setVisibility(View.VISIBLE);
            holder.getMensaje().setVisibility(View.VISIBLE);
            Glide.with(context).load(list.get(position).getUrlFoto()).into(holder.getImagenMensaje());

        }else{
            holder.getImagenMensaje().setVisibility(View.GONE);
            holder.getMensaje().setVisibility(View.VISIBLE);
        }

    }

    /**
     * Método que obtiene el número de elementos en la lista.
     *
     * @return Número de mensajes.
     */
    @Override
    public int getItemCount() {
        return list.size();
    }
}
