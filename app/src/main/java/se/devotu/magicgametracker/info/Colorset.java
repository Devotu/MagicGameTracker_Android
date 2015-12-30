package se.devotu.magicgametracker.info;

import java.util.ArrayList;

import se.devotu.magicgametracker.enums.ManaColor;

/**
 * Created by Devotu on 2015-01-20.
 */
public class Colorset {

    private ArrayList<ManaColor> colors;

    public Colorset() {
        super();
        colors = new ArrayList<ManaColor>();
    }

    public Colorset(ManaColor intitalColor) {
        colors.add(intitalColor);
    }

    public void addColor(ManaColor color){
        colors.add(color);
    }

    public ArrayList<ManaColor> getColors(){
        return colors;
    }

    public int getNumberOfColors(){
        return colors.size();
    }
}