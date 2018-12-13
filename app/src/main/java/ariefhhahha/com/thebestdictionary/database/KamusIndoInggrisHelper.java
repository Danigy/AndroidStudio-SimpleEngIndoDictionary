package ariefhhahha.com.thebestdictionary.database;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import java.util.ArrayList;

import ariefhhahha.com.thebestdictionary.models.IndoInggrisModel;

import static ariefhhahha.com.thebestdictionary.database.DatabaseContract.TABLE_INDONESIA_NAME;

import static ariefhhahha.com.thebestdictionary.database.DatabaseContract.kamusIndonesia.ARTI_INDONESIA;
import static ariefhhahha.com.thebestdictionary.database.DatabaseContract.kamusIndonesia.KATA_INDONESIA;

import static android.provider.BaseColumns._ID;

public class KamusIndoInggrisHelper {

    private Context context;
    private DatabaseHelper databaseHelper;

    private SQLiteDatabase database;

    public KamusIndoInggrisHelper(Context context) {
        this.context = context;
    }

    public KamusIndoInggrisHelper open() throws SQLException {
        databaseHelper = new DatabaseHelper(context);
        database = databaseHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        databaseHelper.close();
    }

     public ArrayList<IndoInggrisModel> getDataIndoInggrisByName(String kata_indonesia) {
        String result = "";
        Cursor cursor = database.query(TABLE_INDONESIA_NAME, null, KATA_INDONESIA + " LIKE ?", new String[]{"%"+kata_indonesia+"%"}, null, null, _ID + " ASC", null);
        cursor.moveToFirst();
        ArrayList<IndoInggrisModel> arrayList = new ArrayList<>();
        IndoInggrisModel indoInggrisModel;
        if (cursor.getCount() > 0) {
            do {
                indoInggrisModel = new IndoInggrisModel();
                indoInggrisModel.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                indoInggrisModel.setKata_indonesia(cursor.getString(cursor.getColumnIndexOrThrow(KATA_INDONESIA)));
                indoInggrisModel.setArti_indonesia(cursor.getString(cursor.getColumnIndexOrThrow(ARTI_INDONESIA)));

                arrayList.add(indoInggrisModel);
                cursor.moveToNext();
            } while (!cursor.isAfterLast());
        }

        cursor.close();
        return arrayList;
     }

     public ArrayList<IndoInggrisModel> getAllIndoInggrisData() {
        Cursor cursor = database.query(TABLE_INDONESIA_NAME, null, null, null, null, null, _ID + " ASC", null);
        cursor.moveToFirst();
        ArrayList<IndoInggrisModel> arrayList = new ArrayList<>();
        IndoInggrisModel indoInggrisModel;
        if (cursor.getCount() > 0) {
            do {
                indoInggrisModel = new IndoInggrisModel();
                indoInggrisModel.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                indoInggrisModel.setKata_indonesia(cursor.getString(cursor.getColumnIndexOrThrow(KATA_INDONESIA)));
                indoInggrisModel.setArti_indonesia(cursor.getString(cursor.getColumnIndexOrThrow(ARTI_INDONESIA)));

                arrayList.add(indoInggrisModel);
                cursor.moveToNext();
            } while (!cursor.isAfterLast());
        }

        cursor.close();
        return arrayList;
     }

     public void beginTransaction() {
        database.beginTransaction();
     }

     public void setTransactionSuccess() {
        database.setTransactionSuccessful();
     }

     public void endTransaction() {
        database.endTransaction();
     }

     public void insertTransactionIndoInggris(IndoInggrisModel indoInggrisModel) {
        String sql = "INSERT INTO " + TABLE_INDONESIA_NAME + " ("+KATA_INDONESIA+", "+ARTI_INDONESIA+") VALUES (?, ?)";
        SQLiteStatement stmt = database.compileStatement(sql);
        stmt.bindString(1, indoInggrisModel.getKata_indonesia());
        stmt.bindString(2, indoInggrisModel.getArti_indonesia());
        stmt.execute();
        stmt.clearBindings();
     }

}
