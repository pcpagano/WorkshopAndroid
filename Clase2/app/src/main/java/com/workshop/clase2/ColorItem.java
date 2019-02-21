package com.workshop.clase2;

import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ColorItem {

    private LinearLayout colourListRow;
    private int name;
    private int colorId;

    public LinearLayout getColourListRow() {
        return colourListRow;
    }

    public void setColourListRow(LinearLayout colourListRow) {
        this.colourListRow = colourListRow;
    }

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }

    public int getColorId() {
        return colorId;
    }

    public void setColorId(int colorId) {
        this.colorId = colorId;
    }
}