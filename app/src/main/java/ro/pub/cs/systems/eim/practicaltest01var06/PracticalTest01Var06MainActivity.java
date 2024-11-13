package ro.pub.cs.systems.eim.practicaltest01var06;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;
import java.util.Set;

public class PracticalTest01Var06MainActivity extends AppCompatActivity {

    private EditText Number1, Number2, Number3;
    private CheckBox checkBox1, checkBox2, checkBox3;
    private Random random;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var06_main);

        // Initialize components
        Number1 = findViewById(R.id.number1);
        Number2 = findViewById(R.id.number2);
        Number3 = findViewById(R.id.number3);

        checkBox1 = findViewById(R.id.checkBox1);
        checkBox2 = findViewById(R.id.checkBox2);
        checkBox3 = findViewById(R.id.checkBox3);

        Button buttonPlay = findViewById(R.id.button_play);
        Button buttonNavigate = findViewById(R.id.button_navigate);

        random = new Random();

        // Set listener for Play button
        buttonPlay.setOnClickListener(v -> {
            String number1 = generateRandomNumber(checkBox1.isChecked());
            String number2 = generateRandomNumber(checkBox2.isChecked());
            String number3 = generateRandomNumber(checkBox3.isChecked());

            // Update EditText fields with the generated values
            Number1.setText(number1);
            Number2.setText(number2);
            Number3.setText(number3);

            // Display the result in a Toast
            Toast.makeText(PracticalTest01Var06MainActivity.this,
                    "Generated numbers: " + number1 + ", " + number2 + ", " + number3,
                    Toast.LENGTH_SHORT).show();
        });

//         Set listener for Navigate button
        buttonNavigate.setOnClickListener(v -> {
            Intent intent = new Intent(PracticalTest01Var06MainActivity.this, PracticalTest01Var06SecondaryActivity.class);

            // Send the numbers
            intent.putExtra("NUMBER_1", Number1.getText().toString());
            intent.putExtra("NUMBER_2", Number2.getText().toString());
            intent.putExtra("NUMBER_3", Number3.getText().toString());

            // Count the checked boxes
            int checkedCount = 0;
            if (checkBox1.isChecked()) checkedCount++;
            if (checkBox2.isChecked()) checkedCount++;
            if (checkBox3.isChecked()) checkedCount++;
            intent.putExtra("CHECKED_COUNT", checkedCount);

            startActivity(intent);
        });
    }

    // Method to generate a random number or keep the current one if checkbox is checked
    private String generateRandomNumber(boolean isLocked) {
        if (isLocked) {
            return "*";  // Return "*" if checkbox is checked
        } else {
            int randomNum = random.nextInt(4) + 1; // Generate a number between 1 and 4
            return randomNum == 4 ? "*" : String.valueOf(randomNum);  // Convert 4 to "*"
        }
    }
}