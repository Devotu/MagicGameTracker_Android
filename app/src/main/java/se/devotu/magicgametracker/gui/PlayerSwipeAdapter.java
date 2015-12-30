package se.devotu.magicgametracker.gui;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import se.devotu.magicgametracker.enums.StatisticsType;

/**
 * Created by Devotu on 2015-02-07.
 */
public class PlayerSwipeAdapter extends FragmentPagerAdapter {

    public PlayerSwipeAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0:
                PlayerStatisticsFragment pTotalsf = new PlayerStatisticsFragment();
                pTotalsf.setStatisticsType(StatisticsType.TOTAL);
                return pTotalsf;
            case 1:
                PlayerStatisticsFragment pActivesf = new PlayerStatisticsFragment();
                pActivesf.setStatisticsType(StatisticsType.ACTIVE);
                return pActivesf;
            case 2:
                PlayerStatisticsFragment pTodaysf = new PlayerStatisticsFragment();
                pTodaysf.setStatisticsType(StatisticsType.TODAY);
                return pTodaysf;
            case 3:
                ColorsInUseFragment pColorsInUse = new ColorsInUseFragment();
                return pColorsInUse;
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
            case 0: return "Total";
            case 1: return "Active";
            case 2: return "Today";
            case 3: return "Colors in decks";
            default: break;
        }
        return null;
    }

}
