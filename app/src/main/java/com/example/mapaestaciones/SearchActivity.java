package com.example.mapaestaciones;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SearchActivity extends AppCompatActivity  {

    SearchView txtBuscar;
    private VehiculoAdapter vAdapter;
    String marca,fechaInicio,fechaFin;
    private ArrayList<Vehiculo> mDataset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        txtBuscar=findViewById(R.id.txtBuscar);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

        Toast.makeText(this, "Por favor, seleccione un vehículo", Toast.LENGTH_LONG).show();

        Intent me=getIntent();

        SharedPreferences p = getSharedPreferences("adminReserva", Context.MODE_PRIVATE);
        SharedPreferences.Editor obj_editor = p.edit();

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getReadableDatabase();

        Cursor todos = BaseDeDatos.rawQuery("select * from vehiculos where marca = '"+marca+"'", null);
        if(todos.moveToFirst()) {
            do {
                Vehiculo vehiculo = new Vehiculo(todos.getString(0), todos.getString(1),
                        todos.getString(2), todos.getString(3),
                        todos.getString(4), todos.getDouble(5),
                        todos.getString(6));
                mDataset.add(vehiculo);
            } while (todos.moveToNext());
        }

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_vehiculos);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        vAdapter = new VehiculoAdapter(mDataset);
        recyclerView.setAdapter(vAdapter);


        //txtBuscar.setOnQueryTextListener(this);


    }
    public void volver_reserva_vehiculos(View view){
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        MenuItem item = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                vAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }


}