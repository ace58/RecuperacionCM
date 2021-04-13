package com.example.mapaestaciones;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class Activity_Reservar_Vehiculos extends AppCompatActivity implements View.OnClickListener {

    Button bfecha,bhora,bfecha2,bhora2, bmapa;
    EditText efecha,ehora,efecha2,ehora2;
    private int dia,mes,ano,hora,minutos;
    /*

     */
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservar_vehiculos);
        bfecha=(Button) findViewById(R.id.bfecha);
        bhora =(Button) findViewById(R.id.bhora);
        efecha=(EditText)findViewById(R.id.efecha);
        ehora=(EditText)findViewById(R.id.ehora);
        bfecha.setOnClickListener(this);
        bhora.setOnClickListener(this);
        bfecha2=(Button) findViewById(R.id.bfecha2);
        bhora2 =(Button) findViewById(R.id.bhora2);
        efecha2=(EditText)findViewById(R.id.efecha2);
        ehora2=(EditText)findViewById(R.id.ehora2);
        bfecha2.setOnClickListener(this);
        bhora2.setOnClickListener(this);

    }

    /*
    public void OnClick(View v){
        if(v ==bfecha){
            final Calendar c= Calendar.getInstance();
            dia=c.get(Calendar.DAY_OF_MONTH);
            mes=c.get(Calendar.MONTH);
            ano=c.get(Calendar.YEAR);
            DatePickerDialog datePickerDialog= new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    efecha.setText(dayOfMonth+"/"+(monthOfYear+1)+"/"+year);

                }
            }
            ,dia,mes,ano);
            datePickerDialog.show();
        }if(v==bhora){
            final Calendar c= Calendar.getInstance();
            hora=c.get(Calendar.HOUR_OF_DAY);
            minutos=c.get(Calendar.MINUTE);
            TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    ehora.setText(hourOfDay+":"+minute);

                }
            },hora,minutos,false);
            timePickerDialog.show();
        }
    }

    */

    public void Actividad_Reserva_atras(View view){

        Intent reserva_veh_atras = new Intent(this,MainActivity.class);
        startActivity(reserva_veh_atras);

    }
    /*Fecha y hora*/
    @Override
    public void onClick(View v) {

        if (v == bfecha) {
            final Calendar c = Calendar.getInstance();
            dia = c.get(Calendar.DAY_OF_MONTH);
            mes = c.get(Calendar.MONTH);
            ano = c.get(Calendar.YEAR);
            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    efecha.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);

                }
            }
                    , dia, mes, ano);
            datePickerDialog.show();
        }
        if (v == bhora) {
            final Calendar c = Calendar.getInstance();
            hora = c.get(Calendar.HOUR_OF_DAY);
            minutos = c.get(Calendar.MINUTE);
            TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    ehora.setText(hourOfDay + ":" + minute);

                }
            }, hora, minutos, false);
            timePickerDialog.show();
        }
        if (v == bfecha2) {
            final Calendar c = Calendar.getInstance();
            dia = c.get(Calendar.DAY_OF_MONTH);
            mes = c.get(Calendar.MONTH);
            ano = c.get(Calendar.YEAR);
            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    efecha2.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);

                }
            }
                    , dia, mes, ano);
            datePickerDialog.show();
        }
        if (v == bhora2) {
            final Calendar c = Calendar.getInstance();
            hora = c.get(Calendar.HOUR_OF_DAY);
            minutos = c.get(Calendar.MINUTE);
            TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    ehora2.setText(hourOfDay + ":" + minute);

                }
            }, hora, minutos, false);
            timePickerDialog.show();
        }
    }
    public void Actividad_Maps (View view){
        Intent mapas_adelante = new Intent(getApplicationContext(), Mapa_con_las_oficinas.class);
        startActivity(mapas_adelante);
    }

    public void Consultar_Disponibilidad(View view) {
        Intent myIntent=new Intent(Activity_Reservar_Vehiculos.this,Resumen_Reserva.class);
        myIntent.putExtra("efecha",(Parcelable) efecha);
        myIntent.putExtra("ehora",(Parcelable) ehora);
        myIntent.putExtra("efecha2", (Parcelable) efecha2);
        myIntent.putExtra("ehora2",(Parcelable) ehora2);
        /*
        Bundle miBundle=new Bundle();
        miBundle.putString("fecha_recogida",efecha.getText().toString());
        myIntent.putExtras(miBundle);
        */
        startActivity(myIntent);

    }
}
