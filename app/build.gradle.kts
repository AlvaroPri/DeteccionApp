plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.compose") version "2.0.0"
}

android {
    namespace = "com.example.deteccionapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.deteccionapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        compose = true // Habilitar Jetpack Compose
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.0" // Establecer la versión correcta del compilador de Compose
    }
}

dependencies {
    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.okhttp)
    implementation(libs.logging.interceptor)

    // Material Design
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.constraintlayout)

    // Jetpack Compose
    implementation(libs.ui)
    implementation(libs.material3)
    implementation(libs.androidx.material)
    implementation(libs.ui.tooling.preview)
    implementation(libs.androidx.runtime)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.material3)

    // Otras dependencias
    implementation(libs.picasso)
    implementation(libs.coil.compose)
    implementation(libs.coil.compose.v200)
    // Dependencias de pruebas
    testImplementation(libs.junit) // Pruebas unitarias
    androidTestImplementation(libs.androidx.junit) // Pruebas instrumentadas
    androidTestImplementation(libs.androidx.espresso.core) // Espresso para pruebas UI
    //otras
    // Para cargar imágenes
    implementation(libs.retrofit) // Retrofit
    implementation(libs.converter.gson) // Gson converter
    implementation(libs.okhttp.v490)

    implementation(libs.androidx.activity.compose.v172)
    implementation(libs.okhttp3.okhttp.v490)
    implementation("androidx.activity:activity-compose:1.7.2")  // Última versión estable
    implementation(libs.okhttp)

}