package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.project.databinding.ActivityFinanceBinding;
import com.google.android.material.button.MaterialButton;

public class Finance extends AppCompatActivity implements View.OnClickListener{
    private ActivityFinanceBinding binding;
    TextView Income, Expense, result_display;
            //MaterialButton buttonAdding, buttonRemove;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                EdgeToEdge.enable(this);
                binding = ActivityFinanceBinding.inflate(getLayoutInflater());

                setContentView(binding.getRoot());
                /*
                ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
                    Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                    v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                    return insets; });
                result_display = findViewById(R.id.result_display);
                Expense = findViewById(R.id.remExp);
                Income = findViewById(R.id.addInc);
                //buttonAdding = findViewById(R.id.ButtonAdding);
                //buttonRemove = findViewById(R.id.buttonRemove);
                */
                setListeners();
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
    @Override public void onClick(View v) {
        MaterialButton button = (MaterialButton) v;
        String buttonText = button.getText().toString();
        String dataToCalculate = Expense.getText().toString();
// String dataToCalculate = Income.getText().toString();
    }
}