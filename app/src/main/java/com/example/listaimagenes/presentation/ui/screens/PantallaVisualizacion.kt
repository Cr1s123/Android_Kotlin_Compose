package com.example.listaimagenes.presentation.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.listaimagenes.presentation.theme.Tamaños
import com.example.listaimagenes.presentation.ui.components.BarraSuperior
import com.example.listaimagenes.presentation.ui.components.DialogoConfirmacion
import com.example.listaimagenes.presentation.ui.components.MenuFacultadesAgregadas
import com.example.listaimagenes.presentation.ui.components.MostrarFacultadAgregada
import com.example.listaimagenes.presentation.ui.components.PiePagina
import com.example.listaimagenes.presentation.utils.AccionesVisualizacion
import com.example.listaimagenes.presentation.utils.MensajeVacio
import com.example.listaimagenes.presentation.viewmodel.ViewModelFormulario
@Composable
fun PantallaVisualizacion(
    alVolverFormulario: () -> Unit
) {
    val viewModel: ViewModelFormulario = viewModel()
    val estado by viewModel.estado.collectAsState()

    Column(modifier = Modifier.fillMaxSize()) {
        BarraSuperior(titulo = "Facultades Agregadas")

        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(Tamaños.EspacioGrande),
            verticalArrangement = Arrangement.spacedBy(Tamaños.EspacioChico)
        ) {
            if (estado.facultadesAgregadas.isNotEmpty()) {
                MenuFacultadesAgregadas(
                    facultadSeleccionada = estado.facultadSeleccionadaVisualizacion,
                    facultadesAgregadas = estado.facultadesAgregadas,
                    alSeleccionar = viewModel::seleccionarFacultadVisualizacion
                )

                AccionesVisualizacion(
                    alVolverFormulario = alVolverFormulario,
                    alLimpiarTodo = viewModel::mostrarConfirmacionLimpiar
                )

                estado.facultadSeleccionadaVisualizacion?.let { facultad ->
                    Spacer(modifier = Modifier.height(Tamaños.EspacioGrande))
                    MostrarFacultadAgregada(
                        facultad = facultad,
                        alEliminar = { viewModel.mostrarConfirmacionEliminar(facultad.nombre) }
                    )
                }
            } else {
                MensajeVacio(
                    mensaje = "No hay facultades agregadas aún.",
                    textoBoton = "Agregar Primera Facultad",
                    onClick = alVolverFormulario
                )
            }
        }

        PiePagina()
    }

    // Diálogos
    if (estado.mostrarConfirmacionEliminar) {
        DialogoConfirmacion(
            titulo = "Eliminar Facultad",
            mensaje = "¿Estás seguro de que deseas eliminar '${estado.facultadAEliminar}'?",
            alConfirmar = viewModel::confirmarEliminar,
            alCancelar = viewModel::cancelarEliminar
        )
    }

    if (estado.mostrarConfirmacionLimpiar) {
        DialogoConfirmacion(
            titulo = "Limpiar Todo",
            mensaje = "¿Estás seguro de que deseas eliminar todas las facultades agregadas?",
            alConfirmar = viewModel::confirmarLimpiar,
            alCancelar = viewModel::cancelarLimpiar
        )
    }
}
