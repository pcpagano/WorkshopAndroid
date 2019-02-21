package com.workshop.clase2;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class ColourListAdapter extends RecyclerView.Adapter<ColourListAdapter.MyViewHolder> {

    interface OnItemClickListener {
        void onItemClick(ColorItem colorItem);
    }

    private List<ColorItem> colourList;
    private OnItemClickListener onItemClickListener;

    class MyViewHolder extends RecyclerView.ViewHolder {

        LinearLayout colourListRow;
        View colourSample;
        TextView name;
        MyViewHolder(View view) {
            super(view);
            colourListRow = (LinearLayout) view.findViewById(R.id.colourListRow);
            colourSample = (View) view.findViewById(R.id.colourSample);
            name = (TextView) view.findViewById(R.id.colourName);
        }

        void bind(final ColorItem colorItem, final OnItemClickListener onItemClickListener) {
            name.setText(colorItem.getName());
            colourSample.setBackgroundColor(colorItem.getColorId());
            colourListRow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(colorItem);
                }
            });
        }
    }

    public ColourListAdapter(List<ColorItem> colourList, OnItemClickListener onItemClickListener) {
        this.colourList = colourList;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.colour_list_row, viewGroup, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        ColorItem color = colourList.get(i);
        myViewHolder.bind(color, onItemClickListener);
    }

    @Override
    public int getItemCount() {
        return colourList.size();
    }
}
