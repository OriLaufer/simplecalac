package com.example.simplecalculator;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Apply window insets for edge-to-edge display
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (view, windowInsets) -> {
            final android.graphics.Insets systemBars = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars()).toPlatformInsets();
            view.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return windowInsets;
        });
    }

    public void onBtnClicked(View view) {
        EditText num1EditText = findViewById(R.id.Num1);
        EditText num2EditText = findViewById(R.id.Num2);

        String num1Text = num1EditText.getText().toString();
        String num2Text = num2EditText.getText().toString();

        if (num1Text.isEmpty() || num2Text.isEmpty()) {
            Toast.makeText(this, "Please enter both numbers", Toast.LENGTH_SHORT).show();
            return;
        }

        int num1, num2;
        try {
            num1 = Integer.parseInt(num1Text);
            num2 = Integer.parseInt(num2Text);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Invalid input. Please enter numbers only.", Toast.LENGTH_SHORT).show();
            return;
        }

        int result = 0;
        boolean validOperation = true;

        switch (view.getId()) {
            case R.id.btnPlus:
                result = num1 + num2;
                break;
            case R.id.btnMinus:
                result = num1 - num2;
                break;
            case R.id.btnMult:
                result = num1 * num2;
                break;
            case R.id.btnDiv:
                if (num2 == 0) {
                    Toast.makeText(this, "Cannot divide by zero", Toast.LENGTH_SHORT).show();
                    validOperation = false;
                } else {
                    result = num1 / num2;
                }
                break;
            default:
                validOperation = false;
        }

        if (validOperation) {
            TextView resultTextView = findViewById(R.id.tvResult);
            DecimalFormat decimalFormat = new DecimalFormat("#,###");
            resultTextView.setText(decimalFormat.format(result));
        }
    }
}