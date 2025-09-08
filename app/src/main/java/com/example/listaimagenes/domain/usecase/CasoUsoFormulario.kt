package com.example.listaimagenes.domain.usecase

import com.example.listaimagenes.data.model.Facultad
import com.example.listaimagenes.data.repository.RepositorioFacultad

class CasoUsoFormulario(
    private val repositorio: RepositorioFacultad
) {
    fun obtenerFacultadesDisponibles(): List<String> {
        return repositorio.obtenerFacultadesDisponibles()
    }

    fun obtenerFacultadesAgregadas(): List<Facultad> {
        return repositorio.obtenerFacultadesAgregadas()
    }

    fun agregarFacultad(nombre: String, descripcion: String, año: Int): Boolean {
        return if (descripcion.isNotBlank() && año > 0) {
            repositorio.agregarFacultad(nombre, descripcion, año)
        } else {
            false
        }
    }

    fun eliminarFacultad(nombre: String): Boolean {
        return repositorio.eliminarFacultad(nombre)
    }

    fun limpiarTodas() {
        repositorio.limpiarTodas()
    }

    fun buscarPorNombre(nombre: String): Facultad? {
        return repositorio.buscarPorNombre(nombre)
    }
}