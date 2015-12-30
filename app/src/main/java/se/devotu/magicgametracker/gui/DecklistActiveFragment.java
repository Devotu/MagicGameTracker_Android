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
import java.util.Comparator;

import se.devotu.magicgametracker.R;
import se.devotu.magicgametracker.bl.DeckManager;
import se.devotu.magicgametracker.bl.StatisticsCalculator;
import se.devotu.magicgametracker.info.Deck;
import se.devotu.magicgametracker.info.DeckListItem;

/**
 * Created by Devotu on 2015-02-07.
 */
public class DecklistActiveFragment extends ListFragment {

    ArrayList<DeckListItem> items;
    ListAdapter adapter;
    DeckManager deckManager;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_decklist, container, false);
        updateList();
        return v;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onResume() {
        super.onResume();
        updateList();
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        //Intent intent = new Intent(DeckList.this, DeckPage.class); Test ViewPager
        Intent intent = new Intent(getActivity(), DeckSwipePage.class);
        intent.putExtra("Deck_ID", items.get(position).getDeck_ID());
        startActivity(intent);
    }

    private void updateList(){

        deckManager = new DeckManager(getActivity());
        ArrayList<Deck> decks = deckManager.getAllActiveDecks();

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
}
