package se.devotu.magicgametracker.gui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Devotu on 2015-02-07.
 */
public class AboutSwipeAdapter extends FragmentPagerAdapter {

    public AboutSwipeAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0:
                AboutAppFragment aboutAppFragment = new AboutAppFragment();
                aboutAppFragment.updateTextNr(1, "This is an app developed for my own usage and have two purposes, to keep track of the decks I have and the games I play.");
                aboutAppFragment.updateTextNr(2, "Sliding to the right of this view you will find help about the app contents and actions sorted by page.");
                aboutAppFragment.updateTextNr(3, "The app will evolve as I keep using it and if you have any problems or want me to add some functionality please use the button below to send me a comment!");
                return aboutAppFragment;
            case 1:
                HelpFragment hf1 = new HelpFragment();
                hf1.updateTextNr(1, "This feature is pretty straight forward.");
                hf1.updateTextNr(2, "Type the Name of the deck, pick Format, check the Colors and write a short description of the Theme.");
                hf1.updateTextNr(3, "If you want to have a neutral colored deck simply leave all the colors unchecked.");
                hf1.updateTextNr(4, "To save press the floppy icon up in the header.");
                return hf1;
            case 2:
                HelpFragment hf2 = new HelpFragment();
                hf2.updateTextNr(1, "This page contains a list of registered opponents.");
                hf2.updateTextNr(2, "To add a new opponent press the plus sign up in the header.");
                hf2.updateTextNr(3, "To view opponent details select an opponent in the list.");
                hf2.updateTextNr(4, "There will always be an opponent named Default Opponent which cannot be deleted.");
                return hf2;
            case 3:
                HelpFragment hf3 = new HelpFragment();
                hf3.updateTextNr(1, "Add Opponent");
                hf3.updateTextNr(2, "Type the name and press the floppy icon up in the header. Done.");
                hf3.updateTextNr(3, "Opponent Details");
                hf3.updateTextNr(4, "This page contains the basic statistics of your play against choosen opponent. " +
                        "The statistics are exactly the same as your normal statistics only that it is calculated on games against choosen opponen only.");
                hf3.updateTextNr(5, "To delete the opponent just press the trashcan");
                return hf3;
            case 4:
                HelpFragment hf4 = new HelpFragment();
                hf4.updateTextNr(1, "The deck list is actually two lists. One that shows all decks created and one that hides all but those marked as active.");
                hf4.updateTextNr(2, "The decks will turn colorcoded as Very Bad, Bad, Average, Good or Very Good as soon as they have played five games.");
                hf4.updateTextNr(3, "The colorcode criteria is a Winrate of below 20%, 20-40%, 40-60%, 60-80% and above 80%");
                hf4.updateTextNr(4, "There will always be a deck called Default Deck. This deck contains all games from decks deleted in order to keep the winrate statistics correct.");
                return hf4;
            case 5:
                HelpFragment hf5 = new HelpFragment();
                hf5.updateTextNr(1, "Deck Page is a collection of views containing information about the selected deck; Details, Statistics, Played Games and Alterations.");
                hf5.updateTextNr(2, "Details contains values about Color, Format, Theme and whether the deck is Active.");
                hf5.updateTextNr(3, "Statistics contains basic Winrate statistics, the  average Performance Rating of the deck and a diagram showing the Winrate history.");
                hf5.updateTextNr(4, "Played Games is a list containing all games played with the deck sorted by date (most recent on top). " +
                        "To view further details about the game just press one." +
                        "The games are colorcoded green for wins and red for losses");
                hf5.updateTextNr(5, "Alterations is a list rather similar to Played Games accept it only contains a single comment given when the alteration is recorded and has no colorcode.");
                return hf5;
            case 6:
                HelpFragment hf6 = new HelpFragment();
                hf6.updateTextNr(1, "There are five actions available down in the header; Add Game, Register Change, Toggle Active, Help and Delete Deck.");
                hf6.updateTextNr(2, "Add Game opens up the page where you can register new games belonging to the deck.");
                hf6.updateTextNr(3, "Register Alteration opens up the page where you can register an Alteration of the deck.");
                hf6.updateTextNr(4, "Toggle Active simply decides whether the deck shall be visible in the list of active decks. " +
                        "The current status can be seen in the Details view.");
                hf6.updateTextNr(5, "Delete Deck do exactly what it says, it deleted the deck. " +
                        "There is however a follow-up dialog if you happened to press it by mistake. " +
                        "Worth noting here is that if the deck contains any played games they will move to the Default Deck.");
                return hf6;
            case 7:
                HelpFragment hf7 = new HelpFragment();
                hf7.updateTextNr(1, "This is the page where you register the details of the played game.");
                hf7.updateTextNr(2, "To register a game; " +
                        "Pick an opponent. If none chosen the game will be against the Default Opponent. " +
                        "Mark the color used by the opponent. " +
                        "Give a general comment about the game. " +
                        "Performance Rating is a star rating meant to illustrate how well the deck actually performed. " +
                        "Lets face it, sometimes you only win because the opponent get mana blocked or you play well and still lose by a tiny margin because you opponent had the game of his life. " +
                        "The Win/Loss choice should be pretty obvious and is the only detail that you actually have to select in order to be able to register.");
                hf7.updateTextNr(3, "To register press the little floppy icon named Save.");
                return hf7;
            case 8:
                HelpFragment hf8 = new HelpFragment();
                hf8.updateTextNr(1, "This page contains all information about the chosen game such as registered.");
                hf8.updateTextNr(2, "Up in the header there is a delete action available.");
                return hf8;
            case 9:
                HelpFragment hf9 = new HelpFragment();
                hf9.updateTextNr(1, "Add Alteration.");
                hf9.updateTextNr(2, "The only parameter here is a simple comment to remind you what changes have been made.");
                hf9.updateTextNr(3, "A small note here is that an alteration doesn't necessary need to be an alteration. " +
                        "I frequently use this to register a temporary alteration to view the Winrate of a deck during series of games.");
                hf9.updateTextNr(4, "View Alteration");
                hf9.updateTextNr(5, "This page contains all information about the chosen alteration such as registered, To delete the opponent just press the trashcan");
                return hf9;
            case 10:
                HelpFragment hf10 = new HelpFragment();
                hf10.updateTextNr(1, "As of now the statistics spans four pages of which the first three only differs in selection.");
                hf10.updateTextNr(2, "The first page shows statistics for all games played so far. " +
                        "There is a general sum of all games played and the resulting Winrate, " +
                        "then there is a diagram of the Winrate history " +
                        "and below that a summary of how many times each color has been played");
                hf10.updateTextNr(3, "The Active page has the same statistics but only regards those games played with the decks marked as active.");
                hf10.updateTextNr(4, "The Today page should be pretty obvious but notice that it actually evaluates the last 24 hours.");
                hf10.updateTextNr(5, "The Colors page gives a summary over how many times the five color occur in your decks. " +
                        "Active and All decks.");
                return hf10;
            case 11:
                HelpFragment hf11 = new HelpFragment();
                hf11.updateTextNr(1, "Here you have some  simple functionality to export and import your game database.");
                hf11.updateTextNr(2, "To Export simply press Export." + "" +
                        " Your database, exportedDB.mgt, will be available on your internal storage in a folder called MagicGameTracker.");
                hf11.updateTextNr(3, "The database has the ending .mgt but is an ordinary sqlite database.");
                hf11.updateTextNr(4, "To import you put the .mgt database in your Download folder on your internal storage " +
                        "and then press Import ");
                hf11.updateTextNr(5, "Notice that while importing a database the current database will be deleted!");
                return hf11;
            default: break;
        }
        return null;
    }

    @Override
    public int getCount() {
        return 12;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0: return "About";
            case 1: return "New Deck";
            case 2: return "Opponents";
            case 3: return "Add/View Opponent";
            case 4: return "Deck list";
            case 5: return "Deck page (1)";
            case 6: return "Deck page (2)";
            case 7: return "Add game";
            case 8: return "View game";
            case 9: return "Add/View alteration";
            case 10: return "Player Statistics";
            case 11: return "Export/Import";
            default: break;
        }
        return null;
    }
}
