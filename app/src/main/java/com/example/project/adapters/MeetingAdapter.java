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

    /**
     * holds all meeting meetings in one place
     */
    private final List<MeetingObj> meetingList;

    /**
     * constucter that connects the data from a previous call of the class
     * @param meetingList
     */
    public MeetingAdapter(List<MeetingObj> meetingList) {

        this.meetingList = meetingList;

    }

    /**
     * creates the recycle view holder
     * @param parent The ViewGroup into which the new View will be added after it is bound to
     *               an adapter position.
     * @param viewType The view type of the new View.
     *
     * @return the meeting view holder
     */
    @NonNull
    @Override
    public MeetingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemContainerMeetingBinding itemContainerMeetingBinding = ItemContainerMeetingBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new MeetingViewHolder(itemContainerMeetingBinding);
    }

    /**
     * binds the view gui components with specific pieces of data
     * @param holder The ViewHolder which should be updated to represent the contents of the
     *        item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull MeetingViewHolder holder, int position) {
        /**
         * has the current meeting being reviewed
         */
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

    /**
     * gets the amount of meetings in the list
     * @return a integer that represents the size of the meetingList
     */
    @Override
    public int getItemCount() {

        return meetingList.size();
    }

    /**
     * connects an item to the recycle view
     */
    class MeetingViewHolder extends RecyclerView.ViewHolder {
        /**
         * connects the item container xml to the java file
         */
        ItemContainerMeetingBinding binding;

        /**
         * retrieves the binding from said container
         * @param itemContainerMeetingBinding the xml refrence
         */
        public MeetingViewHolder(@NonNull ItemContainerMeetingBinding itemContainerMeetingBinding) {

            super(itemContainerMeetingBinding.getRoot());
            binding = itemContainerMeetingBinding;

        }

        /**
         * Updates the GUI information with the meeting object info
         * @param obj the object that contains all relevant meeting information
         */
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