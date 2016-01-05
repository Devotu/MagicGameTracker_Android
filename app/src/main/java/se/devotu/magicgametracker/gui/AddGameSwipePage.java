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
 * Created by Devotu on 2016-01-04.
 */
public class AddGameSwipePage extends FragmentActivity{

    ViewPager addGamePager;
    FragmentPagerAdapter addGamePageAdapter;
    DeckManager dm;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addgameswipepage);
        addGamePager = (ViewPager) findViewById(R.id.vpAddGamePager);
        addGamePageAdapter = new AddGameSwipeAdapter(getSupportFragmentManager(), dm);
        addGamePager.setAdapter(addGamePageAdapter);
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
                intent = new Intent(AddGameSwipePage.this, NewDeck.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                AddGameSwipePage.this.finish();
                return true;
            case R.id.action_help:
                intent = new Intent(AddGameSwipePage.this, AboutSwipePage.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("targetHelp", 7);
                startActivity(intent);
                AddGameSwipePage.this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
