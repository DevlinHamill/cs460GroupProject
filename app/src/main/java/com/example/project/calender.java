package com.example.project;

import android.content.Intent;
import android.os.Bundle;

<<<<<<< HEAD
import androidx.appcompat.app.AppCompatActivity;

import com.example.project.databinding.ActivityCalenderBinding;
=======
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.project.databinding.ActivityCalenderBinding;
import com.example.project.databinding.ActivityHubBinding;
>>>>>>> 05a893865e156dc3b8a6615755e6c60f1ccfc6fa

public class calender extends AppCompatActivity {
    private ActivityCalenderBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
<<<<<<< HEAD

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
=======
//        EdgeToEdge.enable(this);
//        setContentView(R.layout.activity_calender);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
        binding = ActivityCalenderBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setListeners();
    }
    private void setListeners(){
        binding.backbtn.setOnClickListener(v ->
                finish()
        );

>>>>>>> 05a893865e156dc3b8a6615755e6c60f1ccfc6fa
    }
}