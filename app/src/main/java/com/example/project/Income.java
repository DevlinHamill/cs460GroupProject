package com.example.project;
<<<<<<< HEAD
import android.content.Intent;
import android.os.Bundle;
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
    private PreferenceManager preferenceManager;
=======

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
//import com.example.groupproject.databinding.ActivityIncomeBinding;
import com.example.project.databinding.ActivityFinanceBinding;
import com.example.project.databinding.ActivityIncomeBinding;

public class Income extends AppCompatActivity {
    private ActivityIncomeBinding binding;
    private PreferenceManager preferenceManager;
    private boolean validateIncomeDetails;
>>>>>>> 05a893865e156dc3b8a6615755e6c60f1ccfc6fa

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityIncomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
<<<<<<< HEAD
        preferenceManager = new PreferenceManager(getApplicationContext());
=======

>>>>>>> 05a893865e156dc3b8a6615755e6c60f1ccfc6fa
        setListener();
    }

    private void setListener() {
<<<<<<< HEAD
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
        finance.put(constant_finance.KEY_IncomeNote, binding.noteIncome.getText().toString());
        finance.put(constant_finance.KEY_IncomeAmount, binding.amountIncome.getText().toString());

        database.collection(constant_finance.KEY_COLLECTION_FINANCE)
                .add(finance)
                .addOnSuccessListener(documentReference -> {
                    loading(false);
                    preferenceManager.setFinanceBoolean(constant_finance.KEY_ENTER, true);
                    preferenceManager.setFinanceString(constant_finance.KEY_IncomeNote, binding.noteIncome.getText().toString());

                    Intent intent = new Intent(getApplicationContext(), Finance.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                })
                .addOnFailureListener(exception -> {
                    loading(false);
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
=======

        binding.buttonEnter.setOnClickListener(v -> onBackPressed());
    }
    private void showToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

}
>>>>>>> 05a893865e156dc3b8a6615755e6c60f1ccfc6fa
