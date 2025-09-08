package com.example.listaimagenes.data.repository

import com.example.listaimagenes.R
import com.example.listaimagenes.data.model.Facultad

class RepositorioFacultad {
    private val _facultadesAgregadas = mutableListOf<Facultad>()

    private val mapaImagenes = mapOf(
        "Agronomía" to R.drawable.agronomia,
        "Arquitectura y Urbanismo" to R.drawable.arquitectura,
        "Ciencias Administrativas" to R.drawable.administracion,
        "Ciencias Contables y Financieras" to R.drawable.contabilidad,
        "Ciencias" to R.drawable.ciencias,
        "Ciencias de la Salud" to R.drawable.salud,
        "Ciencias Sociales y Educación" to R.drawable.educacion,
        "Derecho y Ciencias Políticas" to R.drawable.derecho,
        "Economía" to R.drawable.economia,
        "Ingeniería Civil" to R.drawable.civil,
        "Ingeniería Industrial" to R.drawable.industrial,
        "Ingeniería de Minas" to R.drawable.minas,
        "Ingeniería Pesquera" to R.drawable.pesquera,
        "Ingeniería Zootecnia" to R.drawable.zootecnia
    )


    private val facultadesBase = listOf(
        "Agronomía",
        "Arquitectura y Urbanismo",
        "Ciencias Administrativas",
        "Ciencias Contables y Financieras",
        "Ciencias",
        "Ciencias de la Salud",
        "Ciencias Sociales y Educación",
        "Derecho y Ciencias Políticas",
        "Economía",
        "Ingeniería Civil",
        "Ingeniería Industrial",
        "Ingeniería de Minas",
        "Ingeniería Pesquera",
        "Ingeniería Zootecnia"
    )


    fun obtenerFacultadesDisponibles(): List<String> {
        val nombresAgregados = _facultadesAgregadas.map { it.nombre }
        return facultadesBase.filter { it !in nombresAgregados }
    }

    fun obtenerFacultadesAgregadas(): List<Facultad> {
        return _facultadesAgregadas.toList()
    }

    fun agregarFacultad(nombre: String, descripcion: String, año: Int): Boolean {
        val imagen = mapaImagenes[nombre] ?: return false

        if (_facultadesAgregadas.any { it.nombre == nombre }) {
            return false
        }

        val nuevaFacultad = Facultad(
            nombre = nombre,
            descripcion = descripcion,
            año = año,
            imagen = imagen
        )

        _facultadesAgregadas.add(nuevaFacultad)
        return true
    }

    fun eliminarFacultad(nombre: String): Boolean {
        return _facultadesAgregadas.removeAll { it.nombre == nombre }
    }

    fun limpiarTodas() {
        _facultadesAgregadas.clear()
    }

    fun buscarPorNombre(nombre: String): Facultad? {
        return _facultadesAgregadas.find { it.nombre == nombre }
    }
}
