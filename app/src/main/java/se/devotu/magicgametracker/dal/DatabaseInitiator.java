package se.devotu.magicgametracker.dal;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import se.devotu.magicgametracker.bl.SettingsManager;

/**
 * Created by Devotu on 2015-01-20.
 */
@SuppressLint("SimpleDateFormat")
public class DatabaseInitiator extends DatabaseManager {

    private Context context;

    public DatabaseInitiator(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        System.out.println("dal Ndb Setting up new database");

        String sql;

        sql = "CREATE TABLE IF NOT EXISTS Decks(" +
                "Deck_ID	int PRIMARY KEY, " +
                "Name 		String," +
                "Format     Format," +
                "Colorset	int," +
                "Theme		String, " +
                "Active		int," +
                "DateCreated String)";
                //"DateAltered String," + Version 3.0
                //"Version	int)";
        db.execSQL(sql);

        //Version 3.0
        sql = "CREATE TABLE IF NOT EXISTS Alterations(" +
                "Alteration_ID	int PRIMARY KEY, " +
                "Deck_Id	int," +
                "Date		String," +
                "Revision	int," +
                "Comment	String)";
        db.execSQL(sql);

        createDefaultDeck(db);

        sql = "CREATE TABLE IF NOT EXISTS Games(" +
                "Game_ID	int PRIMARY KEY, " +
                "Deck_Id	int," +
                "Win		int," +
                "Colorset	int," +
                "Comment	String," +
                "Date		String," +
                "Opponent_Id	int," + //Version 2.0
                "PerformanceRating	int)"; //Version 2.1
        db.execSQL(sql);

        sql = "CREATE TABLE IF NOT EXISTS Opponents(" +
                "Opponent_ID	int PRIMARY KEY, " +
                "Name		String)";
        db.execSQL(sql);

        createDefaultOpponent(db);
    }

    public Boolean checkForDB(){

        SQLiteDatabase checkDB = null;
        try {
            checkDB = SQLiteDatabase.openDatabase(getDatabasePath() + File.separator + getDatabaseName(), null,
                    SQLiteDatabase.OPEN_READONLY);
            System.out.println("dal Ndb Database exists at: " + checkDB.getPath());
            checkDB.close();
        } catch (SQLiteException e) {
            System.out.println("dal Ndb Database does not exist yet");
        }

        return checkDB != null ? true : false;
    }

    public void createDefaultDeck(SQLiteDatabase db) {

        ContentValues defaultDeckValues = new ContentValues();
        SettingsManager sm = new SettingsManager();
        String dateFormat = sm.getDateFormat(context);
        SimpleDateFormat df = new SimpleDateFormat(dateFormat);
        Calendar c = Calendar.getInstance();
        String date = df.format(c.getTime());

        defaultDeckValues.put("Deck_ID", 0);
        defaultDeckValues.put("Name", "Default Deck");
        defaultDeckValues.put("Format", "Collection"); //Version 3.0
        defaultDeckValues.put("Colorset", "NONE");
        defaultDeckValues.put("Theme", "Default Deck to hold orphan Games");
        defaultDeckValues.put("Active", 0);
        defaultDeckValues.put("DateCreated", date);
        //defaultDeckValues.put("Version", 1); //Version 3.0

        db.insert("Decks", "Deck_ID", defaultDeckValues);

        //Version 3.0
        ContentValues defaultDeckCreatedValues = new ContentValues();
        defaultDeckCreatedValues.put("Alteration_ID", 0);
        defaultDeckCreatedValues.put("Deck_Id", 0);
        defaultDeckCreatedValues.put("Date", date);
        defaultDeckCreatedValues.put("Revision", 0);
        defaultDeckCreatedValues.put("Comment", "Deck created");

        db.insert("Alterations", "Alteration_ID", defaultDeckCreatedValues);
    }

    public void createDefaultOpponent(SQLiteDatabase db) {

        ContentValues defaultOpponentValues = new ContentValues();

        defaultOpponentValues.put("Opponent_ID", 0);
        defaultOpponentValues.put("Name", "DefaultOpponent");

        db.insert("Opponents", "Opponent_ID", defaultOpponentValues);
    }

}

