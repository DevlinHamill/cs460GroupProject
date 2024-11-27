package com.example.project.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project.listeners.MeetingListener;
import com.example.project.MeetingObj;
import com.example.project.databinding.ItemContainerMeetingBinding;
import com.example.project.listeners.MeetingListener;

import java.util.List;

public class MeetingAdapter extends RecyclerView.Adapter<MeetingAdapter.MeetingViewHolder> {


    private final List<MeetingObj> meetingList;

    private final MeetingListener meetingListener;


    public MeetingAdapter(List<MeetingObj> meetingList, MeetingListener meetingListener) {

        this.meetingList = meetingList;
        this.meetingListener = meetingListener;
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

        holder.setMeetingData(meetingList.get(position));

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
            binding.TimeTextView.setText(obj.Time);
            binding.getRoot().setOnClickListener(v -> meetingListener.onMeetingClicked(obj));

        }

    }
}


