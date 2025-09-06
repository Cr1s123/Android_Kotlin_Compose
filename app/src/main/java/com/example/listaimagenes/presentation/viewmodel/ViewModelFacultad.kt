package com.example.listaimagenes.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.listaimagenes.data.model.Facultad
import com.example.listaimagenes.domain.usecase.CasoUsoFacultades
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class EstadoFacultades(
    val facultadSeleccionada: Facultad? = null,
    val facultades: List<Facultad> = emptyList(),
    val nombresFacultades: List<String> = emptyList()
)

class ViewModelFacultad(
    private val casoUso: CasoUsoFacultades
) : ViewModel() {

    private val _estado = MutableStateFlow(EstadoFacultades())
    val estado: StateFlow<EstadoFacultades> = _estado.asStateFlow()

    init {
        cargarFacultades()
    }

    private fun cargarFacultades() {
        val facultades = casoUso.obtenerTodas()
        _estado.value = _estado.value.copy(
            facultades = facultades,
            nombresFacultades = facultades.map { it.nombre }
        )
    }

    fun seleccionarFacultad(nombre: String) {
        val facultad = casoUso.buscarPorNombre(nombre)
        _estado.value = _estado.value.copy(facultadSeleccionada = facultad)
    }
}
