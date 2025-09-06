package com.example.listaimagenes.viewmodel


import androidx.lifecycle.ViewModel
import com.example.listaimagenes.R
import com.example.listaimagenes.model.ImagenItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


class ImageSelectorViewModel : ViewModel() {

    private val _imagenes = listOf(
        ImagenItem(
                "FACULTAD DE AGRONOMÍA",
                "La Facultad de Agronomía nace un año posterior a la creación de la Universidad " +
                "Nacional de Piura, el 21 de mayo de 1962, formando ingenieros agrónomos capaces " +
                "de proponer acertadas alternativas para el desarrollo agrícola de la región en las " +
                "disciplinas de producción agrícola, hortícola, ingeniería agrícola, protección de " +
                "plantas, manejo y conservación de suelos.",
                R.drawable.agronomia),
        ImagenItem(
                "FACULTAD DE ARQUITECTURA Y URBANISMO",
                "La Facultad de Arquitectura y Urbanismo de la Universidad Nacional de Piura fue creada " +
                        "el 17 de mayo del año 1996 mediante Resolución de Asamblea Universitaria UNP, " +
                        "Nº 004-AU-96, inició sus actividades académicas en el mes de agosto del mismo año con la " +
                        "finalidad de formar a los especialistas en el campo del diseño arquitectónico, el diseño y la " +
                        "planificación urbana, así como en la construcción, de acuerdo a las especificidades demandadas " +
                        "por el país y de región.",
                R.drawable.arquitectura),
        ImagenItem(
                "FACULTAD DE INGENIERÍA INDUSTRIAL",
                "La Facultad de Ingeniería Industrial de la Universidad Nacional de Piura, se crea un " +
                        "sábado 31 de diciembre de 1966, mediante Resolución No 476-CU-66 con el nombre " +
                        "de Escuela de Ingeniería Industrial.",
                R.drawable.industrial),
        ImagenItem(
                "FACULTAD DE CIENCIAS ADMINISTRATIVAS",
                "El 3 de diciembre de 1979, la especialidad se independizó de Contabilidad, obteniendo autonomía académica y administrativa con la Resolución N.º 1129-R-79. Con la Ley N.º 23733, la especialidad se convirtió en Facultad dentro de la UNP.",
                R.drawable.administracion)
    )

    private val _seleccion = MutableStateFlow<ImagenItem?>(null)
    val seleccion: StateFlow<ImagenItem?> = _seleccion

    val nombres: List<String> = _imagenes.map { it.nombre }

    fun seleccionar(nombre: String) {
        _seleccion.value = _imagenes.find { it.nombre == nombre }
    }
}