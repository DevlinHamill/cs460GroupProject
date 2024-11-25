package com.example.testproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.testproject.databinding.ActivityCalenderBinding;
import com.example.testproject.databinding.ActivityNewMeetingBinding;

public class NewMeeting extends AppCompatActivity {
    private @NonNull ActivityNewMeetingBinding binding;
    private String date;
    private String name;
    String time;
    String desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = com.example.testproject.databinding.ActivityNewMeetingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        date = getIntent().getStringExtra("SELECTED_DATE");
        setListeners();
    }

    private void setListeners() {
        binding.backarrow.setOnClickListener(v ->
                finish()
        );

        binding.confirmMeetingButton.setOnClickListener(v -> {
            // TODO add logic for a new meeting in the database (at a later date)

            name = binding.meetingTitle.getText().toString();
            desc = binding.meetingDescription.getText().toString();

            //get the time from the TimePicker
            int hour = binding.meetingTime.getHour(); // 24-hour format
            int minute = binding.meetingTime.getMinute();
            // Convert 24-hour format to 12-hour format with AM/PM
            String period = (hour >= 12) ? "PM" : "AM";
            // since hour is still in military time, we change it back with modulo math
            int hour12Format = (hour == 0 || hour == 12) ? 12 : hour % 12;
            time = hour + ":" + minute + " " +period;



            //finish();
        });
    }

    private void showToast(String message){
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}
