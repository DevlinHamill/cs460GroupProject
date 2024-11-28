package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.project.databinding.ActivityCalenderBinding;
import com.example.project.databinding.ActivityNewMeetingBinding;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.StringTokenizer;

public class NewMeeting extends AppCompatActivity {
    private @NonNull ActivityNewMeetingBinding binding;
    private String date;
    private String name;
    String time;
    String desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = com.example.project.databinding.ActivityNewMeetingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        date = getIntent().getStringExtra("SELECTED_DATE");
        setListeners();
    }

    private void setListeners() {
        binding.backarrow.setOnClickListener(v ->
                onBackPressed()
        );

        binding.confirmMeetingButton.setOnClickListener(v -> {
            // TODO add logic for a new meeting in the database (at a later date)
            if(isValidateMeetingDetails()) {
                name = binding.meetingTitle.getText().toString();
                desc = binding.meetingDescription.getText().toString();

                //get the time from the TimePicker
                int hour = binding.meetingTime.getHour(); // 24-hour format
                int minute = binding.meetingTime.getMinute();
                // Convert 24-hour format to 12-hour format with AM/PM
                String period = (hour >= 12) ? "PM" : "AM";
                // since hour is still in military time, we change it back with modulo math
                int hour12Format = (hour == 0 || hour == 12) ? 12 : hour % 12;
                time = hour + ":" + minute + " " + period;

                addMeetingToFirebase();

                onBackPressed();
            }
        });
    }

    private void showToast(String message){
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    private void addMeetingToFirebase(){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        HashMap<String, String> meetings = new HashMap<>();
        String newtime = convertTo12Hour(time);

        meetings.put("Meeting_Name", name);
        meetings.put("Meeting_Date", date);
        meetings.put("Meeting_Time", newtime);
        meetings.put("Meeting_Description", desc);

        db.collection("Meetings").add(meetings).addOnSuccessListener( documentReference -> {
            showToast("meeting added");
        }).addOnFailureListener(exception ->{
            showToast(exception.getMessage());
        });
    }

    private boolean isValidateMeetingDetails() {
        if (binding.meetingDescription.getText().toString().trim().isEmpty()){
            showToast("Please enter a description for the meeting");
            return false;
        }else if(binding.meetingTitle.getText().toString().trim().isEmpty()) {
            showToast("Please enter a meeting title");
            return false;
        }else{
            return true;
        }
    }

    private String convertTo12Hour(String time24){
        StringTokenizer tokenizer = new StringTokenizer(time24, ":");
        int hour = 0;


        hour = Integer.parseInt(tokenizer.nextToken());


        int hour12 = (hour == 0 || hour == 12) ? 12 : hour % 12;

        return hour12 +":"+ tokenizer.nextToken();

    }
}
