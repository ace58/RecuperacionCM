package com.example.mapaestaciones;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class VehiculoAdapter extends RecyclerView.Adapter<VehiculoAdapter.ViewHolder> {

    private ArrayList<Vehiculo> mDataset;

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        Context context;
        public TextView v_marca;
        public TextView v_modelo;
        public TextView v_precio;
        public TextView v_matricula;
        Button btn_seleccionar_vehiculo;

        public ViewHolder(View v) {
            super(v);
            context = v.getContext();
            v_marca = (TextView) v.findViewById(R.id.v_marca);
            v_modelo = (TextView) v.findViewById(R.id.v_modelo);
            v_precio = (TextView) v.findViewById(R.id.v_precio);
            v_matricula = (TextView) v.findViewById(R.id.v_matricula);
            btn_seleccionar_vehiculo = (Button) v.findViewById(R.id.btn_seleccionar_vehiculo);
        }

        void setOnClickListeners(){
            btn_seleccionar_vehiculo.setOnClickListener(this);
        }

        public void onClick(@NotNull View v) {
            switch (v.getId()) {
                case R.id.btn_seleccionar_vehiculo:
                    Intent i = new Intent(context, Activity_Confirmar_Reserva.class );
                    i.putExtra("matricula_select", v_matricula.getText());
                    context.startActivity(i);
                    break;
            }
        }
    }

    public VehiculoAdapter(ArrayList<Vehiculo> dataSet) {
        mDataset = dataSet;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_vehiculo, viewGroup, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        viewHolder.v_marca.setText(mDataset.get(position).getMarca());
        viewHolder.v_modelo.setText(mDataset.get(position).getModelo());
        viewHolder.v_precio.setText(mDataset.get(position).getPrecio().toString());
        viewHolder.v_matricula.setText(mDataset.get(position).getMatricula());

        viewHolder.setOnClickListeners();
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

}


