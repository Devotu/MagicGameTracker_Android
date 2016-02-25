package se.devotu.magicgametracker.bl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

import se.devotu.magicgametracker.dal.DeckRecordManager;
import se.devotu.magicgametracker.enums.StatisticsType;
import se.devotu.magicgametracker.info.ColorValue;
import se.devotu.magicgametracker.info.ColorValueCollection;
import se.devotu.magicgametracker.info.Colorset;
import se.devotu.magicgametracker.info.Deck;
import se.devotu.magicgametracker.info.DeckStatistics;
import se.devotu.magicgametracker.info.Game;
import se.devotu.magicgametracker.enums.ManaColor;
import se.devotu.magicgametracker.info.PlayerStatistics;
import android.annotation.SuppressLint;
import android.content.Context;

/**
 * Created by Devotu on 2015-01-20.
 */


@SuppressLint("SimpleDateFormat")
public class StatisticsCalculator {

    Context context;

    public StatisticsCalculator(Context context) {
        super();
        this.context = context;
    }

    public DeckStatistics getDeckStatistics(int deckID){

        GameManager gameManager = new GameManager(context);
        ArrayList<Game> games = gameManager.getAllGamesForDeck(deckID);
        ArrayList<Colorset> colorsetWin = new ArrayList<Colorset>();
        ArrayList<Colorset> colorsetLoss = new ArrayList<Colorset>();
        ArrayList<Integer> performanceRatings = new ArrayList<Integer>(); //Version 2.1
        ArrayList<Float> winrateHistoryRating = new ArrayList<Float>(); //Version 3.0
        DeckStatistics stat = new DeckStatistics();
        stat.setDeck_ID(deckID);

        SettingsManager sm = new SettingsManager();
        String dateFormat = sm.getDateFormat(context);
        SimpleDateFormat df = new SimpleDateFormat(dateFormat);

        Date dateAltered = new Date();
        AlterationManager alterationManager = new AlterationManager(context);

        try {
            dateAltered = df.parse(alterationManager.getDateOfLastAlteration(deckID));
        } catch (ParseException e) {
            System.out.println("Misslyckades att läsa lekens ändrade datum");
            e.printStackTrace();
        }

        Date gameDate = new Date();

        for (Game game : games) {
            try {
                gameDate = df.parse(game.getDate());
            } catch (ParseException e) {
                System.out.println("Misslyckades att läsa lekens ändrade datum");
                e.printStackTrace();
            }
            if (game.isWin()) {
                stat.setTotalWins(stat.getTotalWins() + 1);
                winrateHistoryRating.add(stat.getTotalWinpc()); //Version 3.0
                colorsetWin.add(game.getOpposingColorset());
                if (gameDate.equals(dateAltered) || gameDate.after(dateAltered)) {
                    stat.setCurrentWins(stat.getCurrentWins() + 1);
                }
            } else {
                stat.setTotalLosses(stat.getTotalLosses() + 1);
                winrateHistoryRating.add(stat.getTotalWinpc()); //Version 3.0
                colorsetLoss.add(game.getOpposingColorset());
                if (gameDate.equals(dateAltered) || gameDate.after(dateAltered)) {
                    stat.setCurrentLosses(stat.getCurrentLosses() + 1);
                }
            }
            performanceRatings.add(game.getPerformanceRating()); //Version 2.1
        }
        stat.setWinrateHistory(winrateHistoryRating);

        if (colorsetWin.size() >= 1) {
            stat.setCommonwin(getMostCommonColor(colorsetWin));
        } else {
            stat.setCommonwin(ManaColor.NONE);
        }

        if (colorsetLoss.size() >= 1) {
            stat.setCommonloss(getMostCommonColor(colorsetLoss));
        } else {
            stat.setCommonloss(ManaColor.NONE);
        }

        //Version 2.1
        if (performanceRatings.size() > 0) {
            int performanceRating = 0;
            for (Integer rating : performanceRatings) {
                performanceRating = performanceRating + rating;
            }
            stat.setPerformanceRating((performanceRating/performanceRatings.size()));
        } else {
            stat.setPerformanceRating(50);

        }

        return stat;
    }


    //Version 3.6
    public PlayerStatistics getPlayerStatistics(StatisticsType statisticsType){

        PlayerStatistics playerStat = new PlayerStatistics();

        //Hämta alla games som finns
        GameManager gameManager = new GameManager(context);
        ArrayList<Game> games = gameManager.getAllGamesInDatabase();

        //Filtrera ut de som är relevanta
        switch (statisticsType){
            case TOTAL:
                //Behåll alla
                break;
            case TODAY:
                games = filterTodaysGames(games);
                break;
            case ACTIVE:
                games = filterActiveGames(games);
                break;
        }


        ArrayList<Colorset> playedColorsets = new ArrayList<Colorset>();
        ArrayList<Float> winrateHistoryRating = new ArrayList<Float>();
        DeckRecordManager drm = new DeckRecordManager(context);

        //Räkna statistik på kvarvarande
        for (Game game : games) {
            if (game.isWin()) {
                playerStat.setWins(playerStat.getWins() + 1);
            } else {
                playerStat.setLosses(playerStat.getLosses() + 1);
            }

            winrateHistoryRating.add(playerStat.getWinpc());
            Deck playedDeck = drm.getDeckInfo(game.getDeck_Id());
            playedColorsets.add(playedDeck.getColorset());
        }

        playerStat.setWinrateHistory(winrateHistoryRating);
        ColorValueCollection playedColorValueCollection = convertColorsetArrayToColorValueCollection(playedColorsets);
        playerStat.setColorValueCollection(playedColorValueCollection);

        return playerStat;
    }

    private ArrayList<Game> filterActiveGames(ArrayList<Game> games) {
        DeckRecordManager drm = new DeckRecordManager(context);
        Deck gameDeck;

        ArrayList<Game> filteredGames = new ArrayList<>();
        for (Game game : games){
            gameDeck = drm.getDeckInfo(game.getDeck_Id());
            if (gameDeck.getActive() == 1){
                filteredGames.add(game);
            }
        }

        return filteredGames;
    }

    private ArrayList<Game> filterTodaysGames(ArrayList<Game> games) {

        //Datum, måste finnas bättre sätt för detta?
        SettingsManager sm = new SettingsManager();
        String dateFormat = sm.getDateFormat(context);
        SimpleDateFormat df = new SimpleDateFormat(dateFormat);
        Date gameDate;
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, -1);
        String date = df.format(c.getTime());
        Date todayDate;
        todayDate = new Date();
        try {
            todayDate = df.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        ArrayList<Game> filteredGames = new ArrayList<>();
        for (Game game : games){
            try {
                gameDate = df.parse(game.getDate());
                if (!gameDate.before(todayDate)) {
                    filteredGames.add(game);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        return filteredGames;
    }


    public PlayerStatistics getPlayerOpponentStatistics(StatisticsType statisticsType, int opponentId){

        PlayerStatistics playerStat = new PlayerStatistics();

        GameManager gameManager = new GameManager(context);
        ArrayList<Game> games = gameManager.getAllGamesInDatabase();

        games = filterOpponentGames(games, opponentId);

        ArrayList<Colorset> playedColorsets = new ArrayList<Colorset>();
        ArrayList<Float> winrateHistoryRating = new ArrayList<Float>();
        DeckRecordManager drm = new DeckRecordManager(context);

        //Räkna statistik på kvarvarande
        for (Game game : games) {
            if (game.isWin()) {
                playerStat.setWins(playerStat.getWins() + 1);
            } else {
                playerStat.setLosses(playerStat.getLosses() + 1);
            }

            winrateHistoryRating.add(playerStat.getWinpc());
            Deck playedDeck = drm.getDeckInfo(game.getDeck_Id());
            playedColorsets.add(playedDeck.getColorset());
        }

        playerStat.setWinrateHistory(winrateHistoryRating);
        ColorValueCollection playedColorValueCollection = convertColorsetArrayToColorValueCollection(playedColorsets);
        playerStat.setColorValueCollection(playedColorValueCollection);

        return playerStat;
    }

    private ArrayList<Game> filterOpponentGames(ArrayList<Game> games, int opponentId) {

        ArrayList<Game> filteredGames = new ArrayList<>();
        for (Game game : games){
            if (game.getOpponentId() == opponentId){
                filteredGames.add(game);
            }
        }

        return filteredGames;
    }


    public ColorValueCollection getColorsInUse(StatisticsType statisticsType) {

        ColorValueCollection cvc = new ColorValueCollection();

        DeckRecordManager drm = new DeckRecordManager(context);
        ArrayList<Deck> decks = drm.getAllDecksInDatabase();

        if (statisticsType == StatisticsType.ACTIVE){
            decks = filterActiveDecks(decks);
        }

        for (Deck d : decks){
            ArrayList<ManaColor> colors = d.getColorset().getColors();
            for (ManaColor c : colors){
                switch (c){
                    case BLACK:
                        cvc.addOneToColor(ManaColor.BLACK);
                        break;
                    case WHITE:
                        cvc.addOneToColor(ManaColor.WHITE);
                        break;
                    case RED:
                        cvc.addOneToColor(ManaColor.RED);
                        break;
                    case BLUE:
                        cvc.addOneToColor(ManaColor.BLUE);
                        break;
                    case GREEN:
                        cvc.addOneToColor(ManaColor.GREEN);
                        break;
                    case DEVOID:
                        cvc.addOneToColor(ManaColor.DEVOID);
                        break;
                    case NONE:
                        cvc.addOneToColor(ManaColor.NONE);
                        break;
                    default:
                        break;
                }
            }
        }

        return cvc;
    }

    private ArrayList<Deck> filterActiveDecks(ArrayList<Deck> decks){

        ArrayList<Deck> filteredDecks = new ArrayList<>();
        for (Deck d : decks){
            if (d.getActive() == 1){
                filteredDecks.add(d);
            }
        }

        return filteredDecks;
    }


    public ManaColor getMostCommonColor(ArrayList<Colorset> colorsets) {

        ArrayList<ColorValue> colorStats = new ArrayList<ColorValue>();

        colorStats.add(new ColorValue(ManaColor.BLACK));
        colorStats.add(new ColorValue(ManaColor.WHITE));
        colorStats.add(new ColorValue(ManaColor.RED));
        colorStats.add(new ColorValue(ManaColor.BLUE));
        colorStats.add(new ColorValue(ManaColor.GREEN));

        for (Colorset colorset : colorsets) {
            ArrayList<ManaColor> colors = colorset.getColors();
            for (ManaColor manaColor : colors) {
                switch (manaColor) {
                    case BLACK:
                        colorStats.get(0).addValue(1);
                        break;
                    case WHITE:
                        colorStats.get(1).addValue(1);
                        break;
                    case RED:
                        colorStats.get(2).addValue(1);
                        break;
                    case BLUE:
                        colorStats.get(3).addValue(1);
                        break;
                    case GREEN:
                        colorStats.get(4).addValue(1);
                        break;
                    default:
                        break;
                }
            }
        }

        Collections.sort(colorStats, new ColorStatisticsComparer());

        ColorValue singleMostCommon = new ColorValue(ManaColor.NONE);
        if (colorStats.get(0).getValue() != colorStats.get(1).getValue()) {
            singleMostCommon = colorStats.get(0);
        }

        return singleMostCommon.getColor();
    }

    public ColorValueCollection convertColorsetArrayToColorValueCollection(ArrayList<Colorset> colorsets) {

        ColorValueCollection cvc = new ColorValueCollection();

        for (Colorset colorset : colorsets) {
            ArrayList<ManaColor> colors = colorset.getColors();
            for (ManaColor manaColor : colors) {
                switch (manaColor) {
                    case BLACK:
                        cvc.addOneToColor(ManaColor.BLACK);
                        break;
                    case WHITE:
                        cvc.addOneToColor(ManaColor.WHITE);
                        break;
                    case RED:
                        cvc.addOneToColor(ManaColor.RED);
                        break;
                    case BLUE:
                        cvc.addOneToColor(ManaColor.BLUE);
                        break;
                    case GREEN:
                        cvc.addOneToColor(ManaColor.GREEN);
                        break;
                    case DEVOID:
                        cvc.addOneToColor(ManaColor.DEVOID);
                        break;
                    case NONE:
                        cvc.addOneToColor(ManaColor.NONE);
                        break;
                    default:
                        break;
                }
            }
        }

        return cvc;
    }

    public ManaColor getMostCommonColorInColorValueCollection(ColorValueCollection cvc){
        ManaColor mostCommonColor = ManaColor.NONE;

        for (ManaColor manaColor : ManaColor.values()){
            if (cvc.getValueOfColor(manaColor) > cvc.getValueOfColor(mostCommonColor)){
                mostCommonColor = manaColor;
            } else if (cvc.getValueOfColor(manaColor) == cvc.getValueOfColor(mostCommonColor)){
                mostCommonColor = ManaColor.NONE;
            }
        }

        return mostCommonColor;
    }

    public double getWinPercent(int deckID){
        GameManager gameManager = new GameManager(context);
        ArrayList<Game> games = gameManager.getAllGamesForDeck(deckID);
        int wins = 0, losses = 0;

        for (Game game : games) {
            if (game.isWin()) {
                wins = wins +1;
            } else {
                losses = losses +1;
            }
        }

        double pc;
        if (games.size() >= 5) {
            pc = (double)wins/games.size()*100;
        } else {
            pc = (double)-1;
        }

        return pc;
    }

    class ColorStatisticsComparer implements Comparator<ColorValue>
    {
        @Override
        public int compare(ColorValue lstat, ColorValue rstat) {
            return rstat.getValue() - lstat.getValue();
        }

    }

}
