package se.devotu.magicgametracker.gui;

import android.support.v4.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import se.devotu.magicgametracker.R;
import se.devotu.magicgametracker.bl.OpponentManager;
import se.devotu.magicgametracker.enums.StatisticsType;
import se.devotu.magicgametracker.info.Opponent;

/**
 * Created by Devotu on 2015-01-20.
 */
public class OpponentPage extends FragmentActivity {

    private int opponentID;
    private Opponent opponent;
    private TextView tvOpponentName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opponentpage);
        Intent passedIntent = getIntent();
        opponentID = passedIntent.getIntExtra("Opponent_ID", -1);

        OpponentManager om = new OpponentManager(OpponentPage.this);
        opponent = om.getOpponentById(opponentID);

        tvOpponentName = (TextView)findViewById(R.id.tvOpponentName);
        tvOpponentName.setText(opponent.getOpponentName());

        FragmentManager fm = getSupportFragmentManager();
        PlayerStatisticsFragment playerStatisticsFragment = (PlayerStatisticsFragment) fm.findFragmentById(R.id.frStatisticsFragment);
        playerStatisticsFragment.setStatisticsType(StatisticsType.OPPONENT);
        playerStatisticsFragment.setOpponentId(opponent.getOpponent_ID());

        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.delete_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_delete:
                OpponentManager om = new OpponentManager(OpponentPage.this);
                Boolean deleted = om.deleteOpponent(opponent.getOpponent_ID());
                if (deleted){
                    String msg = getString(R.string.opponent) + " " + getString(R.string.deleted).toLowerCase();
                    Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(OpponentPage.this, OpponentList.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    OpponentPage.this.finish();
                } else {
                    String msg = getString(R.string.thisword)
                            + " " + getString(R.string.opponent).toLowerCase()
                            + " " + getString(R.string.cannotbe).toLowerCase()
                            + " " + getString(R.string.deleted).toLowerCase();
                    Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
                }
                return true;
            case R.id.action_help:
                Intent intent = new Intent(OpponentPage.this, AboutSwipePage.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("targetHelp", 3);
                startActivity(intent);
                OpponentPage.this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}


