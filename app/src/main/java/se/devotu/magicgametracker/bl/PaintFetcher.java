package se.devotu.magicgametracker.bl;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;

import se.devotu.magicgametracker.R;
import se.devotu.magicgametracker.enums.MGTColors;
import se.devotu.magicgametracker.enums.ManaColor;

/**
 * Created by Devotu on 2015-05-18.
 */
public class PaintFetcher {

    private Context context;

    public PaintFetcher(Context context){
        this.context = context;
    }

    public Paint getPaintByManaColor(ManaColor color){
        Paint paint = new Paint();

        switch (color){
            case BLACK:
                paint.setColor(Color.BLACK);
                break;
            case WHITE:
                paint.setColor(Color.WHITE);
                break;
            case RED:
                paint.setColor(Color.RED);
                break;
            case BLUE:
                paint.setColor(Color.BLUE);
                break;
            case GREEN:
                paint.setColor(Color.GREEN);
                break;
            default:
                paint.setColor(Color.GRAY);
                break;
        }

        paint.setStrokeWidth(3);

        return paint;
    }

    public Paint getPaintByColor(MGTColors color){
        Paint paint = new Paint();

        switch (color){
            case VERYBAD:
                paint.setColor(context.getResources().getColor(R.color.VeryBad));
                break;
            case BAD:
                paint.setColor(context.getResources().getColor(R.color.Bad));
                break;
            case AVERAGE:
                paint.setColor(context.getResources().getColor(R.color.Average));
                break;
            case GOOD:
                paint.setColor(context.getResources().getColor(R.color.Good));
                break;
            case VERYGOOD:
                paint.setColor(context.getResources().getColor(R.color.VeryGood));
                break;
            case BACKGROUND:
                paint.setColor(context.getResources().getColor(R.color.Background));
                break;
            case CONTENT:
                paint.setColor(context.getResources().getColor(R.color.Content));
                break;
            case OUTLINE:
                paint.setColor(context.getResources().getColor(R.color.Outline));
                break;
            case ACCENTURE:
                paint.setColor(context.getResources().getColor(R.color.Accenture));
                break;
            case TEXT:
                paint.setColor(context.getResources().getColor(R.color.Text));
                break;
            default:
                paint.setColor(Color.BLACK);
                break;
        }

        paint.setStrokeWidth(3);

        return paint;
    }

    public Paint getPaintByRating(float rating){
        Paint paint = new Paint();

        if (rating > 80){
            paint.setColor(context.getResources().getColor(R.color.VeryGood));
        }else if (rating > 60){
            paint.setColor(context.getResources().getColor(R.color.Good));
        }else if (rating > 40){
            paint.setColor(context.getResources().getColor(R.color.Average));
        }else if (rating > 20){
            paint.setColor(context.getResources().getColor(R.color.Bad));
        }else{
            paint.setColor(context.getResources().getColor(R.color.VeryBad));
        }

        paint.setStrokeWidth(5);

        return paint;
    }
}