package com.example.mapaestaciones;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class Activity_MiPerfil extends AppCompatActivity {

    TextView tv_usuario, tv_nombreusuario, apellidousuario, dniusuario;
    EditText telefonousuario, emailusuario;
    Button btn_guardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mi_perfil);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

        tv_usuario = findViewById(R.id.tv_usuario);
        tv_nombreusuario = findViewById(R.id.nombreusuario);
        apellidousuario = findViewById(R.id.apellidousuario);
        dniusuario = findViewById(R.id.dniusuario);
        telefonousuario = (EditText) findViewById(R.id.telefonousuario);
        emailusuario = (EditText) findViewById(R.id.emailusuario);

        btn_guardar = (Button) findViewById(R.id.btn_guardar);

        SharedPreferences preferences = getSharedPreferences("credenciales", Context.MODE_PRIVATE);
        tv_usuario.setText(preferences.getString("usuario", "AAAAAA"));
        tv_nombreusuario.setText(preferences.getString("nombre", "AquiEsElNombre"));
        apellidousuario.setText(preferences.getString("apellidos", "AquiEsElNombre"));
        dniusuario.setText(preferences.getString("dni", "AquiEsElNombre"));
        telefonousuario.setText(preferences.getString("telefono", "AquiEsElNombre"));
        emailusuario.setText(preferences.getString("email", "AquiEsElNombre"));

    }


    public void guardarModificaciones(Usuario u) {
        SharedPreferences preferences = getSharedPreferences("credenciales", Context.MODE_PRIVATE);
        SharedPreferences.Editor obj_editor = preferences.edit();
        obj_editor.putString("nombre",u.getNombre());
        obj_editor.putString("apellidos",u.getApellidos());
        obj_editor.putString("usuario",u.getUsuario());
        obj_editor.putString("dni",u.getDni());
        obj_editor.putString("telefono", u.getTelefono());
        obj_editor.putString("email",u.getEmail());
        obj_editor.commit();
    }

    public void Guardar(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();
        String telefono = telefonousuario.getText().toString();
        String email = emailusuario.getText().toString();
        String usuario = tv_usuario.getText().toString();

        Usuario userEntrante = new Usuario();
        if( !telefono.isEmpty() && !email.isEmpty()){

            BaseDeDatos.execSQL("UPDATE usuarios SET telefono= '" + telefono + "', email='" + email + "' " +
                    "WHERE usuario = '" + usuario + "'");
            Cursor fila = BaseDeDatos.rawQuery("select * from usuarios where usuario ='"+usuario+"'", null);
            if(fila.moveToFirst()){
                do{
                    Usuario u = new Usuario(fila.getString(0),
                            fila.getString(1),
                            fila.getString(2),
                            fila.getString(3),
                            fila.getString(4),
                            fila.getString(5),
                            fila.getString(6));
                    userEntrante = u;
                }while(fila.moveToNext());
            }
            guardarModificaciones(userEntrante);
            BaseDeDatos.close();
            Toast.makeText(this,"Datos actualizados correctamente", Toast.LENGTH_LONG).show();
        }else {
        Toast.makeText(this,"ERROR: No deje ningún campo vacío", Toast.LENGTH_LONG).show();
        }
    }

    public void Baja(View view) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();
        ArrayList<Reserva> lista = new ArrayList<Reserva>();
        String usuario = tv_usuario.getText().toString();
        String dni = dniusuario.getText().toString();


        Cursor fila =BaseDeDatos.rawQuery("select * from reservas where dni = '"+dni+"'", null);
        if (fila.moveToFirst()) {
            do {
                Reserva reserva = new Reserva(fila.getInt(0), fila.getString(1),
                        fila.getString(2), fila.getString(3), fila.getString(4), fila.getString(5));
                lista.add(reserva);
            } while (fila.moveToNext());

        }
        for(Reserva r : lista){
            String cod = r.getCodigo().toString();
            BaseDeDatos.execSQL("DELETE FROM reservas WHERE codigo = '" + cod + "'");
        }


        BaseDeDatos.execSQL("DELETE FROM usuarios WHERE usuario = '" + usuario + "'");
        BaseDeDatos.close();

        Toast.makeText(this, "Se ha dado de baja correctamente", Toast.LENGTH_LONG).show();
        Intent i = new Intent(this, Activity_Login.class);
        startActivity(i);
    }

    public void Volver(View view){
        Intent i = new Intent(Activity_MiPerfil.this, MainActivity.class);
        startActivity(i);
        finish();
    }
}