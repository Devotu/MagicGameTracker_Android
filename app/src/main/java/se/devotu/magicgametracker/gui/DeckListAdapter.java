package se.devotu.magicgametracker.gui;

import java.util.ArrayList;

import se.devotu.magicgametracker.R;
import se.devotu.magicgametracker.info.DeckListItem;
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
public class DeckListAdapter extends ArrayAdapter<DeckListItem>{

    Context context;
    int layoutResource;
    ArrayList<DeckListItem> objects;

    public DeckListAdapter(Context context, int layoutResource, ArrayList<DeckListItem> objects) {
        super(context, layoutResource, objects);
        this.layoutResource = layoutResource;
        this.context = context;
        this.objects = objects;
    }

    @Override
    public View getView(int position, final View convertView, ViewGroup parent) {
        View row = convertView;
        DeckHolder holder = null;

        if(row == null){
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResource, parent, false);

            holder = new DeckHolder();
            holder.tvName = (TextView)row.findViewById(R.id.tvName);
            holder.llBackground = (LinearLayout)row.findViewById(R.id.llBackground);

            row.setTag(holder);

        }
        else{
            holder = (DeckHolder)row.getTag();
        }

        DeckListItem item = objects.get(position);
        holder.tvName.setText(item.getName());

        if (item.getWinpc() >= 80) {
            holder.llBackground.setBackground(context.getResources().getDrawable(R.drawable.verygood_outline));
        } else if (item.getWinpc() >= 60){
            holder.llBackground.setBackground(context.getResources().getDrawable(R.drawable.good_outline));
        } else if (item.getWinpc() >= 40){
            holder.llBackground.setBackground(context.getResources().getDrawable(R.drawable.average_outline));
        } else if (item.getWinpc() >= 20){
            holder.llBackground.setBackground(context.getResources().getDrawable(R.drawable.bad_outline));
        } else if (item.getWinpc() >= 0){
            holder.llBackground.setBackground(context.getResources().getDrawable(R.drawable.verybad_outline));
        } else {
            holder.llBackground.setBackground(context.getResources().getDrawable(R.drawable.neutral_outline));
        }

        return row;
    }

    //Statisk klass för att hålla en rad
    static class DeckHolder {
        TextView tvName;
        LinearLayout llBackground;
    }


}
