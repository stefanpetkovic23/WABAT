package com.example.swev2.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.swev2.R;
import com.example.swev2.model.Booking;

import java.util.ArrayList;
import java.util.Random;

public class BookingAdapter extends RecyclerView.Adapter<BookingAdapter.MyViewHolder> {
    Context context;
    ArrayList<Booking> list;

    public BookingAdapter(Context context, ArrayList<Booking> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.recycle_view_booking,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull BookingAdapter.MyViewHolder holder, int position) {

        Booking booking = list.get(position);
        holder.txtcard.setText(booking.getKartica());
        holder.txtseat.setText(booking.getSediste());
        holder.txtprice.setText(booking.getUkupnaCena().toString()+"€");
        holder.txttransaction.setText(getRandomNumberString());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView txtcard,txtseat,txtprice,txttransaction;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            txtcard = itemView.findViewById(R.id.bookingcardinfo);
            txtseat = itemView.findViewById(R.id.bookingseatinfo);
            txtprice = itemView.findViewById(R.id.bookingpriceinfo);
            txttransaction = itemView.findViewById(R.id.bookingtransactionnumber);
        }
    }

    public static String getRandomNumberString() {
        // It will generate 6 digit random Number.
        // from 0 to 999999
        Random rnd = new Random();
        int number = rnd.nextInt(900000)+100000;

        // this will convert any number sequence into 6 character.
        return String.format("%06d", number);
    }
}
