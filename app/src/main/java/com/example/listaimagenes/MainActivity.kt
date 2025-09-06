package com.example.listaimagenes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.listaimagenes.ui.theme.ImageSelectorUI
import com.example.listaimagenes.ui.theme.ListaImagenesTheme
import com.example.listaimagenes.viewmodel.ImageSelectorViewModel
import kotlin.getValue

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Instancia del ViewModel
        val imageSelectorViewModel: ImageSelectorViewModel by viewModels()

        setContent {
            ImageSelectorUI(viewModel = imageSelectorViewModel)
        }
    }
}
