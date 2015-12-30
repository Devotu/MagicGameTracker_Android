package se.devotu.magicgametracker.gui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import se.devotu.magicgametracker.R;
import se.devotu.magicgametracker.bl.StatisticsCalculator;
import se.devotu.magicgametracker.enums.StatisticsType;
import se.devotu.magicgametracker.info.ColorValueCollection;

/**
 * Created by Devotu on 2015-02-07.
 */
public class ColorsInUseFragment extends Fragment {

    ColorBarsFragment grTotalUsed, grActiveUsed;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_colorsinuse, container, false);

        //Version 3.6
        grTotalUsed = new ColorBarsFragment();
        getChildFragmentManager().beginTransaction().add(R.id.grTotalColorsContainer, grTotalUsed).commit();
        grActiveUsed = new ColorBarsFragment();
        getChildFragmentManager().beginTransaction().add(R.id.grActiveColorsContainer, grActiveUsed).commit();

        return v;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onResume() {
        super.onResume();
        updateStats();
    }

    private void updateStats(){
        StatisticsCalculator statCalculator = new StatisticsCalculator(getActivity());
        ColorValueCollection colorsInUseTotal, colorsInUseActive;

        colorsInUseTotal = statCalculator.getColorsInUse(StatisticsType.TOTAL);
        colorsInUseActive = statCalculator.getColorsInUse(StatisticsType.ACTIVE);

        if (colorsInUseTotal != null & colorsInUseActive != null) {
            //Version 3.6
            grTotalUsed.drawGraph(colorsInUseTotal);
            grActiveUsed.drawGraph(colorsInUseActive);
        }
    }
}
