package com.example.swev2.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import com.example.swev2.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GridAdapter extends BaseAdapter {

    Context context;
    private final String seatnumber[];

    LayoutInflater inflater;

    public GridAdapter(Context context, String[] seatnumber) {
        this.context = context;
        this.seatnumber = seatnumber;
    }


    @Override
    public int getCount() {
        return seatnumber.length;
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertview, ViewGroup parent) {

        if(inflater == null)
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if(convertview == null){
            convertview = inflater.inflate(R.layout.singleseat,null);
        }
        CardView card = convertview.findViewById(R.id.cardView);

        if (seatnumber[i].equals("D3")) {
            card.setCardBackgroundColor(ContextCompat.getColor(context, R.color.red));
        // card.setClickable(false);
        //card.setFocusable(false);
        card.setEnabled(false);}
        if (seatnumber[i].equals("B4")) {
            card.setCardBackgroundColor(ContextCompat.getColor(context, R.color.red));
           // card.setClickable(false);
           // card.setFocusable(false);
             card.setEnabled(false);}
        if (seatnumber[i].equals("D5")) {
            card.setCardBackgroundColor(ContextCompat.getColor(context, R.color.red));
            // card.setClickable(false);
            // card.setFocusable(false);
            card.setEnabled(false);}
        if (seatnumber[i].equals("A6")) {
            card.setCardBackgroundColor(ContextCompat.getColor(context, R.color.red));
            // card.setClickable(false);
            // card.setFocusable(false);
            card.setEnabled(false);}
        if (seatnumber[i].equals("C8")) {
            card.setCardBackgroundColor(ContextCompat.getColor(context, R.color.red));
            // card.setClickable(false);
            // card.setFocusable(false);
            card.setEnabled(false);}
        if (seatnumber[i].equals("B9")) {
            card.setCardBackgroundColor(ContextCompat.getColor(context, R.color.red));
            // card.setClickable(false);
            // card.setFocusable(false);
            card.setEnabled(false);}
        if (seatnumber[i].equals("D12")) {
            card.setCardBackgroundColor(ContextCompat.getColor(context, R.color.red));
            // card.setClickable(false);
            // card.setFocusable(false);
            card.setEnabled(false);}

        TextView txtseatnumber = convertview.findViewById(R.id.seatID);
        txtseatnumber.setText(seatnumber[i]);
        return convertview;
    }
}
