package se.devotu.magicgametracker.gui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import se.devotu.magicgametracker.R;
import se.devotu.magicgametracker.bl.AlterationManager;
import se.devotu.magicgametracker.bl.DisplayOrganizer;
import se.devotu.magicgametracker.info.Alteration;

/**
 * Created by Devotu on 2015-01-20.
 */
public class AlterationPage extends Activity {

    private int alterationID;
    private Alteration alteration;
    private TextView tvDate, tvComment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alterationpage);
        Intent passedIntent = getIntent();
        alterationID = passedIntent.getIntExtra("Alteration_ID", -1);

        AlterationManager am = new AlterationManager(AlterationPage.this);
        alteration = am.getAlterationById(alterationID);

        tvDate = (TextView)findViewById(R.id.tvAlterationDate);
        DisplayOrganizer displayOrganizer = new DisplayOrganizer();
        tvDate.setText(displayOrganizer.getDisplayDateAndTime(alteration.getDate()));

        tvComment = (TextView)findViewById(R.id.tvComment);
        tvComment.setText(alteration.getComment());

        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.delete_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_delete:
                AlterationManager am = new AlterationManager(AlterationPage.this);
                Boolean deleted = am.deleteAlterationById(alteration.getAlteration_ID());
                if (deleted){
                    String msg = getString(R.string.alteration) + " " + getString(R.string.deleted).toLowerCase();
                    Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AlterationPage.this, DeckSwipePage.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    AlterationPage.this.finish();
                } else {
                    String msg = getString(R.string.thisword)
                            + " " + getString(R.string.alteration).toLowerCase()
                            + " " + getString(R.string.cannotbe).toLowerCase()
                            + " " + getString(R.string.deleted).toLowerCase();
                    Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
                }
                return true;
            case R.id.action_help:
                Intent intent = new Intent(AlterationPage.this, AboutSwipePage.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("targetHelp", 9);
                startActivity(intent);
                AlterationPage.this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}


