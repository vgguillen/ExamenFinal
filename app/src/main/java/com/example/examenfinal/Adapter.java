package com.example.examenfinal;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.io.Serializable;
import java.util.List;


public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> implements View.OnClickListener {
    private List<ModeloRevista> Revistalista;
    private Context contexto;
    private View.OnClickListener listener;

    public Adapter(List<ModeloRevista> revistalista, Context contexto) {
        this.Revistalista = revistalista;
        this.contexto = contexto;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.revistas, parent, false);
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {

        holder.nombre.setText(Revistalista.get(position).getName());
        holder.id.setText(Revistalista.get(position).getJournal_id());
        holder.descripcion.setText(Revistalista.get(position).getDescription());
        Glide.with(contexto)
                .load(Revistalista.get(position).getPortada())
                .centerCrop()
                .into(holder.fotorevistaimg);

    }



    @Override
    public int getItemCount() {
        return Revistalista.size();
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        if(listener!=null){
            listener.onClick(v);
        }
    }




    public static class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView fotorevistaimg;
        private TextView nombre;
        private TextView id;
        private TextView descripcion;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            fotorevistaimg = itemView.findViewById(R.id.img);
            nombre = itemView.findViewById(R.id.Titulorevistatxt);
            id = itemView.findViewById(R.id.IDrevistatxt);
            descripcion = itemView.findViewById(R.id.Descripcionrevistatxt);
        }
    }
}
