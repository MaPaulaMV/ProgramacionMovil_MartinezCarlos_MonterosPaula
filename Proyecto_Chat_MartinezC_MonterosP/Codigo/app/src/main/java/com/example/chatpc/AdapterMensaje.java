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

public class AdapterMensaje extends RecyclerView.Adapter<HolderMensaje> {
    private  List<Mensaje> list = new ArrayList<>();
    private Context context;

    public AdapterMensaje(Context context) {
        this.context = context;
    }

    public void addMensaje(Mensaje mensaje){
        list.add(mensaje);
        notifyItemInserted(list.size());
    }

    @NonNull
    @Override
    public HolderMensaje onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_view_mensajes,parent,false);
        return new HolderMensaje(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderMensaje holder, int position) {
        holder.getNombre().setText(list.get(position).getNombre());
        holder.getMensaje().setText(list.get(position).getMensaje());
        holder.getHora().setText(list.get(position).getHora());
        Glide.with(context).load(list.get(position).getFotoPerfil()).into(holder.getFotomensaje());

        if("2".equals(list.get(position).getTipoMensaje())){
            holder.getImagenmensaje().setVisibility(View.VISIBLE);
            holder.getMensaje().setVisibility(View.VISIBLE);
            Glide.with(context).load(list.get(position).getUrlFoto()).into(holder.getImagenmensaje());

        }else{
            holder.getImagenmensaje().setVisibility(View.GONE);
            holder.getMensaje().setVisibility(View.VISIBLE);
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
