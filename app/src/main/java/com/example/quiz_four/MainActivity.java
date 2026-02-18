package com.example.quiz_four;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Main activity of the application.
 * Provides a menu for navigating to different features.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set the layout for the activity
        setContentView(R.layout.activity_main);

        // Initialize buttons from the layout
        Button btnRegistration = findViewById(R.id.btnGoToRegistration);
        Button btnDepartments = findViewById(R.id.btnGoToDepartments);
        Button btnStartService = findViewById(R.id.btnStartService);
        Button btnStopService = findViewById(R.id.btnStopService);

        // Set listener for registration button
        btnRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CustomerActivity.class);
                startActivity(intent);
            }
        });

        // Set listener for departments (customer list) button
        btnDepartments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CustomerListActivity.class);
                startActivity(intent);
            }
        });

        // Set listener for starting the background service
        btnStartService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Service Started", Toast.LENGTH_SHORT).show();
            }
        });

        // Set listener for stopping the background service
        btnStopService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Service Stopped", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
