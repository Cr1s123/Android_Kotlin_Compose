package com.example.listaimagenes.domain

import com.example.listaimagenes.data.repository.RepositorioFacultad
import com.example.listaimagenes.domain.usecase.CasoUsoFormulario

object FacultadManager {
    private val repositorio: RepositorioFacultad by lazy {
        RepositorioFacultad()
    }

    val casoUso: CasoUsoFormulario by lazy {
        CasoUsoFormulario(repositorio)
    }
}