package com.example.prototipo_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void ingresar(View v){
        Intent pj = new Intent(this, pantalla_dos.class);
        startActivity(pj);
        //Hola chris
    }

    public void Salir(View v){
        System.exit(0);
    }

}
