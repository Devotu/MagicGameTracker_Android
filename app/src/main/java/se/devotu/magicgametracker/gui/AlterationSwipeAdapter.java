package se.devotu.magicgametracker.gui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import se.devotu.magicgametracker.bl.AlterationManager;
import se.devotu.magicgametracker.bl.DeckManager;

/**
 * Created by Devotu on 2016-01-04.
 */
public class AlterationSwipeAdapter extends FragmentPagerAdapter{

    AlterationManager am;
    int deckID;

    public AlterationSwipeAdapter(FragmentManager fm, AlterationManager am, int deckID) {
        super(fm);
        this.am = am;
        this.deckID = deckID;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0: AddQuickAlterationFragment aqaf = new AddQuickAlterationFragment();
                aqaf.setDeckID(this.deckID);
                return aqaf;
            case 1: AddFullAlterationFragment afaf = new AddFullAlterationFragment();
                afaf.setDeckID(deckID);
                return afaf;
            default: break;
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0: return "Quick";
            case 1: return "Full";
            default: break;
        }
        return null;
    }
}
