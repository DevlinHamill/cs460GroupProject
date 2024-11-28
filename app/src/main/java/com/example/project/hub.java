package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project.databinding.ActivityHubBinding;
import com.example.project.utilites.Constants;
import com.example.project.utilites.PreferenceManager;

public class hub extends AppCompatActivity {
    /**
     * stores the current user data
     */
    private PreferenceManager preferenceManager;
    /**
     * connects the current activity xml file with the java file
     */
    private ActivityHubBinding binding;

    /**
     * intializes the application
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preferenceManager = new PreferenceManager(getApplicationContext());
        binding = ActivityHubBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setListeners();
        setVis();
    }

    /**
     * sets listeners for any gui components
     */
    private void setListeners(){
        binding.calenderbtn.setOnClickListener(v ->
            startActivity(new Intent(getApplicationContext(), calender.class))
        );
        binding.financebtn.setOnClickListener(v ->
                startActivity(new Intent(getApplicationContext(), Finance.class))
        );
        binding.messagebtn.setOnClickListener(v ->
                startActivity(new Intent(getApplicationContext(), MembersList.class))
        );
        binding.profilebtn.setOnClickListener(v ->
                startActivity(new Intent(getApplicationContext(), Profile.class))
        );

    }

    /**
     * sets the visibility based on permissions
     */
    private void setVis(){
        /*User and officer*/
        if(preferenceManager.getString(Constants.KEY_PERMISSION).equals("1")
        || preferenceManager.getString(Constants.KEY_PERMISSION).equals("2")){
            binding.financebtn.setVisibility(View.INVISIBLE);

        /*Treasurer*/
        }else if(preferenceManager.getString(Constants.KEY_PERMISSION).equals("4")){
            binding.messagebtn.setVisibility(View.INVISIBLE);

        }
        /*presidents can access everything*/
    }
}