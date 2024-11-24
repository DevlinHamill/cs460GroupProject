package com.example.project;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FinanceAdapter extends RecyclerView.Adapter<FinanceAdapter.FinanceViewHolder> {
    private List<Finance> financeList;
/*
managing how financial data is presented in the RecyclerView
 */
    public FinanceAdapter(List<Finance> financeList) {

        this.financeList = financeList;
    }

    public FinanceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_finance, parent, false);
        return new FinanceViewHolder(view);
    }

    public void onBindViewHolder(FinanceViewHolder holder, int position) {
        Finance finance = financeList.get(position);
        holder.noteTextView.setText(finance.note);
        holder.amountTextView.setText(finance.amount);
    }

    @Override
    public int getItemCount() {

        return financeList.size();
    }

    public class FinanceViewHolder extends RecyclerView.ViewHolder {
        public TextView noteTextView, amountTextView;

        public FinanceViewHolder(View view) {

            super(view);

            noteTextView = view.findViewById(R.id.noteTextView);
            amountTextView = view.findViewById(R.id.amountTextView);
        }
    }
}


