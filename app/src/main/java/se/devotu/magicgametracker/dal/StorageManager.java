package se.devotu.magicgametracker.dal;

import android.os.Environment;

import java.io.File;

import se.devotu.magicgametracker.enums.Status;

/**
 * Created by Devotu on 2015-03-28.
 */
public class StorageManager {

    private static final String INTERNAL_STORAGE = "//data/data/se.devotu.magicgametracker";
    private static final String EXTERNAL_FOLDER = "MagicGameTracker";

    /**
     * @return the internal storage path
     */
    public static String getInternalStoragePath() {
        return INTERNAL_STORAGE;
    }

    public File getInternalStorage(){
        return new File(INTERNAL_STORAGE);
    }

    public Status setUpExternalStorageArea(){
        File folder = new File(Environment.getExternalStorageDirectory()
                +File.separator
                +EXTERNAL_FOLDER);
        if (!folder.exists()) {
            folder.mkdirs();
            System.out.println("created a folder at: " + folder.getPath());
        }

        if (folder.exists()){
            return Status.Done;
        } else {
            return Status.Failed;
        }
    }

    public File getExternalStorage(){
        File folder = new File(Environment.getExternalStorageDirectory()
                +File.separator
                +EXTERNAL_FOLDER);
        return folder;
    }

    public File getDownloadsFolder(){
        return Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
    }
}
