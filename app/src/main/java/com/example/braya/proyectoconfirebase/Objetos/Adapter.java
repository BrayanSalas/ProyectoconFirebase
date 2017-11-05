package com.example.braya.proyectoconfirebase.Objetos;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.braya.proyectoconfirebase.R;

import java.util.List;

/**
 * Created by braya on 16/10/2017.
 */

public class Adapter extends RecyclerView.Adapter<Adapter.BebesviewHolder>{

    List<Bebe> bebes;

    public Adapter(List<Bebe> bebes) {
        this.bebes = bebes;
    }

    @Override
    public BebesviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_recycler, parent, false);
        BebesviewHolder holder = new BebesviewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(BebesviewHolder holder, int position) {
        Bebe bebe = bebes.get(position);
        holder.textview_nombre.setText(bebe.getNombre());
        holder.textview_años.setText(String.valueOf(bebe.getAños()));
    }

    @Override
    public int getItemCount() {
        return bebes.size();
    }

    public static class BebesviewHolder extends RecyclerView.ViewHolder{
        TextView textview_nombre, textview_años;

        public BebesviewHolder(View itemView) {
            super(itemView);
            textview_nombre = (TextView) itemView.findViewById(R.id.textview_nombre);
            textview_años = (TextView) itemView.findViewById(R.id.textview_años);
        }
    }

}
