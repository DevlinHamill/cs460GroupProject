package com.example.project.adapters;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project.MeetingObj;
import com.example.project.databinding.ItemContainerMeetingBinding;

import java.util.List;

public class MeetingAdapter extends RecyclerView.Adapter<MeetingAdapter.MeetingViewHolder> {


    private final List<MeetingObj> meetingList;

    public MeetingAdapter(List<MeetingObj> meetingList) {

        this.meetingList = meetingList;

    }

    @NonNull
    @Override
    public MeetingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemContainerMeetingBinding itemContainerMeetingBinding = ItemContainerMeetingBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new MeetingViewHolder(itemContainerMeetingBinding);
    }

    /**
     *
     * @param holder The ViewHolder which should be updated to represent the contents of the
     *        item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull MeetingViewHolder holder, int position) {
        MeetingObj meeting = meetingList.get(position);
        holder.setMeetingData(meetingList.get(position));

        holder.itemView.setBackgroundColor(
                meeting.isSelected ? Color.GRAY : Color.WHITE
        );

        holder.itemView.setOnClickListener(v -> {
            meeting.isSelected = !meeting.isSelected;
            notifyItemChanged(position);
        });

    }

    @Override
    public int getItemCount() {

        return meetingList.size();
    }


    class MeetingViewHolder extends RecyclerView.ViewHolder {
        ItemContainerMeetingBinding binding;

        public MeetingViewHolder(@NonNull ItemContainerMeetingBinding itemContainerMeetingBinding) {

            super(itemContainerMeetingBinding.getRoot());
            binding = itemContainerMeetingBinding;

        }


        void setMeetingData(MeetingObj obj){


            binding.NameTextView.setText(obj.Name);
            binding.DescriptionTextView.setText(obj.Name);
            binding.DateTextView.setText(obj.Date);
            binding.TimeTextView.setText(obj.Time);


            if(obj.isSelected){
                binding.containerLayout.setBackgroundColor(Color.GRAY);
            }else{
                binding.containerLayout.setBackgroundColor(Color.WHITE);
            }

        }

    }
}