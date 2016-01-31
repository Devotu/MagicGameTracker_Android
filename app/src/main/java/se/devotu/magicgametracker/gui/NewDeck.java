package se.devotu.magicgametracker.gui;

import se.devotu.magicgametracker.R;
import se.devotu.magicgametracker.bl.DeckManager;
import se.devotu.magicgametracker.info.Colorset;
import se.devotu.magicgametracker.enums.Formats;
import se.devotu.magicgametracker.enums.ManaColor;
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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Devotu on 2015-01-20.
 */
public class NewDeck extends Activity {

    private TextView tvTitle;
    private Button bNewDeck;
    private EditText etName, etTheme;
    private Spinner ddFormat;
    private CheckBox cbBlack, cbWhite, cbRed, cbBlue, cbGreen, cbDevoid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newdeck);

        ddFormat = (Spinner)findViewById(R.id.ddFormat);
        this.addItemsToFormatSpinner(ddFormat);
    }

    private void addItemsToFormatSpinner(Spinner spinner) {

        //List<String> formats = Arrays.asList(Formats.values().toString());

        Formats[] formats;
        formats = Formats.values();
        String[] formatNames = new String[formats.length];

        for (int i = 0; i < formats.length; i++) {
            formatNames[i] = formats[i].name();
        }

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(NewDeck.this,
                android.R.layout.simple_spinner_item, formatNames);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
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
                Boolean created = createNewDeck();
                if (created){
                    Intent intent = new Intent(NewDeck.this, MainMenu.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    NewDeck.this.finish();
                }
                return true;
            case R.id.action_cancel:
                Intent intent = new Intent(NewDeck.this, MainMenu.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                NewDeck.this.finish();
                return true;
            case R.id.action_help:
                intent = new Intent(NewDeck.this, AboutSwipePage.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("targetHelp", 1);
                startActivity(intent);
                NewDeck.this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private Boolean createNewDeck() {

        DeckManager deckManager = new DeckManager(NewDeck.this);

        etName = (EditText)findViewById(R.id.etName);
        String name = etName.getText().toString();
        ddFormat = (Spinner)findViewById(R.id.ddFormat);
        String format = ddFormat.getSelectedItem().toString();
        etTheme = (EditText)findViewById(R.id.etTheme);
        String theme = etTheme.getText().toString();

        cbBlack = (CheckBox)findViewById(R.id.cbBlack);
        cbWhite = (CheckBox)findViewById(R.id.cbWhite);
        cbRed = (CheckBox)findViewById(R.id.cbRed);
        cbBlue = (CheckBox)findViewById(R.id.cbBlue);
        cbGreen = (CheckBox)findViewById(R.id.cbGreen);
        cbDevoid = (CheckBox)findViewById(R.id.cbDevoid);
        Colorset colorset = new Colorset();
        if (cbBlack.isChecked()) { colorset.addColor(ManaColor.BLACK);}
        if (cbWhite.isChecked()) { colorset.addColor(ManaColor.WHITE);}
        if (cbRed.isChecked()) { colorset.addColor(ManaColor.RED);}
        if (cbBlue.isChecked()) { colorset.addColor(ManaColor.BLUE);}
        if (cbGreen.isChecked()) { colorset.addColor(ManaColor.GREEN);}
        if (cbDevoid.isChecked()) {colorset.addColor(ManaColor.DEVOID);}

        if (colorset.getNumberOfColors() == 0) {
            colorset.addColor(ManaColor.NONE);
        }

        if (!name.isEmpty()) {
            deckManager.CreateNewDeck(name, format, colorset, theme);
            return true;
        } else {
            etName.requestFocus();
            Toast noNameToast = Toast.makeText(NewDeck.this, R.string.noName_title, Toast.LENGTH_SHORT);
            noNameToast.show();
            return false;
        }
    }
}
