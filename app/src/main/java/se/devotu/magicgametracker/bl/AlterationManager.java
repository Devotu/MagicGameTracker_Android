package se.devotu.magicgametracker.bl;

import android.content.Context;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import se.devotu.magicgametracker.dal.AlterationRecordManager;
import se.devotu.magicgametracker.info.Alteration;

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
}