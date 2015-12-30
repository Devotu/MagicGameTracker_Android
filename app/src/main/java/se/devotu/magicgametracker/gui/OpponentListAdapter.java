package se.devotu.magicgametracker.gui;

import java.util.ArrayList;

import se.devotu.magicgametracker.R;
import se.devotu.magicgametracker.info.Opponent;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Devotu on 2015-01-20.
 */
public class OpponentListAdapter extends ArrayAdapter<Opponent>{

    Context context;
    int layoutResource;
    ArrayList<Opponent> opponents;
    Opponent opponent;

    public OpponentListAdapter(Context context, int layoutResource, ArrayList<Opponent> opponents) {
        super(context, layoutResource, opponents);
        this.layoutResource = layoutResource;
        this.context = context;
        this.opponents = opponents;
    }

    @Override
    public View getView(int position, final View convertView, ViewGroup parent) {
        View row = convertView;
        OpponentHolder holder = null;

        if(row == null){
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResource, parent, false);

            holder = new OpponentHolder();
            holder.tvOpponentName = (TextView)row.findViewById(R.id.tvName);
            holder.llBackground = (LinearLayout)row.findViewById(R.id.llBackground);

            row.setTag(holder);

        }
        else{
            holder = (OpponentHolder)row.getTag();
        }

        opponent = opponents.get(position);
        holder.tvOpponentName.setText(this.opponent.getOpponentName());

        holder.llBackground.setBackground(context.getResources().getDrawable(R.drawable.neutral_outline));

        return row;
    }

    //Statisk klass för att hålla en rad
    static class OpponentHolder{
        TextView tvOpponentName;
        LinearLayout llBackground;
    }


}

