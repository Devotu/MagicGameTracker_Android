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
import se.devotu.magicgametracker.bl.GameManager;
import se.devotu.magicgametracker.info.Game;

/**
 * Created by Devotu on 2015-02-07.
 */
public class DeckGamesFragment extends ListFragment {

    int deckID;
    ArrayList<Game> games;
    ListAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_deckgames, container, false);
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
        Intent intent = new Intent(getActivity(), GamePage.class);
        intent.putExtra("Game_ID", games.get(position).getGame_ID());
        startActivity(intent);
    }

    private void updateList(){

        GameManager gm = new GameManager(getActivity());
        games = gm.getAllGamesForDeck(deckID);
        Collections.reverse(games);

        adapter = new GameListAdapter(getActivity(), R.layout.listitem_comment, games);
        setListAdapter(adapter);
    }
}
