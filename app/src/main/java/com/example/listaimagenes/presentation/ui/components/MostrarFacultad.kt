package com.example.listaimagenes.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import com.example.listaimagenes.data.model.Facultad
import com.example.listaimagenes.presentation.theme.Tamaños

@Composable
fun MostrarFacultad(
    facultad: Facultad,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = facultad.imagen),
            contentDescription = facultad.nombre,
            modifier = Modifier.size(Tamaños.ImagenFacultad)
        )

        Spacer(modifier = Modifier.height(Tamaños.EspacioChico))

        Text(
            text = facultad.descripcion,
            fontSize = Tamaños.TextoNormal,
            textAlign = TextAlign.Justify
        )
    }
}