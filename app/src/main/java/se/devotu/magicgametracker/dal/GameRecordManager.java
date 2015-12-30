package se.devotu.magicgametracker.dal;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import se.devotu.magicgametracker.bl.SettingsManager;
import se.devotu.magicgametracker.info.Game;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


/**
 * Created by Devotu on 2015-01-20.
 */
@SuppressLint("SimpleDateFormat")
public class GameRecordManager extends DatabaseManager {

    private Context context;

    public GameRecordManager(Context context) {
        super(context);
        this.context = context;
    }

    public void createNewGame(Game game) {

        int gameID = getFirstFreeId("Games", "Game_ID");

        SettingsManager sm = new SettingsManager();
        String dateFormat = sm.getDateFormat(context);
        SimpleDateFormat df = new SimpleDateFormat(dateFormat);
        Calendar c = Calendar.getInstance();
        String date = df.format(c.getTime());

        ContentValues newGameValues = new ContentValues();
        SQLiteDatabase db = this.getWritableDatabase();

        SQLiteColorsetCodex colorsetCodex = new SQLiteColorsetCodex();

        newGameValues.put("Game_ID", gameID);
        newGameValues.put("Deck_Id", game.getDeck_Id());
        newGameValues.put("Colorset", colorsetCodex.parseColorset(game.getOpposingColorset()));
        newGameValues.put("Win", game.isWin()? 1:0);
        newGameValues.put("Comment", game.getComment());
        newGameValues.put("Date", date);
        newGameValues.put("Opponent_Id", game.getOpponentId()); //Version 2.0
        newGameValues.put("PerformanceRating", game.getPerformanceRating()); //Version 2.1

        db.insert("Games", "Game_ID", newGameValues);
        db.close();

    }

    public ArrayList<Game> getAllGamesInDatabase() {

        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM Games";

        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();

        ArrayList<Game> games = new ArrayList<Game>();
        SQLiteColorsetCodex colorCodex = new SQLiteColorsetCodex();

        if (cursor.moveToFirst()) {
            do {
                Game game = new Game();
                game.setGame_ID(Integer.parseInt(cursor.getString(0)));
                game.setDeck_Id(Integer.parseInt(cursor.getString(1)));
                game.setWin(Integer.parseInt(cursor.getString(2)) == 1? true:false);
                game.setOpposingColorset(colorCodex.parseSQLiteColorsetObject(cursor.getString(3)));
                game.setComment(cursor.getString(4));
                game.setDate(cursor.getString(5));
                game.setOpponentId(Integer.parseInt(cursor.getString(6))); //Version 2.0
                game.setPerformanceRating(Integer.parseInt(cursor.getString(7))); //Version 2.1

                games.add(game);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return games;
    }

    public ArrayList<Game> getAllGamesForDeck(int deckID) {

        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM Games WHERE Deck_Id = " + deckID;

        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();

        ArrayList<Game> games = new ArrayList<Game>();
        SQLiteColorsetCodex colorCodex = new SQLiteColorsetCodex();

        if (cursor.moveToFirst()) {
            do {
                Game game = new Game();
                game.setGame_ID(Integer.parseInt(cursor.getString(0)));
                game.setDeck_Id(Integer.parseInt(cursor.getString(1)));
                game.setWin(Integer.parseInt(cursor.getString(2)) == 1? true:false);
                game.setOpposingColorset(colorCodex.parseSQLiteColorsetObject(cursor.getString(3)));
                game.setComment(cursor.getString(4));
                game.setDate(cursor.getString(5));
                game.setOpponentId(Integer.parseInt(cursor.getString(6))); //Version 2.0
                game.setPerformanceRating(Integer.parseInt(cursor.getString(7))); //Version 2.1

                games.add(game);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return games;
    }

    public void addDeckGamesToDefaultDeck(int deckID){
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues value = new ContentValues();
        value.put("Deck_Id", 0);
        db.update("Games", value, "Deck_Id = " + deckID, null);
    }


    //Version 3.0
    public Game getGameById(int gameID) {

        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM Games WHERE Game_ID = " + gameID;

        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();

        SQLiteColorsetCodex colorCodex = new SQLiteColorsetCodex();

        Game game = new Game();
        game.setGame_ID(Integer.parseInt(cursor.getString(0)));
        game.setDeck_Id(Integer.parseInt(cursor.getString(1)));
        game.setWin(Integer.parseInt(cursor.getString(2)) == 1? true:false);
        game.setOpposingColorset(colorCodex.parseSQLiteColorsetObject(cursor.getString(3)));
        game.setComment(cursor.getString(4));
        game.setDate(cursor.getString(5));
        game.setOpponentId(Integer.parseInt(cursor.getString(6)));
        game.setPerformanceRating(Integer.parseInt(cursor.getString(7)));

        return game;
    }

    public Boolean deleteGameById(int gameID) {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            if (db.delete("Games", "Game_ID = " + gameID, null) > 0) {
                db.close();
                return true;
            } else {
                db.close();
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}

