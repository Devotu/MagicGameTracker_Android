package se.devotu.magicgametracker.info;

import se.devotu.magicgametracker.enums.ManaColor;

/**
 * Created by Devotu on 2015-01-20.
 */
public class ColorValue {
    ManaColor color;
    protected int value;

    public ColorValue(ManaColor color) {
        super();
        this.color = color;
    }

    public ColorValue(ManaColor color, int value) {
        super();
        this.color = color;
        this.value = value;
    }

    public ManaColor getColor() {
        return color;
    }

    public int getValue() {
        return value;
    }

    public void addValue(int value) {
        this.value = this.value + value;
    }

}