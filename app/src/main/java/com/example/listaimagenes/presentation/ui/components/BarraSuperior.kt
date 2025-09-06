package com.example.listaimagenes.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.listaimagenes.presentation.theme.ColoresApp
import com.example.listaimagenes.presentation.theme.Tamaños
import com.example.listaimagenes.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BarraSuperior(
    titulo: String = "Facultades UNP",
    logoId: Int = R.drawable.logo_informatica
) {
    TopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = logoId),
                    contentDescription = "Logo institucional",
                    modifier = Modifier.size(Tamaños.Logo)
                )
                Text(
                    text = titulo,
                    fontSize = Tamaños.TextoTitulo,
                    color = ColoresApp.TextoBlanco
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = ColoresApp.Principal)
    )
}