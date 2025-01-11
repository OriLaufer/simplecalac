package com.example.simplecalculator;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @SuppressLint("NewApi")
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

    @SuppressLint("SetTextI18n")
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
        Integer result = null;

        if (view.getId() == R.id.btnPlus)
            result = num1 + num2;
        if (view.getId() == R.id.btnMinus)
            result = num1 - num2;
        if (view.getId() == R.id.btnMult)
            result = num1 * num2;
        if (view.getId() == R.id.btnDiv)
            result = num1 / num2;

        if (result != null) {
            TextView tvRes = findViewById(R.id.tvResult);
            tvRes.setText(result.toString());
        }

        if (num2 == 0) {
            Toast.makeText(this, "Cannot divide by zero", Toast.LENGTH_SHORT).show();
        }
    }
}