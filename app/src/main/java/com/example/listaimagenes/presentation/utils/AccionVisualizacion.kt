package com.example.listaimagenes.presentation.utils

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.listaimagenes.presentation.theme.AppTypography
import com.example.listaimagenes.presentation.theme.ColoresApp
import com.example.listaimagenes.presentation.theme.Tamaños

@Composable
fun AccionesVisualizacion(
    alVolverFormulario: () -> Unit,
    alLimpiarTodo: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(Tamaños.EspacioChico)
    ) {
        Button(
            onClick = alVolverFormulario,
            modifier = Modifier.weight(1f),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Blue)
        ) {
            Text("Agregar Más", color = Color.White, style = AppTypography.bodyMedium)
        }

        Button(
            onClick = alLimpiarTodo,
            modifier = Modifier.weight(1f),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD32F2F))
        ) {
            Text("Limpiar Todo", color = Color.White, style = AppTypography.bodyMedium)
        }
    }
}
