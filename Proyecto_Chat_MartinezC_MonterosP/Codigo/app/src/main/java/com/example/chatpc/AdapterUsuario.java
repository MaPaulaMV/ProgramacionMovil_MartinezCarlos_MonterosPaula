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
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Clase que se encarga de llenar con los datos de los usuarios registrados
 * los Card View Usuario.
 *
 * @author Carlos Martínez
 * @author Paula Monteros
 */
public class AdapterUsuario extends RecyclerView.Adapter<AdapterUsuario.ViewHolder> {

    private Context context;
    private List<Usuario> usuarios;

    /**
     * Constructor con parámetros de la clase AdapterUsuario.
     *
     * @param context Context del Activity
     * @param usuarios Lista de usuarios
     */
    public AdapterUsuario (Context context, List<Usuario>usuarios){
        this.usuarios = usuarios;
        this.context = context;
    }

    /**
     * étodo que se encarga de inicializar los elementos del Holder para los Usuarios.
     *
     * @param parent ViewGroup
     * @param viewType Tipo de vista
     * @return Holder para los card view usuarios.
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_view_usuario,parent,false);
        return new AdapterUsuario.ViewHolder(view);
    }

    /**
     *
     * @param holder Holder de Usuarios
     * @param position Posición en la lista de usuarios
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Usuario usuario = usuarios.get(position);
        holder.username.setText(usuario.getNombre() + usuario.getApellido());
        Glide.with(context).load(usuario.getFoto()).into(holder.foto);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(context, MainActivity.class);
                intent.putExtra("userid", usuario.getId());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

    }

    /**
     * Método que obtiene el número de elementos en la lista.
     *
     * @return Número de usuarios en la lista.
     */
    @Override
    public int getItemCount() {
        return usuarios.size();
    }

    /**
     * Método que obtiene los elementos del card view Usuarios por medio de IDs.
     */
    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView username;
        public ImageView foto;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            username=itemView.findViewById(R.id.usuario_nombre);
            foto=itemView.findViewById(R.id.usuario_imagen);
        }
    }
}
