package com.example.deteccionapp.retrofit

import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

// Define el objeto de la solicitud para enviar al servidor
data class ImageRequest(val imageUrl: String)

// Define la interfaz de Retrofit
interface ApiService {

    // Endpoint para la detección de plagas, que recibe una URL de imagen como parámetro
    @POST("predict") // Ruta del servidor, usando POST
    fun detectPlague(@Body request: MultipartBody.Part): Call<String>
}

// RetrofitClient object para crear una instancia de Retrofit y configurar el cliente API
object RetrofitClient {
    private const val BASE_URL = "http://20.94.42.38:5000/" // URL base del servidor

    val instance: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL) // Configura la URL base de tu servidor
            .addConverterFactory(GsonConverterFactory.create()) // Configura el convertidor Gson
            .build()
            .create(ApiService::class.java) // Crea la instancia de la interfaz ApiService
    }
}
