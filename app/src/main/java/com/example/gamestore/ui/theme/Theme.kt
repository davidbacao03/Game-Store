package com.example.gamestore.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val CoresApp = lightColorScheme(
    primary = Color(0xFF1976D2),
    onPrimary = Color.White,
    primaryContainer = Color(0xFFBBDEFB),
    secondary = Color(0xFF388E3C),
    tertiary = Color(0xFFF57C00),
    background = Color(0xFFFAFAFA),
    surface = Color.White,
    error = Color(0xFFD32F2F)
)

@Composable
fun GameStoreTheme(
    conteudo: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = CoresApp,
        content = conteudo
    )
}
