package se.devotu.magicgametracker.bl;

import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

import se.devotu.magicgametracker.dal.DatabaseManager;
import se.devotu.magicgametracker.dal.StorageManager;
import se.devotu.magicgametracker.enums.Status;
import se.devotu.magicgametracker.enums.Validation;

/**
 * Created by Devotu on 2015-03-28.
 */
public class IntegrationManager {

    public Status exportDatabaseToExternalStorage(Context context){

        StorageManager sm = new StorageManager();
        File externalStorageArea = sm.getExternalStorage();
        String targetFilePath = externalStorageArea  + File.separator + "exportedDB.mgt";
        File exportedDB = new File(targetFilePath);

        DatabaseManager dbm = new DatabaseManager(context);
        File db = dbm.getDatabase();

        try {
            copyFile(new FileInputStream(db), new FileOutputStream(exportedDB));
            return Status.Done;
        } catch (IOException e) {
            e.printStackTrace();
            return Status.Failed;
        }
    }

    public Status importDatabaseFromExternalStorage(Context context){

        StorageManager sm = new StorageManager();
        File externalDownloadArea = sm.getDownloadsFolder();
        String newDatabasePath = externalDownloadArea+ File.separator + "exportedDB.mgt";
        File importedDB = new File(newDatabasePath);

        DatabaseManager dbm = new DatabaseManager(context);
        File db = dbm.getDatabase();

        Status replacementStatus = Status.Failed;
        if (dbm.ValidateDatabase(importedDB) == Validation.Correct){
            if (dbm.DeleteCurrentDatabase() == Status.Done){
                try {
                    copyFile(new FileInputStream(importedDB), new FileOutputStream(db));
                    replacementStatus = Status.Done;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

        if (replacementStatus == Status.Done){
            db = dbm.getDatabase();
            if (dbm.ValidateDatabase(db) == Validation.Correct){
                return Status.Done;
            } else {
                return Status.Failed;
            }
        } else {
            return Status.Failed;
        }
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
