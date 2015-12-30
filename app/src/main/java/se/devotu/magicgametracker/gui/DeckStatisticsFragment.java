package se.devotu.magicgametracker.gui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.text.DecimalFormat;

import se.devotu.magicgametracker.R;
import se.devotu.magicgametracker.bl.StatisticsCalculator;
import se.devotu.magicgametracker.info.DeckStatistics;
import se.devotu.magicgametracker.info.PerformanceRating;

/**
 * Created by Devotu on 2015-02-07.
 */
public class DeckStatisticsFragment extends Fragment {

    private TextView tvTotalWins, tvTotalLosses, tvTotalWinPc;
    private TextView tvCurrentWins, tvCurrentLosses, tvCurrentWinPc;
    private int deckID;
    //private SeekBar slPerformanceRating; //Version 2.1
    ImageView imgCommonWin, imgCommonLoss; //Version 3.0
    RatingBar rBarDeckPerformanceRating; //Version 3.0
    WinrateHistoryFragment grWinrateHistory;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_deckstatistics, container, false);

        Intent passedIntent = getActivity().getIntent();
        deckID = passedIntent.getIntExtra("Deck_ID", -1);

        tvCurrentWins = (TextView)v.findViewById(R.id.tvCurrentWin);
        tvCurrentLosses = (TextView)v.findViewById(R.id.tvCurrentLose);
        tvCurrentWinPc = (TextView)v.findViewById(R.id.tvCurrentStatWin);
        tvTotalWins = (TextView)v.findViewById(R.id.tvTotalWin);
        tvTotalLosses = (TextView)v.findViewById(R.id.tvTotalLose);
        tvTotalWinPc = (TextView)v.findViewById(R.id.tvTotalStatWin);
        //Version 2.0
        //lPerformanceRating = (SeekBar)v.findViewById(R.id.slPerformanceRating);
        //Version 3.0
        imgCommonWin = (ImageView)v.findViewById(R.id.imgCommonWin);
        imgCommonLoss = (ImageView)v.findViewById(R.id.imgCommonLoss);
        rBarDeckPerformanceRating = (RatingBar)v.findViewById(R.id.rbarDeckPerformanceRating);

        //Version 3.6
        grWinrateHistory = new WinrateHistoryFragment();
        getFragmentManager().beginTransaction().add(R.id.grTotalColorsContainer, grWinrateHistory).commit();

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateStats();
    }

    private void updateStats(){
        StatisticsCalculator statCalculator = new StatisticsCalculator(getActivity());
        DeckStatistics stats = statCalculator.getDeckStatistics(deckID);
        DecimalFormat df = new DecimalFormat("###.#");

        tvCurrentWins.setText(String.valueOf(stats.getCurrentWins()));
        tvCurrentLosses.setText(String.valueOf(stats.getCurrentLosses()));
        tvCurrentWinPc.setText(String.valueOf(df.format(stats.getCurrentWinpc())));
        tvTotalWins.setText(String.valueOf(stats.getTotalWins()));
        tvTotalLosses.setText(String.valueOf(stats.getTotalLosses()));
        tvTotalWinPc.setText(String.valueOf(df.format(stats.getTotalWinpc())));

        //Version 3.6
        //setMostCommonManaSymbol(imgCommonWin, stats.getCommonwin());
        //setMostCommonManaSymbol(imgCommonLoss, stats.getCommonloss());

        //Version 2.1
        //slPerformanceRating.setProgress(stats.getPerformanceRating());
        //slPerformanceRating.setEnabled(false);

        //Version 3.0
        PerformanceRating performanceRating = new PerformanceRating();
        performanceRating.setPerformanceRatingFromRaw(stats.getPerformanceRating());
        rBarDeckPerformanceRating.setRating(performanceRating.getPerformanceRatingAsStars());

        //Version 3.6
        grWinrateHistory.drawGraph(stats.getWinrateHistory());

    }

}
