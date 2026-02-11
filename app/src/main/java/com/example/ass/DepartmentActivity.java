package com.example.ass;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DepartmentActivity extends AppCompatActivity {

    private ListView lvDepartments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_department);

        lvDepartments = findViewById(R.id.lvDepartments);

        List<Employee> employees = EmployeeData.getEmployeeList();
        Set<String> departmentSet = new HashSet<>();
        for (Employee e : employees) {
            departmentSet.add(e.getDepartment());
        }

        List<String> departments = new ArrayList<>(departmentSet);
        if (departments.isEmpty()) {
            departments.add("No Departments Found (Register Employees first)");
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, departments);
        lvDepartments.setAdapter(adapter);

        lvDepartments.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // For simplicity, this could lead to a list of employees in that department
                // or just show the same list for now as per assignment description flow.
                startActivity(new Intent(DepartmentActivity.this, EmployeeListActivity.class));
            }
        });
    }
}
