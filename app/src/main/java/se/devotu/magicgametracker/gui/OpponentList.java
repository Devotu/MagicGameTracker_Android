package se.devotu.magicgametracker.gui;

import java.util.ArrayList;
import se.devotu.magicgametracker.R;
import se.devotu.magicgametracker.bl.OpponentManager;
import se.devotu.magicgametracker.info.Opponent;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by Devotu on 2015-01-20.
 */
public class OpponentList extends ListActivity {

    ListView listView;
    ArrayList<Opponent> opponents;
    TextView tvHeader;
    ListAdapter adapter;
    OpponentManager om;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opponentlist);

        listView = OpponentList.this.getListView();

        updateList();
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateList();
    }

    private void updateList(){

        om = new OpponentManager(OpponentList.this);
        opponents = om.getAllOpponents();
        //opponents.remove(0);
        adapter = new OpponentListAdapter(OpponentList.this, R.layout.listitem_name, opponents);
        setListAdapter(adapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        //Intent intent = new Intent(DeckList.this, DeckPage.class); Test ViewPager
        Intent intent = new Intent(OpponentList.this, OpponentPage.class);
        intent.putExtra("Opponent_ID", opponents.get(position).getOpponent_ID());
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.add_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.action_add:
                intent = new Intent(OpponentList.this, AddOpponent.class);
                startActivity(intent);
                return true;
            case R.id.action_help:
                intent = new Intent(OpponentList.this, AboutSwipePage.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("targetHelp", 2);
                startActivity(intent);
                OpponentList.this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}

