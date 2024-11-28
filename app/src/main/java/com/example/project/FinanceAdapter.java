package com.example.project;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
/**
 * @author BT&T
 * CS 460
 */
public class FinanceAdapter extends RecyclerView.Adapter<FinanceAdapter.FinanceViewHolder> {
    /**
     * stores all finance objects in one place
     */
    private final List<FinanceObj> financeList;
    /**
     * managing how financial data is presented in the RecyclerView
     */
    @NonNull
    @Override
    public FinanceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        /**
         * the recycle view item
         */
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_finance, parent, false);
        return new FinanceViewHolder(view);
    }

    /**
     * Binds the data to the
     * @param holder The ViewHolder which should be updated to represent the contents of the
     *        item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull FinanceViewHolder holder, int position) {
        /**
         * retrieves the current finance object based on the position
         */
        FinanceObj finance = financeList.get(position);
        holder.noteTextView.setText(finance.Note);
        holder.amountTextView.setText(finance.Amount);

        if(finance.Condition.equals("+")){
            holder.noteTextView.setTextColor(Color.GREEN);
            holder.amountTextView.setTextColor(Color.GREEN);

        }else{
            holder.noteTextView.setTextColor(Color.RED);
            holder.amountTextView.setTextColor(Color.RED);
        }


    }

    /**
     * retrieves the amount of finance objects
     * @return return a integer representing the amount of objects
     */
    @Override
    public int getItemCount() {

        return financeList.size();
    }

    /**
     * updates the list of all finance object with a pre existing list
     * @param financeList new finance object list
     */
    public FinanceAdapter(List<FinanceObj> financeList) {

        this.financeList = financeList;
    }

    /**
     * accesses the recycle view holder
     */
    static class FinanceViewHolder extends RecyclerView.ViewHolder {
        /**
         * keeps track of the note text view
         */
        public TextView noteTextView;
        /**
         * keeps track of the amount textview on the recycle view
         */
        public TextView amountTextView;
        /**
         * keeps track of the linear layout
         */
        public LinearLayout container;

        /**
         * refrences the ids of each gui component
         * @param view the recycle view data
         */
        public FinanceViewHolder(@NonNull View view) {

            super(view);
            container = view.findViewById(R.id.containerLayout);
            noteTextView = view.findViewById(R.id.noteTextView);
            amountTextView = view.findViewById(R.id.amountTextView);
        }

    }
}


