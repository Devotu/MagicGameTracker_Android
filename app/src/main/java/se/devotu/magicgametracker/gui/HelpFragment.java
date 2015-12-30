package se.devotu.magicgametracker.gui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import se.devotu.magicgametracker.R;

/**
 * Created by Devotu on 2015-02-07.
 */
public class HelpFragment extends Fragment {

    private String t1, t2, t3, t4, t5;
    private TextView tv1,tv2, tv3, tv4, tv5;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_help, container, false);

        this.tv1 = (TextView)v.findViewById(R.id.tvHelp1);
        tv1.setText(t1);
        this.tv2 = (TextView)v.findViewById(R.id.tvHelp2);
        tv2.setText(t2);
        this.tv3 = (TextView)v.findViewById(R.id.tvHelp3);
        tv3.setText(t3);
        this.tv4 = (TextView)v.findViewById(R.id.tvHelp4);
        tv4.setText(t4);
        this.tv5 = (TextView)v.findViewById(R.id.tvHelp5);
        tv5.setText(t5);

        return v;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(false);
    }

    public void updateTextNr(int textToUpdate, String newText){
        switch (textToUpdate){
            case 1: t1 = newText;
                break;
            case 2: t2 = newText;
                break;
            case 3: t3 = newText;
                break;
            case 4: t4 = newText;
                break;
            case 5: t5 = newText;
                break;
            default:
                break;
        }
    }
}
