package com.example.braya.proyectoconfirebase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import com.example.braya.proyectoconfirebase.Objetos.Adapter;
import com.example.braya.proyectoconfirebase.Objetos.Bebe;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Trabajador extends AppCompatActivity {

    RecyclerView rv;

    List<Bebe> bebes;

    Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trabajador);

        rv = (RecyclerView) findViewById(R.id.recycler);

        rv.setLayoutManager(new LinearLayoutManager(this));

        bebes = new ArrayList<>();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        adapter = new Adapter(bebes);

        rv.setAdapter(adapter);

        database.getReference().getRoot().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                bebes.removeAll(bebes);
                for (DataSnapshot snapshot:
                     dataSnapshot.getChildren()) {
                    Bebe bebe = snapshot.getValue(Bebe.class);
                    bebes.add(bebe);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



    }
}
