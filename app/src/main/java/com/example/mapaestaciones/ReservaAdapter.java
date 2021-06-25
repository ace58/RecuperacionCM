package com.example.mapaestaciones;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class ReservaAdapter extends BaseAdapter {

    Context context;
    private final String [] codigos;
    private final String [] lugares;

    private final String [] finicios;
    private final String [] ffins;

    public ReservaAdapter(Context context, String [] codigos, String [] lugares, String [] finicios, String [] ffins){
        //super(context, R.layout.single_list_app_item, utilsArrayList);
        this.context = context;
        this.codigos = codigos;
        this.lugares = lugares;
        this.finicios = finicios;
        this.ffins = ffins;
    }

    @Override
    public int getCount() {
        return codigos.length;
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position,  View convertView, ViewGroup parent) {


        ViewHolder viewHolder;

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.list_item_reservas, parent, false);
            viewHolder.txtCodigo = (TextView) convertView.findViewById(R.id.txt_codigo2);
            viewHolder.txtLugar = (TextView) convertView.findViewById(R.id.txt_lugar);
            viewHolder.txtFinicio = (TextView) convertView.findViewById(R.id.txt_finicio);
            viewHolder.txtFfin = (TextView) convertView.findViewById(R.id.txt_ffin);

            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

        viewHolder.txtCodigo.setText(codigos[position]);
        viewHolder.txtLugar.setText(lugares[position]);
        viewHolder.txtFinicio.setText(finicios[position]);
        viewHolder.txtFfin.setText(ffins[position]);

        return convertView;
    }

    private static class ViewHolder {

        TextView txtCodigo;
        TextView txtLugar;
        TextView txtFinicio;
        TextView txtFfin;

    }

}

