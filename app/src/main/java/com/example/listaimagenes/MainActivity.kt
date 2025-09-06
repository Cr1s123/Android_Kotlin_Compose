package com.example.listaimagenes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import com.example.listaimagenes.presentation.theme.TemaApp
import com.example.listaimagenes.presentation.ui.screens.PantallaFormulario
import com.example.listaimagenes.presentation.ui.screens.PantallaVisualizacion

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TemaApp {
                AppPrincipal()
            }
        }
    }
}

@Composable
fun AppPrincipal() {
    var pantallaActual by remember { mutableStateOf("formulario") }

    when (pantallaActual) {
        "formulario" -> PantallaFormulario(
            alIrAVisualizacion = { pantallaActual = "visualizacion" }
        )
        "visualizacion" -> PantallaVisualizacion(
            alVolverFormulario = { pantallaActual = "formulario" }
        )
    }
}