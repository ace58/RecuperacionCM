package com.example.mapaestaciones;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;

public class ReservaAdapter extends RecyclerView.Adapter<ReservaAdapter.ViewHolder> {

    private ArrayList<Reserva> mDataset;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        Context context;
        public TextView v_codigo;
        public TextView v_oficina;
        public TextView v_fechaInicio;
        public ViewHolder(View v) {
            super(v);
            context = v.getContext();
            v_codigo = (TextView) v.findViewById(R.id.v_codigo);
            v_oficina = (TextView) v.findViewById(R.id.v_oficina);
            v_fechaInicio = (TextView) v.findViewById(R.id.v_fechaInicio);
        }

    }

    public ReservaAdapter(ArrayList<Reserva> dataSet) {
        mDataset = dataSet;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_reserva, viewGroup, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        viewHolder.v_codigo.setText(mDataset.get(position).getCodigo());
        viewHolder.v_oficina.setText(mDataset.get(position).getNombreOficina());
        viewHolder.v_fechaInicio.setText(mDataset.get(position).getFechaInicio());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
    public Filter getFilter() {
        return filter;
    }
    Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            ArrayList<Reserva> arrayFiltrado =new ArrayList<Reserva>();
            if(charSequence.toString().isEmpty()){
                arrayFiltrado.addAll(mDataset);
            }else{
                for(Reserva v :mDataset){
                    if(v.getNombreOficina().toLowerCase().contains(charSequence.toString().toLowerCase())){
                        arrayFiltrado.add(v);
                    }
                }
            }
            FilterResults filterResults= new FilterResults();
            filterResults.values=arrayFiltrado;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            mDataset.clear();
            mDataset.addAll((Collection<? extends Reserva>) results.values);
            notifyDataSetChanged();
        }
    };


}


