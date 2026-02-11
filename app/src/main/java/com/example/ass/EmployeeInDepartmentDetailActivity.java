package com.example.ass;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class EmployeeInDepartmentDetailActivity extends AppCompatActivity {

    private TextView tvId, tvName, tvGender, tvDept, tvEmail, tvPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_detail);

        tvId = findViewById(R.id.tvDetailId);
        tvName = findViewById(R.id.tvDetailName);
        tvGender = findViewById(R.id.tvDetailGender);
        tvDept = findViewById(R.id.tvDetailDept);
        tvEmail = findViewById(R.id.tvDetailEmail);
        tvPhone = findViewById(R.id.tvDetailPhone);

        Employee employee = (Employee) getIntent().getSerializableExtra("employee");

        if (employee != null) {
            tvId.setText("ID: " + employee.getEmployeeId());
            tvName.setText("Name: " + employee.getName());
            tvGender.setText("Gender: " + employee.getGender());
            tvDept.setText("Department: " + employee.getDepartment());
            tvEmail.setText("Email: " + employee.getEmail());
            tvPhone.setText("Phone: " + employee.getPhone());

            tvEmail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
                    emailIntent.setData(Uri.parse("mailto:" + employee.getEmail()));
                    startActivity(Intent.createChooser(emailIntent, "Send Email"));
                }
            });

            tvPhone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent dialIntent = new Intent(Intent.ACTION_DIAL);
                    dialIntent.setData(Uri.parse("tel:" + employee.getPhone()));
                    startActivity(dialIntent);
                }
            });
        }
    }
}
