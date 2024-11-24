package com.example.project;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project.databinding.ActivityCalenderBinding;

public class calender extends AppCompatActivity {
    private ActivityCalenderBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = com.example.project.databinding.ActivityCalenderBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setListeners();
    }

    private void setListeners() {
        binding.backarrow.setOnClickListener(v ->
                finish()
        );

       binding.addMeeting.setOnClickListener(v ->
                startActivity(new Intent(getApplicationContext(), NewMeeting.class))
       );
    }
}