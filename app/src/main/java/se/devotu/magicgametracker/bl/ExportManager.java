package se.devotu.magicgametracker.bl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import se.devotu.magicgametracker.dal.DatabaseManager;
import se.devotu.magicgametracker.dal.SQLiteColorsetCodex;
import se.devotu.magicgametracker.info.Deck;
import se.devotu.magicgametracker.info.DeckInfo;
import se.devotu.magicgametracker.info.Game;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaScannerConnection;
import android.os.Environment;

/**
 * Created by Devotu on 2015-01-20.
 * Deprecated 2015-03-29
 */


@SuppressLint("SimpleDateFormat")
public class ExportManager {

    Context context;

    public ExportManager(Context context) {
        super();
        this.context = context;
    }

    public File createPublicFolder(){
        return createFolder();
    }

    private File createFolder(){
        File folder = new File(Environment.getExternalStorageDirectory()
                +File.separator
                +"MagicGameTracker");
        if (!folder.exists()) {
            folder.mkdirs();
            System.out.println("created a folder at: " + folder.getPath());
        }

        return folder;
    }

    public void exportAllGames() {

        ArrayList<String> exportData = new ArrayList<String>();
        DeckManager dm = new DeckManager(context);
        ArrayList<Deck> decks = dm.getAllDecks();
        GameManager gm = new GameManager(context);
        ArrayList<Game> allGames = new ArrayList<Game>();
        ArrayList<Game> deckGames = new ArrayList<Game>();

        for (Deck deck : decks) {
            deckGames = gm.getAllGamesForDeck(deck.getDeck_ID());

            for (Game game : deckGames) {
                allGames.add(game);
            }
            deckGames = new ArrayList<Game>();
        }

        SQLiteColorsetCodex codex = new SQLiteColorsetCodex();

        for (Game game : allGames) {
            exportData.add(game.getDeck_Id() +
                    ";" + game.getGame_ID() +
                    ";" + game.isWin() +
                    ";" + codex.parseColorset(game.getOpposingColorset()) +
                    ";" + game.getComment() +
                    ";" + game.getDate() +
                    ";" + game.getOpponentId() +
                    ";" + game.getPerformanceRating());
        }


        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            System.out.println("no SDCARD present");
        } else {

            File folder = createFolder();

            SettingsManager sm = new SettingsManager();
            String dateFormat = sm.getDateFormat(context);
            SimpleDateFormat df = new SimpleDateFormat(dateFormat);
            Calendar c = Calendar.getInstance();
            String date = df.format(c.getTime());

            try{
                BufferedWriter bFileWriter = new BufferedWriter(new FileWriter(folder.getPath() + "/ExportedGames_" + date + ".txt", true));
                bFileWriter.append("dId;gId;win;color;comment;date;oId"); //TODO Fixa så den inte dör när lekar exporteras

                for (String csvgame : exportData) {
                    bFileWriter.append("\r\n" + csvgame);
                }
                bFileWriter.flush();
                bFileWriter.close();
                System.out.println("Did create a file at: " + folder.getPath() + "/ExportedGames_" + date + ".txt");
            }catch(Exception e){
                e.printStackTrace();
                System.out.println("Did NOT create a file at: " + folder.getPath() + "/ExportedGames_" + date + ".txt");
            }

            MediaScannerConnection.scanFile(context, new String[]{folder.getAbsolutePath() + "/ExportedGames_" + date + ".txt"}, null, null);

        }



    }

    public void exportAllDecks() {

        ArrayList<String> exportData = new ArrayList<String>();
        DeckManager dm = new DeckManager(context);
        ArrayList<Deck> decks = dm.getAllDecks();
        ArrayList<DeckInfo> deckInfos = dm.getDeckInfoForEachDeck(decks);
        SQLiteColorsetCodex codex = new SQLiteColorsetCodex();

        for (DeckInfo deckInfo : deckInfos) {
            exportData.add(deckInfo.getDeck_ID() +
                    ";" + deckInfo.getName() +
                    ";" + codex.parseColorset(deckInfo.getColorset()) +
                    ";" + deckInfo.getTheme() +
                    ";" + deckInfo.getActive() +
                    ";" + deckInfo.getDateCreated()); // Version 3.0
                    //";" + deckInfo.getDateAltered() +
                    //";" + deckInfo.getVersion());
        }

        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            System.out.println("no SDCARD present");
        } else {

            File folder = createFolder();

            SettingsManager sm = new SettingsManager();
            String dateFormat = sm.getDateFormat(context);
            SimpleDateFormat df = new SimpleDateFormat(dateFormat);
            Calendar c = Calendar.getInstance();
            String date = df.format(c.getTime());

            try{
                BufferedWriter bFileWriter = new BufferedWriter(new FileWriter(folder.getPath() + "/ExportedDecks_" + date + ".txt", true));
                bFileWriter.append("dId;name;color;theme;active;created;altered;version");

                for (String csvdeck : exportData) {
                    bFileWriter.append("\r\n" + csvdeck);
                }
                bFileWriter.flush();
                bFileWriter.close();
                System.out.println("Did create a file at: " + folder.getPath() + "/ExportedDecks_" + date + ".txt");
            }catch(Exception e){
                e.printStackTrace();
                System.out.println("Did NOT create a file at: " + folder.getPath() + "/ExportedDecks_" + date + ".txt");
            }

            MediaScannerConnection.scanFile(context, new String[]{folder.getAbsolutePath() + "/ExportedDecks_" + date + ".txt"}, null, null);

        }



    }

    public void exportDatabase(){

        DatabaseManager dm = new DatabaseManager(context);
        SQLiteDatabase db = dm.getReadableDatabase();

    }

    public static void copyFile(FileInputStream fromFile, FileOutputStream toFile) throws IOException {
        FileChannel fromChannel = null;
        FileChannel toChannel = null;
        try {
            fromChannel = fromFile.getChannel();
            toChannel = toFile.getChannel();
            fromChannel.transferTo(0, fromChannel.size(), toChannel);
        } finally {
            try {
                if (fromChannel != null) {
                    fromChannel.close();
                }
            } finally {
                if (toChannel != null) {
                    toChannel.close();
                }
            }
        }
    }

}

