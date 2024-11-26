package br.com.fiap.challengequod.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.fiap.challengequod.ui.theme.BlackQuod
import br.com.fiap.challengequod.ui.theme.BlueQuod
import br.com.fiap.challengequod.ui.theme.PurpleQuod
import br.com.fiap.challengequod.ui.theme.WhiteQuod

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CheckScoreScreen(navController: NavController) {
    var cpfValue by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(WhiteQuod)
    ) {
        // Conteúdo Principal
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Cabeçalho clean
            Spacer(modifier = Modifier.height(32.dp))
            Icon(
                imageVector = Icons.Default.Check, // Ícone de exemplo
                contentDescription = "Ícone do Serviço",
                modifier = Modifier.size(80.dp),
                tint = BlueQuod
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Consultar Score",
                color = PurpleQuod,
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(40.dp))

            // Campo CPF
            TextField(
                value = cpfValue,
                onValueChange = { newValue ->
                    cpfValue = newValue.filter { it.isDigit() }.take(11)
                },
                label = { Text("Digite o CPF") },
                placeholder = { Text("000.000.000-00") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                singleLine = true,
                textStyle = MaterialTheme.typography.bodyMedium,
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = WhiteQuod,
                    focusedIndicatorColor = BlueQuod,
                    unfocusedIndicatorColor = Color.LightGray
                )
            )

            Spacer(modifier = Modifier.height(40.dp))

            // Botão de Verificação
            Button(
                onClick = { /* Ação do botão */ },
                colors = ButtonDefaults.buttonColors(containerColor = BlueQuod),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = "Consultar Score",
                    color = WhiteQuod,
                    style = MaterialTheme.typography.bodyLarge,
                    fontSize = 20.sp
                )
            }
        }

        // Botão Voltar
        IconButton(
            onClick = {navController.navigate("home")},
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Voltar para a Home",
                tint = BlueQuod,
                modifier = Modifier
                    .size(40.dp)
            )
        }
    }
}
