package br.com.fiap.challengequod.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.fiap.challengequod.ui.theme.BlueQuod
import br.com.fiap.challengequod.ui.theme.PurpleQuod
import br.com.fiap.challengequod.ui.theme.WhiteQuod

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IdentityVerificationScreen(navController: NavController) {

    // Estados para armazenar valores dos campos
    var cpfValue by remember { mutableStateOf("") }
    var nameValue by remember { mutableStateOf("") }
    var phoneValue by remember { mutableStateOf("") }
    var cepValue by remember { mutableStateOf("") }
    var streetValue by remember { mutableStateOf("") }
    var numberValue by remember { mutableStateOf("") }
    var complementValue by remember { mutableStateOf("") }
    var neighborhoodValue by remember { mutableStateOf("") }
    var cityValue by remember { mutableStateOf("") }
    var stateValue by remember { mutableStateOf("") }


    val textFieldColors = TextFieldDefaults.textFieldColors(
        containerColor = WhiteQuod,
        focusedIndicatorColor = BlueQuod,
        unfocusedIndicatorColor = Color.LightGray
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(WhiteQuod)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(32.dp))

            // Ícone e cabeçalho
            Icon(
                imageVector = Icons.Default.Person, // Ícone temporário de exemplo
                contentDescription = "Formulário de Registro",
                modifier = Modifier.size(80.dp),
                tint = BlueQuod
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Formulário de Registro",
                style = MaterialTheme.typography.titleLarge,
                color = PurpleQuod
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Campo CPF
            TextField(
                value = cpfValue,
                onValueChange = { newValue ->
                    cpfValue = newValue.filter { it.isDigit() }.take(11)
                },
                label = { Text("Digite o CPF") },
                placeholder = { Text("000.000.000-00") },
                modifier = Modifier
                    .fillMaxWidth(),
                singleLine = true,
                textStyle = MaterialTheme.typography.bodyMedium,
                colors = textFieldColors
            )

            // Campo Nome
            TextField(
                value = nameValue,
                onValueChange = { nameValue = it },
                label = { Text("Nome Completo") },
                placeholder = { Text("Digite seu nome completo") },
                modifier = Modifier
                    .fillMaxWidth(),
                singleLine = true,
                textStyle = MaterialTheme.typography.bodyMedium,
                colors = textFieldColors
            )

            // Campo Telefone
            TextField(
                value = phoneValue,
                onValueChange = { phoneValue = it.filter { char -> char.isDigit() }.take(11) },
                label = { Text("Telefone Celular") },
                placeholder = { Text("(00) 00000-0000") },
                modifier = Modifier
                    .fillMaxWidth(),
                singleLine = true,
                textStyle = MaterialTheme.typography.bodyMedium,
                colors = textFieldColors
            )

            // Endereço
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Endereço",
                style = MaterialTheme.typography.bodyLarge,
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ){
                TextField(
                    value = cepValue,
                    onValueChange = { cepValue = it.filter { char -> char.isDigit()}.take(8)},
                    label = { Text("CEP")},
                    placeholder = { Text("00000-000")},
                    modifier = Modifier
                        .width(150.dp),
                    singleLine = true,
                    textStyle = MaterialTheme.typography.bodyMedium,
                    colors = textFieldColors
                )

                //Icone de pesquisa
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription ="Pesquisa CEP",
                    modifier = Modifier.size(30.dp),
                    tint = BlueQuod
                )
            }

            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                TextField(
                    value = streetValue,
                    onValueChange = { streetValue = it },
                    label = { Text("Logradouro") },
                    placeholder = { Text("Rua, Avenida, etc.") },
                    modifier = Modifier
                        .width(300.dp),
                    singleLine = true,
                    textStyle = MaterialTheme.typography.bodyMedium,
                    colors = textFieldColors
                )

                TextField(
                    value = numberValue,
                    onValueChange = { numberValue = it.filter { char -> char.isDigit() } },
                    label = { Text("Nº") },
                    placeholder = { Text("Nº") },
                    modifier = Modifier
                        .width(70.dp),
                    singleLine = true,
                    textStyle = MaterialTheme.typography.bodyMedium,
                    colors = textFieldColors
                )
            }

            Row (
                modifier = Modifier.fillMaxWidth()
            ) {
                TextField(
                    value = complementValue,
                    onValueChange = { complementValue = it },
                    label = { Text("Complemento") },
                    placeholder = { Text("Apartamento, bloco, etc.") },
                    modifier = Modifier
                        .fillMaxWidth(),
                    singleLine = true,
                    textStyle = MaterialTheme.typography.bodyMedium,
                    colors = textFieldColors
                )
            }

            Row (
                modifier = Modifier.fillMaxWidth()
            ) {
                TextField(
                    value = neighborhoodValue,
                    onValueChange = { neighborhoodValue = it },
                    label = { Text("Bairro") },
                    placeholder = { Text("Digite o bairro") },
                    modifier = Modifier
                        .fillMaxWidth(),
                    singleLine = true,
                    textStyle = MaterialTheme.typography.bodyMedium,
                    colors = textFieldColors
                )
            }

            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                TextField(
                    value = cityValue,
                    onValueChange = { cityValue = it },
                    label = { Text("Cidade") },
                    placeholder = { Text("Digite a cidade") },
                    modifier = Modifier
                        .width(280.dp),
                    //.padding(vertical = 8.dp),
                    singleLine = true,
                    textStyle = MaterialTheme.typography.bodyMedium,
                    colors = textFieldColors
                )
                TextField(
                    value = stateValue,
                    onValueChange = { stateValue = it.take(2).uppercase() },
                    label = { Text("UF") },
                    placeholder = { Text("Ex: SP") },
                    modifier = Modifier
                        .width(80.dp),
                    //.padding(vertical = 8.dp),
                    singleLine = true,
                    textStyle = MaterialTheme.typography.bodyMedium,
                    colors = textFieldColors
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Botão de Validar
            Button(
                onClick = { /* Lógica para salvar os dados */ },
                colors = ButtonDefaults.buttonColors(containerColor = BlueQuod),
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = "Validar",
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