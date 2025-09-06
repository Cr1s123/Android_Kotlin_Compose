package com.example.listaimagenes.presentation.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val EsquemaOscuro = darkColorScheme(
    primary = ColoresApp.Primario,
    secondary = ColoresApp.Secundario,
    tertiary = ColoresApp.Confirmacion,
    error = ColoresApp.Error,
    onPrimary = ColoresApp.TextoInverso,
    onSecondary = ColoresApp.TextoPrincipal,
    onTertiary = ColoresApp.TextoInverso
)

private val EsquemaClaro = lightColorScheme(
    primary = ColoresApp.Primario,
    secondary = ColoresApp.Secundario,
    tertiary = ColoresApp.Confirmacion,
    error = ColoresApp.Error,
    onPrimary = ColoresApp.TextoInverso,
    onSecondary = ColoresApp.TextoPrincipal,
    onTertiary = ColoresApp.TextoInverso
)



@Composable
fun TemaApp(
    temaOscuro: Boolean = isSystemInDarkTheme(),
    colorDinamico: Boolean = true,
    contenido: @Composable () -> Unit
) {
    val esquemaColor = when {
        colorDinamico && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val contexto = LocalContext.current
            if (temaOscuro) dynamicDarkColorScheme(contexto) else dynamicLightColorScheme(contexto)
        }
        temaOscuro -> EsquemaOscuro
        else -> EsquemaClaro
    }

    MaterialTheme(
        colorScheme = esquemaColor,
        typography = AppTypography,
        content = contenido
    )
}