package se.devotu.magicgametracker.gui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import se.devotu.magicgametracker.R;
import se.devotu.magicgametracker.bl.DeckManager;

/**
 * Created by Devotu on 2015-02-07.
 */
public class DeckSwipePage extends FragmentActivity{

    ViewPager deckPager;
    FragmentPagerAdapter deckPageAdapter;
    int deckID;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent passedIntent = getIntent();
        deckID = passedIntent.getIntExtra("Deck_ID", -1);

        setContentView(R.layout.activity_deckswipepage);
        deckPager = (ViewPager) findViewById(R.id.vpDeckPager);
        deckPageAdapter = new DeckSwipeAdapter(getSupportFragmentManager());
        deckPager.setAdapter(deckPageAdapter);

        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.deckpage_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        final DeckManager dm = new DeckManager(DeckSwipePage.this);
        switch (item.getItemId()) {
            case R.id.action_addgame:
                intent = new Intent(DeckSwipePage.this, AddGameSwipePage.class);
                intent.putExtra("Deck_ID", deckID);
                startActivity(intent);
                return true;
            case R.id.action_addalteration:
                intent = new Intent(DeckSwipePage.this, AddAlteration.class);
                intent.putExtra("Deck_ID", deckID);
                startActivity(intent);
                return true;
            case R.id.action_toggleactive:
                dm.toggleActive(deckID);
                deckPageAdapter = new DeckSwipeAdapter(getSupportFragmentManager());
                deckPager.setAdapter(deckPageAdapter);
                return true;
            case R.id.action_help:
                intent = new Intent(DeckSwipePage.this, AboutSwipePage.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("targetHelp", 5);
                startActivity(intent);
                DeckSwipePage.this.finish();
                return true;
            case R.id.action_delete:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder
                        .setTitle(getString(R.string.delete))
                        .setMessage(getString(R.string.delete) + " " + getString(R.string.deck).toLowerCase() + " ?")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setNegativeButton(getString(R.string.no), null)
                        .setPositiveButton(getString(R.string.yes), new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Boolean deleted = dm.deleteDeck(deckID);
                                if (deleted){
                                    String msg = getString(R.string.deck) + " " + getString(R.string.deleted).toLowerCase();
                                    Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
                                    Intent deleteIntent = new Intent(DeckSwipePage.this, DecklistSwipePage.class);
                                    deleteIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(deleteIntent);
                                    DeckSwipePage.this.finish();
                                } else {
                                    String msg = getString(R.string.thisword)
                                            + " " + getString(R.string.deck).toLowerCase()
                                            + " " + getString(R.string.cannotbe).toLowerCase()
                                            + " " + getString(R.string.deleted).toLowerCase();
                                    Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
                                }
                            }
                        })
                        .show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
