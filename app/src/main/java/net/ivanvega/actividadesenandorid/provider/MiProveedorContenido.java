package net.ivanvega.actividadesenandorid.provider;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import net.ivanvega.actividadesenandorid.data.DAOUsuarios;

public class MiProveedorContenido extends ContentProvider {

    // Creates a UriMatcher object.
    private static final UriMatcher uriMatcher =
            new UriMatcher(UriMatcher.NO_MATCH);

    static {
        /*
         * The calls to addURI() go here, for all of the content URI patterns that the provider
         * should recognize. For this snippet, only the calls for table 3 are shown.
         */

        /*
         * Sets the integer value for multiple rows in table 3 to 1. Notice that no wildcard is used
         * in the path
         * content://net.ivanvega.actividadesenandorid.provider/usuarios
         */
        uriMatcher.addURI("net.ivanvega.actividadesenandorid.provider", "usuarios", 1);

        /*
         * Sets the code for a single row to 2. In this case, the "#" wildcard is
         * used. "content://com.example.app.provider/table3/3" matches, but
         * "content://com.example.app.provider/table3 doesn't.
         * content://net.ivanvega.actividadesenandorid.provider/usuarios/89
         */
        uriMatcher.addURI("net.ivanvega.actividadesenandorid.provider", "usuarios/#", 2);


        /*
         * Sets the code for a single row to 2. In this case, the "#" wildcard is
         * used. "content://com.example.app.provider/table3/3" matches, but
         * "content://com.example.app.provider/table3 doesn't.
         * content://net.ivanvega.actividadesenandorid.provider/usuarios/*
         content://net.ivanvega.actividadesenandorid.provider/usuarios/lacteos
         */

        uriMatcher.addURI("net.ivanvega.actividadesenandorid.provider", "usuarios/*", 3);
    }

    DAOUsuarios daoUsuarios ;
    @Override
    public boolean onCreate() {
        daoUsuarios = new DAOUsuarios(getContext());

        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] strings, @Nullable String s, @Nullable String[] strings1, @Nullable String s1) {
        Cursor result=null;

        switch (uriMatcher.match(uri)){
            case 1:
                //base de datos
                result = daoUsuarios.getAllCursor();
                break;

            case 2:
               //base de datos
               String id =  uri.getLastPathSegment();

               result = daoUsuarios.getOneByIDCursor( Long.parseLong(id) );

        }

        return result;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        String result="";

        //vnd.android.cursor.dir/vnd.net.ivanvega.actividadesenandorid.provider.usuarios    varios registros
        //vnd.android.cursor.item/vnd.net.ivanvega.actividadesenandorid.provider.usuarios  uno   registro

        switch (uriMatcher.match(uri)){
            case 1:
                result = "vnd.android.cursor.dir/vnd.net.ivanvega.actividadesenandorid.provider.usuarios";
                break;

            case 2:
                result = "vnd.android.cursor.item/vnd.net.ivanvega.actividadesenandorid.provider.usuarios";
                break;
            case 3:
                result = "vnd.android.cursor.dir/vnd.net.ivanvega.actividadesenandorid.provider.usuarios";
                break;
        }
        return result;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        Uri result=null;

        switch (uriMatcher.match(uri)){
            case  1:
                long idnewrow = daoUsuarios.insert(contentValues);
                result = Uri.withAppendedPath(uri, String.valueOf( idnewrow));
                break;
        }
        return result;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        int result=0;
        switch (uriMatcher.match(uri)){
            case 1:
                break;
            case 2:
                    result =
                            (daoUsuarios.delete
                                        (Long.parseLong( uri.getLastPathSegment()))) ? 1:0;
                break;
        }

        return result;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }
}
