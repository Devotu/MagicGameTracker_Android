package se.devotu.magicgametracker.gui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.internal.de;

import se.devotu.magicgametracker.R;
import se.devotu.magicgametracker.bl.AlterationManager;
import se.devotu.magicgametracker.bl.DeckManager;
import se.devotu.magicgametracker.enums.Formats;
import se.devotu.magicgametracker.enums.ManaColor;
import se.devotu.magicgametracker.info.Colorset;
import se.devotu.magicgametracker.info.Deck;
import se.devotu.magicgametracker.info.DeckInfo;

/**
 * Created by Devotu on 2016-01-04.
 */
public class AddFullAlterationFragment extends Fragment {


    private int deckID;

    private Deck originalDeck;
    private EditText etName, etTheme;
    private Spinner ddFormat;
    private CheckBox cbBlack, cbWhite, cbRed, cbBlue, cbGreen, cbDevoid;
    private EditText etComment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_addfullalteration, container, false);

        Intent passedIntent = getActivity().getIntent();
        deckID = passedIntent.getIntExtra("Deck_ID", -1);
        DeckManager dm = new DeckManager(this.getActivity());
        this.originalDeck = dm.getDeckInfo(deckID);

        etName = (EditText)v.findViewById(R.id.etName);
        etName.setText(originalDeck.getName());

        ddFormat = (Spinner)v.findViewById(R.id.ddFormat);
        this.addItemsToFormatSpinner(ddFormat);
        this.setChosenFormat(ddFormat, originalDeck.getFormat());

        cbBlack = (CheckBox)v.findViewById(R.id.cbBlack);
        cbWhite = (CheckBox)v.findViewById(R.id.cbWhite);
        cbRed = (CheckBox)v.findViewById(R.id.cbRed);
        cbBlue = (CheckBox)v.findViewById(R.id.cbBlue);
        cbGreen = (CheckBox)v.findViewById(R.id.cbGreen);
        cbDevoid = (CheckBox)v.findViewById(R.id.cbDevoid);//Version 4.2
        activateColorCheckboxes(originalDeck.getColorset(), cbBlack, cbWhite, cbRed, cbBlue, cbGreen, cbDevoid);

        etTheme = (EditText)v.findViewById(R.id.etTheme);
        etTheme.setText(originalDeck.getTheme());

        etComment = (EditText)v.findViewById(R.id.etComment);

        return v;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_save:
                addFullAlteration();
                return true;
            case R.id.action_cancel:
                navigateBack();
                return true;
            case R.id.action_help:
                Intent intent = new Intent(this.getActivity(), AboutSwipePage.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("targetHelp", 9);
                startActivity(intent);
                this.getActivity().finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void navigateBack() {
        Intent intent = new Intent(this.getActivity(), DeckSwipePage.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        this.getActivity().finish();
    }

    private void addItemsToFormatSpinner(Spinner spinner) {

        Formats[] formats;
        formats = Formats.values();
        String[] formatNames = new String[formats.length];

        for (int i = 0; i < formats.length; i++) {
            formatNames[i] = formats[i].name();
        }

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this.getActivity(),
                android.R.layout.simple_spinner_item, formatNames);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
    }

    private boolean addFullAlteration(){
        AlterationManager am = new AlterationManager(this.getActivity());

        Deck deck = new Deck();
        deck.setDeck_ID(this.deckID);

        deck.setName(etName.getText().toString());
        deck.setFormat(ddFormat.getSelectedItem().toString());
        deck.setTheme(etTheme.getText().toString());

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

        deck.setColorset(colorset);

        if (!deck.getName().isEmpty()) {
            am.addNewFullAlteration(originalDeck, deck, etComment.getText().toString());
            String msg = getString(R.string.alteration) + " " + getString(R.string.registerd).toLowerCase();
            Toast.makeText(this.getActivity(), msg, Toast.LENGTH_SHORT).show();
            navigateBack();
            return true;
        } else {
            etName.requestFocus();
            Toast noNameToast = Toast.makeText(this.getActivity(), R.string.noName_title, Toast.LENGTH_SHORT);
            noNameToast.show();
            return false;
        }
    }

    private void activateColorCheckboxes(Colorset colorset, CheckBox ivBlack, CheckBox ivWhite, CheckBox ivRed, CheckBox ivBlue, CheckBox ivGreen, CheckBox ivDevoid) {

        for (ManaColor color : colorset.getColors()) {

            switch (color) {
                case BLACK:
                    cbBlack.setChecked(true);
                    break;
                case WHITE:
                    cbWhite.setChecked(true);
                    break;
                case RED:
                    cbRed.setChecked(true);
                    break;
                case BLUE:
                    cbBlue.setChecked(true);
                    break;
                case GREEN:
                    cbGreen.setChecked(true);
                    break;
                case DEVOID:
                    cbDevoid.setChecked(true);
                    break;
                case NONE:
                    break;
                default:
                    break;
            }

        }
    }

    private void setChosenFormat(Spinner formatSpinner, String format) {
        int index = Formats.valueOf(format).ordinal();
        formatSpinner.setSelection(index);
    }

    public void setDeckID(int deckID){
        this.deckID = deckID;
    }
}
