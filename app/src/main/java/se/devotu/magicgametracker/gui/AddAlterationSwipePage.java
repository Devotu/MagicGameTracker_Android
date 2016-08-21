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
import se.devotu.magicgametracker.bl.AlterationManager;

/**
 * Created by Devotu on 2016-01-04.
 */
public class AddAlterationSwipePage extends FragmentActivity{

    ViewPager addAlterationPager;
    FragmentPagerAdapter addAlterationPageAdapter;
    AlterationManager am;
    int deckID;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent passedIntent = getIntent();
        deckID = passedIntent.getIntExtra("Deck_ID", -1);

        setContentView(R.layout.activity_addgameswipepage);
        addAlterationPager = (ViewPager) findViewById(R.id.vpAddGamePager);
        addAlterationPageAdapter = new AlterationSwipeAdapter(getSupportFragmentManager(), am, deckID);
        addAlterationPager.setAdapter(addAlterationPageAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.save_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //TODO Dela upp per fragment
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_cancel:
                navigateBack();
                return true;
            case R.id.action_help:
                Intent intent = new Intent(AddAlterationSwipePage.this, AboutSwipePage.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("targetHelp", 9);
                startActivity(intent);
                AddAlterationSwipePage.this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void navigateBack() {
        Intent intent = new Intent(AddAlterationSwipePage.this, DeckSwipePage.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        AddAlterationSwipePage.this.finish();
    }

}
