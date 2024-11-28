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
    /**
     * binds the xml with the java
     */
    private @NonNull ActivityNewMeetingBinding binding;
    /**
     * the date of the meeting
     */
    private String date;
    /**
     * the name of the meeting
     */
    private String name;
    /**
     * the time of the meeting
     */
    private String time;
    /**
     * the description of the meeting
     */
    private String desc;

    /**
     * creates the application
     * @param savedInstanceState the current application instance
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = com.example.project.databinding.ActivityNewMeetingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        date = getIntent().getStringExtra("SELECTED_DATE");
        setListeners();
    }

    /**
     * sets listeners for buttons
     */
    private void setListeners() {
        binding.backarrow.setOnClickListener(v ->
                onBackPressed()
        );

        binding.confirmMeetingButton.setOnClickListener(v -> {

            if(isValidateMeetingDetails()) {
                name = binding.meetingTitle.getText().toString();
                desc = binding.meetingDescription.getText().toString();

                /**
                 * get the hours from the TimePicker
                 */
                int hour = binding.meetingTime.getHour(); // 24-hour format
                /**
                 * get the hours from the TimePicker
                 */
                int minute = binding.meetingTime.getMinute();
                // Convert 24-hour format to 12-hour format with AM/PM
                String period = (hour >= 12) ? "PM" : "AM";
                /**
                 *  since hour is still in military time, we change it back with modulo math
                 */
                int hour12Format = (hour == 0 || hour == 12) ? 12 : hour % 12;
                time = hour + ":" + minute + " " + period;

                addMeetingToFirebase();

                onBackPressed();
            }
        });
    }

    /**
     * displays the toast message
     * @param message string that needs to be displayed
     */
    private void showToast(String message){
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    /**
     * adds the meeting to the firebase
     */
    private void addMeetingToFirebase(){
        /**
         * contains the firebase refrence
         */
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        /**
         * stores all meeting data
         */
        HashMap<String, String> meetings = new HashMap<>();
        /**
         * updates the new time with the converter
         */
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

    /**
     * checks if the input is valid
     * @return a boolean value that tells if the input is valid
     */
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

    /**
     * converts military time to 12 hour based time
     * @param time24 takes in a
     * @return the new time
     */
    private String convertTo12Hour(String time24){
        /**
         * tokenizes the time string without the :
         */
        StringTokenizer tokenizer = new StringTokenizer(time24, ":");
        /**
         * stores the current hour based on the string
         */
        int hour = 0;
        hour = Integer.parseInt(tokenizer.nextToken());
        /**
         * converts the string to a 12 hour time
         */
        int hour12 = (hour == 0 || hour == 12) ? 12 : hour % 12;

        return hour12 +":"+ tokenizer.nextToken();
    }
}
