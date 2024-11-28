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
import com.example.project.utilites.Constants;
import com.example.project.utilites.PreferenceManager;
import com.example.project.utilites.constant_meetings;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

public class calender extends AppCompatActivity{
    /**
     * references an object that keeps track of the current users data
     */
    private PreferenceManager preferenceManager;
    /**
     * binds the calender xml to the java file
     */
    private ActivityCalenderBinding binding;
    /**
     * will be used to keep track if a date has been selected
     */
    public boolean calenderSelected;

    /**
     * store all meeting info within the class
     */
    public List<MeetingObj> meetings;
    /**
     * stores the date being selected
     */
    private String selectedDate;

    /**
     * creates the application accessing the current activity and methods
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCalenderBinding.inflate(getLayoutInflater());
        preferenceManager = new PreferenceManager(getApplicationContext());

        if(preferenceManager.getString(Constants.KEY_PERMISSION).equals("1")){
            binding.removeMeeting.setVisibility(View.INVISIBLE);
            binding.addMeeting.setVisibility(View.INVISIBLE);
        }

        /**
         * formats the selected date to be compared with the firebase
         */
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy", Locale.getDefault());
        selectedDate = sdf.format(new Date(binding.calendarView.getDate()));
        calenderSelected = false;
        setContentView(binding.getRoot());
        setListeners();
        getMeeting();
    }

    /**
     * sets the button listeners for all buttons that exist on the binding
     */
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

    /**
     * retrieves the meeting info from the firebase
     */
    private void getMeeting(){
        /*
         * if a calender is not selected or no data is displayed then this version is evalutated
         */
        if(!calenderSelected) {
            loading(true);
            /**
             * stors the firebase refrence as a object
             */
            FirebaseFirestore database = FirebaseFirestore.getInstance();
            database.collection(constant_meetings.KEY_COLLECTION_MEETINGS).get()
                    .addOnCompleteListener(task -> {
                        loading(false);
                        if (task.isSuccessful() && task.getResult() != null) {
                            meetings = new ArrayList<>();
                            for (QueryDocumentSnapshot queryDocumentSnapshot : task.getResult()) {
                                /**
                                 * creates a meeting object to store all info
                                 */
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

        /*
            if the current date does have info then only that specific data is displayed and set
         */
        }else{
            loading(true );
            /**
             * Stores the firebase refrence as an object
             */
            FirebaseFirestore database = FirebaseFirestore.getInstance();
            database.collection(constant_meetings.KEY_COLLECTION_MEETINGS)
                    .whereEqualTo(constant_meetings.KEY_M_DATE, selectedDate)
                    .get()
                    .addOnCompleteListener(task -> {
                        loading(false);
                        if (task.isSuccessful() && task.getResult() != null) {
                            meetings = new ArrayList<>();
                            for (QueryDocumentSnapshot queryDocumentSnapshot : task.getResult()) {
                                /**
                                 * stores all info for the current meeting
                                 */
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

    /**
     * displays a toast message depending on a given string
     * @param message string that needs to be displayed
     */
    private void showToast(String message){
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    /**
     * changes the visibility of the recycle view depending on if something is loading
     * @param isLoading a boolean condition that will tell if the recycle view should be visible or not
     */
    private void loading(boolean isLoading) {
        if (isLoading) {
            binding.meetingRecyclerView.setVisibility(View.INVISIBLE);

        }
        else {
            binding.meetingRecyclerView.setVisibility(View.VISIBLE);
        }
    }

    /**
     * displays an error message
     */
    private void showErrorMessage() {
        showToast("No Meeting Schedualed!");
        binding.meetingRecyclerView.setVisibility(View.GONE);
    }

    /**
     * removes data from the firebase
     */
    private void removeData(){
        /**
         * creates a firebase object to manipulate data on said firebase
         */
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