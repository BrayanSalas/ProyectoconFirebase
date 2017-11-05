package com.example.braya.proyectoconfirebase;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Registrarse extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    EditText registerEmail;
    EditText registerPassword;
    Button registerUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrarse);

        mAuth = FirebaseAuth.getInstance();
        registerEmail = (EditText) findViewById(R.id.registerEmail);
        registerPassword = (EditText) findViewById(R.id.registerPassword);
    }

    public void Register(View view){
        //------SE OBTIENEN LOS VALORES DE LOS EditText
        String email= registerEmail.getText().toString();
        String password = registerPassword.getText().toString();
        final String auxpassword = password;
        final String auxemail = email;

        try{

            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            Log.i("USUARIO", "createUserWithEmail:onComplete:" + task.isSuccessful());

                            // If sign in fails, display a message to the user. If sign in succeeds
                            // the auth state listener will be notified and logic to handle the
                            // signed in user can be handled in the listener.



                            if (!task.isSuccessful()) {
                                Toast.makeText(getApplicationContext(), "No se pudo registrar, intente denuevo", Toast.LENGTH_SHORT).show();
                                if(auxpassword.length() <= 6){
                                    Toast.makeText(getApplicationContext(), "La contraseña debe tener un minimo de 6 caracteres", Toast.LENGTH_SHORT).show();
                                }

                                boolean contieneArroba = true;
                                for(int i=0; i<=auxemail.length(); i++){
                                    if(!auxemail.contains("@")){
                                        contieneArroba = false;
                                    }
                                }
                                Toast.makeText(Registrarse.this, "El correo no es valido", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Toast.makeText(getApplicationContext(),"Se ha creado la cuenta correctamente", Toast.LENGTH_LONG).show();
                                Intent i = new Intent(Registrarse.this, SolicitarAuto.class);
                                startActivity(i);
                                finish();
                            }
                            // ...
                        }
                    });

        }catch(Exception e){
            Toast.makeText(this, "Rellene ambos campos", Toast.LENGTH_SHORT).show();
        }

        }

}
