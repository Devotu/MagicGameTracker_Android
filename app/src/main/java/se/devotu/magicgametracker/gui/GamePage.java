package se.devotu.magicgametracker.gui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import se.devotu.magicgametracker.R;
import se.devotu.magicgametracker.bl.GameManager;
import se.devotu.magicgametracker.info.Colorset;
import se.devotu.magicgametracker.info.GameInfo;
import se.devotu.magicgametracker.enums.ManaColor;
import se.devotu.magicgametracker.info.PerformanceRating;

/**
 * Created by Devotu on 2015-01-20.
 */
public class GamePage extends Activity {

    private int gameID;
    private GameInfo gameInfo;
    private TextView tvResult, tvOpponent, tvComment;
    private ImageView imgBlack, imgWhite, imgRed, imgGreen, imgBlue, imgDevoid;
    private RatingBar rbarPerformanceRating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gamepage);
        Intent passedIntent = getIntent();
        gameID = passedIntent.getIntExtra("Game_ID", -1);

        GameManager gm = new GameManager(GamePage.this);
        gameInfo = gm.getGameInfoById(gameID);

        tvResult = (TextView)findViewById(R.id.tvGameResult);
        if (gameInfo.isWin()){
            tvResult.setText(R.string.win);
        }else {
            tvResult.setText(R.string.loss);
        }

        tvOpponent = (TextView)findViewById(R.id.tvGameOpponent);
        tvOpponent.setText(gameInfo.getOpponentName());

        imgBlack = (ImageView)findViewById(R.id.imgBlack);
        imgWhite = (ImageView)findViewById(R.id.imgWhite);
        imgRed = (ImageView)findViewById(R.id.imgRed);
        imgGreen = (ImageView)findViewById(R.id.imgGreen);
        imgBlue = (ImageView)findViewById(R.id.imgBlue);
        imgDevoid = (ImageView)findViewById(R.id.imgDevoid);
        activateColors(gameInfo.getOpposingColorset(), imgBlack, imgWhite, imgRed, imgBlue, imgGreen, imgDevoid);

        tvComment = (TextView)findViewById(R.id.tvComment);
        tvComment.setText(gameInfo.getComment());

        rbarPerformanceRating = (RatingBar)findViewById(R.id.rbarGamePerformanceRating);
        PerformanceRating performanceRating = new PerformanceRating();
        performanceRating.setPerformanceRatingFromRaw((float)gameInfo.getPerformanceRating());
        rbarPerformanceRating.setRating(performanceRating.getPerformanceRatingAsStars());

        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.delete_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_delete:
                GameManager gm = new GameManager(GamePage.this);
                Boolean deleted = gm.deleteGameById(gameInfo.getGame_ID());
                if (deleted){
                    String msg = getString(R.string.game) + " " + getString(R.string.deleted).toLowerCase();
                    Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(GamePage.this, DeckSwipePage.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    GamePage.this.finish();
                } else {
                    String msg = getString(R.string.thisword)
                            + " " + getString(R.string.game).toLowerCase()
                            + " " + getString(R.string.cannotbe).toLowerCase()
                            + " " + getString(R.string.deleted).toLowerCase();
                    Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
                }
                return true;
            case R.id.action_help:
                Intent intent = new Intent(GamePage.this, AboutSwipePage.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("targetHelp", 8);
                startActivity(intent);
                GamePage.this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void activateColors(Colorset colorset, ImageView ivBlack, ImageView ivWhite, ImageView ivRed, ImageView ivBlue, ImageView ivGreen, ImageView ivDevoid){

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
                case DEVOID:
                    ivDevoid.setImageResource(R.drawable.devoid_mana_big);
                    break;
                case NONE:
                    break;
                default:
                    break;
            }

        }
    }
}



