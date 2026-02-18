package com.example.quiz_four;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * Activity for displaying a list of customers.
 */
public class CustomerListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CustomerAdapter customerAdapter;
    private List<Customer> customerList;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_list);

        // Initialize database helper
        dbHelper = new DatabaseHelper(this);
        // Load all customers from the database
        customerList = dbHelper.getAllCustomers();

        // Set up the RecyclerView
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize and set the adapter for the RecyclerView
        customerAdapter = new CustomerAdapter(this, customerList);
        recyclerView.setAdapter(customerAdapter);
    }
}
