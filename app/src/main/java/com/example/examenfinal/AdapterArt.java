package com.example.examenfinal;

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

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class AdapterArt extends RecyclerView.Adapter<AdapterArt.ViewHolder> implements View.OnClickListener{
    private List<RevistaDetalleModelo> Revistalista;
    private Context contexto;
    private View.OnClickListener listener;

    public AdapterArt(List<RevistaDetalleModelo> revistalista, Context contexto) {
        this.Revistalista = revistalista;
        this.contexto = contexto;
    }
    @NonNull
    @Override
    public AdapterArt.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.revistas_detalles, parent, false);

        return new AdapterArt.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull AdapterArt.ViewHolder holder, int position) {
        holder.titulo.setText(Revistalista.get(position).getTitle());
        holder.id.setText(Revistalista.get(position).getIssue_id());
        holder.publicacion.setText(Revistalista.get(position).getDate_published());
        holder.volumen.setText(Revistalista.get(position).getVolume());
        Glide.with(contexto)
                .load(Revistalista.get(position).getCover())
                .centerCrop()
                .into(holder.foto);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(), Articulos.class);
                intent.putExtra("id", Revistalista.get(position).getIssue_id());
                holder.itemView.getContext().startActivity(intent);
            }
        });
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

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView foto;
        private TextView titulo;
        private TextView id;
        private TextView publicacion;
        private TextView volumen;


        public ViewHolder(@NonNull  View itemView) {
            super(itemView);
            foto = itemView.findViewById(R.id.img);
            titulo = itemView.findViewById(R.id.Titulorevistatxt);
            id = itemView.findViewById(R.id.IDrevistatxt);
            publicacion = itemView.findViewById(R.id.Fecharevistatxt);
            volumen = itemView.findViewById(R.id.Volumenrevistatxt);
        }
    }
}
