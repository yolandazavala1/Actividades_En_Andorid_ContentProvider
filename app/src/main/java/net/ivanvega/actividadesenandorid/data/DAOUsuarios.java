package net.ivanvega.actividadesenandorid.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.recyclerview.widget.LinearSmoothScroller;

import java.util.ArrayList;
import java.util.List;

public class DAOUsuarios {
    Context context;
    DB db;
    SQLiteDatabase ad;



    public DAOUsuarios(Context ctx){
        this.context = ctx;
        db = new DB(ctx);
         ad = db.getWritableDatabase();
    }

    public Cursor getAllByName(String nombre){

         return  ad.rawQuery("Select * from " + DB.TABLE_USUARIOS_NAME +
                " where nombre like  ? " ,
                new String[]{ "\\%"+ nombre+ "\\%"}

        );

    }

    public boolean delete (long id){

        return ad.delete(DB.TABLE_USUARIOS_NAME,
                "_id=? and status=?",
                new String[]{String.valueOf(id), "VI"}) >  0;

    }

    public boolean update(Usuario usuario){

        ContentValues cv = new ContentValues();

        cv.put(DB.COLUMS_TABLEUSUARIOS[1], usuario.getNombre());
        cv.put(DB.COLUMS_TABLEUSUARIOS[2], usuario.getEmail());
        cv.put(DB.COLUMS_TABLEUSUARIOS[3], usuario.getContraseña());
        cv.put(DB.COLUMS_TABLEUSUARIOS[4], usuario.getTelefono());

        return   ad.update(
                DB.TABLE_USUARIOS_NAME,
                cv ,
                "_id=?",
                new String[]{ String.valueOf( usuario.getID())}
                )   > 0;
    }

    public List<Usuario> getAll(){
        List<Usuario> lst = new ArrayList<Usuario>();

        Cursor cursor = ad.query(DB.TABLE_USUARIOS_NAME, DB.COLUMS_TABLEUSUARIOS,
                null, null, null , null, null);

        if ( cursor.getCount()>0){
            while(cursor.moveToNext()){
                lst.add(
                        new Usuario( cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2) ,
                        cursor.getString(3),
                        cursor.getString(4))
                );
            }

        }
        return lst;
    }

    public Cursor getAllCursor(){

        Cursor cursor = ad.query(DB.TABLE_USUARIOS_NAME, DB.COLUMS_TABLEUSUARIOS,
                null, null, null , null, null);
        
        return cursor;
    }

    public Usuario getOneByID(long id){

        Cursor cursor = null;
        Usuario usuario = null;

       cursor = ad.rawQuery("select * from " + DB.TABLE_USUARIOS_NAME + " where " +
                DB.COLUMS_TABLEUSUARIOS[0]  + "=?",
               new String[]{ String.valueOf( id)} );


       if(cursor!=null){
           if( cursor.moveToFirst()){
               usuario = new Usuario(
                       cursor.getInt(0), cursor.getString(1), cursor.getString(2) ,
                       cursor.getString(3), cursor.getString(4)
               );
           }
       }

        return usuario;
    }

    public Cursor getOneByIDCursor(long id){

        Cursor cursor = null;


        cursor = ad.rawQuery("select * from " + DB.TABLE_USUARIOS_NAME + " where " +
                        DB.COLUMS_TABLEUSUARIOS[0]  + "=?",
                new String[]{ String.valueOf( id)} );


        return cursor;
    }

    public Usuario autenticar(Usuario usuario){

        Cursor c = ad.query( DB.TABLE_USUARIOS_NAME  ,
                DB.COLUMS_TABLEUSUARIOS,
                "email=? and password=?",
                new String[]{usuario.getEmail(), usuario.getContraseña()},
                null,
                null,
                null
        );

        if (c.moveToFirst()){
            usuario.setID( c.getLong(0) );
            usuario.setNombre(c.getString(1));
            usuario.setTelefono(c.getString(4));
        }

        return usuario;
    }

    public long insert(Usuario usuario){
        ContentValues cv = new ContentValues();

        cv.put(DB.COLUMS_TABLEUSUARIOS[1], usuario.getNombre());
        cv.put(DB.COLUMS_TABLEUSUARIOS[2], usuario.getEmail());
        cv.put(DB.COLUMS_TABLEUSUARIOS[3], usuario.getContraseña());
        cv.put(DB.COLUMS_TABLEUSUARIOS[4], usuario.getTelefono());

        return ad.insert(DB.TABLE_USUARIOS_NAME,
                null ,
                  cv);

    }

    public long insert(ContentValues cv){

        return ad.insert(DB.TABLE_USUARIOS_NAME,
                null ,
                cv);

    }

}
