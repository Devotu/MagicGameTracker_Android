package se.devotu.magicgametracker.info;

/**
 * Created by Devotu on 2015-03-15.
 */
public class GameInfo extends Game {

    private String opponentName;

    public GameInfo(int game_ID, Boolean win, Colorset colorset, String comment, String opponent, int performanceRating) {
        this.game_ID = game_ID;
        this.win = win;
        this.opposingColorset = colorset;
        this.comment = comment;
        this.opponentName = opponent;
        this.performanceRating = performanceRating;
    }

    public GameInfo (Game game, Opponent opponent){
        this.game_ID = game.game_ID;
        this.win = game.isWin();
        this.opposingColorset = game.getOpposingColorset();
        this.comment = game.getComment();
        this.opponentName = opponent.getOpponentName();
        this.performanceRating = game.getPerformanceRating();
    }

    public String getOpponentName() {
        return opponentName;
    }

    public void setOpponentName(String opponent) {
        this.opponentName = opponent;
    }

}
