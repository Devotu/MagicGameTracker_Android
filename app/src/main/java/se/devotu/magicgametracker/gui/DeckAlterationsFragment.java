package se.devotu.magicgametracker.gui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;

import se.devotu.magicgametracker.R;
import se.devotu.magicgametracker.bl.AlterationManager;
import se.devotu.magicgametracker.info.Alteration;

/**
 * Created by Devotu on 2015-02-07.
 */
public class DeckAlterationsFragment extends ListFragment {

    int deckID;
    ArrayList<Alteration> alterations;
    ListAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_deckalterations, container, false);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent passedIntent;
        passedIntent = getActivity().getIntent();
        deckID = passedIntent.getIntExtra("Deck_ID", -1);

        updateList();
    }

    @Override
    public void onResume() {
        super.onResume();

        updateList();
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Intent intent = new Intent(getActivity(), AlterationPage.class);
        intent.putExtra("Alteration_ID", alterations.get(position).getAlteration_ID());
        startActivity(intent);
    }

    private void updateList(){

        AlterationManager am = new AlterationManager(getActivity());
        alterations = am.getAllAlterationsForDeck(deckID);
        Collections.reverse(alterations);

        adapter = new AlterationListAdapter(getActivity(),R.layout.listitem_comment, alterations);
        setListAdapter(adapter);
    }
}
