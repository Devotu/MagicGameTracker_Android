package se.devotu.magicgametracker.bl;

import java.util.ArrayList;

import se.devotu.magicgametracker.R;
import se.devotu.magicgametracker.dal.OpponentRecordManager;
import se.devotu.magicgametracker.info.Opponent;
import android.content.Context;

/**
 * Created by Devotu on 2015-01-20.
 */
public class OpponentManager {

    Context context;

    public OpponentManager(Context context) {
        super();
        this.context = context;
    }

    public void AddOpponent(String opponentName) {
        OpponentRecordManager orm = new OpponentRecordManager(context);
        orm.addOpponent(opponentName);
    }

    public ArrayList<Opponent> getAllOpponents() {

        OpponentRecordManager orm = new OpponentRecordManager(context);
        ArrayList<Opponent> opponents = new ArrayList<Opponent>();
        opponents = orm.getAllOpponents();

        //Hämta korrekt namn för Default opponent och byt ut
        String defaultOpponentGivenName = context.getString(R.string.defaultOpponent);
        opponents.get(0).setOpponentName(defaultOpponentGivenName);

        return opponents;
    }

    public Opponent getOpponentById(int opponentId) {
        OpponentRecordManager orm = new OpponentRecordManager(context);
        return orm.getOpponentById(opponentId);
    }

    public Boolean deleteOpponent(int opponentId) {
        if (opponentId != 0) {
            OpponentRecordManager orm = new OpponentRecordManager(context);
            return orm.deleteOpponent(opponentId);
        } else {
            return false;
        }
    }
}
