package ro.pub.cs.systems.eim.practicaltest01var06;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PracticalTest01Var06SecondaryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var06_secondary);

        // Get the data from the intent
        String number1 = getIntent().getStringExtra("NUMBER_1");
        String number2 = getIntent().getStringExtra("NUMBER_2");
        String number3 = getIntent().getStringExtra("NUMBER_3");
        int checkedCount = getIntent().getIntExtra("CHECKED_COUNT", 0);

        // Calculate the gain if the numbers match (considering "*" as joker)
        boolean isGained = checkIfGained(number1, number2, number3);
        int gain = calculateGain(checkedCount);

        TextView textViewResult = findViewById(R.id.resultTextView);

        if (isGained) {
            textViewResult.setText("Gained! Your prize: " + gain);
        } else {
            textViewResult.setText("Not gained");
        }

        // Optionally, show a Toast with the result
        Toast.makeText(this, isGained ? "Gained! Prize: " + gain : "Not gained", Toast.LENGTH_LONG).show();
    }

    private boolean checkIfGained(String num1, String num2, String num3) {
        // Check if all numbers are the same or can be substituted by "*"
        return (num1.equals(num2) || num1.equals("*") || num2.equals("*")) &&
                (num1.equals(num3) || num1.equals("*") || num3.equals("*")) &&
                (num2.equals(num3) || num2.equals("*") || num3.equals("*"));
    }

    private int calculateGain(int checkedCount) {
        switch (checkedCount) {
            case 0:
                return 100;
            case 1:
                return 50;
            case 2:
                return 10;
            default:
                return 0;
        }
    }
}