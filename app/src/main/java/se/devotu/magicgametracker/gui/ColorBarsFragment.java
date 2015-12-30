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
import se.devotu.magicgametracker.info.ColorValue;
import se.devotu.magicgametracker.info.ColorValueCollection;

/**
 * Created by Devotu on 2015-02-07.
 */
public class ColorBarsFragment extends Fragment {

    RelativeLayout colorsGraph;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_colorbars, container, false);

        this.colorsGraph = (RelativeLayout)v.findViewById(R.id.frColorBars);

        return v;
    }

    public void drawGraph(ColorValueCollection colorValueCollection){

        PaintFetcher paintFetcher = new PaintFetcher(getActivity());
        int origo = 0;
        int totalW = (int)getActivity().getResources().getDimension(R.dimen.component_width_graph);
        int totalH = (int)getActivity().getResources().getDimension(R.dimen.component_height_graph);
        int displayW = (int)getActivity().getResources().getDimension(R.dimen.component_display_width_graph);
        int displayH = (int)getActivity().getResources().getDimension(R.dimen.component_display_height_graph);
        int descriptionW = (int)getActivity().getResources().getDimension(R.dimen.component_description_width_graph);
        int descriptionH = (int)getActivity().getResources().getDimension(R.dimen.component_description_height_graph);
        int textH = (int)getActivity().getResources().getDimension(R.dimen.component_text_graph);
        int marginWH = (int)getActivity().getResources().getDimension(R.dimen.margin_square);
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
        textPaintLeft.setTextSize(textH);
        textPaintLeft.setTextAlign(Paint.Align.LEFT);
        Paint textPaintRight = paintFetcher.getPaintByColor(MGTColors.TEXT);
        textPaintRight.setTextSize(textH);
        textPaintRight.setTextAlign(Paint.Align.RIGHT);

        int maxValue = colorValueCollection.getMaximumValue();
        canvas.drawText(String.format("%.0f", (float)maxValue),origo+descriptionW,textH,textPaintRight);
        canvas.drawText(String.format("%.0f", ((float)maxValue)/2),origo+descriptionW,(displayH+textH)/2,textPaintRight);
        canvas.drawText("0",origo+descriptionW,displayH,textPaintRight);

        //Rita graf
        int i = 0;
        Paint textPaintCenter = paintFetcher.getPaintByColor(MGTColors.TEXT);
        textPaintCenter.setTextAlign(Paint.Align.CENTER);
        ArrayList<ColorValue> colorValues = colorValueCollection.getColorValueCollection();
        for (ColorValue cv : colorValues) {
            //Stapel
            float xL, yB, xR, yT;
            xL = i*((float)displayW/(float)5) + descriptionW + marginWH; i++;
            yB = displayH;
            xR = i*((float)displayW/(float)5) + descriptionW - marginWH;
            yT = displayH - ((displayH - marginWH) * ((float)cv.getValue()/(float)maxValue));
            Paint barPaint = paintFetcher.getPaintByManaColor(cv.getColor());
            canvas.drawRect(xL,yT,xR,yB,barPaint);

            //Etikett
            float xLabel, yLabel;
            xLabel = (xL + xR)/(float)2;
            yLabel = displayH + textH;
            canvas.drawText(String.valueOf(cv.getValue()), xLabel, yLabel, textPaintCenter);
        };

        this.colorsGraph.setBackground(new BitmapDrawable(getActivity().getResources(), bg));
    }
}
