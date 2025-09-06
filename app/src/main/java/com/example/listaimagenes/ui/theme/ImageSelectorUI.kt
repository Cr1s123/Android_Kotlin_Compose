package com.example.listaimagenes.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.listaimagenes.viewmodel.ImageSelectorViewModel
import com.example.listaimagenes.R
import kotlinx.coroutines.delay
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ImageSelectorUI(viewModel: ImageSelectorViewModel) {
    val seleccion by viewModel.seleccion.collectAsState()
    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        //  CABECERA CON TOOLBAR
        TopAppBar(
            title = {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,

                    modifier = Modifier.fillMaxWidth()
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.logo_informatica),
                        contentDescription = "Logo institucional",
                        modifier = Modifier.size(40.dp)
                    )
                    Text(
                        text = "Facultades de la UNP",
                        fontSize = 20.sp,
                        color = Color.White
                    )
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF00695C))
        )

        // CONTENIDO CENTRAL
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box {
                OutlinedTextField(
                    value = seleccion?.nombre ?: "",
                    onValueChange = {},
                    label = { Text("Selecciona una facultad") },
                    readOnly = true,
                    trailingIcon = {
                        IconButton(onClick = { expanded = !expanded }) {
                            Icon(Icons.Default.ArrowDropDown, contentDescription = null)
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                )

                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    viewModel.nombres.forEach { nombre ->
                        DropdownMenuItem(
                            text = { Text(nombre) },
                            onClick = {
                                viewModel.seleccionar(nombre)
                                expanded = false
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            seleccion?.let {
                Image(
                    painter = painterResource(id = it.recursoId),
                    contentDescription = it.nombre,
                    modifier = Modifier.size(200.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = it.descripcion,
                    fontSize = 16.sp
                )
            }
        }

        //  PIE DE PÁGINA

        val horaActual = HoraActualView()


        Text(
            text = "© 2025 Pasache • $horaActual • Piura, Perú",
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            textAlign = TextAlign.Center,
            fontSize = 12.sp,
            color = Color.Gray
        )
    }
}

@Composable
fun HoraActualView(): String {
    var horaActual by remember { mutableStateOf(obtenerHoraActual()) }

    // Actualiza la hora cada segundo
    LaunchedEffect(Unit) {
        while (true) {
            horaActual = obtenerHoraActual()
            delay(1000) // 1 segundo
        }
    }

    return horaActual
}

fun obtenerHoraActual(): String {
    val formato = SimpleDateFormat("hh:mm:ss a", Locale("es", "PE"))
    return formato.format(Date())
}