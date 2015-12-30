package se.devotu.magicgametracker.info;

/**
 * Created by Devotu on 2015-01-20.
 */
public class Alteration {

    private int alteration_ID;
    private int deck_Id;
    private String date;
    private int revision;
    private String comment;

    public Alteration() {
    }

    public Alteration(int alteration_ID, int deck_Id, String date, int revision, String comment) {
        this.alteration_ID = alteration_ID;
        this.deck_Id = deck_Id;
        this.date = date;
        this.revision = revision;
        this.comment = comment;
    }

    public int getAlteration_ID() {
        return alteration_ID;
    }

    public void setAlteration_ID(int alteration_ID) {
        this.alteration_ID = alteration_ID;
    }

    public int getDeck_Id() {
        return deck_Id;
    }

    public void setDeck_Id(int deck_Id) {
        this.deck_Id = deck_Id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getRevision() {
        return revision;
    }

    public void setRevision(int revision) {
        this.revision = revision;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
