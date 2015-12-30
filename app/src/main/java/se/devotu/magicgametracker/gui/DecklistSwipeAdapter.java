package se.devotu.magicgametracker.gui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import se.devotu.magicgametracker.bl.DeckManager;

/**
 * Created by Devotu on 2015-02-07.
 */
public class DecklistSwipeAdapter extends FragmentPagerAdapter {

    DeckManager dm;

    public DecklistSwipeAdapter(FragmentManager fm, DeckManager dm) {
        super(fm);
        this.dm = dm;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0: return new DecklistActiveFragment();
            case 1: return new DecklistAllFragment();
            case 2: return new DecklistFormatFragment();
            default: break;
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0: return "Active decks";
            case 1: return "All decks";
            case 2: return "Format filter";
            default: break;
        }
        return null;
    }
}
