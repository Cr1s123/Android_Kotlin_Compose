package com.example.listaimagenes.data.repository

import com.example.listaimagenes.R
import com.example.listaimagenes.data.model.Facultad

class RepositorioFacultad{
    fun obtenerFacultades(): List<Facultad> = listOf(
        Facultad(
            nombre = "Agronomía",
            descripcion = "La Facultad de Agronomía nace un año posterior a la creación de la Universidad " +
                    "Nacional de Piura, el 21 de mayo de 1962, formando ingenieros agrónomos capaces " +
                    "de proponer alternativas para el desarrollo agrícola de la región.",
            imagen = R.drawable.agronomia
        ),
        Facultad(
            nombre = "Arquitectura y Urbanismo",
            descripcion = "Creada el 17 de mayo de 1996, forma especialistas en diseño arquitectónico, " +
                    "planificación urbana y construcción, de acuerdo a las necesidades del país y la región.",
            imagen = R.drawable.arquitectura
        ),
        Facultad(
            nombre = "Ingeniería Industrial",
            descripcion = "Creada el 31 de diciembre de 1966 mediante Resolución No 476-CU-66 con el nombre " +
                    "de Escuela de Ingeniería Industrial.",
            imagen = R.drawable.industrial
        ),
        Facultad(
            nombre = "Ciencias Administrativas",
            descripcion = "El 3 de diciembre de 1979, la especialidad obtuvo autonomía académica y administrativa. " +
                    "Con la Ley N.º 23733, se convirtió en Facultad dentro de la UNP.",
            imagen = R.drawable.administracion
        )
    )
}