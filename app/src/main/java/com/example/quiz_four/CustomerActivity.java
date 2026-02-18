package com.example.quiz_four;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CustomerActivity extends AppCompatActivity {

    private EditText etFullNames, etGender, etBirthDate, etNationality, etCustomerNID;
    private Button btnSave;
    private DatabaseHelper dbHelper;
    private int customerId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);

        dbHelper = new DatabaseHelper(this);

        etFullNames = findViewById(R.id.et_full_names);
        etGender = findViewById(R.id.et_gender);
        etBirthDate = findViewById(R.id.et_birth_date);
        etNationality = findViewById(R.id.et_nationality);
        etCustomerNID = findViewById(R.id.et_customer_nid);
        btnSave = findViewById(R.id.btn_save);

        if (getIntent().hasExtra("customer_id")) {
            customerId = getIntent().getIntExtra("customer_id", -1);
            loadCustomerData(customerId);
            btnSave.setText("Update");
        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveCustomer();
            }
        });
    }

    private void loadCustomerData(int id) {
        Customer customer = dbHelper.getCustomer(id);
        etFullNames.setText(customer.getFullNames());
        etGender.setText(customer.getGender());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        etBirthDate.setText(sdf.format(customer.getBirthDate()));
        etNationality.setText(customer.getNationality());
        etCustomerNID.setText(customer.getCustomerNID());
    }

    private void saveCustomer() {
        String names = etFullNames.getText().toString();
        String gender = etGender.getText().toString();
        String birthDateStr = etBirthDate.getText().toString();
        String nationality = etNationality.getText().toString();
        String nid = etCustomerNID.getText().toString();

        if (names.isEmpty() || gender.isEmpty() || birthDateStr.isEmpty() || nationality.isEmpty() || nid.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        Date birthDate;
        try {
            birthDate = sdf.parse(birthDateStr);
        } catch (ParseException e) {
            Toast.makeText(this, "Invalid date format. Use yyyy-mm-dd", Toast.LENGTH_SHORT).show();
            return;
        }

        Customer customer = new Customer();
        customer.setFullNames(names);
        customer.setGender(gender);
        customer.setBirthDate(birthDate);
        customer.setNationality(nationality);
        customer.setCustomerNID(nid);

        if (customerId == -1) {
            dbHelper.addCustomer(customer);
            Toast.makeText(this, "Customer saved", Toast.LENGTH_SHORT).show();
        } else {
            customer.setCustomerID(customerId);
            dbHelper.updateCustomer(customer);
            Toast.makeText(this, "Customer updated", Toast.LENGTH_SHORT).show();
        }
        finish();
    }
}
