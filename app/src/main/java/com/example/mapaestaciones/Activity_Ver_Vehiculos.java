package com.example.mapaestaciones;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class Activity_Ver_Vehiculos extends AppCompatActivity {

    private EditText tv1;
    private ListView lv1;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__ver__vehiculos);

        spinner= findViewById(R.id.spinner);
        lv1=(ListView)findViewById(R.id.lv1);
        ArrayList<String> lista = new ArrayList<String>();
        ArrayList<String> lista2 = new ArrayList<String>();

        //funciona
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getReadableDatabase();
        Cursor fila = BaseDeDatos.rawQuery("select * from vehiculos", null);
        if(fila.moveToFirst()){
            do{
                Vehiculo vehiculo = new Vehiculo(fila.getString(0),fila.getString(1),
                        fila.getString(2), fila.getString(3),
                        fila.getString(4),fila.getDouble(5),
                        fila.getString(6));
                lista.add(vehiculo.toString2());
            }while (fila.moveToNext());
        }

        for(int i=0;i<lista.size();i++){
            int cont = 0;
            for(int j=0;j<lista2.size();j++){
                if ((lista.get(i).equals(lista2.get(j))) ==true){
                    cont++;
                }
            }
            if (cont==0){
                lista2.add(lista.get(i));
            }
        }
        spinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,lista2));
        BaseDeDatos.close();

    }
    public void Consult(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        String marca = spinner.getSelectedItem().toString();
        ArrayList<String> lista = new ArrayList<String>();


        if(!marca.isEmpty()){
            Cursor fila = BaseDeDatos.rawQuery
                    ("select * from vehiculos where marca ='" + marca + "'", null);
            if(fila.moveToFirst()){
                do{
                    Vehiculo vehiculo = new Vehiculo(fila.getString(0),fila.getString(1),
                            fila.getString(2), fila.getString(3),
                            fila.getString(4),fila.getDouble(5),
                            fila.getString(6));
                    lista.add(vehiculo.toString());
                }while (fila.moveToNext());
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.list_item_vervehiculos,lista);
                lv1.setAdapter(adapter);
            }else{
                Toast.makeText(this, "No existe el vehiculo", Toast.LENGTH_SHORT).show();
                BaseDeDatos.close();
            }
        }else{
            Toast.makeText(this, "Debes introducir la matricula del vehiculo", Toast.LENGTH_LONG).show();
        }
    }
    public void Actividad_Reserva_adelante (View view){
        Intent reserva_adelante = new Intent(getApplicationContext(), Activity_Reservar_Vehiculos.class);
        startActivity(reserva_adelante);
    }
    public void Actividad_Ver_Reservas (View view) {
        Intent ver_oficinas_adelante = new Intent(getApplicationContext(), Activity_Ver_Reservas.class);
        startActivity(ver_oficinas_adelante);
    }
    public void Actividad_QR (View view) {
        Intent escanerQR = new Intent(getApplicationContext(), Activity_QR.class);
        startActivity(escanerQR);
    }
    public void menu_principal(View view){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }


}