package com.example.mapaestaciones;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.Transition;
import android.view.Gravity;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tv_credenciales;
    public static final long DURATION_TRANSITION=1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //poer el icono en action bar
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);


        tv_credenciales=(TextView)findViewById(R.id.tv_credenciales);

        boolean bandera = getValuePreference(getApplicationContext());

        if(bandera){
            Intent i = new Intent(getApplicationContext(), Activity_Login.class);
            startActivity(i);
        }

        SharedPreferences preferences = getSharedPreferences("credenciales", Context.MODE_PRIVATE);
        tv_credenciales.setText(preferences.getString("usuario", "AAAAAA"));
    }

    private Transition transition;

    public void onExplodeClicked(View view){
        transition = new Explode();
        iniciarActividadMisReservas();
    }
    public void onSlideClicked(View view){
        transition = new Slide(Gravity.START);
        iniciarActividadEscaner();
    }
    public void onFadeClicked(View view){
        transition = new Fade(Fade.OUT);
        iniciarActividadReservaVehiculos();
    }
    public void iniciarActividadReservaVehiculos(){
        transition.setDuration(DURATION_TRANSITION);
        transition.setInterpolator(new DecelerateInterpolator());
        getWindow().setExitTransition(transition);
        Intent intent = new Intent(this,Activity_Reservar_Vehiculos.class);
        startActivity(intent, ActivityOptionsCompat.makeSceneTransitionAnimation(this).toBundle());

    }

    public void iniciarActividadEscaner(){
        transition.setDuration(DURATION_TRANSITION);
        transition.setInterpolator(new DecelerateInterpolator());
        getWindow().setExitTransition(transition);
        Intent intent = new Intent(this,Activity_QR.class);
        startActivity(intent, ActivityOptionsCompat.makeSceneTransitionAnimation(this).toBundle());

    }

    public void iniciarActividadMisReservas(){
        transition.setDuration(DURATION_TRANSITION);
        transition.setInterpolator(new DecelerateInterpolator());
        getWindow().setExitTransition(transition);
        Intent intent = new Intent(this,Activity_Ver_Reservas.class);
        startActivity(intent, ActivityOptionsCompat.makeSceneTransitionAnimation(this).toBundle());

    }









    public void Actividad_Reserva_adelante (View view){
        Intent reserva_adelante = new Intent(getApplicationContext(), Activity_Reservar_Vehiculos.class);
        startActivity(reserva_adelante);
    }
    public void Actividad_Acciones_BD (View view){
        Intent acciones_bd_adelante = new Intent(getApplicationContext(), Activity_Acciones_BD.class);
        startActivity(acciones_bd_adelante);
    }
    public void Actividad_Ver_Vehiculos (View view){
        Intent ver_vehiculos_adelante = new Intent(getApplicationContext(), Activity_Ver_Vehiculos.class);
        startActivity(ver_vehiculos_adelante);
    }
    public void Actividad_Acciones_Oficinas_BD (View view){
        Intent acciones_oficinas_bd_adelante = new Intent(getApplicationContext(), Activity_Acciones_Oficinas_BD.class);
        startActivity(acciones_oficinas_bd_adelante);
    }
    public void Actividad_Acciones_Reservas_BD (View view){
        Intent acciones_reservas_bd_adelante = new Intent(getApplicationContext(), Activity_Acciones_Reservas_BD.class);
        startActivity(acciones_reservas_bd_adelante);
    }

    public void Actividad_Ver_Oficinas (View view) {
        Intent ver_oficinas_adelante = new Intent(getApplicationContext(), Activity_Ver_Oficinas.class);
        startActivity(ver_oficinas_adelante);
    }

    public void Actividad_Ver_Reservas (View view) {
        Intent ver_oficinas_adelante = new Intent(getApplicationContext(), Activity_Ver_Reservas.class);
        startActivity(ver_oficinas_adelante);
    }
    public void Actividad_SearchActivity (View view) {
        Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
        startActivity(intent);
    }

    public void Actividad_Prueba_Login (View view) {
        Intent login = new Intent(getApplicationContext(), Activity_Login.class);
        startActivity(login);
    }

    public void Actividad_QR (View view) {
        Intent escanerQR = new Intent(getApplicationContext(), Activity_QR.class);
        startActivity(escanerQR);
    }

    public void Cerrar_Sesion (View view){
        saveValuePreference(getApplicationContext(), true);
        reiniciarActivity(this);
    }

    public static void reiniciarActivity(Activity actividad){
        Intent intent=new Intent();
        intent.setClass(actividad, actividad.getClass());
        actividad.startActivity(intent);
        actividad.finish();
    }

    public void Actividad_MiPerfil(View view) {
        Intent miperfil = new Intent(getApplicationContext(), MiPerfil.class);
        startActivity(miperfil);
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

}