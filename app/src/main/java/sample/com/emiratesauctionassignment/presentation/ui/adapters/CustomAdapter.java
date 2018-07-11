package sample.com.emiratesauctionassignment.presentation.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by elmalah on 7/7/2018.
 */

public class CustomAdapter extends RecyclerView.Adapter {
    ArrayList personNames;
    Context context;
    public CustomAdapter(Context context, ArrayList personNames) {
        this.context = context;
        this.personNames = personNames;
        //this.personImages = personImages;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // infalte the item Layout
        View v = LayoutInflater.from(parent.getContext()).inflate(sample.com.emiratesauctionassignment.R.layout.rowlayout, parent, false);
        // set the view's size, margins, paddings and layout parameters
        MyViewHolder vh = new MyViewHolder(v); // pass the view to View Holder
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        // set the data in items
//        holder.name.setText(personNames.get(position));
//        holder.image.setImageResource(personImages.get(position));
        // implement setOnClickListener event on item view.
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // display a toast with person name on item click
                //Toast.makeText(context, personNames.get(position), Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public int getItemCount() {
        return personNames.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        // init the item view's
        TextView name;
        ImageView image;
        public MyViewHolder(View itemView) {
            super(itemView);
            // get the reference of item view's
//            name = (TextView) itemView.findViewById(R.id.name);
//            image = (ImageView) itemView.findViewById(R.id.image);
        }
    }
}
