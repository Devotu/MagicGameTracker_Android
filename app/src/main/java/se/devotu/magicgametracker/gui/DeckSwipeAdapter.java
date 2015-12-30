package se.devotu.magicgametracker.gui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Devotu on 2015-02-07.
 */
public class DeckSwipeAdapter extends FragmentPagerAdapter {

    public DeckSwipeAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0: return new DeckDetailsFragment();
            case 1: return new DeckStatisticsFragment();
            case 2: return new DeckGamesFragment();
            case 3: return new DeckAlterationsFragment();
            default: break;
        }
        return null;
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0: return "Details";
            case 1: return "Statistics";
            case 2: return "Games";
            case 3: return "Alterations";
            default: break;
        }
        return null;
    }
}
