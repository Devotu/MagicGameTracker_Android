package se.devotu.magicgametracker.gui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import se.devotu.magicgametracker.R;
import se.devotu.magicgametracker.bl.DeckManager;

/**
 * Created by Devotu on 2015-02-07.
 */
public class DecklistSwipePage extends FragmentActivity{

    ViewPager decklistPager;
    FragmentPagerAdapter decklistPageAdapter;
    DeckManager dm;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decklistswipepage);
        decklistPager = (ViewPager) findViewById(R.id.vpDecklistPager);
        decklistPageAdapter = new DecklistSwipeAdapter(getSupportFragmentManager(), dm);
        decklistPager.setAdapter(decklistPageAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.add_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.action_add:
                intent = new Intent(DecklistSwipePage.this, NewDeck.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                DecklistSwipePage.this.finish();
                return true;
            case R.id.action_help:
                intent = new Intent(DecklistSwipePage.this, AboutSwipePage.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("targetHelp", 4);
                startActivity(intent);
                DecklistSwipePage.this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
