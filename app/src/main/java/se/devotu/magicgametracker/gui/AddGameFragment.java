package se.devotu.magicgametracker.gui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import se.devotu.magicgametracker.R;
import se.devotu.magicgametracker.bl.GameManager;
import se.devotu.magicgametracker.bl.OpponentManager;
import se.devotu.magicgametracker.enums.ManaColor;
import se.devotu.magicgametracker.info.Colorset;
import se.devotu.magicgametracker.info.Opponent;
import se.devotu.magicgametracker.info.PerformanceRating;

/**
 * Created by Devotu on 2016-01-04.
 */
public class AddGameFragment extends Fragment {

    //private Button bAddWin, bAddLose;
    private Button bAddGame; //Version 2.1
    private RadioButton cbWin, cbLoss; //Version 2.1
    private int deckID;
    private CheckBox cbBlack, cbWhite, cbRed, cbBlue, cbGreen;
    private EditText etComment;
    private TextView tvTitle;
    private Spinner spOpponent;
    private ArrayList<Opponent> opponents; //Version 2.0
    private RatingBar rbarPerformanceRating; //Version 3.1

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_addgame, container, false);

        Intent passedIntent = getActivity().getIntent();
        deckID = passedIntent.getIntExtra("Deck_ID", -1);

        //Version 2.0
        spOpponent = (Spinner)v.findViewById(R.id.ddOpponent);
        addItemsToOpponentSpinner(spOpponent);

        //Version 3.1
        rbarPerformanceRating = (RatingBar)v.findViewById(R.id.rbarGamePerformanceRating);

        cbWin = (RadioButton)v.findViewById(R.id.rbWin);
        cbLoss = (RadioButton)v.findViewById(R.id.rbLoss);

        return v;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    private void addItemsToOpponentSpinner(Spinner spinner) {

        OpponentManager oppManager = new OpponentManager(this.getActivity());
        opponents= oppManager.getAllOpponents();

        //TODO remove and replace with custom adapter
        List<String> opponentNames = new ArrayList<String>();
        for (Opponent opponent : opponents) {
            opponentNames.add(opponent.getOpponentName());
        }

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this.getActivity(),
                android.R.layout.simple_spinner_item, opponentNames);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
    }
}
