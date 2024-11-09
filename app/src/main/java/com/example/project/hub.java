package com.example.project;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.project.databinding.ActivityHubBinding;

public class hub extends AppCompatActivity {
    private ActivityHubBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
//        setContentView(R.layout.activity_hub);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
        binding = ActivityHubBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setListeners();
    }

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
}