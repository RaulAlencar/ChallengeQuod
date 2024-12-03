package br.com.fiap.challengequod.screens

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
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
    val scannerOffset by rememberInfiniteTransition(label = "").animateFloat(
        initialValue = 0f,
        targetValue = 600f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ), label = ""
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
        delay(3000) // Scanner ocorre por 3 segundos
        showScanner = false
        showResult = true
        currentStep = if (isSuccess) "Autenticação Bem-Sucedida!" else "Autenticação Falhou!\nTente Novamente"
        delay(2000) // Mostra o resultado por 2 segundos
        //voltar para a tela anterior
        navController.popBackStack()
    }

    Dialog(onDismissRequest = {}) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            contentAlignment = Alignment.Center
        ) {
            // O Card que forma o conteúdo do AlertDialog
            Column(
                modifier = Modifier
                    .background(Color(0xFFE0E0E0), shape = RoundedCornerShape(16.dp))
                    //.padding(10.dp)
                    .alpha(1f)
                    .fillMaxWidth()
                    .height(400.dp),
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
                            .size(200.dp, 240.dp)
                            .clip(RoundedCornerShape(90.dp))
                            .background(Color.Black),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(if (isSuccess) R.drawable.homem else R.drawable.mulher),
                            contentDescription = "Foto Capturada",
                            modifier = Modifier
                                .fillMaxSize()
                                .graphicsLayer { alpha = fadeInAlpha.value } // Aplicando o fade-in
                                .then(
                                    if (showScanner) Modifier.drawWithContent {
                                        drawContent()
                                        drawRect(
                                            color = Color.Cyan.copy(alpha = 0.5f),
                                            topLeft = Offset(0f, scannerOffset),
                                            size = Size(size.width, 10f)
                                        )
                                    } else Modifier
                                )
                        )
                    }
                } else {
                    // Exibição do resultado
                    Column(
                        modifier = Modifier
                            .size(220.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .background(if (isSuccess) Color(0xFFDFF7DF) else Color(0xFFFEEDEE))
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Top
                    ) {
                        // Ícone de sucesso ou erro
                        Image(
                            painter = painterResource(if (isSuccess) R.drawable.sucesso else R.drawable.falha),
                            contentDescription = if (isSuccess) "Sucesso" else "Erro",
                            modifier = Modifier.size(150.dp)
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        // Mensagem
                        Text(
                            text = if (isSuccess) "Validação concluída com sucesso" else "Erro na validação biométrica",
                            style = MaterialTheme.typography.bodyLarge,
                            textAlign = TextAlign.Center,
                            color = if (isSuccess) Color(0xFF388E3C) else Color(0xFFD32F2F)
                        )
                    }

                }
            }
        }
    }
}
