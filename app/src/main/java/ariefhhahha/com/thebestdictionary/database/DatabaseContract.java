package ariefhhahha.com.thebestdictionary.database;

import android.provider.BaseColumns;

public class DatabaseContract {

    //untuk kamus bahasa indonesia ke bahasa inggris
    static String TABLE_INDONESIA_NAME = "tb_indonesia";

    //untuk kamus bahasa inggris ke bahasa indonesia
    static String TABLE_INGGRIS_NAME = "tb_inggris";

    static final class kamusIndonesia implements BaseColumns {

        //kata-kata tb indonesia
        static String KATA_INDONESIA = "kata_indo";
        //arti tb indonesia
        static String ARTI_INDONESIA = "arti_indo";

        //kata-kata tb inggris
        static String KATA_INGGRIS = "kata_inggris";
        //arti tb inggris
        static String ARTI_INGGRIS = "arti_inggris";
    }
}
