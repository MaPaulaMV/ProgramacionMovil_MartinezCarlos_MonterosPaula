package app.ejemplo.carlos.proyecto2;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.ArrayList;

public class AdapterDatos extends RecyclerView.Adapter<AdapterDatos.ViewHolderDatos> {

    ArrayList<Objeto> list = new ArrayList<>();
    ArrayList<Objeto> listOb = new ArrayList<>();
    Context context;

    public AdapterDatos(ArrayList<Objeto> list, Context context1) {
        this.list = list;
        this.context=context1;
    }

    @NonNull
    @Override
    public AdapterDatos.ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list,viewGroup,false);
        return new ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final AdapterDatos.ViewHolderDatos viewHolderDatos, final int i) {
        viewHolderDatos.nombre.setText(list.get(i).getNombre_mascota());
        viewHolderDatos.lugar.setText(list.get(i).getMapa());
        viewHolderDatos.desc.setText(list.get(i).getDesc());
        //viewHolderDatos.id.setText(list.get(i).getId());

        Glide.with(context).load(list.get(i).getUrl()).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                viewHolderDatos.imagen.setImageResource(R.drawable.ic_error_black_24dp);
                return false;
            }
            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                viewHolderDatos.imagen.setVisibility(View.VISIBLE);
                return false;
            }
        }).into(viewHolderDatos.imagen);

        viewHolderDatos.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent miIntent = new Intent(context,Descripcion.class);
                miIntent.putExtra("idObjeto",list.get(i).getId_mascota());
                miIntent.addFlags(miIntent.FLAG_ACTIVITY_CLEAR_TASK|miIntent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(miIntent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {

        TextView nombre,id;
        TextView desc, lugar;
        LinearLayout parentLayout;
        ImageView imagen;

        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.idNombre);
            desc = itemView.findViewById(R.id.idInfo);
            lugar = itemView.findViewById(R.id.idLugar);
           // id=itemView.findViewById(R.id.idObj);
            imagen=itemView.findViewById(R.id.imgImgen);
            parentLayout=itemView.findViewById(R.id.parent_layout);

        }


    }

    public void updateList( ArrayList<Objeto> newList){
         list = new ArrayList<>();
         list.addAll(newList);
         notifyDataSetChanged();
    }
}