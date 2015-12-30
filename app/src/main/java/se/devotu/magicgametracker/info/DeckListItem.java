package se.devotu.magicgametracker.info;

/**
 * Created by Devotu on 2015-01-20.
 */
public class DeckListItem {
    private int deck_ID;
    private String name;
    private double winpc;

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
    public double getWinpc() {
        return winpc;
    }
    public void setWinpc(double winpc) {
        this.winpc = winpc;
    }
}
