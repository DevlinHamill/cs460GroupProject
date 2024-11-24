package com.example.project;
import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.project.utilites.PreferenceManager;
import com.example.project.databinding.ActivityOutcomeBinding;
import com.example.project.utilites.constant_finance;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class Outcome extends AppCompatActivity {
    private ActivityOutcomeBinding binding;
    private PreferenceManager preferenceManager;
    private boolean validateIncomeDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOutcomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        preferenceManager = new PreferenceManager(getApplicationContext());
        setListener();
    }

    private void setListener() {
        binding.buttonEnter.setOnClickListener(v -> {
            if (isValidIncomeData()) {
                outCome();
            }
        });
        //binding.buttonEnter.setOnClickListener(v -> onBackPressed());
    }
    private void outCome() {
        loading(true);
        FirebaseFirestore database = FirebaseFirestore.getInstance();
        HashMap<String, String> finance = new HashMap<>();
        finance.put(constant_finance.KEY_OutcomeNote, binding.noteOutcome.getText().toString());
        finance.put(constant_finance.KEY_OutcomeAmount, binding.amountOutcome.getText().toString());
        database.collection(constant_finance.KEY_COLLECTION_FINANCE)
                .add(finance)
                .addOnSuccessListener(documentReference -> {
                    loading(false);
                    Log.d(TAG, "Outcome document added with ID: " + documentReference.getId());
                    preferenceManager.setFinanceBoolean(constant_finance.KEY_ENTER, true);
                    preferenceManager.setFinanceString(constant_finance.KEY_IncomeNote, binding.noteOutcome.getText().toString());
                    Intent intent = new Intent(getApplicationContext(), Finance.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                })
                .addOnFailureListener(exception -> {
                    loading(false);
                    Log.w(TAG, "Error adding outcome document", exception);
                    showToast(exception.getMessage());
                });
    }
    private void loading(boolean isLoading) {
        if (isLoading) {
            binding.buttonEnter.setVisibility(View.INVISIBLE);
            binding.progressBar.setVisibility(View.VISIBLE);
        } else {
            binding.buttonEnter.setVisibility(View.VISIBLE);
            binding.progressBar.setVisibility(View.INVISIBLE);
        }
    }
    private boolean isValidIncomeData() {
        if (binding.noteOutcome.getText().toString().trim().isEmpty()) {
            showToast("Please put Note");
            return false;
        } else if (binding.amountOutcome.getText().toString().trim().isEmpty()) {
            showToast("Please Enter the Amount $ ");
            return false;
        } else {
            return true;
        }
    }
    private void showToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}
