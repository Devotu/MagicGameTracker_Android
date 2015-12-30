package se.devotu.magicgametracker.gui;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import se.devotu.magicgametracker.R;
import se.devotu.magicgametracker.info.Game;

public class GameListAdapter extends ArrayAdapter<Game>{

	Context context;
	int layoutResource;
	ArrayList<Game> objects;
	
	public GameListAdapter(Context context, int layoutResource, ArrayList<Game> games) {
		super(context, layoutResource, games);
		this.layoutResource = layoutResource;
		this.context = context;
		this.objects = games;
	}

	@Override
	public View getView(int position, final View convertView, ViewGroup parent) {
		View row = convertView;
		GameHolder holder = null;

		if(row == null){
			LayoutInflater inflater = ((Activity)context).getLayoutInflater();
			row = inflater.inflate(layoutResource, parent, false);
			
			holder = new GameHolder();
			holder.tvComment = (TextView)row.findViewById(R.id.tvComment);
			holder.llBackground = (LinearLayout)row.findViewById(R.id.llBackground);

			row.setTag(holder);
			
		}
		else{
			holder = (GameHolder)row.getTag();
		}
		
		Game game = objects.get(position);		
		holder.tvComment.setText(game.getComment());

		if (game.isWin()) {
			holder.llBackground.setBackground(context.getResources().getDrawable(R.drawable.positive_outline));
		} else {
			holder.llBackground.setBackground(context.getResources().getDrawable(R.drawable.negative_outline));
		}
		
		return row;
	}

	//Statisk klass f�r att h�lla en rad
	static class GameHolder{
		TextView tvComment;
		LinearLayout llBackground;
	}
}