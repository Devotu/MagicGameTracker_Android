package se.devotu.magicgametracker.info;

import se.devotu.magicgametracker.enums.Formats;

/**
 * Created by Devotu on 2015-01-20.
 */
public class Deck {

    protected int deck_ID;
    protected int active;
    protected String name;
    protected String format;
    protected Colorset colorset;
    protected String theme;


    public Deck(int deck_ID, String name, String format, Colorset colorset, String theme) {
        this.deck_ID = deck_ID;
        this.name = name;
        this.format = format;
        this.colorset = colorset;
        this.theme = theme;
    }

    public Deck() {
    }

    public int getDeck_ID() {
        return deck_ID;
    }
    public void setDeck_ID(int deck_ID) {
        this.deck_ID = deck_ID;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getFormat() {
        return format;
    }
    public Formats getEnumFormat(){
        return Formats.valueOf(format);
    }
    public void setFormat(String format) {
        this.format = format;
    }
    public Colorset getColorset() {
        return colorset;
    }
    public void setColorset(Colorset colorset) {
        this.colorset = colorset;
    }
    public String getTheme() {
        return theme;
    }
    public void setTheme(String theme) {
        this.theme = theme;
    }
    public int getActive() {
        return active;
    }
    public void setActive(int active) {
        this.active = active;
    }
}