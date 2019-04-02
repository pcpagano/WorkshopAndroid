package android.workshop.clase4

import io.reactivex.Observable
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

interface MercadoLibreApiService {

    companion object {
        fun create(): MercadoLibreApiService {
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(
                    RxJava2CallAdapterFactory.create())
                .addConverterFactory(
                    GsonConverterFactory.create())
//                .client(OkHttpClient().newBuilder()
//                    .readTimeout(60, TimeUnit.SECONDS)
//                    .connectTimeout(60, TimeUnit.SECONDS)
//                    .build())
                .baseUrl("https://api.mercadolibre.com/")
                .build()

            return retrofit.create(MercadoLibreApiService::class.java)
        }
    }

    @GET("sites/MLA/search")
    fun searchResults(@Query("q") q: String):
            Observable<Model.Response>

    @GET("items/{productId}")
    fun searchProduct(@Path("productId") productId: String):
            Observable<Results>

    @GET("items/{productId}/description")
    fun getProductDescription(@Path("productId") productId: String):
            Observable<Results>
}