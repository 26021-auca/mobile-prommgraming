package com.example.quiz_four;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Date;

/**
 * Activity for adding or editing a customer.
 */
public class CustomerActivity extends AppCompatActivity {

    private EditText etFullNames, etGender, etBirthDate, etNationality, etCustomerNID;
    private Button btnSave;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);

        // Initialize database helper
        dbHelper = new DatabaseHelper(this);

        etFullNames = findViewById(R.id.etFullNames);
        etGender = findViewById(R.id.etGender);
        etBirthDate = findViewById(R.id.etBirthDate);
        etNationality = findViewById(R.id.etNationality);
        etCustomerNID = findViewById(R.id.etCustomerNID);
        btnSave = findViewById(R.id.btnSave);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveCustomer();
            }
        });
    }

    private void saveCustomer() {
        String fullNames = etFullNames.getText().toString().trim();
        String gender = etGender.getText().toString().trim();
        String birthDateStr = etBirthDate.getText().toString().trim();
        String nationality = etNationality.getText().toString().trim();
        String customerNID = etCustomerNID.getText().toString().trim();

        // Basic validation
        if (fullNames.isEmpty() || gender.isEmpty() || birthDateStr.isEmpty() || nationality.isEmpty() || customerNID.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Create a new customer object
        Customer customer = new Customer();
        customer.setFullNames(fullNames);
        customer.setGender(gender);
        // Note: Date parsing should be handled more robustly
        customer.setBirthDate(new Date()); // Placeholder, replace with actual date parsing
        customer.setNationality(nationality);
        customer.setCustomerNID(customerNID);

        dbHelper.addCustomer(customer);
        Toast.makeText(this, "Customer saved successfully", Toast.LENGTH_SHORT).show();
        finish();
    }
}
