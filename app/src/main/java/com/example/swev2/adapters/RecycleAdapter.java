package com.example.swev2.adapters;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.swev2.R;
import com.example.swev2.model.Flight;
import com.example.swev2.ui.bookflights.BookFlightFragment;
import com.example.swev2.ui.others.FlightDialog;
import com.example.swev2.ui.others.FlightInfoFragment;
import com.example.swev2.ui.others.HomeFlightInfoFragment;

import java.util.ArrayList;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.MyViewHolder> {

    Context context;
    ArrayList<Flight> list;

    Dialog mydialog;
    public RecycleAdapter(Context context, ArrayList<Flight> list) {
        this.context = context;
        this.list = list;
    }

    public void setFilteredList(ArrayList<Flight> filteredList){
        this.list=filteredList;
        notifyDataSetChanged();
    }
    public void resetList() {
        this.list = new ArrayList<>(list);  // Reset the list to the original unfiltered list
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.recycle_view_flight,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Flight flight = list.get(position);
        holder.cena.setText(flight.getCena().toString()+"€");
        holder.dolazak.setText(flight.getDolazak());
        holder.polazak.setText(flight.getPolazak());
        holder.sifra_leta.setText(flight.getSifra_leta());
        holder.vreme_dolaska.setText(flight.getVreme_dolaska()+"h");
        holder.vreme_polaska.setText(flight.getVreme_polaska()+"h");
        holder.trajanje.setText(flight.getTrajanje());
        holder.datum.setText(flight.getDatum_polaska());

        mydialog = new Dialog(context);
        mydialog.setContentView(R.layout.dialog_flight_info);


        holder.buynow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                   AppCompatActivity activity  = (AppCompatActivity)view.getContext();
                   FlightInfoFragment flightInfoFragment = new FlightInfoFragment(flight.getCena(),flight.getDolazak(),
                           flight.getPolazak(),flight.getGate(),flight.getSifra_leta(),flight.getVreme_dolaska(),flight.getVreme_polaska(),flight.getTrajanje(),flight.getDatum_polaska());
                   activity.getSupportFragmentManager().beginTransaction().replace(R.id.containerID,new HomeFlightInfoFragment(flightInfoFragment))
                           .addToBackStack(null).commit();
            }
        });

        holder.slika.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                TextView dialog_cena = mydialog.findViewById(R.id.dialogflightinfocena);
                TextView dialog_polazak = mydialog.findViewById(R.id.dialogflightinfopolazak);
                TextView dialog_dolazak = mydialog.findViewById(R.id.dialogflightinfodolazak);
                TextView dialog_gate = mydialog.findViewById(R.id.dialogflightinfogate);
                TextView dialog_sifra_leta = mydialog.findViewById(R.id.dialogflightinfosifraleta);
                TextView dialog_vreme_polaska = mydialog.findViewById(R.id.dialogflightinfovremepolaska);
                TextView dialog_vreme_dolaska = mydialog.findViewById(R.id.dialogflightinfovremedolaska);
                TextView dialog_trajanje = mydialog.findViewById(R.id.dialoginfotrajanje);
                TextView dialog_datum = mydialog.findViewById(R.id.dialogflightinfodatum);

                dialog_cena.setText(flight.getCena().toString()+"€");
                dialog_polazak.setText(flight.getPolazak());
                dialog_dolazak.setText(flight.getDolazak());
                dialog_gate.setText(flight.getGate());
                dialog_sifra_leta.setText(flight.getSifra_leta());
                dialog_vreme_polaska.setText(flight.getVreme_polaska()+"h");
                dialog_vreme_dolaska.setText(flight.getVreme_dolaska()+"h");
                dialog_trajanje.setText(flight.getTrajanje()+"h");
                dialog_datum.setText(flight.getDatum_polaska());



                mydialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                mydialog.setCancelable(true);
                mydialog.show();


            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public  static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView cena,dolazak,polazak,trajanje,sifra_leta,vreme_dolaska,vreme_polaska,datum;
        ImageView slika, buynow;
        CardView cardView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            cena = itemView.findViewById(R.id.price);
            dolazak = itemView.findViewById(R.id.finaldestination);
            polazak = itemView.findViewById(R.id.startdestination);
            sifra_leta = itemView.findViewById(R.id.flightnumber);
            vreme_polaska = itemView.findViewById(R.id.timestart);
            vreme_dolaska = itemView.findViewById(R.id.endtime);
            trajanje = itemView.findViewById(R.id.flightlength);
            datum = itemView.findViewById(R.id.fromdate);
            cardView =itemView.findViewById(R.id.cardflight);
            slika= itemView.findViewById(R.id.rec_plane);
            buynow = itemView.findViewById(R.id.buyticket);
        }
    }
}
