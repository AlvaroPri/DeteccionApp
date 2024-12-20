package com.example.deteccionapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.deteccionapp.retrofit.RetrofitClient
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.asRequestBody



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen() {
    var imageUri by remember { mutableStateOf<String?>(null) }
    var resultImageUri by remember { mutableStateOf<String?>(null) }  // Para la imagen procesada

    val pickImage: ActivityResultLauncher<String> = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()) { uri ->
        imageUri = uri?.toString()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        imageUri?.let {
            AsyncImage(
                model = it,
                contentDescription = "Imagen seleccionada",
                modifier = Modifier.size(200.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { pickImage.launch("image/*") }) {
            Text("Seleccionar Imagen")
        }

        Spacer(modifier = Modifier.height(16.dp))

        imageUri?.let {
            Button(onClick = { performDetection(it) { resultImageUri = it } }) {
                Text("Detectar Plaga")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Mostrar la imagen procesada si está disponible
        resultImageUri?.let {
            AsyncImage(
                model = it,
                contentDescription = "Imagen procesada",
                modifier = Modifier.size(200.dp)
            )
        }
    }
}

fun performDetection(imageUri: String, onResult: (String) -> Unit) {
    val file = File(imageUri)
    val requestFile = file.asRequestBody("image/*".toMediaType())
    val body = MultipartBody.Part.createFormData("image", file.name, requestFile)

    RetrofitClient.instance.detectPlague(body).enqueue(object : Callback<String> {
        override fun onResponse(call: Call<String>, response: Response<String>) {
            if (response.isSuccessful) {
                // Asumimos que la respuesta es la URL de la imagen procesada
                onResult(response.body().orEmpty())  // Aquí se pasa la URL o base64 de la imagen procesada
            } else {
                onResult("Error en la detección")
            }
        }

        override fun onFailure(call: Call<String>, t: Throwable) {
            onResult("Fallo en la conexión")
        }
    })
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MaterialTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            MainScreen()
        }
    }
}
