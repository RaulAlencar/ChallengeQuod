package br.com.fiap.challengequod.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import br.com.fiap.challengequod.R

@Composable
fun CustomAlertDialog(
    showDialog: Boolean,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
    message: String,
    isSuccess: Boolean, // Indica sucesso ou falha
    imageResId: Int // ID da imagem (ícone)
) {
    var dialogAlpha by remember { mutableStateOf(0f) }

    // Animação para a transição de aparecimento do diálogo
    LaunchedEffect(showDialog) {
        dialogAlpha = if (showDialog) 1f else 0f
    }

    // Cores e bordas baseados no sucesso ou falha
    val borderColor = if (isSuccess) Color.Green else Color.Red

    // O diálogo é exibido apenas se isVisible for verdadeiro
    if (showDialog) {
        Dialog(onDismissRequest = onDismiss) {
            // Container do Dialog
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),
                contentAlignment = Alignment.Center
            ) {
                // O Card que forma o conteúdo do AlertDialog com bordas coloridas
                Column(
                    modifier = Modifier
                        .background(Color(0xFFE0E0E0), shape = RoundedCornerShape(16.dp))
                        .padding(20.dp)
                        .alpha(dialogAlpha)
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    // Título centralizado
                    Text(
                        text = "Resultado",
                        style = TextStyle(
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        ),
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                    // Ícone (imagem centralizada)
                    Image(
                        painter = painterResource(id = imageResId),
                        contentDescription = null,
                        modifier = Modifier.size(100.dp)
                    )

                    // Mensagem
                    Text(
                        textAlign = TextAlign.Center,
                        text = message,
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Normal,
                            color = if (isSuccess) Color(0xFF119717) else Color(0xFFE4030A)
                        ),
                        modifier = Modifier.padding(vertical = 24.dp)
                    )

                    // Botão OK
                    Button(
                        onClick = onConfirm,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("OK", color = Color.White)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCustomAlertDialog() {
    var isDialogVisible by remember { mutableStateOf(true) }

    CustomAlertDialog(
        showDialog = isDialogVisible,
        onDismiss = { isDialogVisible = false },
        onConfirm = { isDialogVisible = false },
        message = "Sua operação foi bem-sucedida!",
        isSuccess = false, // Altere para false para falha
        imageResId = R.drawable.simswap_alert // Substitua pelo seu ícone de sucesso ou falha
    )
}
