package com.example.braya.proyectoconfirebase;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.braya.proyectoconfirebase.Objetos.Bebe;
import com.example.braya.proyectoconfirebase.Objetos.FirebaseReferences;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

import static com.example.braya.proyectoconfirebase.Objetos.FirebaseReferences.BEBE_REFERENCE;
import static com.example.braya.proyectoconfirebase.R.id.añosBebe;
import static com.example.braya.proyectoconfirebase.R.id.continuar;
import static com.example.braya.proyectoconfirebase.R.id.horasBebe;
import static com.example.braya.proyectoconfirebase.R.id.nombreBebe;
import static com.example.braya.proyectoconfirebase.R.id.telefonoBebe;

public class SolicitarAuto extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    Button asd;
    EditText nombreBebe;
    EditText añosBebe;
    EditText telefonoBebe;
    EditText notasBebe;
    EditText ubicacionBebe;
    EditText horasBebe;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solicitar_auto);

        nombreBebe = (EditText) findViewById(R.id.nombreBebe);
        añosBebe = (EditText) findViewById(R.id.añosBebe);
        telefonoBebe = (EditText) findViewById(R.id.telefonoBebe);
        notasBebe = (EditText) findViewById(R.id.notasBebe);
        ubicacionBebe = (EditText) findViewById(R.id.ubicacionBebe);
        horasBebe = (EditText) findViewById(R.id.horasBebe);






        //VERIFICANDO AUTENTICACION
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Toast.makeText(getApplicationContext(), "Bienvenido " +user.getEmail(), Toast.LENGTH_SHORT).show();
                }
            }
        };



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_solicitar_auto, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_logout:
                mAuth.signOut();
                Toast.makeText(this, "La sesión ha sido cerrada", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
                finish();
                return true;
            case R.id.continuar:

                String auxTelefono = telefonoBebe.getText().toString();


                 if(auxTelefono.length() > 10){
                    Toast.makeText(this, "Un numero no puede ser mayor a 10 digitos(Celular), omita el 044", Toast.LENGTH_SHORT).show();
                }



                else{
                    Toast.makeText(this, "Auto solicitado", Toast.LENGTH_SHORT).show();

                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    final DatabaseReference tutorialref = database.getReference(FirebaseReferences.TUTORIAL_REFERENCE);
                    String nombre = nombreBebe.getText().toString();
                    int años = Integer.parseInt(añosBebe.getText().toString());
                    long telefono = Long.valueOf(telefonoBebe.getText().toString()).longValue();
                    String notas = notasBebe.getText().toString();
                    String ubicacion = ubicacionBebe.getText().toString();
                    int horas = Integer.parseInt(horasBebe.getText().toString());

                    int total = (horas*30) + 100;

                    Toast.makeText(getApplicationContext(),"Su total es de: "+total, Toast.LENGTH_LONG).show();
                    Toast.makeText(this, "La tarifa es 100 viaje + 30*Hora", Toast.LENGTH_SHORT).show();
                    Bebe bebe = new Bebe(nombre, años, telefono, notas, ubicacion, horas, total);
                    tutorialref.child(FirebaseReferences.BEBE_REFERENCE).push().setValue(bebe);

                }


            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //---------------------METODO ONSTART--------------------
    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }


    //---------------------METODO ONSTOP--------------------
    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }


}

