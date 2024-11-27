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
import com.example.project.adapters.MembersAdapter;
import com.example.project.databinding.ActivityCalenderBinding;
import com.example.project.listeners.MeetingListener;
import com.example.project.utilites.constant_meetings;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

public class calender extends AppCompatActivity implements MeetingListener {
    private ActivityCalenderBinding binding;
    private NewMeeting meet;
    private String selectedDate; // Variable to store the selected and formatted date
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCalenderBinding.inflate(getLayoutInflater());

        // Format the date to MM/DD/YYYY
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy", Locale.getDefault());
        selectedDate = sdf.format(new Date(binding.calendarView.getDate()));

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
        });
    }

    private void getMeeting(){
        loading(true);
        FirebaseFirestore database = FirebaseFirestore.getInstance();
        database.collection(constant_meetings.KEY_COLLECTION_MEETINGS).get()
                .addOnCompleteListener(task -> {
                    loading(false);
                    if(task.isSuccessful() && task.getResult() != null){
                        List<MeetingObj> meetings = new ArrayList<>();
                        for(QueryDocumentSnapshot queryDocumentSnapshot: task.getResult()){

                            MeetingObj currentmeeting = new MeetingObj();
                            currentmeeting.Name = queryDocumentSnapshot.getString(constant_meetings.KEY_M_NAME);
                            currentmeeting.Description = queryDocumentSnapshot.getString(constant_meetings.KEY_M_DESCRIPTION);
                            currentmeeting.Date = queryDocumentSnapshot.getString(constant_meetings.KEY_M_DATE);
                            currentmeeting.Time = queryDocumentSnapshot.getString(constant_meetings.KEY_M_TIME);

                            currentmeeting.id = queryDocumentSnapshot.getId();
                            //member.officerStatus = queryDocumentSnapshot.getString(Constants.KEY_OFFICER_STATUS);
                            meetings.add(currentmeeting);
                        }
                        if(meetings.size() > 0){
                            MeetingAdapter meetingAdapter = new MeetingAdapter(meetings, this);
                            binding.meetingRecyclerView.setAdapter(meetingAdapter);
                            binding.meetingRecyclerView.setVisibility(View.VISIBLE);
                        } else {
                            showErrorMessage();
                        }
                    } else {
                        showErrorMessage();
                    }
                });
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
    private void showErrorMessage() { // Implement error handling logic
        showToast("No income data found.");
        binding.meetingRecyclerView.setVisibility(View.GONE);
    }

    @Override
    public void onMeetingClicked(MeetingObj meeting) {

    }
}