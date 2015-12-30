package se.devotu.magicgametracker.dal;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import se.devotu.magicgametracker.bl.SettingsManager;
import se.devotu.magicgametracker.info.Alteration;


/**
 * Created by Devotu on 2015-01-20.
 */
@SuppressLint("SimpleDateFormat")
public class AlterationRecordManager extends DatabaseManager {

    private Context context;

    public AlterationRecordManager(Context context) {
        super(context);
        this.context = context;
    }

    public void addAlteration(int deckID, String comment) {

        //Hitta ledigt Id
        int alterationID = getFirstFreeId("Alterations", "Alteration_ID");

        //Hitta gamla versionen om det finns, annars noll
        int newRevision = 0;
        Alteration currentAlteration = this.getLastAlterationForDeck(deckID);
        if (currentAlteration != null){
            newRevision = currentAlteration.getRevision() +1;
        }

        //Hitta datum
        SettingsManager sm = new SettingsManager();
        String dateFormat = sm.getDateFormat(context);
        SimpleDateFormat df = new SimpleDateFormat(dateFormat);
        Calendar c = Calendar.getInstance();
        String date = df.format(c.getTime());

        ContentValues newAlterationValues = new ContentValues();
        newAlterationValues.put("Alteration_ID", alterationID);
        newAlterationValues.put("Deck_Id", deckID);
        newAlterationValues.put("Date", date);
        newAlterationValues.put("Revision", newRevision);
        newAlterationValues.put("Comment", comment);

        SQLiteDatabase db = this.getReadableDatabase();
        db.insert("Alterations", "Alteration_ID", newAlterationValues);
        db.close();
    }

    public ArrayList<Alteration> getAllAlterationsForDeck(int deckID) {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM Alterations WHERE Deck_Id = " + deckID;

        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();

        ArrayList<Alteration> alterations = new ArrayList<Alteration>();

        if (cursor.moveToFirst()) {
            do {
                Alteration alteration = new Alteration();
                alteration.setAlteration_ID(Integer.parseInt(cursor.getString(0)));
                alteration.setDeck_Id(Integer.parseInt(cursor.getString(1)));
                alteration.setDate(cursor.getString(2));
                alteration.setRevision(Integer.parseInt(cursor.getString(3)));
                alteration.setComment(cursor.getString(4));
                alterations.add(alteration);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return alterations;
    }

    public void deleteAllAlterationForDeck(int deckID){
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "DELETE FROM Alterations WHERE Deck_Id = " + deckID;
        db.execSQL(sql);
    }

    public Alteration getAlterationById(int alterationID) {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM Alterations WHERE Alteration_ID = " + alterationID;

        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();

        Alteration alteration = new Alteration();
        alteration.setAlteration_ID(Integer.parseInt(cursor.getString(0)));
        alteration.setDeck_Id(Integer.parseInt(cursor.getString(1)));
        alteration.setDate(cursor.getString(2));
        alteration.setRevision(Integer.parseInt(cursor.getString(3)));
        alteration.setComment(cursor.getString(4));

        cursor.close();
        db.close();

        return alteration;
    }

    public Boolean deleteAlterationById(int alterationID) {
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            db.delete("Alterations", "Alteration_ID = " + alterationID, null);
            db.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Alteration getLastAlterationForDeck(int deckID) {
        SQLiteDatabase db = this.getReadableDatabase();

        Alteration lastAlteration;

        String sql = "SELECT * FROM Alterations WHERE Deck_Id = " + deckID + " ORDER BY Revision DESC";
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()){
            lastAlteration = new Alteration();
            lastAlteration.setAlteration_ID(Integer.parseInt(cursor.getString(0)));
            lastAlteration.setDeck_Id(Integer.parseInt(cursor.getString(1)));
            lastAlteration.setDate(cursor.getString(2));
            lastAlteration.setRevision(Integer.parseInt(cursor.getString(3)));
            lastAlteration.setComment(cursor.getString(4));
        } else {
            lastAlteration = null;
        }

        cursor.close();
        db.close();

        return lastAlteration;
    }
}

