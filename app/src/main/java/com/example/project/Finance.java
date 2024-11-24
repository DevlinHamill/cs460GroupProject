package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.example.project.databinding.ActivityFinanceBinding;
import com.example.project.utilites.PreferenceManager;
import com.example.project.utilites.constant_finance;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class Finance extends AppCompatActivity implements View.OnClickListener{
    private ActivityFinanceBinding binding;
    private PreferenceManager preferenceManager;
    public String note,amount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityFinanceBinding.inflate(getLayoutInflater());
        //preferenceManager = new PreferenceManager(getApplicationContext());
        setContentView(binding.getRoot());
        setListeners();
        getIncome();
    }

    private void setListeners(){
        binding.backbtn.setOnClickListener(v ->
                finish()
        );
        binding.ButtonAdding.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), Income.class);
            startActivity(intent); });
        binding.buttonExpense.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), Outcome.class);
            startActivity(intent); });
    }
    private void getIncome() {
        loading(true);
        FirebaseFirestore database = FirebaseFirestore.getInstance();
        database.collection(constant_finance.KEY_COLLECTION_FINANCE).get()
                .addOnCompleteListener(task -> {
                    loading(false);
                    if (task.isSuccessful() && task.getResult() != null) {
                        List<Finance> incomes = new ArrayList<>();
                        for (QueryDocumentSnapshot queryDocumentSnapshot : task.getResult()) {
                            Finance income = new Finance();
                            income.note = queryDocumentSnapshot.getString(constant_finance.KEY_Note);
                            income.amount = queryDocumentSnapshot.getString(constant_finance.KEY_Amount);
                            incomes.add(income);
                        }
                        if (incomes.size() > 0) {
                            FinanceAdapter incomeAdapter = new FinanceAdapter(incomes);
                            binding.financeRecyclerView.setAdapter(incomeAdapter);
                            binding.financeRecyclerView.setVisibility(View.VISIBLE);
                        }
                        else {
                            showErrorMessage();
                        }
                    } else {
                        showErrorMessage();
                    }
                });
    }
    private void loading(boolean isLoading) {
        if (isLoading) {
            binding.financeRecyclerView.setVisibility(View.INVISIBLE);
            binding.progressBar.setVisibility(View.VISIBLE);
        }
        else {
            binding.financeRecyclerView.setVisibility(View.VISIBLE);
            binding.progressBar.setVisibility(View.INVISIBLE); }
    }
    private void showErrorMessage() { // Implement error handling logic
        showToast("No income data found.");
        binding.financeRecyclerView.setVisibility(View.GONE);
    }
    private void showToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show(); }
    @Override
    public void onClick(View v) {
        MaterialButton button = (MaterialButton) v;
        String buttonText = button.getText().toString();
        // String dataToCalculate = Outcome.getText().toString();
    }
}
