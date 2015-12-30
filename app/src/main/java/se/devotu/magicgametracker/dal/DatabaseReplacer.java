package se.devotu.magicgametracker.dal;

import android.content.Context;

/**
 * Created by Devotu on 2015-03-27.
 */
public class DatabaseReplacer extends DatabaseManager{

    Context context;

    public DatabaseReplacer(Context context) {
        super(context);
        this.context = context;
    }

    public boolean replaceCurrentDatabaseWith(){
        return true;
    }
}
