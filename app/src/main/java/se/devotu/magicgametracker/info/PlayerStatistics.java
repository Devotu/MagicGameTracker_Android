package se.devotu.magicgametracker.info;

import java.util.ArrayList;

/**
 * Created by Devotu on 2015-01-20.
 */
public class PlayerStatistics {

    int wins, losses, winpc;
    ColorValueCollection colorValueCollection; //Version 3.6
    private ArrayList<Float> winrateHistory; //Version 3.6

    public int getWins() {
        return wins;
    }
    public void setWins(int win) {
        this.wins = win;
    }
    public int getLosses() {
        return losses;
    }
    public void setLosses(int losses) {
        this.losses = losses;
    }
    public float getWinpc() {
        return ((float)wins/(losses+wins)*100);
    }
    public void setWinpc(int winpc) {
        this.winpc = winpc;
    }
    public ColorValueCollection getColorValueCollection() {
        return colorValueCollection;
    }
    public void setColorValueCollection(ColorValueCollection colorValueCollection) {
        this.colorValueCollection = colorValueCollection;
    }
    public ArrayList<Float> getWinrateHistory() {
        return winrateHistory;
    }
    public void setWinrateHistory(ArrayList<Float> winrateHistory) {
        this.winrateHistory = winrateHistory;
    }
}