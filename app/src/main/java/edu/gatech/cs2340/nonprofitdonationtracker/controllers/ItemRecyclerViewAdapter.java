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

import edu.gatech.cs2340.nonprofitdonationtracker.R;

public class ItemRecyclerViewAdapter extends
                        RecyclerView.Adapter<ItemRecyclerViewAdapter.ViewHolder> {
    private static final String TAG = "ItemRecyclerViewAdapter";

    private ArrayList<String> donationNames = new ArrayList<>();
    private ArrayList<String> donationValues = new ArrayList<>();
    private ArrayList<Date> donationDates = new ArrayList<>();
    private ArrayList<String> donationShortDescriptions = new ArrayList<>();
    private ArrayList<String> donationLongDescriptions = new ArrayList<>();
    private ArrayList<String> donationCategories = new ArrayList<>();
    private int locationID;
    private Context mContext;

    public ItemRecyclerViewAdapter(ArrayList<String> donationNames, ArrayList<String> donationValues,
                                   ArrayList<Date> donationDates, ArrayList<String> shortDescrips,
                                   ArrayList<String> longDescrips, ArrayList<String> donationCategories,
                                   int locationID, Context context) {
        this.donationNames = donationNames;
        this.donationValues = donationValues;
        this.donationDates = donationDates;
        this.donationShortDescriptions = shortDescrips;
        this.donationLongDescriptions = longDescrips;
        this.donationCategories = donationCategories;
        this.locationID = locationID;
        this.mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.layout_donation_item, parent, false);
        ViewHolder viewHolder = new ItemRecyclerViewAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: called");
        holder.donationName.setText(donationNames.get(position));
        holder.donationValue.setText(donationValues.get(position));
        holder.donationDate.setText(donationDates.get(position).toString());
        holder.donationDescription.setText(donationShortDescriptions.get(position));
        holder.donationLongDescription = donationLongDescriptions.get(position);
        holder.donationCategory = donationCategories.get(position);
    }

    @Override
    public int getItemCount() {
        return donationNames.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView donationName;
        TextView donationValue;
        TextView donationDate;
        TextView donationDescription;
        String donationLongDescription;
        String donationCategory;
        RelativeLayout parentLayout;

        /**
         * References the layout_location_item
         * used to map individual elements in the recycler view
         * @param itemView the layout_location_item
         */
        public ViewHolder(View itemView) {
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
                intent.putExtra("donation_ShortDescription", donationDescription.getText().toString());
                intent.putExtra("donation_LongDescription", donationLongDescription);
                intent.putExtra("donation_Category", donationCategory);
                intent.putExtra("location_id", locationID);
                view.getContext().startActivity(intent);
                }
            });

        }
    }
}

