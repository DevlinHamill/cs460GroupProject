package com.example.testproject;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.testproject.databinding.ActivityFinanceBinding;

public class Finance extends AppCompatActivity {
    private ActivityFinanceBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
//        setContentView(R.layout.activity_finance);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
        binding = com.example.testproject.databinding.ActivityFinanceBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setListeners();
    }
    private void setListeners(){
        binding.backarrow.setOnClickListener(v ->
                finish()
        );

    }
}