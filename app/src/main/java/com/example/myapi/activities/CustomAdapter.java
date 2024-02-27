package com.example.myapi.activities;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapi.R;
import com.example.myapi.models.States;
import com.example.myapi.services.DataServices;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    ArrayList<States> dataSet = DataServices.getArrState();;

    public CustomAdapter(ArrayList<States> dataSet) {
        this.dataSet = dataSet;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView nativeName;
        ImageView imageView;
        CustomAdapter adapter;

        public MyViewHolder(View itemView){
            super(itemView);

            name = itemView.findViewById(R.id.nameTextView);
            nativeName = itemView.findViewById(R.id.nativeTextView);
            imageView = itemView.findViewById(R.id.imageView);
            this.adapter = adapter;

        }
    }

    @NonNull
    @Override
    public CustomAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout,parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.MyViewHolder holder, int position) {
        TextView textViewName = holder.name;
        TextView textViewNativeName = holder.nativeName;
        ImageView imageView = holder.imageView;

        // Get the current state from the dataSet
        States state = dataSet.get(position);

        textViewName.setText(state.getName());
        textViewNativeName.setText(state.getNativeName());

        // Load flag image using Picasso
//        Picasso.get().load(state.getFlag().get("png")).into(imageView);

        // Parse flag URLs from the string
        String flagString = state.getFlag();
        String pngUrl = "";
        if (flagString != null && !flagString.isEmpty()) {
            String[] parts = flagString.split(",");
            for (String part : parts) {
                if (part.contains("png")) {
                    pngUrl = (part.split(":")[1] + ":" + part.split(":")[2]).trim();
                    // Remove curly braces and quotes
                    pngUrl = pngUrl.replace("{", "").replace("}", "").replace("\"", "").trim();
                    Log.d("result", pngUrl);
                    break; // No need to iterate further
                }
            }
        }

        // Load flag image using Picasso
        if (!pngUrl.isEmpty()) {
            Picasso.get().load(pngUrl).into(imageView);
        } else {
            // Handle case when pngUrl is empty or not found
            // For example, you can set a placeholder image
            imageView.setImageResource(R.drawable.cupcake);
        }
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}

