package com.example.examenfinal;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class AdapterArticulos extends RecyclerView.Adapter<AdapterArticulos.ViewHolder> implements View.OnClickListener {
    private List<ModeloArticulo> modeloArticulos;
    private Context contexto;
    private View.OnClickListener listener;
    String urlarticulo = "https://revistas.uteq.edu.ec/index.php/cyt/article/view/";

    public AdapterArticulos(List<ModeloArticulo> modeloArticulos, Context contexto) {
        this.modeloArticulos = modeloArticulos;
        this.contexto = contexto;
    }

    @NonNull
    @Override
    public AdapterArticulos.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.revistas, parent, false);
        view.setOnClickListener(this);
        return new AdapterArticulos.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull AdapterArticulos.ViewHolder holder, int position) {

        holder.titulo.setText(modeloArticulos.get(position).getTitle());
        holder.resumen.setText(modeloArticulos.get(position).getResumen());
        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri u = Uri.parse(urlarticulo+modeloArticulos.get(position).getSubmission_id());
                Intent intent = new Intent(Intent.ACTION_VIEW, u);
                contexto.startActivity(intent);
            }
        });

    }


    @Override
    public int getItemCount() {
        return modeloArticulos.size();
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

        private TextView titulo;
        private TextView resumen;
        private Button btn;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            titulo = itemView.findViewById(R.id.Tituloarttxt);
            resumen = itemView.findViewById(R.id.resumenarttxt);
            btn = itemView.findViewById(R.id.btnhtml);

        }
    }
}
