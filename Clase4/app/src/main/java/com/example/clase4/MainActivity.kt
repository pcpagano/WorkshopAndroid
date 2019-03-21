package com.example.clase4

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val searchTerm = findViewById<EditText>(R.id.searchTerm)
        val buttonClear = findViewById<Button>(R.id.buttonClear)
        val buttonSearch = findViewById<Button>(R.id.buttonSearch)

        buttonClear.setOnClickListener() { v -> searchTerm.setText("") }
        buttonSearch.setOnClickListener() { v ->  }

    }
}
