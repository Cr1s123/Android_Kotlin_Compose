package com.example.listaimagenes.presentation.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.listaimagenes.data.repository.RepositorioFacultadAgregada
import com.example.listaimagenes.domain.usecase.CasoUsoFormulario
import com.example.listaimagenes.presentation.theme.AppTypography
import com.example.listaimagenes.presentation.theme.ColoresApp
import com.example.listaimagenes.presentation.theme.Tamaños
import com.example.listaimagenes.presentation.ui.components.BarraSuperior
import com.example.listaimagenes.presentation.ui.components.MenuFacultadesDisponibles
import com.example.listaimagenes.presentation.ui.components.PiePagina
import com.example.listaimagenes.presentation.viewmodel.ViewModelFormulario
import kotlinx.coroutines.delay

@Composable
fun PantallaFormulario(
    alIrAVisualizacion: () -> Unit
) {
    val repositorio = remember { RepositorioFacultadAgregada() }
    val casoUso = remember { CasoUsoFormulario(repositorio) }
    val viewModel: ViewModelFormulario = viewModel {
        ViewModelFormulario(casoUso)
    }

    val estado by viewModel.estado.collectAsState()

    // Efecnavegar después to para del éxito
    LaunchedEffect(estado.mensajeExito) {
        if (estado.mensajeExito.isNotEmpty()) {
            delay(1500)
            viewModel.limpiarMensajes()
            alIrAVisualizacion()
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        BarraSuperior(titulo = "Agregar Facultad")

        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(Tamaños.EspacioGrande),
            verticalArrangement = Arrangement.spacedBy(Tamaños.EspacioChico)
        ) {
            // Dropdown de facultades disponibles
            if (estado.facultadesDisponibles.isNotEmpty()) {
                MenuFacultadesDisponibles(
                    facultadSeleccionada = estado.facultadSeleccionadaFormulario,
                    facultadesDisponibles = estado.facultadesDisponibles,
                    alSeleccionar = viewModel::seleccionarFacultadFormulario
                )

                // Campo descripción
                OutlinedTextField(
                    value = estado.descripcion,
                    onValueChange = viewModel::actualizarDescripcion,
                    label = { Text("Descripción") },
                    placeholder = { Text("Ingresa la descripción de la facultad...") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp),
                    maxLines = 5,
                    singleLine = false
                )

                // Campo año
                OutlinedTextField(
                    value = estado.año,
                    onValueChange = viewModel::actualizarAño,
                    label = { Text("Año de creación") },
                    placeholder = { Text("Ej: 1985") },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    singleLine = true
                )

                // Botón enviar
                Button(
                    onClick = viewModel::enviarFormulario,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onPrimary
                    )
                ) {
                    Text(
                        text = "Agregar Facultad",
                        style = AppTypography.labelLarge
                    )
                }
            } else {
                // No hay facultades disponibles
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant
                    )
                ) {
                    Text(
                        text = "No hay facultades disponibles para agregar.\nVe a visualización para eliminar algunas.",
                        modifier = Modifier.padding(Tamaños.EspacioGrande),
                        textAlign = TextAlign.Center,
                        style = AppTypography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                Button(
                    onClick = alIrAVisualizacion,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Ir a Visualización", style = AppTypography.labelLarge)
                }
            }

            // Mensajes de error
            if (estado.mensajeError.isNotEmpty()) {
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.errorContainer
                    )
                ) {
                    Text(
                        text = estado.mensajeError,
                        modifier = Modifier.padding(Tamaños.EspacioChico),
                        style = AppTypography.bodyMedium,
                        color = MaterialTheme.colorScheme.onErrorContainer
                    )
                }
            }

            // Mensajes de éxito
            if (estado.mensajeExito.isNotEmpty()) {
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = ColoresApp.Confirmacion.copy(alpha = 0.1f)
                    )
                ) {
                    Text(
                        text = estado.mensajeExito,
                        modifier = Modifier.padding(Tamaños.EspacioChico),
                        style = AppTypography.bodyMedium,
                        color = ColoresApp.Confirmacion
                    )
                }
            }
        }

        PiePagina()
    }
}
