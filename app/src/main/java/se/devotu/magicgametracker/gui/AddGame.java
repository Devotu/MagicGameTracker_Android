package se.devotu.magicgametracker.gui;

import java.util.ArrayList;
import java.util.List;

import se.devotu.magicgametracker.R;
import se.devotu.magicgametracker.bl.GameManager;
import se.devotu.magicgametracker.bl.OpponentManager;
import se.devotu.magicgametracker.info.Colorset;
import se.devotu.magicgametracker.enums.ManaColor;
import se.devotu.magicgametracker.info.Opponent;
import se.devotu.magicgametracker.info.PerformanceRating;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Devotu on 2015-01-20.
 */
public class AddGame extends Activity {

    //private Button bAddWin, bAddLose;
    private Button bAddGame; //Version 2.1
    private RadioButton cbWin, cbLoss; //Version 2.1
    private int deckID;
    private CheckBox cbBlack, cbWhite, cbRed, cbBlue, cbGreen;
    private EditText etComment;
    private TextView tvTitle;
    private Spinner spOpponent;
    private ArrayList<Opponent> opponents; //Version 2.0
    //private SeekBar slPerformanceRating; //Version 2.1
    private RatingBar rbarPerformanceRating; //Version 3.1

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addgame);

        Intent passedIntent = getIntent();
        deckID = passedIntent.getIntExtra("Deck_ID", -1);

        //Version 2.0
        spOpponent = (Spinner)findViewById(R.id.ddOpponent);
        addItemsToOpponentSpinner(spOpponent);

        //Version 2.1
        //slPerformanceRating = (SeekBar)findViewById(R.id.slPerformanceRating);
        //slPerformanceRating.setProgress(50);

        //Version 3.1
        rbarPerformanceRating = (RatingBar)findViewById(R.id.rbarGamePerformanceRating);

        cbWin = (RadioButton)findViewById(R.id.rbWin);
        cbLoss = (RadioButton)findViewById(R.id.rbLoss);

        /*bAddGame = (Button)findViewById(R.id.bAddGame);
        bAddGame.setOnClickListener(new OnClickListener() {
            public void onClick(View v)
            {
                if (cbWin.isChecked()) {
                    addGame(true);
                    String msg = getString(R.string.win) + " " + getString(R.string.registerd).toLowerCase();
                    Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
                    navigateBack();
                } else if (cbLoss.isChecked()) {
                    addGame(false);
                    String msg = getString(R.string.loss) + " " + getString(R.string.registerd).toLowerCase();
                    Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
                    navigateBack();
                } else {
                    String msg = getString(R.string.parametersMissing) + " " + getString(R.string.no) + " " + getString(R.string.game) + " " + getString(R.string.registerd);
                    Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
                }
            }
        });*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.save_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_save:
                if (cbWin.isChecked()) {
                    addGame(true);
                    String msg = getString(R.string.win) + " " + getString(R.string.registerd).toLowerCase();
                    Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
                    navigateBack();
                } else if (cbLoss.isChecked()) {
                    addGame(false);
                    String msg = getString(R.string.loss) + " " + getString(R.string.registerd).toLowerCase();
                    Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
                    navigateBack();
                } else {
                    String msg = getString(R.string.parametersMissing) + " " + getString(R.string.no) + " " + getString(R.string.game) + " " + getString(R.string.registerd);
                    Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
                }
                return true;
            case R.id.action_cancel:
                navigateBack();
                return true;
            case R.id.action_help:
                Intent intent = new Intent(AddGame.this, AboutSwipePage.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("targetHelp", 7);
                startActivity(intent);
                AddGame.this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void addGame(Boolean win){

        cbBlack = (CheckBox)findViewById(R.id.cbBlack);
        cbWhite = (CheckBox)findViewById(R.id.cbWhite);
        cbRed = (CheckBox)findViewById(R.id.cbRed);
        cbBlue = (CheckBox)findViewById(R.id.cbBlue);
        cbGreen = (CheckBox)findViewById(R.id.cbGreen);
        Colorset opposingColorset = new Colorset();
        if (cbBlack.isChecked()) { opposingColorset.addColor(ManaColor.BLACK);}
        if (cbWhite.isChecked()) { opposingColorset.addColor(ManaColor.WHITE);}
        if (cbRed.isChecked()) { opposingColorset.addColor(ManaColor.RED);}
        if (cbBlue.isChecked()) { opposingColorset.addColor(ManaColor.BLUE);}
        if (cbGreen.isChecked()) { opposingColorset.addColor(ManaColor.GREEN);}

        etComment = (EditText)findViewById(R.id.etComment);
        String comment = etComment.getText().toString();

        // Version 2.0
        //TODO Måste finnas ett bättre sätt!
        int opponentIdPos = spOpponent.getSelectedItemPosition();
        int opponentId = opponents.get(opponentIdPos).getOpponent_ID();

        // Version 2.1
        //int performanceRating = slPerformanceRating.getProgress();

        //Version 3.1
        PerformanceRating performanceRating = new PerformanceRating();
        performanceRating.setPerformanceRatingFromStars(rbarPerformanceRating.getRating());

        GameManager gameManager = new GameManager(AddGame.this);
        gameManager.addNewGame(deckID, win, opposingColorset, comment, opponentId, (int)performanceRating.getPerformanceRatingAsRaw());
    }

    private void addItemsToOpponentSpinner(Spinner spinner) {

        OpponentManager oppManager = new OpponentManager(AddGame.this);
        opponents= oppManager.getAllOpponents();

        //TODO remove and replace with custom adapter
        List<String> opponentNames = new ArrayList<String>();
        for (Opponent opponent : opponents) {
            opponentNames.add(opponent.getOpponentName());
        }

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(AddGame.this,
                android.R.layout.simple_spinner_item, opponentNames);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
    }

    private void navigateBack() {
        Intent intent = new Intent(AddGame.this, DeckSwipePage.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        AddGame.this.finish();
    }

}
