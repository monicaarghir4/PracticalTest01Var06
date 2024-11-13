package ro.pub.cs.systems.eim.practicaltest01var06;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;
import java.util.Set;

public class PracticalTest01Var06MainActivity extends AppCompatActivity {

    private EditText Number1, Number2, Number3;
    private CheckBox checkBox1, checkBox2, checkBox3;
    private Random random;

    private ActivityResultLauncher<Intent> activityResultLauncher;

    private int scor = 0;  // Variabila pentru scor
    private int lastScor = 0;  // Ultimul scor calculat pentru verificare

    private static final int REQUEST_CODE_SECONDARY = 1; // Cod pentru apelarea activității secundare

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

        // Inițializarea ActivityResultLauncher
        activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        int gainedValue = result.getData().getIntExtra("GAINED_VALUE", 0);
                        lastScor = scor;
                        scor += gainedValue;

                        // Afișează scorul actualizat într-un Toast
                        Toast.makeText(this, "Scorul actualizat: " + scor, Toast.LENGTH_SHORT).show();
                    }
                });

        // Setează listener pentru butonul Play
        buttonPlay.setOnClickListener(v -> {
            // Generăm numere noi pentru fiecare câmp dacă checkbox-ul nu este bifat
            String number1 = generateRandomNumber(checkBox1.isChecked());
            String number2 = generateRandomNumber(checkBox2.isChecked());
            String number3 = generateRandomNumber(checkBox3.isChecked());

            Number1.setText(number1);
            Number2.setText(number2);
            Number3.setText(number3);

            // Verificăm dacă scorul s-a modificat
            if (scor == lastScor) {
                // Dacă scorul nu s-a modificat, afișează scorul curent fără a apela activitatea secundară
                Toast.makeText(PracticalTest01Var06MainActivity.this, "Scorul curent: " + scor, Toast.LENGTH_SHORT).show();
            } else {
                // Apelăm activitatea secundară pentru a calcula câștigul
                Intent intent = new Intent(PracticalTest01Var06MainActivity.this, PracticalTest01Var06SecondaryActivity.class);
                intent.putExtra("NUMBER_1", number1);
                intent.putExtra("NUMBER_2", number2);
                intent.putExtra("NUMBER_3", number3);

                // Contorizează checkbox-urile bifate și le trimite activității secundare
                int checkedCount = 0;
                if (checkBox1.isChecked()) checkedCount++;
                if (checkBox2.isChecked()) checkedCount++;
                if (checkBox3.isChecked()) checkedCount++;
                intent.putExtra("CHECKED_COUNT", checkedCount);

                activityResultLauncher.launch(intent); // Lansează activitatea folosind ActivityResultLauncher
            }
        });

//        // Set listener for Play button
//        buttonPlay.setOnClickListener(v -> {
//            // Generăm numere noi pentru fiecare câmp dacă checkbox-ul nu este bifat
//            String number1 = generateRandomNumber(checkBox1.isChecked());
//            String number2 = generateRandomNumber(checkBox2.isChecked());
//            String number3 = generateRandomNumber(checkBox3.isChecked());
//
//            Number1.setText(number1);
//            Number2.setText(number2);
//            Number3.setText(number3);
//
//            // Verificăm dacă scorul s-a modificat
//            if (scor == lastScor) {
//                // Dacă scorul nu s-a modificat, afișează scorul curent fără a apela activitatea secundară
//                Toast.makeText(PracticalTest01Var06MainActivity.this, "Scorul curent: " + scor, Toast.LENGTH_SHORT).show();
//            } else {
//                // Apelăm activitatea secundară pentru a calcula câștigul
//                Intent intent = new Intent(PracticalTest01Var06MainActivity.this, PracticalTest01Var06SecondaryActivity.class);
//                intent.putExtra("NUMBER_1", number1);
//                intent.putExtra("NUMBER_2", number2);
//                intent.putExtra("NUMBER_3", number3);
//
//                // Contorizează checkbox-urile bifate și le trimite activității secundare
//                int checkedCount = 0;
//                if (checkBox1.isChecked()) checkedCount++;
//                if (checkBox2.isChecked()) checkedCount++;
//                if (checkBox3.isChecked()) checkedCount++;
//                intent.putExtra("CHECKED_COUNT", checkedCount);
//
//                startActivityForResult(intent, REQUEST_CODE_SECONDARY);
//            }
//            });



//            String number1 = generateRandomNumber(checkBox1.isChecked());
//            String number2 = generateRandomNumber(checkBox2.isChecked());
//            String number3 = generateRandomNumber(checkBox3.isChecked());
//
//            // Update EditText fields with the generated values
//            Number1.setText(number1);
//            Number2.setText(number2);
//            Number3.setText(number3);
//            int currentScor = scor;
//            if (currentScor == lastScor) {
//                // Dacă scorul nu s-a modificat, afișează scorul curent fără a apela activitatea secundară
//                Toast.makeText(PracticalTest01Var06MainActivity.this, "Scorul curent: " + scor, Toast.LENGTH_SHORT).show();
//            } else {
//                // Apelăm activitatea secundară
//                Intent intent = new Intent(PracticalTest01Var06MainActivity.this, PracticalTest01Var06SecondaryActivity.class);
//                intent.putExtra("NUMBER_1", Number1.getText().toString());
//                intent.putExtra("NUMBER_2", Number2.getText().toString());
//                intent.putExtra("NUMBER_3", Number3.getText().toString());
//
//                // Contorizează checkbox-urile bifate și le trimite activității secundare
//                int checkedCount = 0;
//                if (checkBox1.isChecked()) checkedCount++;
//                if (checkBox2.isChecked()) checkedCount++;
//                if (checkBox3.isChecked()) checkedCount++;
//                intent.putExtra("CHECKED_COUNT", checkedCount);
//
//                startActivityForResult(intent, REQUEST_CODE_SECONDARY);
//            }
//            String number1 = generateRandomNumber(checkBox1.isChecked());
//            String number2 = generateRandomNumber(checkBox2.isChecked());
//            String number3 = generateRandomNumber(checkBox3.isChecked());
//
//            // Update EditText fields with the generated values
//            Number1.setText(number1);
//            Number2.setText(number2);
//            Number3.setText(number3);
//
//            // Display the result in a Toast
//            Toast.makeText(PracticalTest01Var06MainActivity.this,
//                    "Generated numbers: " + number1 + ", " + number2 + ", " + number3,
//                    Toast.LENGTH_SHORT).show();
//        });

//         Set listener for Navigate button
//        buttonNavigate.setOnClickListener(v -> {
//            Intent intent = new Intent(PracticalTest01Var06MainActivity.this, PracticalTest01Var06SecondaryActivity.class);
//
//            // Send the numbers
//            intent.putExtra("NUMBER_1", Number1.getText().toString());
//            intent.putExtra("NUMBER_2", Number2.getText().toString());
//            intent.putExtra("NUMBER_3", Number3.getText().toString());
//
//            // Count the checked boxes
//            int checkedCount = 0;
//            if (checkBox1.isChecked()) checkedCount++;
//            if (checkBox2.isChecked()) checkedCount++;
//            if (checkBox3.isChecked()) checkedCount++;
//            intent.putExtra("CHECKED_COUNT", checkedCount);
//
//            startActivity(intent);
//        });

        buttonNavigate.setOnClickListener(v -> {
            Intent intent = new Intent(PracticalTest01Var06MainActivity.this, PracticalTest01Var06SecondaryActivity.class);
//            activityResultLauncher.launch(intent);
            startActivity(intent);

        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_SECONDARY && resultCode == RESULT_OK && data != null) {
            // Preia valoarea câștigului din activitatea secundară
            int gainedValue = data.getIntExtra("GAINED_VALUE", 0);
            lastScor = scor;
            scor += gainedValue;

            // Afișează scorul actualizat într-un Toast
            Toast.makeText(this, "Scorul actualizat: " + scor, Toast.LENGTH_SHORT).show();
        }
    }

    // Salvăm starea scorului pentru cazul în care activitatea este distrusă
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("scor", scor);
        outState.putInt("lastScor", lastScor);  // Salvăm și valoarea scorului anterior
    }

    // Restaurăm starea scorului după recrearea activității
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        scor = savedInstanceState.getInt("scor", 0);
        lastScor = savedInstanceState.getInt("lastScor", 0);
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