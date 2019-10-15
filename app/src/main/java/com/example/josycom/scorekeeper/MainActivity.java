package com.example.josycom.scorekeeper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.annotation.TargetApi;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private int mScore1, mScore2;
    private TextView mScoreText1, mScoreText2;
    static final String STATE_SCORE_1 = "Team 1 Score";
    static final String STATE_SCORE_2 = "Team 2 Score";
    private SharedPreferences mPreferences;
    private String sharedPrefFile = "com.example.josycom.scorekeeper";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mScoreText1 = findViewById(R.id.score_1);
        mScoreText2 = findViewById(R.id.score_2);
        mPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);


        //if(savedInstanceState != null){
//            mScore1 = savedInstanceState.getInt(STATE_SCORE_1);
//            mScore2 = savedInstanceState.getInt(STATE_SCORE_2);
//            //Set the score text views
//            mScoreText1.setText(String.valueOf(mScore1));
//            mScoreText2.setText(String.valueOf(mScore2));

            mScore1 = mPreferences.getInt(STATE_SCORE_1, mScore1);
            mScore2 = mPreferences.getInt(STATE_SCORE_2, mScore2);
            // Set the score text views
            mScoreText1.setText(String.valueOf(mScore1));
            mScoreText2.setText(String.valueOf(mScore2));
        //}

    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void decreaseScore(View view) {
        int viewID = view.getId();
        switch (viewID){
            case R.id.decreaseTeam1:
                mScore1--;
                mScoreText1.setText(String.valueOf(mScore1));
                break;
            case R.id.decreaseTeam2:
                mScore2--;
                mScoreText2.setText(String.valueOf(mScore2));
        }
    }

    public void increaseScore(View view) {
        int viewID = view.getId();
        switch (viewID){
            case R.id.increaseTeam1:
                mScore1++;
                mScoreText1.setText(String.valueOf(mScore1));
                break;
            case R.id.increaseTeam2:
                mScore2++;
                mScoreText2.setText(String.valueOf(mScore2));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        // Changes the label of the menu based on the state of the app
        int nightMode = AppCompatDelegate.getDefaultNightMode();
        if (nightMode == AppCompatDelegate.MODE_NIGHT_YES){
            menu.findItem(R.id.night_mode).setTitle(R.string.day_mode);
        } else {
            menu.findItem(R.id.night_mode).setTitle(R.string.night_mode);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //Checks if the correct item was clicked
        if (item.getItemId() == R.id.night_mode){
            // Gets the night mode state of the app
            int nightMode = AppCompatDelegate.getDefaultNightMode();
            //Sets the theme mode for the restarted activity
            if (nightMode == AppCompatDelegate.MODE_NIGHT_YES){
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            }
            // Recreates the activity for the theme change to take effect
            recreate();
        }
        return true;
    }

//    @Override
//    protected void onSaveInstanceState(Bundle outState) {
//        //Saves the scores
//        outState.putInt(STATE_SCORE_1, mScore1);
//        outState.putInt(STATE_SCORE_2, mScore2);
//        super.onSaveInstanceState(outState);
//    }

    @Override
    protected void onPause() {
        super.onPause();

        SharedPreferences.Editor preferenceEditor = mPreferences.edit();
        preferenceEditor.putInt(STATE_SCORE_1, mScore1);
        preferenceEditor.putInt(STATE_SCORE_2, mScore2);
        preferenceEditor.apply();
    }

    public void openBatteryActivity(View view) {
        Intent intent = new Intent(MainActivity.this, BatteryActivity.class);
        startActivity(intent);
    }

    public void resetScore(View view) {
        //Reset scores
        mScore1 = 0;
        mScore2 = 0;
        mScoreText1.setText(String.valueOf(mScore1));
        mScoreText2.setText(String.valueOf(mScore2));

        //Clear preferences
        SharedPreferences.Editor preferenceEditor = mPreferences.edit();
        preferenceEditor.clear();
        preferenceEditor.apply();
    }
}
