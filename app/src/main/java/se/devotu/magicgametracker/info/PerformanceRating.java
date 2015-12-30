package se.devotu.magicgametracker.info;

/**
 * Created by Devotu on 2015-03-22.
 */
public class PerformanceRating {

    private float performanceRating;

    private float convertFromStarToRaw(float starRating){
        return (starRating-1)*25;
    }

    private float convertFromRawToStar(float rawRating){
        return (rawRating/5/5)+1;
    }

    public void setPerformanceRatingFromStars(float starRating){
        this.performanceRating = convertFromStarToRaw(starRating);
    }

    public float getPerformanceRatingAsStars(){
        return convertFromRawToStar(this.performanceRating);
    }

    public void setPerformanceRatingFromRaw(float performanceRating){
        this.performanceRating = performanceRating;
    }

    public float getPerformanceRatingAsRaw(){
        return performanceRating;
    }
}
