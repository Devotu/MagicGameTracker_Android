package se.devotu.magicgametracker.bl;

import java.util.ArrayList;

import se.devotu.magicgametracker.dal.GameRecordManager;
import se.devotu.magicgametracker.info.Colorset;
import se.devotu.magicgametracker.info.Game;
import se.devotu.magicgametracker.info.GameInfo;
import se.devotu.magicgametracker.info.Opponent;

import android.content.Context;

/**
 * Created by Otto on 2015-01-20.
 */
public class GameManager {

    Context context;
    GameRecordManager grm;

    public GameManager(Context context) {
        super();
        this.context = context;
        grm = new GameRecordManager(context);
    }

    public void addNewGame(int deckID, Boolean win, Colorset colorset, String comment, int opponentId, int performanceRating) {
        grm.createNewGame(new Game(-1, deckID, win, colorset, comment, opponentId, performanceRating));
    }

    public ArrayList<Game> getAllGamesInDatabase() {
        return grm.getAllGamesInDatabase();
    }

    public ArrayList<Game> getAllGamesForDeck(int deckID) {
        return grm.getAllGamesForDeck(deckID);
    }

    //Version 3.0
    public Game getGameById(int gameID) {
        return grm.getGameById(gameID);
    }

    public GameInfo getGameInfoById(int gameID){
        Game game = grm.getGameById(gameID);
        OpponentManager om = new OpponentManager(context);
        Opponent opponent = om.getOpponentById(game.getOpponentId());
        return new GameInfo(game, opponent);
    }

    public Boolean deleteGameById(int gameID) {
        return grm.deleteGameById(gameID);
    }
}