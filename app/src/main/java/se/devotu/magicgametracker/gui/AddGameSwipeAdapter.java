package se.devotu.magicgametracker.gui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import se.devotu.magicgametracker.bl.DeckManager;

/**
 * Created by Devotu on 2016-01-04.
 */
public class AddGameSwipeAdapter extends FragmentPagerAdapter{

    DeckManager dm;

    public AddGameSwipeAdapter(FragmentManager fm, DeckManager dm) {
        super(fm);
        this.dm = dm;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0: return new AddGameFragment();
            case 1: return new LifeTrackerCollectionFragment();
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
            case 0: return "Add Game";
            case 1: return "Lifetracker";
            default: break;
        }
        return null;
    }
}
