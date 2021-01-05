package com.example.prototipo_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class plantat {
    public static final String codpla="codigo";
    public static final String nombre="nom";
    public static final String descripcion = "des";
    public static final String propiedades_c="pro";
    public static final String uso="uso";
    public static final String precaucion="prec";
    private static final String n_tabla="plant";
    private static final String n_bd="plantasmed.db"; // nombre de la base de datos
    private static final int version_db=1;

    private creatua control;
    private final Context ncontexto;
    private SQLiteDatabase pBD;
    public plantat(creatua control, Context ncontexto) {
        this.control = control;
        this.ncontexto = ncontexto;
    }

    private static class creatua extends SQLiteOpenHelper {
        public creatua(@Nullable Context context) {
            super(context, n_bd,null,version_db);
        }
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE "+n_tabla+"("+codpla+" INTEGER PRIMARY KEY,"+nombre+" TEXT NOT NULL,"+descripcion+" TEXT NOT NULL, "+propiedades_c+"TEXT NOT NULL, "+uso+" TEXT NOT NULL, "+precaucion+" TEXT NOT NULL);"
            );
        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS "+n_tabla); // si la tabla existe la elimina
            onCreate(db);
        }
    }

    public plantat (Context c){
        ncontexto = c;
    }
    public plantat apertura () throws Exception{
        control = new creatua(ncontexto);
        pBD = control.getWritableDatabase();
        return this;
    }

    public long insetar(String qcod,String qnom,  String qdes,String qpro, String quso, String qpre){    //insertar datos en la tabla producto
        ContentValues cv = new ContentValues(); //cv para que pueda retener los valores del regitro
        cv.put(codpla,qcod); //rellenar una fila con el ContentVlues cv y prepararlo para un insert
        cv.put(nombre,qnom);//rellenar una fila con el ContentVlues cv y prepararlo para un insert
        cv.put(descripcion,qdes);//rellenar una fila con el ContentVlues cv y prepararlo para un insert
        cv.put(propiedades_c,qpro);
        cv.put(uso,quso);
        cv.put(precaucion,qpre);
        return pBD.insert(n_tabla,null,cv); //insertar la fila con los datos en ContentValues
    }

    public String busqueda(){
        String[] columnas = new String[]{codpla,nombre,descripcion,propiedades_c,uso,precaucion};
        Cursor c = pBD.query(n_tabla,columnas,null,null,null,null,null,null);
        String res ="";
        int iCod = c.getColumnIndex(codpla);
        int iDes = c.getColumnIndex(nombre);
        int iPre = c.getColumnIndex(descripcion);
        int ipro = c.getColumnIndex(propiedades_c);
        int iUs = c.getColumnIndex(uso);
        int iPreca=c.getColumnIndex(precaucion);
        for(c.moveToFirst(); !c.isAfterLast();c.moveToNext()){
            res= res+c.getString(iCod)+";"+c.getString(iDes)+";"+c.getString(iPre)+";"+c.getString(ipro)+";"+c.getString(iUs)+";"+c.getString(iPreca);
        }
        return res;
    }


}
