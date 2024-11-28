package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.project.databinding.ActivityIncomeBinding;
import com.example.project.utilites.PreferenceManager;
import com.example.project.utilites.constant_finance;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class Income extends AppCompatActivity {
    /**
     * connects the xml to the java file
     */
    private ActivityIncomeBinding binding;

    /**
     * creates the application
     * @param savedInstanceState contains the current application instance
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityIncomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setListener();
    }

    /**
     * sets listeners for specific buttons
     */
    private void setListener() {

        binding.buttonEnter.setOnClickListener(v -> {
            if (isValidIncomeData()) {
                inCome();
            }
        });
        binding.backbtn.setOnClickListener(v ->
                onBackPressed()
        );
    }

    /**
     * puts the current income into the firestore database
     */
    private void inCome() { //store data in firebase
        loading(true);
        /**
         * declares a firestore database object to add values to it
         */
        FirebaseFirestore database = FirebaseFirestore.getInstance();
        /**
         * contains all finance data to be stored
         */
        HashMap<String, String> finance = new HashMap<>();
        finance.put(constant_finance.KEY_Note, binding.noteIncome.getText().toString());
        finance.put(constant_finance.KEY_Amount, binding.amountIncome.getText().toString());
        finance.put(constant_finance.KEY_CONDITION, "+");

        database.collection(constant_finance.KEY_COLLECTION_FINANCE)
                .add(finance)
                .addOnSuccessListener(documentReference -> {
                    loading(false);
                    /**
                     * intializes a path to start a new activity
                     */
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
     * hides gui components depending on the applicaiton is loading.
     * @param isLoading tells if the application
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
     * checks if the data being inputed is valid
     * @return a boolean value to tell if it is valid
     */
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

    /**
     * displays a toast message based on a string input
     * @param message string message being displayed
     */
    private void showToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}