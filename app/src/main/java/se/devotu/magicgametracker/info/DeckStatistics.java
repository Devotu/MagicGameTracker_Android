package se.devotu.magicgametracker.info;

import java.util.ArrayList;

import se.devotu.magicgametracker.enums.ManaColor;

/**
 * Created by Devotu on 2015-01-20.
 */
public class DeckStatistics {

    private int deck_ID;
    private int totalWins, totalLosses;
    private int currentWins, currentLosses;
    private ManaColor commonwin, commonloss;
    private int performanceRating; //Version 2.1
    private ArrayList<Float> winrateHistory; //Version 3.0

    public int getDeck_ID() {
        return deck_ID;
    }
    public void setDeck_ID(int deck_ID) {
        this.deck_ID = deck_ID;
    }
    public float getTotalWinpc() {
        return ((float)totalWins/(totalWins+totalLosses)*100);
    }
    public float getCurrentWinpc() {
        return ((float)currentWins/(currentWins+currentLosses)*100);
    }
    public ManaColor getCommonwin() {
        return commonwin;
    }
    public void setCommonwin(ManaColor commonwin) {
        this.commonwin = commonwin;
    }
    public ManaColor getCommonloss() {
        return commonloss;
    }
    public void setCommonloss(ManaColor commonloss) {
        this.commonloss = commonloss;
    }
    public int getTotalWins() {
        return totalWins;
    }
    public void setTotalWins(int totalWins) {
        this.totalWins = totalWins;
    }
    public int getTotalLosses() {
        return totalLosses;
    }
    public void setTotalLosses(int totalLosses) {
        this.totalLosses = totalLosses;
    }
    public int getCurrentWins() {
        return currentWins;
    }
    public void setCurrentWins(int currentWins) {
        this.currentWins = currentWins;
    }
    public int getCurrentLosses() {
        return currentLosses;
    }
    public void setCurrentLosses(int currentLosses) {
        this.currentLosses = currentLosses;
    }
    public int getPerformanceRating() {
        return performanceRating;
    }
    public void setPerformanceRating(int performanceRating) {
        this.performanceRating = performanceRating;
    }
    public ArrayList<Float> getWinrateHistory() {
        return winrateHistory;
    }
    public void setWinrateHistory(ArrayList<Float> winrateHistory) {
        this.winrateHistory = winrateHistory;
    }

}