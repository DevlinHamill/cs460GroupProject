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

public class FinanceAdapter extends RecyclerView.Adapter<FinanceAdapter.FinanceViewHolder> {
    private final List<FinanceObj> financeList;
/*
managing how financial data is presented in the RecyclerView
 */
    @NonNull
    @Override
    public FinanceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_finance, parent, false);
        return new FinanceViewHolder(view);
    }

    /**
     *
     * @param holder The ViewHolder which should be updated to represent the contents of the
     *        item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull FinanceViewHolder holder, int position) {

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

    @Override
    public int getItemCount() {

        return financeList.size();
    }

    public FinanceAdapter(List<FinanceObj> financeList) {

        this.financeList = financeList;
    }

    static class FinanceViewHolder extends RecyclerView.ViewHolder {
        public TextView noteTextView, amountTextView;
        public LinearLayout container;

        public FinanceViewHolder(@NonNull View view) {

            super(view);
            container = view.findViewById(R.id.containerLayout);
            noteTextView = view.findViewById(R.id.noteTextView);
            amountTextView = view.findViewById(R.id.amountTextView);
        }


        void bindMembers(final FinanceObj obj){
            //I will add more things to be binded later
            noteTextView.setText(obj.Note);
            amountTextView.setText(obj.Amount);

        }

    }
}


