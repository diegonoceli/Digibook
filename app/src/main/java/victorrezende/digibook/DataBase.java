package victorrezende.digibook;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by VICTOR on 29/06/2016.
 */
public class DataBase extends SQLiteOpenHelper {

    public DataBase(Context context){
        super(context, "BB_GRAFO", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(ScriptSQL.getCreateGrafo());
        db.execSQL(ScriptSQL.getCreateAcao());



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}

