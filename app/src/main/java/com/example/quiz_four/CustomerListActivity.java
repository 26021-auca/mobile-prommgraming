package com.example.quiz_four;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class CustomerListActivity extends AppCompatActivity {

    private ListView lvCustomers;
    private FloatingActionButton fabAdd;
    private DatabaseHelper dbHelper;
    private CustomerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_list);

        dbHelper = new DatabaseHelper(this);
        lvCustomers = findViewById(R.id.lv_customers);
        fabAdd = findViewById(R.id.fab_add);

        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CustomerListActivity.this, CustomerActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadCustomers();
    }

    private void loadCustomers() {
        List<Customer> customers = dbHelper.getAllCustomers();
        adapter = new CustomerAdapter(this, customers, dbHelper);
        lvCustomers.setAdapter(adapter);
    }
}
