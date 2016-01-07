package se.devotu.magicgametracker.gui;

import se.devotu.magicgametracker.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainMenu extends Activity {

    private Button bToNewDeck, bToDeckList, bToPlayerView, bToOppenentView, bToAboutView;
    private TextView tvHeader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        bToDeckList = (Button)findViewById(R.id.bToDecks);
        bToDeckList.setOnClickListener(new OnClickListener() {
            public void onClick(View v)
            {
                Intent intent = new Intent(MainMenu.this, DecklistSwipePage.class);
                startActivity(intent);
            }
        });

        /* Version 4.0
        bToNewDeck = (Button)findViewById(R.id.bToNewDeck);
        bToNewDeck.setOnClickListener(new OnClickListener() {
            public void onClick(View v)
            {
                Intent intent = new Intent(MainMenu.this, NewDeck.class);
                startActivity(intent);
            }
        });
        */

        bToOppenentView = (Button)findViewById(R.id.bToOpponents);
        bToOppenentView.setOnClickListener(new OnClickListener() {
            public void onClick(View v)
            {
                Intent intent = new Intent(MainMenu.this, OpponentList.class);
                startActivity(intent);
            }
        });

        bToPlayerView = (Button)findViewById(R.id.bToPlayerPage);
        bToPlayerView.setOnClickListener(new OnClickListener() {
            public void onClick(View v)
            {
                Intent intent = new Intent(MainMenu.this, PlayerSwipePage.class);
                startActivity(intent);
            }
        });

        /* Version 3.0
        bToAboutView = (Button)findViewById(R.id.bToAbout);
        bToAboutView.setOnClickListener(new OnClickListener() {
            public void onClick(View v)
            {
                Intent intent = new Intent(MainMenu.this, AboutSwipePage.class);
                startActivity(intent);
            }
        });*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.action_exportimport:
                intent = new Intent(MainMenu.this, ExportImport.class);
                startActivity(intent);
                return true;
            case R.id.action_help:
                intent = new Intent(MainMenu.this, AboutSwipePage.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}

