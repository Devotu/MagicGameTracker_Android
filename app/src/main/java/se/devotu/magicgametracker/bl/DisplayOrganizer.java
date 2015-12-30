package se.devotu.magicgametracker.bl;

/**
 * Created by Devotu on 2015-04-08.
 */
public class DisplayOrganizer {

    /**
     * Accepts a sting in standard date and time format (yyMMddHHmm).
     */
    public String getDisplayDateAndTime(String dateAndTime){
        String returnDateAndTime;
        returnDateAndTime = dateAndTime.substring(0,6) + " " + dateAndTime.substring(6,8) + ":" + dateAndTime.substring(8,10);
        return returnDateAndTime;
    }
}
