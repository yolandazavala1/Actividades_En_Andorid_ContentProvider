package net.ivanvega.actividadesenandorid.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DB extends SQLiteOpenHelper {
    public static final String DATABASE_NAME =  "dbusuarios";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_USUARIOS_NAME = "usuarios";
    public static final String[] COLUMS_TABLEUSUARIOS =
            {"_id",
            "nombre","email", "password", "tel"};
    private  static final String SCRIPT_TABLE_USUARIOS =
            "create table usuarios (" +
                    "_id integer primary key autoincrement, " +
                    "nombre text, " +
                    "email text not null, " +
                    "password text not null, " +
                    "tel text not null); " ;

    Context context;

    public DB(Context context
              ) {
        super(context, DATABASE_NAME , null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SCRIPT_TABLE_USUARIOS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
