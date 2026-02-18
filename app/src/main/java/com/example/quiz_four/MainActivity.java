package com.example.quiz_four;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Start CustomerListActivity
        Intent intent = new Intent(MainActivity.this, CustomerListActivity.class);
        startActivity(intent);
        finish(); // Optional: finish MainActivity so it's not on the back stack
    }
}
