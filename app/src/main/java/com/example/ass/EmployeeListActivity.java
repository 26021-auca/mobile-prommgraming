package com.example.ass;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class EmployeeListActivity extends AppCompatActivity {

    private ListView lvEmployees;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_list);

        lvEmployees = findViewById(R.id.lvEmployees);

        List<Employee> employees = EmployeeData.getEmployeeList();
        ArrayAdapter<Employee> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, employees);
        lvEmployees.setAdapter(adapter);

        lvEmployees.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Employee selectedEmployee = (Employee) parent.getItemAtPosition(position);
                Intent intent = new Intent(EmployeeListActivity.this, EmployeeInDepartmentDetailActivity.class);
                intent.putExtra("employee", selectedEmployee);
                startActivity(intent);
            }
        });
    }
}
