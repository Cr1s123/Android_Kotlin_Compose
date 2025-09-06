package com.example.listaimagenes.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.listaimagenes.data.model.FacultadAgregada
import com.example.listaimagenes.domain.usecase.CasoUsoFormulario
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class EstadoFormulario(
    val facultadesDisponibles: List<String> = emptyList(),
    val facultadesAgregadas: List<FacultadAgregada> = emptyList(),
    val facultadSeleccionadaFormulario: String = "",
    val descripcion: String = "",
    val año: String = "",
    val facultadSeleccionadaVisualizacion: FacultadAgregada? = null,
    val mostrarConfirmacionEliminar: Boolean = false,
    val facultadAEliminar: String = "",
    val mostrarConfirmacionLimpiar: Boolean = false,
    val mensajeError: String = "",
    val mensajeExito: String = ""
)

class ViewModelFormulario(
    private val casoUso: CasoUsoFormulario
) : ViewModel() {

    private val _estado = MutableStateFlow(EstadoFormulario())
    val estado: StateFlow<EstadoFormulario> = _estado.asStateFlow()

    init {
        cargarDatos()
    }

    private fun cargarDatos() {
        val disponibles = casoUso.obtenerFacultadesDisponibles()
        val agregadas = casoUso.obtenerFacultadesAgregadas()

        _estado.value = _estado.value.copy(
            facultadesDisponibles = disponibles,
            facultadesAgregadas = agregadas,
            facultadSeleccionadaFormulario = disponibles.firstOrNull() ?: ""
        )
    }

    fun seleccionarFacultadFormulario(nombre: String) {
        _estado.value = _estado.value.copy(facultadSeleccionadaFormulario = nombre)
    }

    fun actualizarDescripcion(descripcion: String) {
        _estado.value = _estado.value.copy(descripcion = descripcion)
    }

    fun actualizarAño(año: String) {
        _estado.value = _estado.value.copy(año = año)
    }

    fun enviarFormulario() {
        val estadoActual = _estado.value

        // Validaciones
        if (estadoActual.descripcion.isBlank()) {
            _estado.value = estadoActual.copy(mensajeError = "La descripción es requerida")
            return
        }

        if (estadoActual.descripcion.length < 50) {
            _estado.value = estadoActual.copy(mensajeError = "La descripción debe tener al menos 50 caracteres")
            return
        }

        val añoInt = estadoActual.año.toIntOrNull()
        if (añoInt == null || añoInt < 1960 || añoInt > 2024) {
            _estado.value = estadoActual.copy(mensajeError = "El año debe estar entre 1960 y 2024")
            return
        }

        // Intentar agregar
        val exito = casoUso.agregarFacultad(
            estadoActual.facultadSeleccionadaFormulario,
            estadoActual.descripcion,
            añoInt
        )

        if (exito) {
            _estado.value = estadoActual.copy(
                mensajeExito = "Facultad agregada exitosamente",
                mensajeError = "",
                descripcion = "",
                año = ""
            )
            cargarDatos()
        } else {
            _estado.value = estadoActual.copy(mensajeError = "Error al agregar la facultad")
        }
    }

    fun seleccionarFacultadVisualizacion(nombre: String) {
        val facultad = casoUso.buscarPorNombre(nombre)
        _estado.value = _estado.value.copy(facultadSeleccionadaVisualizacion = facultad)
    }

    fun mostrarConfirmacionEliminar(nombre: String) {
        _estado.value = _estado.value.copy(
            mostrarConfirmacionEliminar = true,
            facultadAEliminar = nombre
        )
    }

    fun confirmarEliminar() {
        casoUso.eliminarFacultad(_estado.value.facultadAEliminar)
        _estado.value = _estado.value.copy(
            mostrarConfirmacionEliminar = false,
            facultadAEliminar = "",
            facultadSeleccionadaVisualizacion = null
        )
        cargarDatos()
    }

    fun cancelarEliminar() {
        _estado.value = _estado.value.copy(
            mostrarConfirmacionEliminar = false,
            facultadAEliminar = ""
        )
    }

    fun mostrarConfirmacionLimpiar() {
        _estado.value = _estado.value.copy(mostrarConfirmacionLimpiar = true)
    }

    fun confirmarLimpiar() {
        casoUso.limpiarTodas()
        _estado.value = _estado.value.copy(
            mostrarConfirmacionLimpiar = false,
            facultadSeleccionadaVisualizacion = null
        )
        cargarDatos()
    }

    fun cancelarLimpiar() {
        _estado.value = _estado.value.copy(mostrarConfirmacionLimpiar = false)
    }

    fun limpiarMensajes() {
        _estado.value = _estado.value.copy(mensajeError = "", mensajeExito = "")
    }
}