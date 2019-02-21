package com.workshop.clase2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SelectedColourActivity extends AppCompatActivity {

    private TextView selectedColourText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_colour);

        Intent intent = getIntent();
        int selectedColourId = intent.getIntExtra("selectedColourId", -1);

        View colourSample;
        colourSample = (View) findViewById(R.id.colourSampleLeft);
        colourSample.setBackgroundColor(selectedColourId);
        colourSample = (View) findViewById(R.id.colourSampleRight);
        colourSample.setBackgroundColor(selectedColourId);
        colourSample = (View) findViewById(R.id.colourSampleUp);
        colourSample.setBackgroundColor(selectedColourId);
        colourSample = (View) findViewById(R.id.colourSampleBottom);
        colourSample.setBackgroundColor(selectedColourId);

        selectedColourText = (TextView) findViewById(R.id.selectedColourText);
        int selectedColourName = intent.getIntExtra("selectedColourName", -1);
        selectedColourText.setText(selectedColourName);
    }
}
