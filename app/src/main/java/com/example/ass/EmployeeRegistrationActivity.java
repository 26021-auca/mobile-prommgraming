package com.example.ass;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EmployeeRegistrationActivity extends AppCompatActivity {

    private EditText etEmployeeId, etName, etEmail, etPhone, etDepartment;
    private RadioGroup rgGender;
    private Button btnRegister, btnViewList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_registration);

        etEmployeeId = findViewById(R.id.etEmployeeId);
        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etPhone = findViewById(R.id.etPhone);
        etDepartment = findViewById(R.id.etDepartment);
        rgGender = findViewById(R.id.rgGender);
        btnRegister = findViewById(R.id.btnRegister);
        btnViewList = findViewById(R.id.btnViewList);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerEmployee();
            }
        });

        btnViewList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EmployeeRegistrationActivity.this, EmployeeListActivity.class));
            }
        });
    }

    private void registerEmployee() {
        String id = etEmployeeId.getText().toString();
        String name = etName.getText().toString();
        String email = etEmail.getText().toString();
        String phone = etPhone.getText().toString();
        String dept = etDepartment.getText().toString();
        
        int selectedGenderId = rgGender.getCheckedRadioButtonId();
        if (selectedGenderId == -1 || id.isEmpty() || name.isEmpty() || email.isEmpty() || phone.isEmpty() || dept.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        RadioButton rbGender = findViewById(selectedGenderId);
        String gender = rbGender.getText().toString();

        Employee employee = new Employee(id, name, gender, email, phone, dept);
        EmployeeData.addEmployee(employee);

        Toast.makeText(this, "Employee Registered!", Toast.LENGTH_SHORT).show();
        clearFields();
    }

    private void clearFields() {
        etEmployeeId.setText("");
        etName.setText("");
        etEmail.setText("");
        etPhone.setText("");
        etDepartment.setText("");
        rgGender.clearCheck();
    }
}
