package com.example.listaimagenes.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.listaimagenes.data.model.Facultad
import com.example.listaimagenes.domain.FacultadManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class EstadoFormulario(
    val facultadesDisponibles: List<String> = emptyList(),
    val facultadesAgregadas: List<Facultad> = emptyList(),
    val facultadSeleccionadaFormulario: String = "",
    val descripcion: String = "",
    val año: String = "",
    val facultadSeleccionadaVisualizacion: Facultad? = null,
    val mostrarConfirmacionEliminar: Boolean = false,
    val facultadAEliminar: String = "",
    val mostrarConfirmacionLimpiar: Boolean = false,
    val mensajeError: String = "",
    val mensajeExito: String = ""
)

class ViewModelFormulario : ViewModel() {
    private val casoUso = FacultadManager.casoUso
    private val _estado = MutableStateFlow(EstadoFormulario())
    val estado: StateFlow<EstadoFormulario> = _estado.asStateFlow()

    init {
        cargarDatos()
    }

    private fun cargarDatos() {
        val disponibles = casoUso.obtenerFacultadesDisponibles()
        val agregadas = casoUso.obtenerFacultadesAgregadas()

        _estado.update { estadoActual ->
            estadoActual.copy(
                facultadesDisponibles = disponibles,
                facultadesAgregadas = agregadas,
                facultadSeleccionadaVisualizacion = estadoActual.facultadSeleccionadaVisualizacion
                    ?.takeIf { facultadActual ->
                        agregadas.any { it.nombre == facultadActual.nombre }
                    }
            )
        }
    }

    fun seleccionarFacultadFormulario(nombre: String) {
        _estado.update { it.copy(facultadSeleccionadaFormulario = nombre) }
    }

    fun actualizarDescripcion(descripcion: String) {
        _estado.update { it.copy(descripcion = descripcion, mensajeError = "", mensajeExito = "") }
    }

    fun actualizarAño(año: String) {
        _estado.update { it.copy(año = año, mensajeError = "", mensajeExito = "") }
    }

    fun enviarFormulario() {
        val estadoActual = _estado.value
        val añoInt = estadoActual.año.toIntOrNull() ?: -1

        val exito = casoUso.agregarFacultad(
            estadoActual.facultadSeleccionadaFormulario,
            estadoActual.descripcion,
            añoInt
        )

        if (exito) {
            _estado.update {
                it.copy(
                    mensajeExito = "Facultad agregada exitosamente",
                    mensajeError = "",
                    facultadSeleccionadaFormulario = "",
                    descripcion = "",
                    año = ""
                )
            }
            cargarDatos()
        } else {
            _estado.update {
                it.copy(mensajeError = "Error al agregar la facultad", mensajeExito = "")
            }
        }
    }

    fun seleccionarFacultadVisualizacion(facultad: Facultad) {
        _estado.update { it.copy(facultadSeleccionadaVisualizacion = facultad) }
    }

    fun mostrarConfirmacionEliminar(nombre: String) {
        _estado.update {
            it.copy(
                mostrarConfirmacionEliminar = true,
                facultadAEliminar = nombre
            )
        }
    }

    fun confirmarEliminar() {
        val nombre = _estado.value.facultadAEliminar
        casoUso.eliminarFacultad(nombre)

        _estado.update {
            it.copy(
                mostrarConfirmacionEliminar = false,
                facultadAEliminar = "",
                facultadSeleccionadaVisualizacion = null
            )
        }
        cargarDatos()
    }

    fun cancelarEliminar() {
        _estado.update {
            it.copy(
                mostrarConfirmacionEliminar = false,
                facultadAEliminar = ""
            )
        }
    }

    fun mostrarConfirmacionLimpiar() {
        _estado.update { it.copy(mostrarConfirmacionLimpiar = true) }
    }

    fun confirmarLimpiar() {
        casoUso.limpiarTodas()
        _estado.update {
            it.copy(
                mostrarConfirmacionLimpiar = false,
                facultadSeleccionadaVisualizacion = null
            )
        }
        cargarDatos()
    }

    fun cancelarLimpiar() {
        _estado.update { it.copy(mostrarConfirmacionLimpiar = false) }
    }

    fun limpiarMensajes() {
        _estado.update { it.copy(mensajeError = "", mensajeExito = "") }
    }
}