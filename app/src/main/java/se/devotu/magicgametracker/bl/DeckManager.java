package se.devotu.magicgametracker.bl;

import java.util.ArrayList;

import se.devotu.magicgametracker.dal.AlterationRecordManager;
import se.devotu.magicgametracker.dal.DeckRecordManager;
import se.devotu.magicgametracker.dal.GameRecordManager;
import se.devotu.magicgametracker.enums.Formats;
import se.devotu.magicgametracker.info.Colorset;
import se.devotu.magicgametracker.info.Deck;
import se.devotu.magicgametracker.info.DeckInfo;
import android.content.Context;

/**
 * Created by Devotu on 2015-01-20.
 */
public class DeckManager {

    Context context;
    DeckRecordManager drm;

    public DeckManager(Context context) {
        super();
        this.context = context;
        drm = new DeckRecordManager(context);
    }

    public void CreateNewDeck(String name, String format, Colorset colorset, String theme) {
        drm.createNewDeck(new Deck(-1, name, format, colorset, theme));
    }

    public ArrayList<Deck> getAllDecks() {
        return drm.getAllDecksInDatabase();
    }

    public ArrayList<DeckInfo> getDeckInfoForEachDeck(ArrayList<Deck> decks) {
        ArrayList<DeckInfo> deckInfos = new ArrayList<DeckInfo>();

        for (Deck deck : decks) {
            deckInfos.add(drm.getDeckInfo(deck.getDeck_ID()));
        }
        return deckInfos;
    }

    public ArrayList<Deck> getAllActiveDecks() {
        ArrayList<Deck> allDecks = drm.getAllDecksInDatabase();
        ArrayList<Deck> activeDecks = new ArrayList<Deck>();
        for (int i = 0; i < allDecks.size(); i++) {
            if (allDecks.get(i).getActive() == 1) {
                activeDecks.add(allDecks.get(i));
            }
        }
        return activeDecks;
    }

    public ArrayList<Deck> getAllDecksOfKind(Formats format) {
        ArrayList<Deck> allDecks = drm.getAllDecksInDatabase();
        ArrayList<Deck> filterdDecks = new ArrayList<Deck>();
        for (int i = 0; i < allDecks.size(); i++) {
            if (allDecks.get(i).getEnumFormat() == format) {
                filterdDecks.add(allDecks.get(i));
            }
        }
        return filterdDecks;
    }

    public DeckInfo getDeckInfo(int deckID) {
        return drm.getDeckInfo(deckID);
    }

    public void toggleActive(int deckID){
        Deck deckToToggle = drm.getDeckInfo(deckID);
        if (deckToToggle.getActive() == 1){
            drm.deactivateDeck(deckID);
        }else {
            drm.activateDeck(deckID);
        }
    }

    public Boolean deleteDeck(int deckID) {
        if (deckID != 0){
            GameRecordManager gm = new GameRecordManager(context);
            gm.addDeckGamesToDefaultDeck(deckID);
            AlterationRecordManager am = new AlterationRecordManager(context);
            am.deleteAllAlterationForDeck(deckID);
            drm.deleteDeck(deckID);
            return true;
        }else {
            return false;
        }
    }

    public void updateDeck(Deck newDeck) {
        drm.updateDeck(newDeck);
    }

    /*
    //Flyttat till AlterationManager
    public void AddAlteration(int deckID, String comment) {
        DeckRecordManager deckManagerDB = new DeckRecordManager(context);
        deckManagerDB.addAlteration(deckID, comment);
    }*/
}
