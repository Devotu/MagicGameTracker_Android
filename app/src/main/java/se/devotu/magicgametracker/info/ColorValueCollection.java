package se.devotu.magicgametracker.info;

import java.util.ArrayList;

import se.devotu.magicgametracker.enums.ManaColor;

/**
 * Created by Devotu on 2015-06-02.
 */
public class ColorValueCollection {
    private ArrayList<ColorValue>colorValues;

    public ColorValueCollection(){
        this.colorValues = new ArrayList<>();
        this.colorValues.add(new ColorValue(ManaColor.BLACK, 0));
        this.colorValues.add(new ColorValue(ManaColor.WHITE, 0));
        this.colorValues.add(new ColorValue(ManaColor.RED, 0));
        this.colorValues.add(new ColorValue(ManaColor.BLUE, 0));
        this.colorValues.add(new ColorValue(ManaColor.GREEN, 0));
        this.colorValues.add(new ColorValue(ManaColor.NONE, 0));
    }

    public ArrayList<ColorValue> getColorValueCollection(){
        return this.colorValues;
    }

    public void setValueOfColor(int value, ManaColor color){
        for (ColorValue cv : this.colorValues){
            if (cv.getColor() == color){
                cv.addValue(value);
            }
        }
    }

    public int getValueOfColor(ManaColor color){
        for (ColorValue cv : this.colorValues){
            if (cv.getColor() == color){
                return cv.getValue();
            }
        }
        return -1;
    }

    public void addOneToColor(ManaColor color){
        for (ColorValue cv : this.colorValues){
            if (cv.getColor() == color){
                cv.addValue(1);
            }
        }
    }

    public int getMaximumValue(){
        if (this.colorValues != null){
            int maxValue = 0;
            for (int i = 0; i < this.colorValues.size(); i++) {
                if (colorValues.get(i).getValue() > maxValue){
                    maxValue = colorValues.get(i).getValue();
                }
            }
            return maxValue;
        } else {
            return -1; //Skulle vilja returnera null men verkar inte gå
        }
    }
}
