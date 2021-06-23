package com.example.mapaestaciones;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class Activity_Reservar_Vehiculos extends AppCompatActivity implements View.OnClickListener{

    Button btn_fecha_inicio,btn_fecha_fin, btn_lugar_recogida,btn_lugar_entrega;
    EditText et_fecha_inicio,et_fecha_fin,et_lugar_recogida;
    String title,title2;
    private int dia,mes,ano;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservar_vehiculos);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

        et_fecha_inicio=(EditText)findViewById(R.id.efecha);
        et_fecha_fin=(EditText)findViewById(R.id.efecha2);
        et_lugar_recogida = (EditText)findViewById(R.id.elugar2);

        btn_fecha_fin=(Button) findViewById(R.id.bfecha2);
        btn_fecha_inicio=(Button) findViewById(R.id.bfecha);
        btn_lugar_entrega = (Button)findViewById(R.id.blugar2);

        title2 = getIntent().getStringExtra("title");
        title = getIntent().getStringExtra("title");
        et_lugar_recogida.setText(title2);

    }

    public void Actividad_Reserva_atras(View view){
        Intent reserva_veh_atras = new Intent(this,MainActivity.class);
        startActivity(reserva_veh_atras);
    }

    /*Fecha*/
    @Override
    public void onClick(View v) {

        if (v == btn_fecha_inicio) {
            final Calendar c = Calendar.getInstance();
            dia = c.get(Calendar.DAY_OF_MONTH);
            mes = c.get(Calendar.MONTH);
            ano = c.get(Calendar.YEAR);
            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    if (monthOfYear + 1 > 10) {
                        if (monthOfYear + 1 > 10) {
                            et_fecha_inicio.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                        } else {
                            et_fecha_inicio.setText(year + "-" + (monthOfYear + 1) + "-0" + dayOfMonth);
                        }
                    } else {
                        if (dayOfMonth >= 10) {
                            et_fecha_inicio.setText(year + "-0" + (monthOfYear + 1) + "-" + dayOfMonth);
                        } else {
                            et_fecha_inicio.setText(year + "-0" + (monthOfYear + 1) + "-0" + dayOfMonth);
                        }
                    }
                }
            }
                    , ano, mes, dia);
            datePickerDialog.show();
        }

        if (v == btn_fecha_fin) {
            final Calendar c = Calendar.getInstance();
            dia = c.get(Calendar.DAY_OF_MONTH);
            mes = c.get(Calendar.MONTH);
            ano = c.get(Calendar.YEAR);
            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    if (monthOfYear + 1 > 10) {
                        if (monthOfYear + 1 > 10) {
                            et_fecha_fin.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                        } else {
                            et_fecha_fin.setText(year + "-" + (monthOfYear + 1) + "-0" + dayOfMonth);
                        }
                    } else {
                        if (dayOfMonth >= 10) {
                            et_fecha_fin.setText(year + "-0" + (monthOfYear + 1) + "-" + dayOfMonth);
                        } else {
                            et_fecha_fin.setText(year + "-0" + (monthOfYear + 1) + "-0" + dayOfMonth);

                        }
                    }
                }
            }
                    , ano, mes, dia);
            datePickerDialog.show();
        }
    }

    public void Actividad_Maps (View view){

        Intent mapas_adelante = new Intent(getApplicationContext(), Mapa_con_las_oficinas.class);
        startActivityForResult(mapas_adelante,100);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 100) // Debe coincidir con "startActivityForResult"
        {
        // Recuperamos el valor
            //short code = (short) data.getExtras().get("mi_return");
            Bundle tap = data.getExtras();
            double lng=(double) data.getExtras().get("lng");

            double lat=data.getDoubleExtra("lat",0.0);
            //TextView elugar =findViewById(R.id.elugar);
            //elugar.setText("Long: " + lng + "Lat :" + lat);

        }

    }


    public void Consultar_Disponibilidad(View view) {

        String s_efecha =et_fecha_inicio.getText().toString().trim();
        String s_efecha2 =et_fecha_fin.getText().toString().trim();
        String s_elugar = et_lugar_recogida.getText().toString().trim();

        if(!s_efecha.isEmpty() && !s_efecha2.isEmpty() && !s_elugar.isEmpty()){

            Intent myIntent=new Intent(Activity_Reservar_Vehiculos.this, Activity_Seleccionar_Vehiculo.class);

            myIntent.putExtra("fecha_inicio", s_efecha);
            myIntent.putExtra("fecha_fin",  s_efecha2);
            myIntent.putExtra("lugar_recogida",  s_elugar);

            startActivity(myIntent);

        }else{
            Toast.makeText(this, "Debe rellenar todos los campos", Toast.LENGTH_LONG).show();
        }

    }
    public void volver_main(View view){
        finish();
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
