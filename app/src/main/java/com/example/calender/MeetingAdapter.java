package com.example.calender;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;


/*
public class MeetingAdapter extends RecyclerView.Adapter<MeetingAdapter.MeetingViewHolder>{
    private List<String> meetingList;

    // Constructor to set the data
    public MeetingAdapter(List<String> meetingList) {
        this.meetingList = meetingList;
    }

    @NonNull
    @Override
    public MeetingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_meeting, parent, false);
        //return new MeetingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MeetingViewHolder holder, int position) {
        // Set the string for each TextView
        holder.meetingTitle.setText(meetingList.get(position));
    }

    @Override
    public int getItemCount() {
        return meetingList.size();
    }

    // ViewHolder class to hold the TextView
    public static class MeetingViewHolder extends RecyclerView.ViewHolder {
        TextView meetingTitle;

        public MeetingViewHolder(@NonNull View itemView) {
            super(itemView);
            //meetingTitle = itemView.findViewById(R.id.meetingTitle);
        }
    }
}
 */