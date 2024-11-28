package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
//import com.example.groupproject.databinding.ActivityIncomeBinding;
import com.example.project.databinding.ActivityFinanceBinding;
import com.example.project.databinding.ActivityOutcomeBinding;
import com.example.project.utilites.constant_finance;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class Outcome extends AppCompatActivity {
    /**
     * binds the xml to the java application
     */
    private ActivityOutcomeBinding binding;

    /**
     * creates the application
     * @param savedInstanceState the current application instance
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOutcomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setListener();
    }

    /**
     * sets the listeners for the application
     */
    private void setListener() {

        binding.buttonEnter.setOnClickListener(v -> {
            if (isValidIncomeData()) {
                outCome();
            }
        });

        binding.backbtn.setOnClickListener(v ->
                onBackPressed()
        );
    }

    /**
     * adds an expense to the application
     */
    private void outCome() { //store data in firebase
        loading(true);
        /**
         * refrences the firestore database
         */
        FirebaseFirestore database = FirebaseFirestore.getInstance();
        /**
         * stores the finance info based on inputs
         */
        HashMap<String, String> finance = new HashMap<>();
        finance.put(constant_finance.KEY_Note, binding.noteOutcome.getText().toString());
        finance.put(constant_finance.KEY_Amount, binding.amountOutcome.getText().toString());
        finance.put(constant_finance.KEY_CONDITION, "-");

        database.collection(constant_finance.KEY_COLLECTION_FINANCE)
                .add(finance)
                .addOnSuccessListener(documentReference -> {
                    loading(false);
                    //preferenceManager.setFinanceBoolean(constant_finance.KEY_ENTER, true);
                    //preferenceManager.setFinanceString(constant_finance.KEY_IncomeNote, binding.noteIncome.getText().toString());

                    Intent intent = new Intent(getApplicationContext(), Finance.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                })
                .addOnFailureListener(exception -> {
                    loading(false);
                    showToast(exception.getMessage());
                });
    }

    /**
     * sets the GUI visibility based on a if the application is loading
     * @param isLoading tells if the applicaiton is loading
     */
    private void loading(boolean isLoading) {
        if (isLoading) {
            binding.buttonEnter.setVisibility(View.INVISIBLE);
            binding.progressBar.setVisibility(View.VISIBLE);
        } else {
            binding.buttonEnter.setVisibility(View.VISIBLE);
            binding.progressBar.setVisibility(View.INVISIBLE);
        }
    }

    /**
     * checks if the inputs are valid
     * @return a boolean value that tell if the inputs are valid
     */
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

    /**
     * displays a toast message based on a input
     * @param message input that needs to be displayed
     */
    private void showToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}