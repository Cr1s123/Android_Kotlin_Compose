package com.example.listaimagenes.presentation.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.listaimagenes.data.repository.RepositorioFacultad
import com.example.listaimagenes.domain.usecase.CasoUsoFacultades
import com.example.listaimagenes.presentation.theme.Tamaños
import com.example.listaimagenes.presentation.ui.components.BarraSuperior
import com.example.listaimagenes.presentation.ui.components.MenuFacultades
import com.example.listaimagenes.presentation.ui.components.MostrarFacultad
import com.example.listaimagenes.presentation.ui.components.PiePagina
import com.example.listaimagenes.presentation.viewmodel.ViewModelFacultad

@Composable
fun PantallaPrincipal() {
    val repositorio = remember { RepositorioFacultad() }
    val casoUso = remember { CasoUsoFacultades(repositorio) }
    val viewModel: ViewModelFacultad = viewModel{
        ViewModelFacultad(casoUso)
    }

    val estado by viewModel.estado.collectAsState()

    Column(modifier = Modifier.fillMaxSize()) {
        BarraSuperior()

        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(Tamaños.EspacioGrande),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            MenuFacultades(
                facultadSeleccionada = estado.facultadSeleccionada,
                listaFacultades = estado.nombresFacultades,
                alSeleccionar = viewModel::seleccionarFacultad
            )

            Spacer(modifier = Modifier.height(Tamaños.EspacioGrande))

            estado.facultadSeleccionada?.let { facultad ->
                MostrarFacultad(facultad = facultad)
            }
        }

        PiePagina()
    }
}
