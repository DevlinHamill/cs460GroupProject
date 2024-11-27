package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.example.project.databinding.ActivityCalenderBinding;

public class calender extends AppCompatActivity {
    private ActivityCalenderBinding binding;
    private NewMeeting meet;
    private String selectedDate; // Variable to store the selected and formatted date
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = com.example.project.databinding.ActivityCalenderBinding.inflate(getLayoutInflater());

        // Format the date to MM/DD/YYYY
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy", Locale.getDefault());
        selectedDate = sdf.format(new Date(binding.calendarView.getDate()));

        setContentView(binding.getRoot());
        setListeners();
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

    private void showToast(String message){
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}