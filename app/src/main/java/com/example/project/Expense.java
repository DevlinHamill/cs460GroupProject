package com.example.project;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
//import com.example.groupproject.databinding.ActivityIncomeBinding;
import com.example.project.databinding.ActivityFinanceBinding;
import com.example.project.databinding.ActivityOutcomeBinding;

public class Expense extends AppCompatActivity {
    private ActivityOutcomeBinding binding;
    private PreferenceManager preferenceManager;
    private boolean validateIncomeDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOutcomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setListener();
    }

    private void setListener() {
        binding.buttonEnter.setOnClickListener(v -> onBackPressed());
    }
    private void showToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}
