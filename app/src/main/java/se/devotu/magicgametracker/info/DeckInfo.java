package se.devotu.magicgametracker.info;

import java.util.ArrayList;

/**
 * Created by Devotu on 2015-01-20.
 */
public class DeckInfo extends Deck{

    private ArrayList<ColorValue> wins, losses;
    //Version 1.2
    //private int version; Version 3.0
    private String dateCreated; //, dateAltered; Version 3.0
    private String dateAltered; //, dateAltered; Version 3.0

    public DeckInfo(int deck_ID, String name, Colorset colorset, String theme, ArrayList<ColorValue> wins, ArrayList<ColorValue> losses) {
        this.deck_ID = deck_ID;
        this.name = name;
        this.colorset = colorset;
        this.theme = theme;
        this.wins = wins;
        this.losses = losses;
    }

    public DeckInfo() {
    }

    /* Version 3.0
    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
    */


    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    /* Version 3.0
    public String getDateAltered() {
        return dateAltered;
    }

    public void setDateAltered(String dateAltered) {
        this.dateAltered = dateAltered;
    }
    */

    public ArrayList<ColorValue> getWins() {
        return wins;
    }

    public void setWins(ArrayList<ColorValue> wins) {
        this.wins = wins;
    }

    public ArrayList<ColorValue> getLosses() {
        return losses;
    }

    public void setLosses(ArrayList<ColorValue> losses) {
        this.losses = losses;
    }
}
