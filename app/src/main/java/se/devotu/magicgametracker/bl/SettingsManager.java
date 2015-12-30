package se.devotu.magicgametracker.bl;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * Created by Devotu on 2015-04-08.
 */
public class SettingsManager {

    public void setDateFormat(String dateFormat, Context context){
        SharedPreferences sharedpreferences = context.getSharedPreferences("AppSettings", Context.MODE_PRIVATE);
        Editor editor = sharedpreferences.edit();
        editor.putString("DateAndTime", dateFormat);
        editor.commit();
    }

    public String getDateFormat(Context context){
        SharedPreferences sharedpreferences = context.getSharedPreferences("AppSettings", Context.MODE_PRIVATE);
        return sharedpreferences.getString("DateAndTime", "Date not found");
    }
}
