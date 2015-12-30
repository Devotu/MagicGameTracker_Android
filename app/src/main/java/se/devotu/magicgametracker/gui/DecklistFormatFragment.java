package se.devotu.magicgametracker.gui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import se.devotu.magicgametracker.R;
import se.devotu.magicgametracker.bl.DeckManager;
import se.devotu.magicgametracker.bl.StatisticsCalculator;
import se.devotu.magicgametracker.enums.Formats;
import se.devotu.magicgametracker.info.Deck;
import se.devotu.magicgametracker.info.DeckListItem;

/**
 * Created by Devotu on 2015-02-07.
 */
public class DecklistFormatFragment extends ListFragment {

    ArrayList<DeckListItem> items;
    ListAdapter adapter;
    DeckManager deckManager;
    View header;
    Formats format;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        header = inflater.inflate(R.layout.listitem_choise_format, null);
        final Spinner formatSpinner = (Spinner) (View) header.findViewById(R.id.ddFormat);
        addItemsToFormatSpinner(formatSpinner);

        formatSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                format = Formats.valueOf(formatSpinner.getSelectedItem().toString());
                updateList();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // do nothing
            }

        });

        View v = inflater.inflate(R.layout.fragment_decklist, container, false);
        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (header != null)  this.getListView().addHeaderView(header);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        format = Formats.Standard;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateList();
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        if (position > 0){
            Intent intent = new Intent(getActivity(), DeckSwipePage.class);
            intent.putExtra("Deck_ID", items.get(position -1).getDeck_ID());
            startActivity(intent);
        }
    }

    private void updateList(){

        deckManager = new DeckManager(getActivity());
        ArrayList<Deck> decks = deckManager.getAllDecksOfKind(format);

        items = new ArrayList<DeckListItem>();
        StatisticsCalculator statCalc = new StatisticsCalculator(getActivity());

        for (Deck deck : decks) {
            DeckListItem item = new DeckListItem();
            item.setDeck_ID(deck.getDeck_ID());
            item.setName(deck.getName());
            item.setWinpc(statCalc.getWinPercent(deck.getDeck_ID()));
            items.add(item);
        }

        Collections.sort(items, new PositiveComparer());

        adapter = new DeckListAdapter(getActivity(), R.layout.listitem_name, items);
        setListAdapter(adapter);
    }

    /**
     * @author Devotu
     * En comparator där mest kommer överst
     */
    private class PositiveComparer implements Comparator<DeckListItem>
    {
        public int compare(DeckListItem left, DeckListItem right) {
            return (int) (right.getWinpc() - left.getWinpc());
        }
    }

    private void addItemsToFormatSpinner(Spinner spinner) {

        Formats[] formats;
        formats = Formats.values();
        String[] formatNames = new String[formats.length];

        for (int i = 0; i < formats.length; i++) {
            formatNames[i] = formats[i].name();
        }

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, formatNames);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
    }
}
