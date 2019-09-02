package com.example.josycom.scorekeeper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class BatteryActivity extends AppCompatActivity {
    int battery = 1;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battery);
        imageView = findViewById(R.id.battery_image);
    }

    public void decreaseBattery(View view) {
        if (battery <= 7){
            battery--;
            imageView.setImageLevel(battery);
        }

    }

    public void increaseBattery(View view) {
        if (battery <= 7){
            battery++;
            imageView.setImageLevel(battery);
        }
    }
}
