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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.gatech.cs2340.nonprofitdonationtracker.R;

/**
 * Creates an adapter for mapping a Donation Item to a viewable
 * and clickable list
 *
 * @author cking90
 *
 */
public class ItemRecyclerViewAdapter extends
                        RecyclerView.Adapter<ItemRecyclerViewAdapter.ViewHolder> {
    private static final String TAG = "ItemRecyclerViewAdapter";

    private final List<String> donationNames;
    private final List<String> donationValues;
    private final List<Date> donationDates;
    private final List<String> donationShortDescriptions;
    private final List<String> donationLongDescriptions;
    private final List<String> donationCategories;
    private final List<Integer> locationIDs;
    private final Context mContext;

    /**
     * Constructor for the adapter that takes in all the information
     * necessary to populate the recycler view.
     *
     * @param donationNames a list of the donation names to be displayed
     * @param donationValues a list of the donation values (prices) to be displayed
     * @param donationDates a list of the donation dates to be displayed
     * @param shortDescrips a list of short descriptions about the donation to be displayed
     * @param longDescrips a list of long descriptions about the donation to be displayed
     * @param donationCategories a list of donation categories to be displayed
     * @param locationID a list of a donation's location id to be displayed
     * @param context the context of the viewHolder that can later be used to
     *                display a location's image once such feature is implemented
     */
    public ItemRecyclerViewAdapter(List<String> donationNames,
                                   List<String> donationValues, List<Date> donationDates,
                                   List<String> shortDescrips, List<String> longDescrips,
                                   List<String> donationCategories,
                                   List<Integer> locationID, Context context) {
        this.donationNames = new ArrayList<>(donationNames);
        this.donationValues = new ArrayList<>(donationValues);
        this.donationDates = new ArrayList<>(donationDates);
        this.donationShortDescriptions = new ArrayList<>(shortDescrips);
        this.donationLongDescriptions = new ArrayList<>(longDescrips);
        this.donationCategories = new ArrayList<>(donationCategories);
        this.locationIDs = new ArrayList<>(locationID);
        this.mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_donation_item, parent, false);
        return new ItemRecyclerViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: called");

        Date date = donationDates.get(position);

        holder.donationName.setText(donationNames.get(position));
        holder.donationValue.setText(donationValues.get(position));
        holder.donationDate.setText(date.toString());
        holder.donationDescription.setText(donationShortDescriptions.get(position));
        holder.donationLongDescription = donationLongDescriptions.get(position);
        holder.donationCategory = donationCategories.get(position);
        holder.locationID = locationIDs.get(position);
    }

    @Override
    public int getItemCount() {
        return donationNames.size();
    }

    /**
     * Provides the holder for the layout_donation
     * used in the adapter
     *
     */
    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView donationName;
        private final TextView donationValue;
        private final TextView donationDate;
        private final TextView donationDescription;
        String donationLongDescription;
        String donationCategory;
        private final RelativeLayout parentLayout;
        int locationID;

        /**
         * References the layout_location_item
         * used to map individual elements in the recycler view
         *
         * Contains and onClick listener that brings the user
         * to the ViewSingleDonation Activity where they can view
         * more information about the item
         *
         * @param itemView the layout_location_item
         */
        ViewHolder(View itemView) {
            super(itemView);
            donationName = itemView.findViewById(R.id.donationNameTextView);
            donationValue = itemView.findViewById(R.id.donationValueTextView);
            donationDate = itemView.findViewById(R.id.donationDateTextView);
            donationDescription = itemView.findViewById(R.id.donationShortDescriptionTextView);
            parentLayout = itemView.findViewById(R.id.donationAdapterLayout);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                Intent intent = new Intent(view.getContext(), ViewSingleDonationActivity.class);
                intent.putExtra("donation_Date", donationDate.getText().toString());
                intent.putExtra("donation_Name", donationName.getText().toString());
                intent.putExtra("donation_Value", donationValue.getText().toString());
                intent.putExtra("donation_ShortDescription",
                        donationDescription.getText().toString());
                intent.putExtra("donation_LongDescription", donationLongDescription);
                intent.putExtra("donation_Category", donationCategory);
                intent.putExtra("location_id", locationID);
                view.getContext().startActivity(intent);
                }
            });

        }
    }
}

