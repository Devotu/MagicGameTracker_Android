package se.devotu.magicgametracker.gui;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;

import se.devotu.magicgametracker.R;
import se.devotu.magicgametracker.bl.StatisticsCalculator;
import se.devotu.magicgametracker.enums.ManaColor;
import se.devotu.magicgametracker.enums.StatisticsType;
import se.devotu.magicgametracker.enums.Status;
import se.devotu.magicgametracker.info.PlayerStatistics;

/**
 * Created by Devotu on 2015-02-07.
 */
public class PlayerStatisticsFragment extends Fragment {

    private TextView tvTotal, tvWins, tvLosses, tvWinPc;
    StatisticsType statisticsType;
    int statObjectId;
    Status statisticsTypeSet, statisticsObjectSet;
    ColorBarsFragment grColorBars;
    WinrateHistoryFragment grWinrateHistory;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_playerstatistics, container, false);

        tvTotal = (TextView)v.findViewById(R.id.tvTotal);
        tvWins = (TextView)v.findViewById(R.id.tvWin);
        tvLosses = (TextView)v.findViewById(R.id.tvLose);
        tvWinPc = (TextView)v.findViewById(R.id.tvStatWin);

        //Version 3.6
        grWinrateHistory = new WinrateHistoryFragment();
        getChildFragmentManager().beginTransaction().add(R.id.grTotalColorsContainer, grWinrateHistory).commit();
        grColorBars = new ColorBarsFragment();
        getChildFragmentManager().beginTransaction().add(R.id.grActiveColorsContainer, grColorBars).commit();

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
        if (this.statisticsType != null){
            updateStats();
        }
    }

    public Status setStatisticsType(StatisticsType statisticsType){
        this.statisticsType = statisticsType;
        statisticsTypeSet = Status.Done;
        return statisticsTypeSet;
    }

    public Status setOpponentId(int opponentId){
        this.statObjectId = opponentId;
        statisticsObjectSet = Status.Done;
        return statisticsObjectSet;
    }

    private void updateStats(){
        StatisticsCalculator statCalculator = new StatisticsCalculator(getActivity());
        PlayerStatistics stats = null;

        switch (this.statisticsType) {
            case TOTAL:
                stats = statCalculator.getPlayerStatistics(StatisticsType.TOTAL);;
                break;
            case TODAY:
                stats = statCalculator.getPlayerStatistics(StatisticsType.TODAY);
                break;
            case ACTIVE:
                stats = statCalculator.getPlayerStatistics(StatisticsType.ACTIVE);;
                break;
            case OPPONENT:
                if (statisticsObjectSet == Status.Done){
                    stats = statCalculator.getPlayerOpponentStatistics(StatisticsType.OPPONENT, statObjectId);
                }
                break;
            default:
                break;
        }

        if (stats != null) {
            DecimalFormat df = new DecimalFormat("###.#");

            tvTotal.setText(String.valueOf(stats.getWins()+stats.getLosses()));
            tvWins.setText(String.valueOf(stats.getWins()));
            tvLosses.setText(String.valueOf(stats.getLosses()));
            tvWinPc.setText(String.valueOf(df.format(stats.getWinpc())));

            //Version 3.6
            grWinrateHistory.drawGraph(stats.getWinrateHistory());
            grColorBars.drawGraph(stats.getColorValueCollection());
        }
    }

    private void setMostCommonManaSymbol(ManaColor mostCommonColor, ImageView img){

        switch (mostCommonColor) {
            case BLACK:
                img.setImageResource(R.drawable.black_mana_big);
                break;
            case WHITE:
                img.setImageResource(R.drawable.white_mana_big);
                break;
            case RED:
                img.setImageResource(R.drawable.red_mana_big);
                break;
            case BLUE:
                img.setImageResource(R.drawable.blue_mana_big);
                break;
            case GREEN:
                img.setImageResource(R.drawable.green_mana_big);
                break;
            case NONE:
                img.setImageResource(android.R.color.transparent);
                break;
        }
    }
}
