package se.devotu.magicgametracker.dal;

import java.util.ArrayList;

import se.devotu.magicgametracker.info.Colorset;
import se.devotu.magicgametracker.enums.ManaColor;

/**
 * Created by Devotu on 2015-01-20.
 */
public class SQLiteColorsetCodex {

    /**
     * Omvandlar ett Colorset till ett SQLiteColorsetObject
     * @param colorset
     * @return
     */
    public String parseColorset(Colorset colorset){

        String sco = ""; //SQLite Colorset Object
        ArrayList<ManaColor> colors = colorset.getColors();

        for (ManaColor manaColor : colors) {
            sco = sco + "," + manaColor.toString();
        }

        return  sco;
    }

    /**
     * Omvandlar ett SQLiteColorsetObject till ett Colorset
     * @param sqliteColorsetObject
     * @return
     */
    public Colorset parseSQLiteColorsetObject(String sqliteColorsetObject){

        Colorset colorset = new Colorset();
        String[] color = sqliteColorsetObject.split(",");

        for (int i = 0; i < color.length; i++) {
            if (color[i].equals("BLACK")) {
                colorset.addColor(ManaColor.BLACK);
            } else if (color[i].equals("WHITE")) {
                colorset.addColor(ManaColor.WHITE);
            } else if (color[i].equals("RED")) {
                colorset.addColor(ManaColor.RED);
            } else if (color[i].equals("BLUE")) {
                colorset.addColor(ManaColor.BLUE);
            } else if (color[i].equals("GREEN")) {
                colorset.addColor(ManaColor.GREEN);
            } else if (color[i].equals("NONE")) {
                colorset.addColor(ManaColor.NONE);
            }
        }

        return colorset;
    }
}

