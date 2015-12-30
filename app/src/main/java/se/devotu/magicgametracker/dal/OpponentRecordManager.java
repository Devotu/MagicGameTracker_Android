package se.devotu.magicgametracker.dal;

import java.util.ArrayList;
import se.devotu.magicgametracker.info.Opponent;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Devotu on 2015-01-20.
 */
public class OpponentRecordManager extends DatabaseManager {

    public OpponentRecordManager(Context context) {
        super(context);
    }

    public ArrayList<Opponent> getAllOpponents(){
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM Opponents";

        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();

        ArrayList<Opponent> opponents = new ArrayList<Opponent>();
        Opponent foundOpponent;

        if (cursor.moveToFirst()) {
            do {
                foundOpponent = new Opponent();
                foundOpponent.setOpponent_ID(Integer.parseInt(cursor.getString(0)));
                foundOpponent.setOpponentName(cursor.getString(1));
                opponents.add(foundOpponent);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return opponents;
    }

    public Opponent getOpponentById(int opponentId) {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM Opponents WHERE Opponent_ID = " + opponentId;

        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        Opponent opponent = new Opponent(cursor.getInt(0), cursor.getString(1));

        cursor.close();
        db.close();

        return opponent;

    }

    public void addOpponent(String opponentName) {
        int opponentID = getFirstFreeId("Opponents", "Opponent_ID");
        String sql = "INSERT INTO Opponents (Opponent_ID, Name) VALUES (" + opponentID +", '"+ opponentName +"')";
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(sql);
        db.close();
    }

    public Boolean deleteOpponent(int opponentId) {
        try {
            addGamesToDefaultOpponent(opponentId);
            SQLiteDatabase db = this.getWritableDatabase();
            String sql = "DELETE FROM Opponents WHERE Opponent_ID = " + opponentId;
            db.execSQL(sql);
            db.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return  false;
        }
    }

    public void addGamesToDefaultOpponent(int opponentID){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put("Opponent_Id", 0);
        db.update("Games", value, "Opponent_Id = " + opponentID, null);
        db.close();
    }

}

