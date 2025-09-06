package com.example.listaimagenes.presentation.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.example.listaimagenes.data.model.Facultad

@Composable
fun MenuFacultades(
    facultadSeleccionada: Facultad?,
    listaFacultades: List<String>,
    alSeleccionar: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var expandido by remember { mutableStateOf(false) }

    Box(modifier = modifier) {
        OutlinedTextField(
            value = facultadSeleccionada?.nombre ?: "",
            onValueChange = {},
            label = { Text("Selecciona una facultad") },
            readOnly = true,
            trailingIcon = {
                IconButton(onClick = { expandido = !expandido }) {
                    Icon(
                        Icons.Default.ArrowDropDown,
                        contentDescription = "Abrir menú"
                    )
                }
            },
            modifier = Modifier.fillMaxWidth()
        )

        DropdownMenu(
            expanded = expandido,
            onDismissRequest = { expandido = false }
        ) {
            listaFacultades.forEach { nombre ->
                DropdownMenuItem(
                    text = { Text(nombre) },
                    onClick = {
                        alSeleccionar(nombre)
                        expandido = false
                    }
                )
            }
        }
    }
}