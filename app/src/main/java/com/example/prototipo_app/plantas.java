package com.example.prototipo_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class plantas extends AppCompatActivity {
    public String res="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plantas);

        //Bundle param = getIntent().getExtras();
       // int vi[]={R.drawable.chocolate , R.drawable.vainilla ,R.drawable.casero, R.drawable.nueces };
       // String vt[]={"Helado de yogur, chocolate y brownie","Helado de Vainilla y galletas Oreo","Sandwich de helado casero con galletas de chocolate","Helado de plátano, nueves y chocolate"};


       // String  a = param.getString("nom");
        int pp = 1;
      //  ImageView im =(ImageView) findViewById(R.id.imageView2);
       // im.setImageResource(vi[pp]);
        TextView txt = (TextView) findViewById(R.id.textView3);
      //  txt.setText(vt[pp]);

        TextView t5 = (TextView) findViewById(R.id.textView2);
        AssetManager pam = getAssets();
        InputStream f =null;
        String res2="";
        try {
            f = pam.open("PLANTAS.txt");
            res= cargararchivo(f);
        }
        catch (IOException e) {
            Toast.makeText(this, e.getMessage() ,Toast.LENGTH_SHORT).show();
        }
       // TextView t5 = (TextView) findViewById(R.id.textView2);
        t5.setText(res.toString());
        // TRABAJAMDO CON LA BASE DE DATOS
        try {

            plantat db=new plantat(plantas.this);
            db.apertura();
            String m[]= res.split(";");
            int c = m.length/6;
            int cont=0;
            for (int i =0; i<c; i++){
                String a = m[c]; c++;
                String b = m[c]; c++;
                String cc = m[c]; c++;
                String d = m[c]; c++;
                String e= m[c];c++;
                String ff = m[c];

                db.insetar(a,b,cc,d,e,ff);
                Toast.makeText(this, "Recuperacion de datos exitosa", Toast.LENGTH_SHORT).show();
            }

        } catch (Exception e) {
            Toast.makeText(this, "Error al cargar la Base De Datos", Toast.LENGTH_SHORT).show();
        }

    }

    public void cero(View v){
        Intent pa = new Intent(this, clase_respuesta.class);
      //  pa.putExtra("nom","0");
        startActivity(pa);
    }

    public String cargararchivo(InputStream a) throws IOException {
        ByteArrayOutputStream vdatos = new ByteArrayOutputStream();
        byte[] vbyte = new byte[1024];
        int largp =0;
        while((largp=a.read(vbyte)) > 0){
            vdatos.write(vbyte,0 , largp);
        }
        return new String(vdatos.toByteArray(), "UTF8");
    }
}
