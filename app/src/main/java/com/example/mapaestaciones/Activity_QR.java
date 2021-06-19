package com.example.mapaestaciones;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

import static com.example.mapaestaciones.R.layout.*;

public class Activity_QR extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    private ZXingScannerView myScanerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_qr);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);
    }

    public void bntEscaner(View view){
        //Creamos la variable
         myScanerView = new ZXingScannerView(this);
         //que queremos que muestre
         setContentView(myScanerView);
         //necesitamos acceder al metodo de resultHandel para poder resolver el codigo y mostrar el código por pantalla
         myScanerView.setResultHandler(this);
         //se necesitan los permisos para usar la camara, se dan e forma manual; se podría pedir por código.
         myScanerView.startCamera();
    }


    //Se encarga de escanear el código y traducirlo
    @Override
    public void handleResult(Result result) {
        //Para que se muestre lo que acabamos de escanear
        Log.v("HandleResult",result.getText());
        //lo pasamos por un alertDialog aunq podría usarse otra cosa
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //El resultado que acabamos de escanear lo pasamos a texto
        builder.setMessage(result.getText());
        //En esta parte se crea el dialogo
        AlertDialog alertDialog = builder.create();
        //lo mostramos
        alertDialog.show();

        //por si queremos seguir escaneando
        //myScanerView.resumeCameraPreview(this);
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

    public void Cerrar_Sesion (View view) {
        saveValuePreference(getApplicationContext(), true);
        reiniciarActivity(this);
    }
    public static void reiniciarActivity(Activity actividad){
        Intent intent=new Intent();
        intent.setClass(actividad, actividad.getClass());
        actividad.startActivity(intent);
        actividad.finish();
    }

    public static void saveValuePreference(Context context, Boolean b) {
        SharedPreferences sesion = context.getSharedPreferences("sesion", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor;
        editor = sesion.edit();
        editor.putBoolean("logueado", b);
        editor.commit();
    }

    public boolean getValuePreference(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("sesion", MODE_PRIVATE);
        return  preferences.getBoolean("logueado", true);
    }
    public void menu_principal(View view){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }
}