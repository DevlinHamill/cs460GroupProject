package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.example.project.adapters.MeetingAdapter;
import com.example.project.databinding.ActivityCalenderBinding;
import com.example.project.utilites.constant_meetings;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

public class calender extends AppCompatActivity{
    private ActivityCalenderBinding binding;
    public boolean calenderSelected;
    private NewMeeting meet;
    public List<MeetingObj> meetings;
    private String selectedDate; // Variable to store the selected and formatted date
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCalenderBinding.inflate(getLayoutInflater());

        // Format the date to MM/DD/YYYY
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy", Locale.getDefault());
        selectedDate = sdf.format(new Date(binding.calendarView.getDate()));
        calenderSelected = false;
        setContentView(binding.getRoot());
        setListeners();
        getMeeting();
    }

    private void setListeners() {

        binding.backarrow.setOnClickListener(v -> onBackPressed());

        binding.addMeeting.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), NewMeeting.class);
            intent.putExtra("SELECTED_DATE", selectedDate); // Add the date as an extra
            startActivity(intent);
        });

        // Listener for CalendarView date changes with a formatted MM/dd/yyyy
        binding.calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            selectedDate = String.format(Locale.getDefault(),
                    "%02d/%02d/%d", month + 1, dayOfMonth, year);
            calenderSelected = true;
            getMeeting();
        });
        binding.removeMeeting.setOnClickListener(v-> {
            removeData();
        });
    }

    private void getMeeting(){
        if(!calenderSelected) {
            loading(true);
            FirebaseFirestore database = FirebaseFirestore.getInstance();
            database.collection(constant_meetings.KEY_COLLECTION_MEETINGS).get()
                    .addOnCompleteListener(task -> {
                        loading(false);
                        if (task.isSuccessful() && task.getResult() != null) {
                            meetings = new ArrayList<>();
                            for (QueryDocumentSnapshot queryDocumentSnapshot : task.getResult()) {

                                MeetingObj currentmeeting = new MeetingObj();
                                currentmeeting.Name = queryDocumentSnapshot.getString(constant_meetings.KEY_M_NAME);
                                currentmeeting.Description = queryDocumentSnapshot.getString(constant_meetings.KEY_M_DESCRIPTION);
                                currentmeeting.Date = queryDocumentSnapshot.getString(constant_meetings.KEY_M_DATE);
                                currentmeeting.Time = queryDocumentSnapshot.getString(constant_meetings.KEY_M_TIME);
                                currentmeeting.isSelected = false;
                                currentmeeting.id = queryDocumentSnapshot.getId();

                                meetings.add(currentmeeting);
                            }
                            if (meetings.size() > 0) {
                                MeetingAdapter meetingAdapter = new MeetingAdapter(meetings);
                                binding.meetingRecyclerView.setAdapter(meetingAdapter);
                                binding.meetingRecyclerView.setVisibility(View.VISIBLE);
                            } else {
                                showErrorMessage();
                            }
                        } else {
                            showErrorMessage();
                        }
                    });
        }else{
            loading(true );
            FirebaseFirestore database = FirebaseFirestore.getInstance();
            database.collection(constant_meetings.KEY_COLLECTION_MEETINGS)
                    .whereEqualTo(constant_meetings.KEY_M_DATE, selectedDate)
                    .get()
                    .addOnCompleteListener(task -> {
                        loading(false);
                        if (task.isSuccessful() && task.getResult() != null) {
                            meetings = new ArrayList<>();
                            for (QueryDocumentSnapshot queryDocumentSnapshot : task.getResult()) {

                                MeetingObj currentmeeting = new MeetingObj();
                                currentmeeting.Name = queryDocumentSnapshot.getString(constant_meetings.KEY_M_NAME);
                                currentmeeting.Description = queryDocumentSnapshot.getString(constant_meetings.KEY_M_DESCRIPTION);
                                currentmeeting.Date = queryDocumentSnapshot.getString(constant_meetings.KEY_M_DATE);
                                currentmeeting.Time = queryDocumentSnapshot.getString(constant_meetings.KEY_M_TIME);
                                currentmeeting.isSelected = false;
                                currentmeeting.id = queryDocumentSnapshot.getId();

                                meetings.add(currentmeeting);
                            }
                            if (meetings.size() > 0) {
                                MeetingAdapter meetingAdapter = new MeetingAdapter(meetings);
                                binding.meetingRecyclerView.setAdapter(meetingAdapter);
                                binding.meetingRecyclerView.setVisibility(View.VISIBLE);
                            } else {
                                showToast("No meetings schedualed on the date");
                                calenderSelected = false;
                                getMeeting();
                            }
                        } else {
                            showErrorMessage();
                        }
                    });
        }
    }

    private void showToast(String message){
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
    private void loading(boolean isLoading) {
        if (isLoading) {
            binding.meetingRecyclerView.setVisibility(View.INVISIBLE);

        }
        else {
            binding.meetingRecyclerView.setVisibility(View.VISIBLE);
        }
    }
    private void showErrorMessage() {
        showToast("No Meeting Schedualed!");
        binding.meetingRecyclerView.setVisibility(View.GONE);
    }

    private void removeData(){
        FirebaseFirestore database = FirebaseFirestore.getInstance();
        for(MeetingObj meeting: meetings ){
            if(meeting.isSelected){
                database.collection(constant_meetings.KEY_COLLECTION_MEETINGS)
                        .document(meeting.id)
                        .delete()
                        .addOnCompleteListener(task -> {
                            showToast("Removed meeting(s)");
                            meetings.remove(meeting);
                        })
                        .addOnFailureListener(e ->{
                            showToast("Failed to remove meeting(s)");
                        });
            }
        }

        getMeeting();

    }

}