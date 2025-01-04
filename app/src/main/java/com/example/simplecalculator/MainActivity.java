package com.example.simplecalculator;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void onBtnClicked(View view) {
        // התחברות לשדות הקלט
        EditText et1 = findViewById(R.id.Num1);
        EditText et2 = findViewById(R.id.Num2);

        // קריאת הטקסט משדות הקלט
        String et1Text = et1.getText().toString();
        String et2Text = et2.getText().toString();

        // בדיקה שהקלט אינו ריק
        if (et1Text.isEmpty() || et2Text.isEmpty()) {
            Toast.makeText(this, "Please enter both numbers", Toast.LENGTH_LONG).show();
            return;
        }

        // המרת המחרוזת למספר שלם
        Integer num1 = Integer.valueOf(et1Text);
        Integer num2 = Integer.valueOf(et2Text);

        // הפעלת הפעולה המתאימה
        Integer result = null;
        if (view.getId() == R.id.btnPlus) {
            result = num1 + num2;
        } else if (view.getId() == R.id.btnMinus) {
            result = num1 - num2;
        } else if (view.getId() == R.id.btnMult) {
            result = num1 * num2;
        } else if (view.getId() == R.id.btnDiv) {
            if (num2 == 0) {
                // הודעת שגיאה במקרה של חלוקה באפס
                Toast.makeText(this, "Can't divide by 0", Toast.LENGTH_LONG).show();
                return;
            }
            result = num1 / num2;
        }

        // הצגת התוצאה
        if (result != null) {
            TextView tvRes = findViewById(R.id.tvResult);
            tvRes.setText(result.toString());
        }
    }

