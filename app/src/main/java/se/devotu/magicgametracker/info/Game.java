package se.devotu.magicgametracker.info;

/**
 * Created by Devotu on 2015-01-20.
 */
public class Game {

    protected int game_ID;
    protected int deck_Id;
    protected boolean win;
    protected Colorset opposingColorset;
    protected String comment;
    //Version 1.2
    protected String date;
    //Version 2.0
    protected int opponentId;
    //Version 2.1
    protected int performanceRating;

    public Game(int gameID, int deckId, Boolean win, Colorset colorset, String comment, int opponentId, int performanceRating) {
        this.game_ID = gameID;
        this.deck_Id = deckId;
        this.win = win;
        this.opposingColorset = colorset;
        this.comment = comment;
        this.opponentId = opponentId;
        this.performanceRating = performanceRating;
    }

    public Game() {
    }



    public int getGame_ID() {
        return game_ID;
    }
    public void setGame_ID(int game_ID) {
        this.game_ID = game_ID;
    }
    public int getDeck_Id() {
        return deck_Id;
    }
    public void setDeck_Id(int deck_Id) {
        this.deck_Id = deck_Id;
    }
    public boolean isWin() {
        return win;
    }
    public void setWin(boolean win) {
        this.win = win;
    }
    public Colorset getOpposingColorset() {
        return opposingColorset;
    }
    public void setOpposingColorset(Colorset opposingColorset) {
        this.opposingColorset = opposingColorset;
    }
    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getOpponentId() {
        return opponentId;
    }

    public void setOpponentId(int opponentId) {
        this.opponentId = opponentId;
    }

    public int getPerformanceRating() {
        return performanceRating;
    }

    public void setPerformanceRating(int performanceRating) {
        this.performanceRating = performanceRating;
    }

}
