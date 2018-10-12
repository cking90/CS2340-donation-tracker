package edu.gatech.cs2340.nonprofitdonationtracker.controllers;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

import edu.gatech.cs2340.nonprofitdonationtracker.R;

/**
 * Created by Charlie 10/11/18
 * Creates an adapter for the Location display list
 */
public class LocationRecyclerViewAdapter extends
                        RecyclerView.Adapter<LocationRecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "LocationRecyclerViewAdapter";

    private ArrayList<String> locationNames = new ArrayList<>();
    private ArrayList<String> locationAddresses = new ArrayList<>();
    private ArrayList<String> locationIDs = new ArrayList<>();
    private Context mContext;

    public LocationRecyclerViewAdapter(ArrayList<String> locationNames, ArrayList<String> locationAddresses,
                                       ArrayList<String> locationIDs, Context context) {
        this.locationNames = locationNames;
        this.locationAddresses = locationAddresses;
        this.locationIDs = locationIDs;
        this.mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.layout_location_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.locationName.setText(locationNames.get(position));

        holder.locationAddress.setText(locationAddresses.get(position));

        holder.locationID.setText(locationIDs.get(position));
        final int currentID = Integer.parseInt(holder.locationID.toString());
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), LoginActivity.class);
                intent.putExtra("location_id", currentID);
                view.getContext().startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return locationIDs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView locationName;
        TextView locationAddress;
        TextView locationID;
        RelativeLayout parentLayout;

        /**
         * References the layout_location_item
         * used to map individual elements in the recycler view
         * @param itemView the layout_location_item
         */
        public ViewHolder(View itemView) {
            super(itemView);
            locationName = itemView.findViewById(R.id.locationNameTextView);
            locationAddress = itemView.findViewById(R.id.locationAddressTextView);
            locationID = itemView.findViewById(R.id.locationIDTextVew);
            parentLayout = itemView.findViewById(R.id.locationAdapterLayout);

        }
    }
}