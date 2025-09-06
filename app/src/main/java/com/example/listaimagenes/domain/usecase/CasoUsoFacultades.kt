package com.example.listaimagenes.domain.usecase

import com.example.listaimagenes.data.model.Facultad
import com.example.listaimagenes.data.repository.RepositorioFacultad

class CasoUsoFacultades(
    private val repositorio: RepositorioFacultad
) {
    fun obtenerTodas(): List<Facultad> = repositorio.obtenerFacultades()

    fun buscarPorNombre(nombre: String): Facultad? {
        return repositorio.obtenerFacultades().find { it.nombre == nombre }
    }
}