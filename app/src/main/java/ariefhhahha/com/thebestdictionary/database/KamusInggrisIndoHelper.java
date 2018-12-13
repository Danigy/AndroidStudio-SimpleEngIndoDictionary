package ariefhhahha.com.thebestdictionary.database;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import java.util.ArrayList;

import ariefhhahha.com.thebestdictionary.models.InggrisIndoModel;

import static android.provider.BaseColumns._ID;
import static ariefhhahha.com.thebestdictionary.database.DatabaseContract.TABLE_INGGRIS_NAME;
import static ariefhhahha.com.thebestdictionary.database.DatabaseContract.kamusIndonesia.ARTI_INGGRIS;
import static ariefhhahha.com.thebestdictionary.database.DatabaseContract.kamusIndonesia.KATA_INGGRIS;

public class KamusInggrisIndoHelper {

    private Context context;
    private DatabaseHelper databaseHelper;

    private SQLiteDatabase database;

    public KamusInggrisIndoHelper(Context context) {
        this.context = context;
    }

    public KamusInggrisIndoHelper open() throws SQLException {
        databaseHelper = new DatabaseHelper(context);
        database = databaseHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        databaseHelper.close();
    }

    public ArrayList<InggrisIndoModel> getDataInggrisIndoByName(String kata_inggris) {
        String result = "";
        Cursor cursor = database.query(TABLE_INGGRIS_NAME, null, KATA_INGGRIS + " LIKE ? ", new String[]{"%"+kata_inggris+"%"}, null, null,_ID + " ASC", null);
        cursor.moveToFirst();
        ArrayList<InggrisIndoModel> arrayList = new ArrayList<>();
        InggrisIndoModel inggrisIndoModel;
        if (cursor.getCount() > 0) {
            do {
                inggrisIndoModel = new InggrisIndoModel();
                inggrisIndoModel.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                inggrisIndoModel.setKata_inggris(cursor.getString(cursor.getColumnIndexOrThrow(KATA_INGGRIS)));
                inggrisIndoModel.setArti_inggris(cursor.getString(cursor.getColumnIndexOrThrow(ARTI_INGGRIS)));

                arrayList.add(inggrisIndoModel);
                cursor.moveToNext();
            } while (!cursor.isAfterLast());
        }

        cursor.close();
        return arrayList;
    }

    public ArrayList<InggrisIndoModel> getAllInggrisIndoData() {
        Cursor cursor = database.query(TABLE_INGGRIS_NAME, null, null, null, null, null, _ID + " ASC", null);
        cursor.moveToFirst();
        ArrayList<InggrisIndoModel> arrayList = new ArrayList<>();
        InggrisIndoModel inggrisIndoModel;
        if (cursor.getCount() > 0) {
            do {
                inggrisIndoModel = new InggrisIndoModel();
                inggrisIndoModel.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                inggrisIndoModel.setKata_inggris(cursor.getString(cursor.getColumnIndexOrThrow(KATA_INGGRIS)));
                inggrisIndoModel.setArti_inggris(cursor.getString(cursor.getColumnIndexOrThrow(ARTI_INGGRIS)));

                arrayList.add(inggrisIndoModel);
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

    public void insertTransactionInggrisIndo(InggrisIndoModel inggrisIndoModel) {
        String sql = "INSERT INTO " + TABLE_INGGRIS_NAME + " ("+KATA_INGGRIS+", "+ARTI_INGGRIS+") VALUES (?, ?)";
        SQLiteStatement stmt = database.compileStatement(sql);
        stmt.bindString(1, inggrisIndoModel.getKata_inggris());
        stmt.bindString(2, inggrisIndoModel.getArti_inggris());
        stmt.execute();
        stmt.clearBindings();
    }
}
