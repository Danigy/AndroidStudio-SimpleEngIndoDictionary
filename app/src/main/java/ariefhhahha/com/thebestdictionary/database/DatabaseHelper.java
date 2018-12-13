package ariefhhahha.com.thebestdictionary.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static android.provider.BaseColumns._ID;
import static ariefhhahha.com.thebestdictionary.database.DatabaseContract.TABLE_INDONESIA_NAME;
import static ariefhhahha.com.thebestdictionary.database.DatabaseContract.TABLE_INGGRIS_NAME;
import static ariefhhahha.com.thebestdictionary.database.DatabaseContract.kamusIndonesia.ARTI_INDONESIA;
import static ariefhhahha.com.thebestdictionary.database.DatabaseContract.kamusIndonesia.ARTI_INGGRIS;
import static ariefhhahha.com.thebestdictionary.database.DatabaseContract.kamusIndonesia.KATA_INDONESIA;
import static ariefhhahha.com.thebestdictionary.database.DatabaseContract.kamusIndonesia.KATA_INGGRIS;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static String DATABASE_NAME = "db_kamus_indonesia_inggris";
    private static final int DATABASE_VERSION = 1;

    //bikin table kamus indonesia - inggris
    public static String CREATE_TB_INDONESIA = "create table " + TABLE_INDONESIA_NAME  + " (" + _ID + " integer primary key autoincrement, " + KATA_INDONESIA + " text not null, " + ARTI_INDONESIA + " text not null);";

    //bikin table kamus inggris - indonesia
    public static String CREATE_TB_INGGRIS = "create table " + TABLE_INGGRIS_NAME  + " (" + _ID + " integer primary key autoincrement, " + KATA_INGGRIS + " text not null, " + ARTI_INGGRIS + " text not null);";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TB_INDONESIA);
        db.execSQL(CREATE_TB_INGGRIS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_INDONESIA_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_INGGRIS_NAME);
        onCreate(db);
    }
}
