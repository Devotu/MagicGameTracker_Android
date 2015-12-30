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
import se.devotu.magicgametracker.info.Alteration;

public class AlterationListAdapter extends ArrayAdapter<Alteration>{

	Context context;
	int layoutResource;
	ArrayList<Alteration> objects;

	public AlterationListAdapter(Context context, int layoutResource, ArrayList<Alteration> alterations) {
		super(context, layoutResource, alterations);
		this.layoutResource = layoutResource;
		this.context = context;
		this.objects = alterations;
	}

	@Override
	public View getView(int position, final View convertView, ViewGroup parent) {
		View row = convertView;
		AlterationHolder holder = null;

		if(row == null){
			LayoutInflater inflater = ((Activity)context).getLayoutInflater();
			row = inflater.inflate(layoutResource, parent, false);
			
			holder = new AlterationHolder();
			holder.tvComment = (TextView)row.findViewById(R.id.tvComment);
            holder.llBackground = (LinearLayout)row.findViewById(R.id.llBackground);

			row.setTag(holder);
		}
		else{
			holder = (AlterationHolder)row.getTag();
		}
		
		Alteration alteration = objects.get(position);
		holder.tvComment.setText(alteration.getComment());

        holder.llBackground.setBackground(context.getResources().getDrawable(R.drawable.neutral_outline));
		
		return row;
	}

	//Statisk klass f�r att h�lla en rad
	static class AlterationHolder {
		TextView tvComment;
        LinearLayout llBackground;
	}
}