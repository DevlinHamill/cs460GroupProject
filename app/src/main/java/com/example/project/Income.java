package com.example.project;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.project.Finance;
import com.example.project.databinding.ActivityIncomeBinding;
import com.example.project.utilites.PreferenceManager;
import com.example.project.utilites.constant_finance;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Income extends AppCompatActivity {

    private ActivityIncomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityIncomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setListener();
    }

    private void setListener() {
        binding.buttonEnter.setOnClickListener(v -> {
            if (isValidIncomeData()) {
                inCome();
            }
        });
    }

    private void inCome() { //store data in firebase
        loading(true);
        FirebaseFirestore database = FirebaseFirestore.getInstance();
        HashMap<String, String> finance = new HashMap<>();
        String note = binding.noteIncome.getText().toString();
        String amount = binding.amountIncome.getText().toString();

        finance.put(constant_finance.KEY_Note, note);
        finance.put(constant_finance.KEY_Amount, amount);
        finance.put(constant_finance.KEY_CONDITION,"+");
        Log.d(TAG, "Adding Income data: Note = " + note + ", Amount = " + amount);
        database.collection(constant_finance.KEY_COLLECTION_FINANCE)
                .add(finance)
                .addOnSuccessListener(documentReference -> {
                    loading(false);
                    Log.d(TAG, "Income document added with ID: " + documentReference.getId());
                    Intent intent = new Intent(getApplicationContext(), Finance.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                })
                .addOnFailureListener(exception -> {
                    loading(false);
                    Log.w(TAG, "Error adding Income document", exception);
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
        if (binding.noteIncome.getText().toString().trim().isEmpty()) {
            showToast("Please put Note");
            return false;
        } else if (binding.amountIncome.getText().toString().trim().isEmpty()) {
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