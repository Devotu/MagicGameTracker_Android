package se.devotu.magicgametracker.bl;

/**
 * Created by Devotu on 2015-01-20.
 * Depricated Version 3.6
 */
public class BackgroundManager {

    /*Version 3.6 Depricated
    public Drawable getDeckBackgroundColorDrawable(int deckID, Context context){

        DeckManager deckManager = new DeckManager(context);
        DeckInfo deck = deckManager.getDeckInfo(deckID);

        ArrayList<ManaColor> deckColors = deck.getColorset().getColors();
        int[] colors = new int[deckColors.size()];

        for (int i = 0; i < deckColors.size() && i < 6 ; i++) {
            switch (deckColors.get(i)) {
                case BLACK:
                    colors[i] = Color.BLACK;
                    break;
                case WHITE:
                    colors[i] = Color.WHITE;
                    break;
                case RED:
                    colors[i] = Color.RED;
                    break;
                case BLUE:
                    colors[i] = Color.BLUE;
                    break;
                case GREEN:
                    colors[i] = Color.GREEN;
                    break;
                case NONE:
                    colors[i] = Color.LTGRAY;
                    break;
                default:
                    colors[i] = Color.LTGRAY;
                    break;
            }
        }

        GradientDrawable gradient;
        if (deckColors.size() > 1) {
            gradient = new GradientDrawable(GradientDrawable.Orientation.RIGHT_LEFT, colors);
        } else {
            gradient = new GradientDrawable();
            gradient.setColor(colors[0]);
        }

        return gradient;
    }

    public Drawable getPlayerBackgroundColorDrawable(Context context){

        StatisticsCalculator statCalculator = new StatisticsCalculator(context);
        ManaColor mostCommonColor = statCalculator.getPlayerTotalStats().getMostCommonColor();

        int color = 0;
        switch (mostCommonColor) {
            case BLACK:
                color = Color.BLACK;
                break;
            case WHITE:
                color = Color.WHITE;
                break;
            case RED:
                color = Color.RED;
                break;
            case BLUE:
                color = Color.BLUE;
                break;
            case GREEN:
                color = Color.GREEN;
                break;
            case NONE:
                color = Color.LTGRAY;
                break;
        }

        GradientDrawable gradient = new GradientDrawable();
        gradient.setColor(color);

        return gradient;
    }
    */
}
