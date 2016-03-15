package se.devotu.magicgametracker.bl;

import android.content.Context;
import android.widget.EditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import se.devotu.magicgametracker.dal.AlterationRecordManager;
import se.devotu.magicgametracker.enums.ManaColor;
import se.devotu.magicgametracker.info.Alteration;
import se.devotu.magicgametracker.info.Deck;

/**
 * Created by Otto on 2015-01-20.
 */
public class AlterationManager {

    Context context;
    AlterationRecordManager arm;

    public AlterationManager(Context context) {
        super();
        this.context = context;
        this.arm = new AlterationRecordManager(context);
    }

    public void addNewAlteration(int deckID, String comment) {
        arm.addAlteration(deckID, comment);
    }

    public ArrayList<Alteration> getAllAlterationsForDeck(int deckID) {
        return arm.getAllAlterationsForDeck(deckID);
    }

    public Alteration getAlterationById(int alterationID){
        return arm.getAlterationById(alterationID);
    }

    public Boolean deleteAlterationById(int alterationID) {
        SettingsManager sm = new SettingsManager();
        String dateFormat = sm.getDateFormat(context);
        SimpleDateFormat df = new SimpleDateFormat(dateFormat);
        Alteration alterationToDelete = getAlterationById(alterationID);
        Date dateOfDeleteAlteration;
        Date dateOfComparedAlteration;
        ArrayList<Alteration> allAlterationForDeck = getAllAlterationsForDeck(alterationToDelete.getDeck_Id());
        for (Alteration a : allAlterationForDeck){
            try {
                dateOfComparedAlteration = df.parse(a.getDate());
                dateOfDeleteAlteration = df.parse(alterationToDelete.getDate());
            } catch (ParseException e) {
                System.out.println("Misslyckades att läsa lekens ändrade datum");
                e.printStackTrace();
                return false;
            }
            if (dateOfComparedAlteration.compareTo(dateOfDeleteAlteration)<0){
                return arm.deleteAlterationById(alterationID);
            }
        }
        return false;
    }

    public String getDateOfLastAlteration(int deckID){
        Alteration lastAlteration;
        lastAlteration = arm.getLastAlterationForDeck(deckID);
        return lastAlteration.getDate();
    }

    public void addNewFullAlteration(Deck originalDeck, Deck newDeck, String comment) {
        String fullComment = "";

        //Name
        if (!newDeck.getName().equals(originalDeck.getName())){
            fullComment = fullComment + "Name changed from " + originalDeck.getName() + " to " + newDeck.getName() + ". ";
        }

        //Format
        if (!newDeck.getFormat().equals(originalDeck.getFormat())){
            fullComment = fullComment + "Format changed from " + originalDeck.getFormat() + " to " + newDeck.getFormat() + ". ";
        }

        //Color
        if (!newDeck.getColorset().getColors().equals(originalDeck.getColorset().getColors())){
            String colorChange = "Colors changed from ";

            for (ManaColor mc : originalDeck.getColorset().getColors()) {
                 colorChange = colorChange + mc.toString() + ", ";
            }
            colorChange = colorChange.substring(0, colorChange.length()-2);

            colorChange = colorChange + " to ";

            for (ManaColor mc : newDeck.getColorset().getColors()) {
                colorChange = colorChange + mc.toString() + ", ";
            }
            colorChange = colorChange.substring(0, colorChange.length()-2);

            fullComment = fullComment + colorChange + ". ";
        }

        //Theme
        if (!newDeck.getTheme().equals(originalDeck.getTheme())){
            fullComment = fullComment + "Theme changed from " + originalDeck.getTheme() + " to " + newDeck.getTheme() + ". ";
        }

        //Comment
        if (!comment.equals("")){
            fullComment = fullComment + "Comment: " + comment;
        }

        arm.addAlteration(originalDeck.getDeck_ID(), fullComment);

        DeckManager dm = new DeckManager(context);
        dm.updateDeck(newDeck);
    }
}