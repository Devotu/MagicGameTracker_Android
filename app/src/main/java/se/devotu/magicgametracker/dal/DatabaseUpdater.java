package se.devotu.magicgametracker.dal;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Devotu on 2015-01-20.
 */
public class DatabaseUpdater extends DatabaseManager {

    private Context context;

    public DatabaseUpdater(Context context) {
        super(context);
        this.context = context;
    }

    public void updateDB(){


        //Det som ska med om inget ska göras
		/*System.out.println("dal DUp Inga Databasuppdateringar gjorda");

		//Det som ska med om något ska göras
		System.out.println("dal DUp Editing database");
		SQLiteDatabase db = this.getReadableDatabase();
		String sql;

		//Sätt in uppdateringar här


		//Kör uppdaterings sql
		db.execSQL(sql);
		System.out.println("dal DUp Done editing database");*/



        //Att klippa från

		/*//Lägger till kolumnen PerformanceRating
				sql = "ALTER TABLE Games ADD COLUMN PerformanceRating int;";

		//Sätter alla nyskapade PerformanceRating till 50 (medel)
		sql = "UPDATE Games SET PerformanceRating = 50";
		db.execSQL(sql);/*

		/*sql = "UPDATE Games SET Opponent_Id = 1";
		db.execSQL(sql);
		System.out.println("dal DUp Lagt till Topias på samtliga spelade");*/

		/*sql = "CREATE TABLE IF NOT EXISTS Opponents(" +
				"Opponent_ID	int PRIMARY KEY, " +
				"Name		String)";*/

		/*sql = "INSERT INTO Opponents (Opponent_ID, Name) " +
				"VALUES (0, 'DefaultOpponent')";
		db.execSQL(sql);
		System.out.println("dal DUp Lagt till Default Opponent");

		sql = "INSERT INTO Opponents (Opponent_ID, Name) " +
				"VALUES (1, 'Topias')";
		db.execSQL(sql);
		System.out.println("dal DUp Lagt till Topias");

		sql = "INSERT INTO Opponents (Opponent_ID, Name) " +
				"VALUES (2, 'Rickard')";
		db.execSQL(sql);
		System.out.println("dal DUp Lagt till Rickard");*/

		/*sql = "ALTER TABLE Games ADD COLUMN Opponent_Id int;";
		db.execSQL(sql);*/

        //sql = "UPDATE Decks SET Name='Common Creepers' WHERE Deck_ID=12";

		/*sql = "DELETE FROM Games WHERE Game_ID = 37";
		System.out.println("dal DUp Tagit bort felaktiga game nr 37");
		db.execSQL(sql);*/




    }

    //Version 2.1
    public void removeLastEntry(String table){

        String sql = "SELECT * FROM " + table;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToLast();

        //Bygger på att alla tabeller har en int som id som första kolumn
        int idToRemove = Integer.parseInt(cursor.getString(0));
        String idColumn = cursor.getColumnName(0);

        db.delete(table, idColumn + " = " + idToRemove , null);

        System.out.println("Removed last from " + table);

        //Kontrollera att det rör sig som decks, då måste Games tas hand om
        if (table == "Decks") {
            GameRecordManager gmdb = new GameRecordManager(context);
            gmdb.addDeckGamesToDefaultDeck(idToRemove);
            System.out.println("Games were handled");
        }

    }

}

