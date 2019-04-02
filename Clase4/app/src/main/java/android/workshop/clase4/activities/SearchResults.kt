package android.workshop.clase4.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import android.workshop.clase4.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class SearchResults : AppCompatActivity() {

    var disposable: Disposable? = null

    private val mlApiServe by lazy {
        MercadoLibreApiService.create()
    }

    var searchItems = ArrayList<Results>()
    val itemsAdapter = SearchItemAdapter(searchItems, itemClick())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_results)

        val layoutManager = LinearLayoutManager(applicationContext)
        val recyclerView = findViewById<RecyclerView>(R.id.searchItemsRV)
        recyclerView.layoutManager = layoutManager
        recyclerView.itemAnimator = DefaultItemAnimator() as RecyclerView.ItemAnimator?
        recyclerView.adapter = itemsAdapter

        val parameter = intent.extras?.getString("parameter")

        if (parameter != null) {
            getItems(parameter)
        } else {
            finishActivity(-1)
        }
    }

    fun getItems(parameter : String) {
        disposable = mlApiServe.searchResults(parameter)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { response -> showResult(response.results) },
                { error -> handleError(error as Throwable) }
            )
    }

    fun itemClick(): (String) -> Unit {
        return {
            id: String ->
                val newIntent = Intent(applicationContext, SelectedProduct::class.java)
                newIntent.putExtra("productId", id)
                startActivity(newIntent)
        }
    }

    private fun showResult(results: List<Results>) {
        for (searchItem in results) {
            searchItems.add(searchItem)
        }
        itemsAdapter.notifyDataSetChanged()
    }

    private fun handleError(error: Throwable) {
        Toast.makeText(applicationContext, error.message, Toast.LENGTH_LONG).show()
    }

    override fun onPause() {
        super.onPause()
        disposable?.dispose()
    }
}