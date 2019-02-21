package com.workshop.clase2;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<ColorItem> colourList = new ArrayList<>();
    private RecyclerView recyclerView;
    private ColourListAdapter colourListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        colourListAdapter = new ColourListAdapter(colourList, buildItemClick());

        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(colourListAdapter);

        prepareColourData();
    }

    private void prepareColourData() {
        ColorItem color = new ColorItem();
        color.setColorId(Color.RED);
        color.setName(R.string.rojo);
        colourList.add(color);
        color = new ColorItem();
        color.setColorId(Color.BLUE);
        color.setName(R.string.azul);
        colourList.add(color);
        color = new ColorItem();
        color.setColorId(Color.GREEN);
        color.setName(R.string.verde);
        colourList.add(color);
        color = new ColorItem();
        color.setColorId(Color.YELLOW);
        color.setName(R.string.amarillo);
        colourList.add(color);
        color = new ColorItem();
        color.setColorId(Color.BLACK);
        color.setName(R.string.negro);
        colourList.add(color);
        color = new ColorItem();
        color.setColorId(Color.WHITE);
        color.setName(R.string.blanco);
        colourList.add(color);
        color = new ColorItem();
        color.setColorId(Color.MAGENTA);
        color.setName(R.string.magenta);
        colourList.add(color);
        color = new ColorItem();
        color.setColorId(Color.CYAN);
        color.setName(R.string.cyan);
        colourList.add(color);
        color = new ColorItem();
        color.setColorId(Color.LTGRAY);
        color.setName(R.string.gris_claro);
        colourList.add(color);
        color = new ColorItem();
        color.setColorId(Color.GRAY);
        color.setName(R.string.gris);
        colourList.add(color);

        colourListAdapter.notifyDataSetChanged();
    }

    public ColourListAdapter.OnItemClickListener buildItemClick() {
        return new ColourListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(ColorItem colorItem) {
                Intent intent = new Intent(getApplicationContext(), SelectedColourActivity.class);
                intent.putExtra("selectedColourId", colorItem.getColorId());
                intent.putExtra("selectedColourName", colorItem.getName());
                startActivity(intent);
            }
        };
    }
}
