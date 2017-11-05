package com.example.braya.proyectoconfirebase;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.braya.proyectoconfirebase.Objetos.Coche;
import com.example.braya.proyectoconfirebase.Objetos.FirebaseReferences;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    EditText loginEmail;
    EditText loginPassword;
    Button Entrar;
    Button Registrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        //----------------------VARIABLES DEL XML----------------------------

        loginEmail = (EditText) findViewById(R.id.registerEmail);
        loginPassword = (EditText) findViewById(R.id.registerPassword);
        Registrar = (Button) findViewById(R.id.Registrar);
        Entrar = (Button) findViewById(R.id.logearUsuario);

       Registrar.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent i = new Intent(MainActivity.this, Registrarse.class);
               startActivity(i);
           }
       });



        //----------------------AUTENTICACION DE USUARIOS DE FIREBASE-----------------------
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setMessage("Desea continuar como "+user.getEmail()).setTitle("Inicio de sesión");
                    builder.setPositiveButton("Esta bien", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Intent i = new Intent(MainActivity.this, SolicitarAuto.class);
                            startActivity(i);
                            finish();
                        }
                    });
                    builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Toast.makeText(MainActivity.this, "La sesión ha sido cerrada", Toast.LENGTH_SHORT).show();
                            mAuth.signOut();
                        }
                    });

                    AlertDialog dialog = builder.create();
                    dialog.show();
                }

            }
        };



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

    public void Login(View view){
        String email = loginEmail.getText().toString();
        String password = loginPassword.getText().toString();

        try {
                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Log.d("SIGNIN", "signInWithEmail:onComplete:" + task.isSuccessful());


                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                if (!task.isSuccessful()) {
                                    Log.w("SIGNERROR", "signInWithEmail:failed", task.getException());
                                    Toast.makeText(MainActivity.this, "Error en usuario o contraseña revise los datos introducidos", Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(MainActivity.this, "Bienvenido "+loginEmail.getText().toString(), Toast.LENGTH_SHORT).show();
                                    Intent i = new Intent(MainActivity.this, SolicitarAuto.class);
                                    startActivity(i);
                                    finish();
                                }
                            }
                        });
        }catch(Exception e){
            Toast.makeText(this, "Rellene ambos campos", Toast.LENGTH_SHORT).show();
            Log.i("DATOS", "El usuario no ha introducido nada");
        }

    }

}
