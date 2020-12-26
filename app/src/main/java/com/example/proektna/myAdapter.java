package com.example.proektna;

import android.content.ClipData;
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
import java.util.HashMap;
import java.util.List;

public class myAdapter extends RecyclerView.Adapter<myAdapter.ViewHolder> {
    private List<String> myList;
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
        public TextView myName;
        public ImageView Pic;

        public ViewHolder(View view) {
            super(view);
            myName = (TextView) itemView.findViewById(R.id.Name);
            Pic = (ImageView) itemView.findViewById(R.id.picture);
        }
    }

    public myAdapter(List<String> myList, int rowLayout, Context context) {
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
        String entry = myList.get(i);
        List<Item> items = new ArrayList<Item>();
        items.add(new Item("Austin", R.drawable.austin));
        items.add(new Item("Chicago", R.drawable.chicago));
        items.add(new Item("Los Angeles", R.drawable.los_angeles));
        items.add(new Item("Miami", R.drawable.miami));
        items.add(new Item("New York", R.drawable.new_york));
        items.add(new Item("Oklahoma", R.drawable.oklahoma));
        items.add(new Item("Portland", R.drawable.portland));
        items.add(new Item("San Francisco", R.drawable.san_francisco));
        int ir = items.get(i).drawable;
        viewHolder.Pic.setImageResource(ir);
        viewHolder.myName.setText(entry);
        viewHolder.myName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView tv = (TextView) v;
                Toast.makeText(context, tv.getText(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(v.getContext(), ReservationForm.class);
                // NEW CHANGE
                //intent.putExtra("city_name", myList.get(i));
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(v.getContext());
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("city_name", entry);
                editor.commit();
                context.startActivity(intent);
            }
        });

        // TODO

        //viewHolder.Pic.setImageResource(R.drawable.london);
    }

    @Override
    public int getItemCount() {
        return myList == null ? 0 : myList.size();
    }
}
