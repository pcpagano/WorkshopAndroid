package android.workshop.clase4.activities

import android.animation.ObjectAnimator
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.workshop.clase4.R

class MainActivity : AppCompatActivity() {

    private lateinit var searchTerm : EditText
    private lateinit var buttonClear : Button
    private lateinit var buttonSearch : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        searchTerm = findViewById(R.id.searchTerm)
        buttonClear = findViewById(R.id.buttonClear)
        buttonSearch = findViewById(R.id.buttonSearch)

        buttonClear.setOnClickListener() { v -> clearButton() }
        buttonSearch.setOnClickListener() { v -> callSearchResults(searchTerm) }

        searchTerm.setText("iphone")
    }

    fun clearButton() {
        searchTerm.setText("")
//        ObjectAnimator.ofFloat(searchTerm, "translationY", -100f).apply {
//            duration = 500
//            start()
//        }
    }

    fun callSearchResults(searchTerm : EditText) {
        val searchText = searchTerm.text?.toString()
        if (searchText == null || searchText.length < 1) {
            searchTerm.error = resources.getString(R.string.searchTermError)
        } else {
            Log.i("Search Text", searchText)
            var intent = Intent(this, SearchResults::class.java)
            intent.putExtra("parameter", searchText)
            startActivity(intent)
        }
    }
}