package com.example.pasteleria1000sabores.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color  // ← AGREGAR ESTE IMPORT
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

// ===== ESQUEMA DE COLORES =====
private val LightColorScheme = lightColorScheme(
    primary = RosaSuave,                  // Rosa suave para botones principales
    onPrimary = Blanco,                   // Texto sobre botones rosa

    secondary = Chocolate,                // Chocolate para botones secundarios
    onSecondary = Blanco,                 // Texto sobre botones chocolate

    tertiary = RosaSuave,                 // Acento terciario
    onTertiary = MarronOscuro,            // Texto sobre acento

    background = CremaPastel,             // Fondo principal crema
    onBackground = MarronOscuro,          // Texto sobre fondo crema

    surface = Blanco,                     // Fondo de tarjetas y cards
    onSurface = MarronOscuro,             // Texto sobre tarjetas

    surfaceVariant = CremaPastel,         // Variante de superficie
    onSurfaceVariant = GrisClaro,         // Texto secundario

    error = Color(0xFFB00020),            // Color de error
    onError = Blanco,                     // Texto sobre error

    outline = GrisClaro,                  // Bordes y divisores
)

// Modo oscuro (opcional)
private val DarkColorScheme = darkColorScheme(
    primary = RosaSuave,
    onPrimary = MarronOscuro,
    secondary = Chocolate,
    onSecondary = Blanco,
    background = MarronOscuro,
    onBackground = CremaPastel,
    surface = Color(0xFF3E2723),
    onSurface = CremaPastel,
)

@Composable
fun MiAppModularTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.background.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}