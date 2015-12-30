package se.devotu.magicgametracker.bl;

import se.devotu.magicgametracker.dal.DatabaseUpdater;
import se.devotu.magicgametracker.dal.DatabaseInitiator;
import se.devotu.magicgametracker.dal.StorageManager;

import android.content.Context;

/**
 * Created by Devotu on 2015-01-20.
 */
public class AppInitializer {

    Context context;

    public AppInitializer(Context context) {
        super();
        this.context = context;
    }

    public void initalizeApp(){
        this.setDefaultDateFormat();
        this.checkForPrerequesites();
        this.updateExsistingDatabase();
        this.createPublicFolder();
    }

    /**
     * Checking for correct database
     * if none sets one up
     */
    private void checkForPrerequesites() {
        DatabaseInitiator newDB;
        newDB = new DatabaseInitiator(context);
        if (newDB.checkForDB() == false) {
            this.setUpNewDatabase();
        } else {
            newDB.close();
        }
    }

    private void setUpNewDatabase() {
        DatabaseInitiator initDB = new DatabaseInitiator(context);
        initDB.getReadableDatabase();
        initDB.close();

    }

    private void updateExsistingDatabase() {
        //Anropa databasuppdateraren
        DatabaseUpdater dbUpdater = new DatabaseUpdater(context);
        dbUpdater.updateDB();

    }

    private void createPublicFolder(){
        StorageManager sm = new StorageManager();
        sm.setUpExternalStorageArea();
    }

    private void setDefaultDateFormat(){
        SettingsManager sm = new SettingsManager();
        sm.setDateFormat("yyMMddHHmm", this.context);
    }
}
