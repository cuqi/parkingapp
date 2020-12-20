package com.example.proektna;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
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
        items.add(new Item("Skopje", R.drawable.skopje));
        items.add(new Item("Tokyo", R.drawable.tokyo));
        items.add(new Item("Berlin", R.drawable.berlin));
        items.add(new Item("London", R.drawable.london));
        items.add(new Item("Denver", R.drawable.denver));
        items.add(new Item("Paris", R.drawable.paris));
        items.add(new Item("Oslo", R.drawable.oslo));
        items.add(new Item("Helsinki", R.drawable.helsinki));
        int ir = items.get(i).drawable;
        viewHolder.Pic.setImageResource(ir);
        viewHolder.myName.setText(entry);
        viewHolder.myName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView tv = (TextView) v;
                Toast.makeText(context, tv.getText(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(v.getContext(), ReservationForm.class);
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
