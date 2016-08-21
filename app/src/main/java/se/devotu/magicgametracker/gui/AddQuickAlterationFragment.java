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

import se.devotu.magicgametracker.R;
import se.devotu.magicgametracker.bl.AlterationManager;
import se.devotu.magicgametracker.enums.Formats;

/**
 * Created by Devotu on 2016-01-04.
 */
public class AddQuickAlterationFragment extends Fragment {


    private int deckID;
    private EditText etComment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_addalteration, container, false);

        Intent passedIntent = getActivity().getIntent();
        deckID = passedIntent.getIntExtra("Deck_ID", -1);

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
                addQuickAlteration();
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

    private void addQuickAlteration(){
        AlterationManager am = new AlterationManager(this.getActivity());
        am.addNewAlteration(deckID, etComment.getText().toString());
        String msg = getString(R.string.alteration) + " " + getString(R.string.registerd).toLowerCase();
        Toast.makeText(this.getActivity(), msg, Toast.LENGTH_SHORT).show();
        navigateBack();
    }

    public void setDeckID(int deckID){
        this.deckID = deckID;
    }
}
