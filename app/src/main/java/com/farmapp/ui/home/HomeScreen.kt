package com.farmapp.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.QrCodeScanner
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.SmartToy
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.farmapp.ui.auth.AuthViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    authViewModel: AuthViewModel,
    onLogout: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Farm APP - Catálogo y Servicios") },
                actions = {
                    IconButton(onClick = { authViewModel.logout(onLogout) }) {
                        Icon(Icons.Default.ExitToApp, contentDescription = "Cerrar sesión")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "¡Bienvenido a Farm APP!",
                style = MaterialTheme.typography.titleLarge
            )

            Card(modifier = Modifier.fillMaxWidth()) {
                ListItem(
                    headlineContent = { Text("Escanear Código de Barras / Receta") },
                    supportingContent = { Text("Cámara integrada con ML Kit OCR") },
                    leadingContent = { Icon(Icons.Default.QrCodeScanner, contentDescription = null) }
                )
            }

            Card(modifier = Modifier.fillMaxWidth()) {
                ListItem(
                    headlineContent = { Text("Carrito de Medicamentos") },
                    supportingContent = { Text("Soporte offline con persistencia Room") },
                    leadingContent = { Icon(Icons.Default.ShoppingCart, contentDescription = null) }
                )
            }

            Card(modifier = Modifier.fillMaxWidth()) {
                ListItem(
                    headlineContent = { Text("Asistente Virtual Farmacéutico") },
                    supportingContent = { Text("Consultas de posología 24/7 con Chatbot LLM") },
                    leadingContent = { Icon(Icons.Default.SmartToy, contentDescription = null) }
                )
            }
        }
    }
}
