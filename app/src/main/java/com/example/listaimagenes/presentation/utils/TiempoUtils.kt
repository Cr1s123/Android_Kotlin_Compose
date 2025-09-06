package com.example.listaimagenes.presentation.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun obtenerHoraActual(): String {
    val formato = SimpleDateFormat("hh:mm:ss a", Locale("es", "PE"))
    return formato.format(Date())
}
