package se.devotu.magicgametracker.gui;

import se.devotu.magicgametracker.R;
import se.devotu.magicgametracker.bl.AppInitializer;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

/**
 * Created by Devotu on 2015-01-20.
 */
public class Splashscreen extends Activity{

    private static int SPLASH_TIME_OUT = 2000;
    TextView tvVersionNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen);

        String versionNumber;
        try {
            versionNumber = getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
        } catch (NameNotFoundException e) {
            versionNumber = "x";
            e.printStackTrace();
        }
        tvVersionNumber = (TextView)findViewById(R.id.tvVersionNumber);
        tvVersionNumber.setText(versionNumber);

        System.out.println("gui spl Checking prerequisities");
        AppInitializer appInitializer;
        appInitializer = new AppInitializer(Splashscreen.this);
        appInitializer.initalizeApp();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                Intent intent = new Intent(Splashscreen.this, MainMenu.class);
                startActivity(intent);

                finish();
            }
        }, SPLASH_TIME_OUT);
    }

}
