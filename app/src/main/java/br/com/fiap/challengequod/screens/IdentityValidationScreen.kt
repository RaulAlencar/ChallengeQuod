package br.com.fiap.challengequod.screens



import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.fiap.challengequod.R
import br.com.fiap.challengequod.ui.theme.BlackQuod
import br.com.fiap.challengequod.ui.theme.BlueQuod
import br.com.fiap.challengequod.ui.theme.GreenQuod
import br.com.fiap.challengequod.ui.theme.PurpleQuod
import br.com.fiap.challengequod.ui.theme.WhiteQuod
import br.com.fiap.challengequod.utils.getFailureData
import br.com.fiap.challengequod.utils.getResetData
import br.com.fiap.challengequod.utils.getSuccessData

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
    // Variável para armazenar o resultado
    var result by remember { mutableStateOf(false) }

    var validationMessage by remember { mutableStateOf("") }
    var showDialog by remember { mutableStateOf(false) }

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

            Spacer(modifier = Modifier.height(16.dp))

            // Imagem e cabeçalho
            Image(
                painter = painterResource(id = R.drawable.usuario), // Referência para a sua imagem
                contentDescription = "Imagem de um homem",
                modifier = Modifier
                    .size(150.dp)
                    .padding(top = 20.dp)
            )
            Text(
                text = "Autenticação Cadastral",
                style = MaterialTheme.typography.titleLarge,
                color = BlackQuod
            )

            Spacer(modifier = Modifier.height(6.dp))

            // Campos do Formulário
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

            TextField(
                value = nameValue,
                onValueChange = { nameValue = it },
                label = { Text("Nome Completo") },
                placeholder = { Text("Digite seu nome completo") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                textStyle = MaterialTheme.typography.bodyMedium,
                colors = textFieldColors
            )

            TextField(
                value = phoneValue,
                onValueChange = { phoneValue = it },
                label = { Text("Telefone Celular") },
                placeholder = { Text("(00) 00000-0000") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                textStyle = MaterialTheme.typography.bodyMedium,
                colors = textFieldColors
            )

            // Endereço
            Box{

            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Endereço", style = MaterialTheme.typography.bodyLarge)

            Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                TextField(
                    value = cepValue,
                    onValueChange = { cepValue = it },
                    label = { Text("CEP") },
                    placeholder = { Text("00000-000") },
                    modifier = Modifier.width(150.dp),
                    singleLine = true,
                    textStyle = MaterialTheme.typography.bodyMedium,
                    colors = textFieldColors
                )

                Icon(imageVector = Icons.Default.Search, contentDescription = "Pesquisa CEP", modifier = Modifier.size(30.dp), tint = BlueQuod)
            }

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                TextField(
                    value = streetValue,
                    onValueChange = { streetValue = it },
                    label = { Text("Logradouro") },
                    placeholder = { Text("Rua, Avenida, etc.") },
                    modifier = Modifier.width(300.dp),
                    singleLine = true,
                    textStyle = MaterialTheme.typography.bodyMedium,
                    colors = textFieldColors
                )

                TextField(
                    value = numberValue,
                    onValueChange = { numberValue = it },
                    label = { Text("Nº") },
                    placeholder = { Text("Nº") },
                    modifier = Modifier.width(70.dp),
                    singleLine = true,
                    textStyle = MaterialTheme.typography.bodyMedium,
                    colors = textFieldColors
                )
            }

            Row(modifier = Modifier.fillMaxWidth()) {
                TextField(
                    value = complementValue,
                    onValueChange = { complementValue = it },
                    label = { Text("Complemento") },
                    placeholder = { Text("Apto, Bloco, etc.") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    textStyle = MaterialTheme.typography.bodyMedium,
                    colors = textFieldColors
                )
            }

            Row(modifier = Modifier.fillMaxWidth()) {
                TextField(
                    value = neighborhoodValue,
                    onValueChange = { neighborhoodValue = it },
                    label = { Text("Bairro") },
                    placeholder = { Text("Bairro") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    textStyle = MaterialTheme.typography.bodyMedium,
                    colors = textFieldColors
                )
            }

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {

                TextField(
                    value = cityValue,
                    onValueChange = { cityValue = it },
                    label = { Text("Cidade") },
                    placeholder = { Text("Cidade") },
                    modifier = Modifier.width(280.dp),
                    singleLine = true,
                    textStyle = MaterialTheme.typography.bodyMedium,
                    colors = textFieldColors
                )

                TextField(
                    value = stateValue,
                    onValueChange = { stateValue = it },
                    label = { Text("UF") },
                    placeholder = { Text("UF") },
                    modifier = Modifier.width(90.dp),
                    singleLine = true,
                    textStyle = MaterialTheme.typography.bodyMedium,
                    colors = textFieldColors
                )
            }

            Spacer(modifier = Modifier.height(16.dp))


            // Botões para simular sucesso e fracasso
            Row(horizontalArrangement = Arrangement.Center) {
                OutlinedButton(
                    onClick = {
                        val userInfo = getSuccessData()

                        cpfValue = userInfo.cpf
                        nameValue = userInfo.name
                        phoneValue = userInfo.phone
                        cepValue = userInfo.cep
                        streetValue = userInfo.street
                        numberValue = userInfo.number
                        complementValue = userInfo.complement
                        neighborhoodValue = userInfo.neighborhood
                        cityValue = userInfo.city
                        stateValue = userInfo.state
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
                        text = "Preecher dados\nSimular Sucesso",
                        textAlign = TextAlign.Center
                    )
                }

                OutlinedButton(
                    onClick = {
                        val userInfo = getFailureData()

                        cpfValue = userInfo.cpf
                        nameValue = userInfo.name
                        phoneValue = userInfo.phone
                        cepValue = userInfo.cep
                        streetValue = userInfo.street
                        numberValue = userInfo.number
                        complementValue = userInfo.complement
                        neighborhoodValue = userInfo.neighborhood
                        cityValue = userInfo.city
                        stateValue = userInfo.state
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
                        text = "Preencher dados\nSimular Falha",
                        textAlign = TextAlign.Center
                    )
                }
            }


            Spacer(modifier = Modifier.height(6.dp))
            OutlinedButton(
                onClick = {
                    if (result){
                        validationMessage = "Cadastro Validado com Sucesso!"
                    } else {
                        validationMessage = "Falha na Validação. Dados incorretos."
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
                Text(text = "Validar", fontSize = 14.sp)
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
                   nameValue = userInfo.name
                   phoneValue = userInfo.phone
                   cepValue = userInfo.cep
                   streetValue = userInfo.street
                   numberValue = userInfo.number
                   complementValue = userInfo.complement
                   neighborhoodValue = userInfo.neighborhood
                   cityValue = userInfo.city
                   stateValue = userInfo.state
                   result = false
               },
               onConfirm = {
                   showDialog = false
                   val userInfo = getResetData()

                   cpfValue = userInfo.cpf
                   nameValue = userInfo.name
                   phoneValue = userInfo.phone
                   cepValue = userInfo.cep
                   streetValue = userInfo.street
                   numberValue = userInfo.number
                   complementValue = userInfo.complement
                   neighborhoodValue = userInfo.neighborhood
                   cityValue = userInfo.city
                   stateValue = userInfo.state
                   result = false
               },
               message = validationMessage,
               isSuccess = result,
               imageResId = if (result) R.drawable.mao_positivo else R.drawable.mao_negativo
           )

        }

        // Botão Voltar
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



