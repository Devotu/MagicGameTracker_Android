package se.devotu.magicgametracker.info;

/**
 * Created by Devotu on 2015-01-20.
 */
public class Opponent {

    private int opponent_ID;
    private String opponentName;

    public Opponent(int opponent_ID, String opponentName) {
        super();
        this.opponent_ID = opponent_ID;
        this.opponentName = opponentName;
    }

    public Opponent() {
        super();
        this.opponent_ID = -1;
        this.opponentName = "notset";
    }

    public int getOpponent_ID() {
        return opponent_ID;
    }
    public void setOpponent_ID(int opponent_ID) {
        this.opponent_ID = opponent_ID;
    }
    public String getOpponentName() {
        return opponentName;
    }
    public void setOpponentName(String opponentName) {
        this.opponentName = opponentName;
    }


}
