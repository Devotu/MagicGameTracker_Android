package se.devotu.magicgametracker.gui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import se.devotu.magicgametracker.R;

/**
 * Created by Devotu on 2015-02-07.
 */
public class AboutSwipePage extends FragmentActivity{

    ViewPager aboutPager;
    FragmentPagerAdapter aboutPageAdapter;
    int targetHelp;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutswipepage);
        Intent passedIntent = getIntent();
        targetHelp = passedIntent.getIntExtra("targetHelp", 0);
        aboutPager = (ViewPager) findViewById(R.id.vpAboutPager);
        aboutPageAdapter = new AboutSwipeAdapter(getSupportFragmentManager());
        aboutPager.setAdapter(aboutPageAdapter);
        aboutPager.setCurrentItem(targetHelp, false);
    }
}
