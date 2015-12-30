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
import se.devotu.magicgametracker.bl.OpponentManager;

/**
 * Created by Devotu on 2015-01-30.
 * Version 3.0
 */
public class AddOpponent extends Activity {

    private EditText etName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addopponent);

        etName = (EditText)findViewById(R.id.etOpponentName);
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
                addNewOpponent();
                return true;
            case R.id.action_cancel:
                Intent intent = new Intent(AddOpponent.this, OpponentList.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                AddOpponent.this.finish();
                return true;
            case R.id.action_help:
                intent = new Intent(AddOpponent.this, AboutSwipePage.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("targetHelp", 3);
                startActivity(intent);
                AddOpponent.this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void addNewOpponent(){

        OpponentManager om = new OpponentManager(AddOpponent.this);
        String name = etName.getText().toString();

        if (!name.isEmpty()) {
            om.AddOpponent(name);
            Intent intent = new Intent(AddOpponent.this, OpponentList.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            AddOpponent.this.finish();
        } else {
            etName.requestFocus();
            Toast noNameToast = Toast.makeText(AddOpponent.this, R.string.noName_title, Toast.LENGTH_SHORT);
            noNameToast.show();
        }
    }
}
