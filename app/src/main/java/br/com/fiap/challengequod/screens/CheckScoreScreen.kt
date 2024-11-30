package br.com.fiap.challengequod.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.fiap.challengequod.R
import br.com.fiap.challengequod.ui.theme.BlackQuod
import br.com.fiap.challengequod.ui.theme.BlueQuod
import br.com.fiap.challengequod.ui.theme.PurpleQuod
import br.com.fiap.challengequod.ui.theme.WhiteQuod
import br.com.fiap.challengequod.utils.calcularScore
import br.com.fiap.challengequod.utils.isValidCPF

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CheckScoreScreen(navController: NavController) {
    var cpfValue by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }
    var score by remember { mutableStateOf(0) }

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
                painter = painterResource(id = R.drawable.score),
                contentDescription = "imagem de nivel de pontuação",
                modifier = Modifier
                    .size(150.dp)
                    .padding(top = 20.dp)
            )
            Text(
                text = "Consultar SCORE",
                style = MaterialTheme.typography.titleLarge,
                color = BlackQuod
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Campo CPF com validação
            TextField(
                value = cpfValue,
                onValueChange = {
                    // Atualiza o valor apenas se for um número e o tamanho máximo de 11
                    if (it.length <= 11 && it.all { char -> char.isDigit() }) {
                        cpfValue = it
                    }
                    errorMessage = if (!isValidCPF(cpfValue)) "CPF inválido! Deve ter 11 dígitos e conter apenas números." else ""
                },
                label = { Text("Digite o CPF") },
                placeholder = { Text("000.000.000-00") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                textStyle = MaterialTheme.typography.bodyMedium,
                colors = textFieldColors,
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number) // Apenas números no teclado
            )

            // Exibe a mensagem de erro, se necessário
            if (errorMessage.isNotEmpty()) {
                Text(
                    text = errorMessage,
                    color = Color.Red,
                    style = MaterialTheme.typography.bodySmall
                )
            }

            Spacer(modifier = Modifier.height(26.dp))

            // Botão de Verificação
            OutlinedButton(
                onClick = {
                    if (isValidCPF(cpfValue)) {
                        // Calcula o score se o CPF for válido
                        score = calcularScore(cpfValue)
                    } else {
                        errorMessage = "CPF inválido! Deve ter 11 dígitos e conter apenas números."
                    }
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
                Text(text = "Consultar Score", fontSize = 14.sp)
            }

            Spacer(modifier = Modifier.height(24.dp))
            // Exibe o score calculado
            if (score > 0) {
                Text(
                    text = "Seu Score é: $score",
                    style = MaterialTheme.typography.titleMedium.copy(
                        textDecoration = TextDecoration.Underline
                    ),
                    color = PurpleQuod,
                    fontSize = 22.sp
                )

            }

            // Card com título e instruções
            Spacer(modifier = Modifier.height(20.dp)) // Espaço antes do Card

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .border(1.dp, PurpleQuod, RoundedCornerShape(8.dp)),
                shape = RoundedCornerShape(8.dp),
                elevation = CardDefaults.cardElevation(6.dp),
                colors = CardDefaults.cardColors(containerColor = WhiteQuod)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.Start
                ) {
                    // Título do Card
                    Text(
                        text = "Para testar score",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        ),
                        color = BlackQuod
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    // Instruções abaixo do título
                    Text(
                        text = "As instruções para validação:",
                        style = MaterialTheme.typography.bodyMedium,
                        color = BlackQuod
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    // Pode adicionar as instruções específicas aqui
                    // Instruções detalhadas com exemplos de CPFs
                    Text(
                        text = """
                            1. Informe o seu CPF corretamente.
                            2. Aguarde a validação dos dados.
                            3. O seu score será exibido após a análise.
            
                            Exemplos de CPFs e seus scores:                            
                              - CPF: 172.938.475-62 → Score: 127
                              - CPF: 678.345.123-45 → Score: 336
                              - CPF: 412.303.185-00 → Score: 765
                              - CPF: 342.158.296-40 → Score: 958                            
                             """.trimIndent(),
                        style = MaterialTheme.typography.bodyMedium,
                        color = BlackQuod
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Atenção! para efeitos de simulação do score foi aplicado" +
                                " uma equação sobre os digitos do CPF!",
                        textAlign = TextAlign.Justify,
                        color = Color(0xFFEC1A0A)
                    )

                }
            }
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
