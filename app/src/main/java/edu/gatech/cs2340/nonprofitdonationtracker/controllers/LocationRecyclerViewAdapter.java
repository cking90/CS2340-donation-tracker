package edu.gatech.cs2340.nonprofitdonationtracker.controllers;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import edu.gatech.cs2340.nonprofitdonationtracker.R;

/**
 * Creates an adapter for the Location display list
 *
 * @author cking90
 */
public class LocationRecyclerViewAdapter extends
                        RecyclerView.Adapter<LocationRecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "RecyclerViewAdapter";

    private final List<String> locationNames;
    private final List<String> locationAddresses;
    private final List<String> locationIDs;
    private final String userType;
    private final Context mContext;

    /**
     * Creates an adapter to display the passed in lists of information. The lists
     * should have Location information mapped to the same index
     *
     * @param locationNames list of the location's name to be displayed
     * @param locationAddresses list of the location's addresses to be displayed
     * @param locationIDs list of the location's id to be displayed
     * @param userType String representing the user type of the person who clicked on the
     *                 location. Now deprecated as the current user is stored in the model
     * @param context of the activity in which the adapter is being used. Currently not utilized,
     *                but implemented to provide a framework for adding photo functionality if
     *                needed.
     */
    public LocationRecyclerViewAdapter(List<String> locationNames,
                                       List<String> locationAddresses,
                                       List<String> locationIDs, String userType,
                                       Context context) {
        this.locationNames = locationNames;
        this.locationAddresses = locationAddresses;
        this.locationIDs = locationIDs;
        this.userType = userType;
        this.mContext = context;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.layout_location_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: called");
        holder.locationName.setText(locationNames.get(position));

        holder.locationAddress.setText(locationAddresses.get(position));
        holder.locationID.setText(locationIDs.get(position));


    }

    @Override
    public int getItemCount() {
        return locationIDs.size();
    }

    /**
     * Represents each individual item that is mapped to the list by the
     * adapter. A view holder is created for each item that is displayed
     * within the list.
     */
    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView locationName;
        private final TextView locationAddress;
        private final TextView locationID;
        private final RelativeLayout parentLayout;

        /**
         * References the layout_location_item
         * used to map individual elements in the recycler view
         *
         * Contains an onClickListener that brings the User to the
         * ViewSingleLocation Activity, where information about the
         * location and its donations is accessible.
         *
         * @param itemView the layout_location_item
         */
        public ViewHolder(View itemView) {
            super(itemView);
            locationName = itemView.findViewById(R.id.locationNameTextView);
            locationAddress = itemView.findViewById(R.id.locationAddressTextView);
            locationID = itemView.findViewById(R.id.locationIDTextView);
            parentLayout = itemView.findViewById(R.id.locationAdapterLayout);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), ViewSingleLocationActivity.class);
                    intent.putExtra("location_id",
                            Integer.parseInt(locationID.getText().toString()));
                    intent.putExtra("user_type", userType);
                    view.getContext().startActivity(intent);
                }
            });

        }
    }
}
