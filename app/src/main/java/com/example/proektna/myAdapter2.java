package com.example.proektna;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class myAdapter2 extends RecyclerView.Adapter<myAdapter2.ViewHolder> {
    private List<ParkingModel> myList;
    private int rowLayout;
    private Context context;

    public class Item {
        public String picName;
        public int drawable;

        public Item (String picName, int drawable) {
            this.drawable = drawable;
            this.picName = picName;
        }

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView free;
        public TextView taken;
        public TextView parking_name;

        public ViewHolder(View view) {
            super(view);
            parking_name = (TextView) itemView.findViewById(R.id.parkingName);
            free = (TextView) itemView.findViewById(R.id.free);
            taken = (TextView) itemView.findViewById(R.id.taken);
        }
    }

    public myAdapter2(List<ParkingModel> myList, int rowLayout, Context context) {
        this.myList = myList;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(rowLayout, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        ParkingModel rm = myList.get(i);
        viewHolder.parking_name.setText(rm.getParking_name());
        viewHolder.free.setText(String.valueOf(rm.getFree()));
        viewHolder.taken.setText(String.valueOf(rm.getTaken()));

//        viewHolder.myName.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                TextView tv = (TextView) v;
//                Toast.makeText(context, tv.getText(), Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(v.getContext(), ReservationForm.class);
//                // NEW CHANGE
//                //intent.putExtra("city_name", myList.get(i));
//                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(v.getContext());
//                SharedPreferences.Editor editor = prefs.edit();
//                editor.putString("city_name", entry);
//                editor.commit();
//                context.startActivity(intent);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return myList == null ? 0 : myList.size();
    }
}