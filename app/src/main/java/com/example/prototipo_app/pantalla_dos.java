package com.example.prototipo_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class pantalla_dos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_dos);
    }

    public void dolencias(View v){
        Intent pj = new Intent(this, Dolencia.class);
        startActivity(pj);
    }

    public void planta(View v){
        Intent pj = new Intent(this, plantas.class);
        startActivity(pj);
    }

    public void reco(View v){
        Intent pj = new Intent(this, recomendaciones.class);
        startActivity(pj);
    }

}
