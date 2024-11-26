package br.com.fiap.challengequod.screens

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.com.fiap.challengequod.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun FacialBiometricValidationScreen(navController: NavController, isSuccess: Boolean){
    var currentStep by remember { mutableStateOf("Capturando...") }
    var showScanner by remember { mutableStateOf(false) }
    var showResult by remember { mutableStateOf(false) }
    val fadeInAlpha = remember { Animatable(0f) }

    // Animação do scanner
    val scannerOffset by rememberInfiniteTransition().animateFloat(
        initialValue = 0f,
        targetValue = 500f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    LaunchedEffect(Unit) {
        // Controla o fade-in da imagem
        launch {
            fadeInAlpha.animateTo(
                targetValue = 1f,
                animationSpec = tween(durationMillis = 2000, easing = LinearEasing)
            )
        }

        delay(2000) // Aguarda o término do fade-in
        showScanner = true
        delay(3000) // Scanner ocorre por 2 segundos
        showScanner = false
        showResult = true
        currentStep = if (isSuccess) "Autenticação Bem-Sucedida" else "Autenticação Falhou"
        delay(1500) // Mostra o resultado por 2 segundos
        //voltar para a tela anterior
        navController.popBackStack() // Retorna para a tela anterior
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = currentStep,
            style = MaterialTheme.typography.headlineSmall,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(32.dp))

        if (!showResult) {
            // Exibição da imagem e scanner
            Box(
                modifier = Modifier
                    .size(200.dp)
                    .clip(CircleShape)
                    .background(Color.Black), // Fundo preto inicial
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.foto_homem), // Substitua pela imagem real
                    contentDescription = "Foto Capturada",
                    modifier = Modifier
                        .fillMaxSize()
                        .graphicsLayer { alpha = fadeInAlpha.value } // Aplicando o fade-in
                        .then(
                            if (showScanner) Modifier.drawWithContent {
                                drawContent()
                                drawRect(
                                    color = Color.Cyan.copy(alpha = 0.5f),
                                    topLeft = androidx.compose.ui.geometry.Offset(0f, scannerOffset),
                                    size = androidx.compose.ui.geometry.Size(size.width, 10f)
                                )
                            } else Modifier
                        )
                )
            }
        } else {
            // Exibição do resultado
            Box(
                modifier = Modifier
                    .size(200.dp)
                    .clip(CircleShape)
                    .background(if (isSuccess) Color.Green else Color.Red),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = if (isSuccess) "✔" else "✖",
                    style = MaterialTheme.typography.displayMedium,
                    color = Color.White
                )
            }
        }
    }
}