package se.devotu.magicgametracker.gui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import se.devotu.magicgametracker.R;
import se.devotu.magicgametracker.bl.AlterationManager;

/**
 * Created by Devotu on 2015-01-30.
 * Version 3.0
 */
public class AddAlteration extends Activity {

    private EditText etComment;
    private int deckID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addalteration);

        Intent passedIntent = getIntent();
        deckID = passedIntent.getIntExtra("Deck_ID", -1);

        etComment = (EditText)findViewById(R.id.etComment);
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
                addAlteration();
                return true;
            case R.id.action_cancel:
                navigateBack();
                return true;
            case R.id.action_help:
                Intent intent = new Intent(AddAlteration.this, AboutSwipePage.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("targetHelp", 9);
                startActivity(intent);
                AddAlteration.this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void navigateBack() {
        Intent intent = new Intent(AddAlteration.this, DeckSwipePage.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        AddAlteration.this.finish();
    }

    private void addAlteration(){
        AlterationManager am = new AlterationManager(AddAlteration.this);
        am.addNewAlteration(deckID, etComment.getText().toString());
        String msg = getString(R.string.alteration) + " " + getString(R.string.registerd).toLowerCase();
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
        navigateBack();
    }
}
