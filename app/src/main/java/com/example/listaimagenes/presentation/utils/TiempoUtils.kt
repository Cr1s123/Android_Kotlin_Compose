package com.example.listaimagenes.presentation.utils

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import com.example.listaimagenes.presentation.theme.AppTypography
import com.example.listaimagenes.presentation.theme.ColoresApp
import com.example.listaimagenes.presentation.theme.Tamaños
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun obtenerHoraActual(): String {
    val formato = SimpleDateFormat("hh:mm:ss a", Locale("es", "PE"))
    return formato.format(Date())
}

