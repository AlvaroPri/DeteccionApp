package com.example.deteccionapp.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Definición de la tipografía personalizada para Material 3
val Typography = Typography(
    // Estilo para textos grandes en el cuerpo
    bodyLarge = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp, // Tamaño de fuente
        lineHeight = 24.sp, // Altura de línea
        letterSpacing = 0.5.sp // Espaciado entre letras
    ),
    // Estilo para títulos grandes
    titleLarge = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    // Estilo para etiquetas pequeñas
    labelSmall = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    // Puedes agregar más estilos personalizados aquí si lo necesitas
)
