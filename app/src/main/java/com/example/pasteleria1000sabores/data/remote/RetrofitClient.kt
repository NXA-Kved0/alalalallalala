package com.example.pasteleria1000sabores.data.remote

import android.content.Context
import com.example.pasteleria1000sabores.data.local.TokenManager
import com.example.pasteleria1000sabores.data.remote.api.*
import com.example.pasteleria1000sabores.data.remote.dto.pedido.ClienteDto
import com.example.pasteleria1000sabores.data.remote.dto.pedido.ClienteDtoDeserializer
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 *
 * @see TokenManager
 * @see AuthInterceptor
 */
object RetrofitClient {

    const val BASE_URL = "https://pasteleria-api.onrender.com/api/"

    private lateinit var tokenManager: TokenManager

    fun initialize(context: Context) {
        tokenManager = TokenManager(context.applicationContext)
    }

    private val okHttpClient: OkHttpClient by lazy {
        val authInterceptor = AuthInterceptor(tokenManager)
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .addInterceptor(loggingInterceptor)
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(90, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .build()
    }

    private val gson by lazy {
        GsonBuilder()
            .registerTypeAdapter(ClienteDto::class.java, ClienteDtoDeserializer())
            .create()
    }

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    val pasteleriaAuthApiService: PasteleriaAuthApiService by lazy {
        retrofit.create(PasteleriaAuthApiService::class.java)
    }
    val pasteleriaProductoApiService: PasteleriaProductoApiService by lazy {
        retrofit.create(PasteleriaProductoApiService::class.java)
    }
    // Comentados temporalmente hasta que se corrijan los DTOs
    /*
    val pasteleriaProductorApiService: PasteleriaProductorApiService by lazy {
        retrofit.create(PasteleriaProductorApiService::class.java)
    }
    val pasteleriaClienteApiService: PasteleriaClienteApiService by lazy {
        retrofit.create(PasteleriaClienteApiService::class.java)
    }
    val pasteleriaEntregaApiService: PasteleriaEntregaApiService by lazy {
        retrofit.create(PasteleriaEntregaApiService::class.java)
    }
    val pasteleriaUploadApiService: PasteleriaUploadApiService by lazy {
        retrofit.create(PasteleriaUploadApiService::class.java)
    }
    */
    val pasteleriaPedidoApiService: PasteleriaPedidoApiService by lazy {
        retrofit.create(PasteleriaPedidoApiService::class.java)
    }

    fun getTokenManager(): TokenManager = tokenManager
}