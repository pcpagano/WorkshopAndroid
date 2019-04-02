package android.workshop.clase4.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import android.workshop.clase4.MercadoLibreApiService
import android.workshop.clase4.R
import android.workshop.clase4.Results
import com.squareup.picasso.Picasso
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.text.NumberFormat

class SelectedProduct : AppCompatActivity() {

    var disposable: Disposable? = null

    private val mlApiServe by lazy {
        MercadoLibreApiService.create()
    }

    val format = NumberFormat.getCurrencyInstance()

    init {
        format.maximumFractionDigits = 0
    }

    lateinit var selectedTitle: TextView
    lateinit var imageView: ImageView
    lateinit var selectedPrice: TextView
    lateinit var selectedDescription: TextView
    lateinit var productId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_selected_product)

        selectedTitle = findViewById(R.id.selectedTitle)
        imageView = findViewById(R.id.imageView)
        selectedPrice = findViewById(R.id.selectedPrice)
        selectedDescription = findViewById(R.id.selectedDescription)

        productId = intent.extras?.getString("productId").toString()

        getProductInfo()
    }

    fun getProductInfo() {
        disposable = mlApiServe.searchProduct(productId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { response -> showResult(response) },
                { error -> handleError(error as Throwable) }
            )
    }

    fun showResult(result: Results) {
        selectedTitle.text = result.title
        selectedPrice.text = format.format(result.price.toDouble())
        Picasso.get().load(result.pictures.get(0).url).into(imageView)

        getProductDescription(result.id)
    }

    fun handleError(error: Throwable) {
        Toast.makeText(applicationContext, error.message, Toast.LENGTH_LONG).show()
    }

    fun getProductDescription(descriptionId: String) {
        disposable = mlApiServe.getProductDescription(descriptionId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { response -> getDescription(response) },
                { error -> handleError(error as Throwable) }
            )
    }

    fun getDescription(result: Results) {
        selectedDescription.text = result.plain_text
    }
}
