package se.devotu.magicgametracker.dal;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import se.devotu.magicgametracker.bl.SettingsManager;
import se.devotu.magicgametracker.info.Deck;
import se.devotu.magicgametracker.info.DeckInfo;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Devotu on 2015-01-20.
 */
@SuppressLint("SimpleDateFormat")
public class DeckRecordManager extends DatabaseManager {

    Context context;

    public DeckRecordManager(Context context) {
        super(context);
        this.context = context;
    }

    public void createNewDeck(Deck deck) {

        //Hitta fria Id innan insert eftesom hitta Id stänger db
        int deckID = getFirstFreeId("Decks", "Deck_ID");
        int alterationID = getFirstFreeId("Alterations", "Alteration_ID"); //Version 3.0

        ContentValues newDeckValues = new ContentValues();
        SQLiteDatabase db = this.getWritableDatabase();

        SQLiteColorsetCodex colorsetCodex = new SQLiteColorsetCodex();
        SettingsManager sm = new SettingsManager();
        String dateFormat = sm.getDateFormat(context);
        SimpleDateFormat df = new SimpleDateFormat(dateFormat);
        Calendar c = Calendar.getInstance();
        String date = df.format(c.getTime());

        newDeckValues.put("Deck_ID", deckID);
        newDeckValues.put("Name", deck.getName());
        newDeckValues.put("Format", deck.getFormat());
        newDeckValues.put("Colorset", colorsetCodex.parseColorset(deck.getColorset()));
        newDeckValues.put("Theme", deck.getTheme());
        newDeckValues.put("Active", 1);
        newDeckValues.put("DateCreated", date);
        //newDeckValues.put("Version", 1); //Version 3.0

        db.insert("Decks", "Deck_ID", newDeckValues);
        db.close();

        //Version 3.0
        //Komplettera med en skapad-alteration
        AlterationRecordManager am = new AlterationRecordManager(context);
        am.addAlteration(deckID, "Deck created");
    }

    public ArrayList<Deck> getAllDecksInDatabase() {

        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM Decks";

        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();

        ArrayList<Deck> decks = new ArrayList<Deck>();
        SQLiteColorsetCodex colorCodex = new SQLiteColorsetCodex();

        if (cursor.moveToFirst()) {
            do {
                Deck deck = new Deck();
                deck.setDeck_ID(Integer.parseInt(cursor.getString(0)));
                deck.setName(cursor.getString(1));
                deck.setFormat(cursor.getString(2));
                deck.setColorset(colorCodex.parseSQLiteColorsetObject(cursor.getString(3)));
                deck.setTheme(cursor.getString(4));
                deck.setActive(Integer.parseInt(cursor.getString(5)));

                decks.add(deck);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return decks;
    }

    public DeckInfo getDeckInfo(int deckID) {

        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM Decks WHERE Deck_ID = " + deckID;

        SQLiteColorsetCodex colorCodex = new SQLiteColorsetCodex();

        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();

        DeckInfo deck = new DeckInfo();
        deck.setDeck_ID(Integer.parseInt(cursor.getString(0)));
        deck.setName(cursor.getString(1));
        deck.setFormat(cursor.getString(2));
        deck.setColorset(colorCodex.parseSQLiteColorsetObject(cursor.getString(3)));
        deck.setTheme(cursor.getString(4));
        deck.setActive(Integer.parseInt(cursor.getString(5)));
        deck.setDateCreated(cursor.getString(6));
        //deck.setDateAltered(cursor.getString(7)); Version 3.0
        //deck.setVersion(Integer.parseInt(cursor.getString(8))); Version 3.0

        cursor.close();
        db.close();

        return deck;
    }

    public void activateDeck(int deckID) {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues value = new ContentValues();
        value.put("Active", 1);
        db.update("Decks", value, "Deck_ID = " + deckID, null);
        db.close();
    }

    public void deactivateDeck(int deckID) {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues value = new ContentValues();
        value.put("Active", 0);
        db.update("Decks", value, "Deck_ID = " + deckID, null);
        db.close();
    }

    public void deleteDeck(int deckID) {


        SQLiteDatabase db = this.getReadableDatabase();
        db.delete("Decks", "Deck_ID = " + deckID, null);
        db.close();
    }

    //Version 3.0 Depricated
    public void updateDeck(int deckID){
        SQLiteDatabase db = this.getReadableDatabase();

        String sql = "SELECT Version FROM Decks WHERE Deck_ID = " + deckID;
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();

        int newVersion = cursor.getInt(0) +1;

        SettingsManager sm = new SettingsManager();
        String dateFormat = sm.getDateFormat(context);
        SimpleDateFormat df = new SimpleDateFormat(dateFormat);
        Calendar c = Calendar.getInstance();
        String date = df.format(c.getTime());

        ContentValues values = new ContentValues();
        values.put("Version", newVersion);
        values.put("DateAltered", date);

        db.update("Decks", values, "Deck_ID = " + deckID, null);
    }


}
