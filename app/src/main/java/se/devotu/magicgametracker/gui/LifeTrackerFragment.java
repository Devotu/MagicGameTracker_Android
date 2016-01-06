package se.devotu.magicgametracker.gui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
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
public class LifeTrackerFragment extends Fragment {

    private Button bAdd1, bAdd5, bReduce1, bReduce5;
    private ArrayList<Integer> lifelog;
    private ListView lvLifelog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_lifetracker, container, false);

        return v;
    }
}
