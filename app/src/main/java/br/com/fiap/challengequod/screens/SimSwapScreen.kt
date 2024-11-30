package br.com.fiap.challengequod.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
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
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Call
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.fiap.challengequod.R
import br.com.fiap.challengequod.ui.theme.BlackQuod
import br.com.fiap.challengequod.ui.theme.BlueQuod
import br.com.fiap.challengequod.ui.theme.PurpleQuod
import br.com.fiap.challengequod.ui.theme.WhiteQuod
import br.com.fiap.challengequod.utils.getFailureData
import br.com.fiap.challengequod.utils.getResetData
import br.com.fiap.challengequod.utils.getSuccessData

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SimSwapScreen(navController: NavController) {
    var cpfValue by remember { mutableStateOf("") }
    var phoneNumberValue by remember { mutableStateOf("") }

    val textFieldColors = TextFieldDefaults.textFieldColors(
        containerColor = WhiteQuod,
        focusedIndicatorColor = BlueQuod,
        unfocusedIndicatorColor = Color.LightGray
    )

    // Variável para armazenar o resultado
    var result by remember { mutableStateOf(false) }

    var validationMessage by remember { mutableStateOf("") }
    var showDialog by remember { mutableStateOf(false) }

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

            Spacer(modifier = Modifier.height(32.dp))

            // Imagem e cabeçalho
            Image(
                painter = painterResource(id = R.drawable.chip_sim_swap), // Referência para a sua imagem
                contentDescription = "Chip de celuar alterado",
                modifier = Modifier
                    .size(150.dp)
                    .padding(top = 20.dp)
            )
            Text(
                text = "Autenticação Cadastral",
                style = MaterialTheme.typography.titleLarge,
                color = BlackQuod
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Campo CPF
            TextField(
                value = cpfValue,
                onValueChange = { cpfValue = it },
                label = { Text("Digite o CPF") },
                placeholder = { Text("000.000.000-00") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                textStyle = MaterialTheme.typography.bodyMedium,
                colors = textFieldColors
            )

            // Campo Telefone
            TextField(
                value = phoneNumberValue,
                onValueChange = { phoneNumberValue = it },
                label = { Text("Telefone Celular") },
                placeholder = { Text("(00) 00000-0000") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                textStyle = MaterialTheme.typography.bodyMedium,
                colors = textFieldColors
            )

            Spacer(modifier = Modifier.height(40.dp))


            // Botões para simular Troca recente  e sem troca
            Row(horizontalArrangement = Arrangement.Center) {
                OutlinedButton(
                    onClick = {
                        val userInfo = getSuccessData()

                        cpfValue = userInfo.cpf
                        phoneNumberValue = userInfo.phone
                        result = true
                    },
                    modifier = Modifier
                        .padding(8.dp)
                        .width(180.dp),
                    shape = RoundedCornerShape(90.dp),
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = PurpleQuod,
                        containerColor = Color.White
                    ),
                    border = BorderStroke(1.dp, PurpleQuod)
                ) {
                    Text(
                        text = "Simular \nSIM não alterado",
                        textAlign = TextAlign.Center
                    )
                }

                OutlinedButton(
                    onClick = {
                        val userInfo = getFailureData()

                        cpfValue = userInfo.cpf
                        phoneNumberValue = userInfo.phone
                        result = false
                    },
                    modifier = Modifier
                        .padding(8.dp)
                        .width(180.dp),
                    shape = RoundedCornerShape(90.dp),
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = PurpleQuod,
                        containerColor = Color.White
                    ),
                    border = BorderStroke(1.dp, PurpleQuod)
                ) {
                    Text(
                        text = "Simular \nSIM SWAP ",
                        textAlign = TextAlign.Center
                    )
                }
            }


            Spacer(modifier = Modifier.height(16.dp))
            // Botão de Verificação
            OutlinedButton(
                onClick = {
                    if (result){
                        validationMessage = "Sem alteração de SIM\n Celular seguro!"
                    } else {
                        validationMessage = "SIM Swap realizado\nCUIDADO!!!."
                    }
                    showDialog = true
                },
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .fillMaxWidth(),
                shape = RoundedCornerShape(90.dp),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = Color.White, // Cor do texto
                    containerColor = BlackQuod // Cor de fundo para o botão de validar
                )
            ) {
                Text(text = "Verificar SIM Swap", fontSize = 14.sp)
            }
        }

        // Mostrar diálogo com a validação
        if (showDialog) {


                CustomAlertDialog(
                    showDialog = showDialog,
                    onDismiss = {
                        showDialog = false
                        val userInfo = getResetData()
                        cpfValue = userInfo.cpf
                        phoneNumberValue = userInfo.phone
                        result = false
                    },
                    onConfirm = {
                        showDialog = false
                        val userInfo = getResetData()
                        cpfValue = userInfo.cpf
                        phoneNumberValue = userInfo.phone
                        result = false
                    },
                    message = validationMessage,
                    isSuccess = result,
                    imageResId = if (result) R.drawable.celular_seguro else R.drawable.simswap_alert
                )



//            CustomAlertDialog(
//                showDialog = showDialog,
//                onDismiss = {
//                    showDialog = false
//                    val userInfo = getResetData()
//                    cpfValue = userInfo.cpf
//                    phoneNumberValue = userInfo.phone
//                    result = false
//                },
//                onConfirm = {
//                    showDialog = false
//                    val userInfo = getResetData()
//                    cpfValue = userInfo.cpf
//                    phoneNumberValue = userInfo.phone
//                    result = false
//                },
//                message = validationMessage,
//                isSuccess = result,
//                imageResId = if (result) R.drawable.celular_seguro else R.drawable.chip_sim_swap
//            )
        }

        /// Botão Voltar
        IconButton(
            onClick = {navController.navigate("home")},
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(start = 22.dp, bottom = 24.dp)
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

