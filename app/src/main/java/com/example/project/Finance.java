package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.project.databinding.ActivityFinanceBinding;
import com.example.project.utilites.PreferenceManager;
import com.example.project.utilites.constant_finance;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class Finance extends AppCompatActivity{
    /**
     * attaches the xml to the java doc
     */
    private ActivityFinanceBinding binding;
    /**
     * stores the finance note as a global object
     */
    public String note;
    /**
     * allows for the total to be calculated acrossed the entire application
     */
    public double total;

    /**
     * Creates the application
     * @param savedInstanceState stores the current application
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityFinanceBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setListeners();
        getIncome();
    }

    /**
     * sets the listeners for certain GUI components
     */
    private void setListeners(){
        binding.backbtn.setOnClickListener(v ->
                onBackPressed()
        );
        binding.ButtonAdding.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), Income.class);
            startActivity(intent); });
        binding.buttonExpense.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), Outcome.class);
            startActivity(intent); });
    }

    /**
     * retrieves income data from the firebase
     */
    private void getIncome() {
        loading(true);
        /**
         * creates a firebase object to manipulate the firebase
         */
        FirebaseFirestore database = FirebaseFirestore.getInstance();
        database.collection(constant_finance.KEY_COLLECTION_FINANCE).get()
                .addOnCompleteListener(task -> {
                    loading(false);
                    if (task.isSuccessful() && task.getResult() != null) {
                        /**
                         * contains all finance object
                         */
                        List<FinanceObj> incomes = new ArrayList<>();
                        for (QueryDocumentSnapshot queryDocumentSnapshot : task.getResult()) {
                            /**
                             * contains the current finance object being viewed
                             */
                            FinanceObj currentStatement = new FinanceObj();
                            currentStatement.Note = queryDocumentSnapshot.getString(constant_finance.KEY_Note);
                            currentStatement.Amount = queryDocumentSnapshot.getString(constant_finance.KEY_Amount);
                            currentStatement.Condition = queryDocumentSnapshot.getString(constant_finance.KEY_CONDITION);
                            if(currentStatement.Condition != null) {
                                if (currentStatement.Condition.equals("+")) {
                                    total += Double.parseDouble(currentStatement.Amount);
                                } else {
                                    total -= Double.parseDouble(currentStatement.Amount);
                                }
                            }

                            incomes.add(currentStatement);

                        }
                        if (incomes.size() > 0) {
                            /**
                             * holds the finance adapter info to access the recycle view
                             */
                            FinanceAdapter incomeAdapter = new FinanceAdapter(incomes);

                            binding.financeRecyclerView.setAdapter(incomeAdapter);
                            binding.financeRecyclerView.setVisibility(View.VISIBLE);

                            binding.resultDisplay.setHint(""+total);
                        }
                        else {
                            showErrorMessage();
                        }
                    } else {
                        showErrorMessage();
                    }
                });
    }

    /**
     * hides and shows specific components depending on if the data is loading
     * @param isLoading tells if the connection is still loading for the firebase
     */
    private void loading(boolean isLoading) {
        if (isLoading) {
            binding.financeRecyclerView.setVisibility(View.INVISIBLE);
            binding.progressBar.setVisibility(View.VISIBLE);
        }
        else {
            binding.financeRecyclerView.setVisibility(View.VISIBLE);
            binding.progressBar.setVisibility(View.INVISIBLE); }
    }

    /**
     * display an error message
     */
    private void showErrorMessage() {
        showToast("No income data found.");
        binding.financeRecyclerView.setVisibility(View.GONE);
    }

    /**
     * Displays a toast depending on a input string
     * @param message the string that needs to be displayed
     */
    private void showToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }


}