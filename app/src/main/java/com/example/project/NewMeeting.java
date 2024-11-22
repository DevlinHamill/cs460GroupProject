package com.example.project;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.project.databinding.ActivityCalenderBinding;
import com.example.project.databinding.ActivityNewMeetingBinding;

public class NewMeeting extends AppCompatActivity {
    private @NonNull ActivityNewMeetingBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = com.example.project.databinding.ActivityNewMeetingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setListeners();
    }

    private void setListeners() {
        binding.backarrow.setOnClickListener(v ->
                finish()
        );

        binding.confirmMeetingButton.setOnClickListener(v -> {
            // TODO add logic for a new meeting in the database
            startActivity(new Intent(getApplicationContext(), calender.class));
        });
    }
}
