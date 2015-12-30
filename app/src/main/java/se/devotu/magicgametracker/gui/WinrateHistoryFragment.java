package se.devotu.magicgametracker.gui;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import java.util.ArrayList;

import se.devotu.magicgametracker.R;
import se.devotu.magicgametracker.bl.PaintFetcher;
import se.devotu.magicgametracker.enums.MGTColors;

/**
 * Created by Devotu on 2015-02-07.
 */
public class WinrateHistoryFragment extends Fragment {

    RelativeLayout winrateGraph;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_winratehistory, container, false);

        this.winrateGraph = (RelativeLayout)v.findViewById(R.id.frWinrateHistory);

        return v;
    }

    public void drawGraph(ArrayList<Float> winrateHistory){

        PaintFetcher paintFetcher = new PaintFetcher(getActivity());
        int origo = 0;
        int totalW = (int)getActivity().getResources().getDimension(R.dimen.component_width_graph);
        int totalH = (int)getActivity().getResources().getDimension(R.dimen.component_height_graph);
        int displayW = (int)getActivity().getResources().getDimension(R.dimen.component_display_width_graph);
        int displayH = (int)getActivity().getResources().getDimension(R.dimen.component_display_height_graph);
        int descriptionW = (int)getActivity().getResources().getDimension(R.dimen.component_description_width_graph);
        int descriptionH = (int)getActivity().getResources().getDimension(R.dimen.component_description_height_graph);
        int text = (int)getActivity().getResources().getDimension(R.dimen.component_text_graph);
        int dpAdjustment = (int)getActivity().getResources().getDimension(R.dimen.adjustment_coeficient);

        Bitmap bg = Bitmap.createBitmap(totalW, totalH, Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bg);

        //Rityta
        canvas.drawRect(origo, origo, totalW, totalH, paintFetcher.getPaintByColor(MGTColors.BACKGROUND));
        canvas.drawRect(descriptionW, origo, totalW, displayH, paintFetcher.getPaintByColor(MGTColors.ACCENTURE));

        canvas.drawLine(descriptionW, origo, descriptionW, displayH, paintFetcher.getPaintByColor(MGTColors.OUTLINE));
        canvas.drawLine(descriptionW, displayH, totalW, displayH, paintFetcher.getPaintByColor(MGTColors.OUTLINE));

        //Beskrivning
        Paint textPaintLeft = paintFetcher.getPaintByColor(MGTColors.TEXT);
        textPaintLeft.setTextSize(text);
        textPaintLeft.setTextAlign(Paint.Align.LEFT);
        Paint textPaintRight = paintFetcher.getPaintByColor(MGTColors.TEXT);
        textPaintRight.setTextSize(text);
        textPaintRight.setTextAlign(Paint.Align.RIGHT);

        canvas.drawText(getActivity().getResources().getString(R.string.gamesago),descriptionW,displayH+text,textPaintLeft);
        canvas.drawText(getActivity().getResources().getString(R.string.now),totalW,displayH+text,textPaintRight);

        canvas.drawText("100",origo+descriptionW,text,textPaintRight);
        canvas.drawText("50",origo+descriptionW,(displayH+text)/2,textPaintRight);
        canvas.drawText("0",origo+descriptionW,displayH,textPaintRight);

        //Rita graf
        if (winrateHistory.size() > 1) {

            float oldx = descriptionW;
            float oldy = displayH - winrateHistory.get(0)*dpAdjustment;
            float endx;
            float endy;

            for (int i = 1; i < winrateHistory.size(); i++) {
                endx = (i * (displayW / (winrateHistory.size()-1))) + descriptionW;
                endy = displayH - winrateHistory.get(i) * dpAdjustment;
                Paint ratingPaint = paintFetcher.getPaintByColor(MGTColors.TEXT);
                if (i>3){//Motsvarar 5:e spelade
                    ratingPaint = paintFetcher.getPaintByRating(winrateHistory.get(i));
                }
                canvas.drawLine(oldx, oldy, endx, endy, ratingPaint);
                oldx = endx;
                oldy = endy;
            }
        }

        this.winrateGraph.setBackground(new BitmapDrawable(getActivity().getResources(), bg));
    }
}
