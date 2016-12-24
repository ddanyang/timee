package wecourse.utils.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by ddany on 2016/5/12.
 */
public class CreateOrUpDB extends SQLiteOpenHelper {

    Context context;
    SQLiteDatabase db;

    public CreateOrUpDB(Context context) {
        super(context,Static_DB.DB_NAME,null,Static_DB.DB_VERSION);
    }

    public CreateOrUpDB(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context = context;
    }

    public CreateOrUpDB(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Static_DB.EDB_CREATE_TABLE_ONE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Toast.makeText(context, "数据库升级", Toast.LENGTH_SHORT).show();
    }

    public boolean insert(String content) {
        db = getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("time", System.currentTimeMillis());
        values.put("content", content);
        db.insert(Static_DB.DB_TABLE_ONE, null, values);
        return true;
    }

    public boolean delete(int _id) {

        return false;
    }
}
