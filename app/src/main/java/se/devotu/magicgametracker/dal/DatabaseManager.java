package se.devotu.magicgametracker.dal;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;

import se.devotu.magicgametracker.enums.Status;
import se.devotu.magicgametracker.enums.Validation;

/**
 * Created by Devotu on 2015-01-20.
 */
public class DatabaseManager extends SQLiteOpenHelper{

    private static final String DATABASE_NAME = "MagicDecks";
    private static int DATABASE_VERSION = 1;
    private static final String DATABASE_PATH = File.separator + "databases";

    public DatabaseManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Nothing
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + getDatabaseNameAsString());
        onCreate(db);
    }

    /**
     * @return the databaseName
     */
    public static String getDatabaseNameAsString() {
        return DATABASE_NAME;
    }

    /**
     * @return the databasePath
     */
    public static String getDatabasePath() {
        return StorageManager.getInternalStoragePath() + DATABASE_PATH;
    }

    /**
     * @return a File reference to the database
     */
    public File getDatabase(){
        return new File(this.getDatabasePath() + File.separator + this.getDatabaseNameAsString());
    }


    public int getFirstFreeId(String table, String idName) {

        String sql = "SELECT * FROM " + table;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();

        int freeIdx = 0;
        int rowID = 0;

        for (int i = 0; i < cursor.getCount(); i++) {
            rowID = Integer.parseInt(cursor.getString(0));
            if (rowID >= freeIdx) {
                freeIdx = rowID +1;
            }
            cursor.moveToNext();
        }

        cursor.close();
        db.close();

        System.out.println("Found a free Id at: " + freeIdx);
        return freeIdx;
    }

    public Status DeleteCurrentDatabase(){
        if(getDatabase().delete()){
            return Status.Done;
        } else {
            return Status.Failed;
        }
    }

    public Validation ValidateDatabase(File db){
        return Validation.Correct;
    }

}

