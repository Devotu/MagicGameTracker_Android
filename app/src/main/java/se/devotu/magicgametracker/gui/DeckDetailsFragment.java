package se.devotu.magicgametracker.gui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import se.devotu.magicgametracker.R;
import se.devotu.magicgametracker.bl.DeckManager;
import se.devotu.magicgametracker.enums.ManaColor;
import se.devotu.magicgametracker.info.Colorset;
import se.devotu.magicgametracker.info.DeckInfo;

/**
 * Created by Devotu on 2015-02-07.
 */
public class DeckDetailsFragment extends Fragment {

    private TextView tvTitle, tvTheme, tvFormat, tvActive;
    private ImageView imgBlack, imgWhite, imgRed, imgGreen, imgBlue; //Version 3.6
    private int deckID;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_deckdetails, container, false);

        Intent passedIntent = getActivity().getIntent();
        deckID = passedIntent.getIntExtra("Deck_ID", -1);

        DeckManager deckManager = new DeckManager(getActivity());
        DeckInfo deck = deckManager.getDeckInfo(deckID);

        tvTitle = (TextView)v.findViewById(R.id.tvDeckName);
        tvTitle.setText(deck.getName());

        tvActive = (TextView)v.findViewById(R.id.tvActive);
        if (deck.getActive() == 1){
            tvActive.setText(getString(R.string.active));
        } else {
            tvActive.setText(getString(R.string.inactive));
        }

        tvFormat = (TextView)v.findViewById(R.id.tvFormat);
        tvFormat.setText(deck.getFormat());

        tvTheme = (TextView)v.findViewById(R.id.tvTheme);
        tvTheme.setText(deck.getTheme());

        //Version 3.6
        imgBlack = (ImageView)v.findViewById(R.id.imgBlack);
        imgWhite = (ImageView)v.findViewById(R.id.imgWhite);
        imgRed = (ImageView)v.findViewById(R.id.imgRed);
        imgGreen = (ImageView)v.findViewById(R.id.imgGreen);
        imgBlue = (ImageView)v.findViewById(R.id.imgBlue);
        activateColors(deck.getColorset(), imgBlack, imgWhite, imgRed, imgBlue, imgGreen);

        return v;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    //Version 3.6
    private void activateColors(Colorset colorset, ImageView ivBlack, ImageView ivWhite, ImageView ivRed, ImageView ivBlue, ImageView ivGreen){

        for (ManaColor color : colorset.getColors()){

            switch (color) {
                case BLACK:
                    ivBlack.setImageResource(R.drawable.black_mana_big);
                    break;
                case WHITE:
                    ivWhite.setImageResource(R.drawable.white_mana_big);
                    break;
                case RED:
                    ivRed.setImageResource(R.drawable.red_mana_big);
                    break;
                case BLUE:
                    ivBlue.setImageResource(R.drawable.blue_mana_big);
                    break;
                case GREEN:
                    ivGreen.setImageResource(R.drawable.green_mana_big);
                    break;
                case NONE:
                    break;
                default:
                    break;
            }

        }
    }
}
