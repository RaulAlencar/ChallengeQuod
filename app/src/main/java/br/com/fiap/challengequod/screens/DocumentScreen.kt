package br.com.fiap.challengequod.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import br.com.fiap.challengequod.R
import br.com.fiap.challengequod.model.DocumentViewModel
import br.com.fiap.challengequod.ui.theme.BlackQuod
import br.com.fiap.challengequod.ui.theme.BlueQuod
import br.com.fiap.challengequod.ui.theme.GreenQuod
import br.com.fiap.challengequod.ui.theme.PurpleQuod
import br.com.fiap.challengequod.ui.theme.WhiteQuod
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun DocumentScreen(navController: NavController, documentViewModel: DocumentViewModel = viewModel()) {
    val documentCaptured by documentViewModel.documentCaptured.collectAsState()
    val documentValid by documentViewModel.documentValid.collectAsState()
    val biometricCaptured by documentViewModel.biometricCaptured.collectAsState()
    val biometricValid by documentViewModel.biometricValid.collectAsState()

    var result by remember { mutableStateOf(false) }
    var validationMessage by remember { mutableStateOf("") }
    var showDialog by remember { mutableStateOf(false) }

    val outlinedButtonColorsDocument = ButtonDefaults.outlinedButtonColors(
        contentColor = PurpleQuod,
        containerColor = Color.White
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
            Spacer(modifier = Modifier.height(36.dp))

            Image(
                painter = painterResource(id = R.drawable.documentoscopia),
                contentDescription = "Imagem de pesquisa em documento",
                modifier = Modifier.size(100.dp)
            )
            Text(
                text = "Análise de Documentos",
                style = MaterialTheme.typography.titleLarge,
                color = BlackQuod
            )
            Text(
                text = "Escolha uma opção para experimentar nossa tecnologia.",
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(WhiteQuod),
                contentAlignment = Alignment.Center
            ) {
                Card(
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp, top = 10.dp, bottom = 10.dp)
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    shape = RoundedCornerShape(16.dp),
                    elevation = CardDefaults.cardElevation(8.dp),
                    colors = CardDefaults.cardColors(containerColor = WhiteQuod)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Text(
                            text = "Simular captura de documento.",
                            style = MaterialTheme.typography.bodyLarge,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(bottom = 5.dp)
                        )

                        // Exibir a imagem
                        Image(
                            painter = painterResource(
                                id = if (documentCaptured) {
                                    if (documentValid) R.drawable.rg else R.drawable.rg_em_branco
                                } else {
                                    R.drawable.rg_em_branco // Um recurso de imagem padrão
                                }
                            ),
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(120.dp)
                                .clip(RoundedCornerShape(12.dp)),
                            contentScale = ContentScale.FillHeight
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        // Texto abaixo da imagem
                        if (documentCaptured) {
                            Text(
                                text = if (documentValid) "Documento capturado com sucesso" else "Falha na captura, tente novamente",
                                color = if (documentValid) Color(0xFF388E3C) else Color(0xFFD32F2F),
                                style = MaterialTheme.typography.bodyLarge,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.padding(bottom = 5.dp)
                            )
                        }

                        Row (
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 20.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceAround

                        ){
                            // Simular captura com sucesso
                            OutlinedButton(
                                onClick = {
                                    // navegação para a tela de validação
                                    navController.navigate("documentosValidacao/true")

                                    // coroutine para atrasar a mudança de estado
                                    CoroutineScope(Dispatchers.Main).launch {
                                        // Atrasar a atualização do estado
                                        delay(2000L)

                                        // Atualizar a variável captured na viewModel
                                        documentViewModel.updateDocumentStatus(captured = true, valid = true)
                                    }
                                },
                                modifier = Modifier
                                    .padding(vertical = 4.dp)
                                    .size(120.dp, 35.dp),
                                shape = RoundedCornerShape(12.dp),
                                colors = outlinedButtonColorsDocument,
                                border = BorderStroke(1.dp, GreenQuod)

                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.mao_positivo),
                                    contentDescription = null,
                                    modifier = Modifier.size(24.dp),
                                    tint = Color.Unspecified
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(text = "OK", fontSize = 14.sp)
                            }


                            // Simular captura com falha
                            OutlinedButton(
                                onClick = {
                                    navController.navigate("documentosValidacao/false")

                                    //coroutine para atrasar a mudança de estado
                                    CoroutineScope(Dispatchers.Main).launch {
                                        // Atrasar a atualização do estado
                                        delay(2000L)

                                        // Atualizar a variável captured na viewModel
                                        documentViewModel.updateDocumentStatus(
                                            captured = true,
                                            valid = false
                                        )
                                        documentViewModel.updateBiometricStatus(
                                            captured = false,
                                            valid = false
                                        )
                                    }

                                },
                                modifier = Modifier
                                    .padding(vertical = 4.dp)
                                    .size(120.dp, 35.dp),
                                shape = RoundedCornerShape(12.dp),
                                colors = outlinedButtonColorsDocument,
                                border = BorderStroke(1.dp, Color(0xFFFF0505))
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.mao_negativo),
                                    contentDescription = null,
                                    modifier = Modifier.size(24.dp),
                                    tint = Color.Unspecified
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(text = "Falha", fontSize = 14.sp)
                            }
                        }

                    }
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(WhiteQuod),
                contentAlignment = Alignment.Center
            ) {
                val alpha = if (documentValid) 1f else 0.5f  // Ajusta a opacidade
                Card(
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp, bottom = 15.dp)
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .alpha(alpha),
                    shape = RoundedCornerShape(16.dp),
                    elevation = CardDefaults.cardElevation(8.dp),
                    colors = CardDefaults.cardColors(containerColor = WhiteQuod)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                            .then(
                                if (documentValid) Modifier.clickable { }
                                else Modifier // Sem clique quando desabilitado
                            ),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Text(
                            text = "Simular captura de biometrica facial.",
                            style = MaterialTheme.typography.bodyLarge,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(bottom = 10.dp)
                        )

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(160.dp)
                                .clip(RoundedCornerShape(12.dp))
                        ) {

                            Image(
                                painter = painterResource(id = R.drawable.facial_back),
                                contentDescription = null,
                                modifier = Modifier
                                    .align(Alignment.Center)
                            )


                            Image(
                                painter = painterResource(
                                    id = if (biometricCaptured) {
                                        if (biometricValid) R.drawable.captura_sucesso else R.drawable.facial_mulher
                                    } else {
                                        R.drawable.facial_mulher
                                    }
                                ),
                                contentDescription = null,
                                modifier = Modifier
                                    .align(Alignment.Center)
                                    .size(130.dp),
                            )
                        }



                        Spacer(modifier = Modifier.height(10.dp))

                        // Texto abaixo da imagem
                        if (biometricCaptured) {
                            Text(
                                text = if (biometricValid) "Rosto capturado com sucesso" else "Falha na captura, tente novamente",
                                color = if (biometricValid) Color(0xFF388E3C) else Color(0xFFD32F2F),
                                style = MaterialTheme.typography.bodyLarge,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.padding(bottom = 5.dp)
                            )
                        }

                        Row (
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 20.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceAround

                        ){
                            // Simular captura com sucesso
                            OutlinedButton(
                                enabled = documentValid,
                                onClick = {
                                    // navegação para a tela de validação
                                    navController.navigate("biometriaFacialValidacao/true")

                                    // coroutine para atrasar a mudança de estado
                                    CoroutineScope(Dispatchers.Main).launch {
                                        // Atrasar a atualização do estado
                                        delay(2000L)

                                        // Atualizar a variável captured na viewModel
                                        documentViewModel.updateBiometricStatus(captured = true, valid = true)
                                    }
                                },
                                modifier = Modifier
                                    .padding(vertical = 4.dp)
                                    .size(120.dp, 35.dp), // Botões menores
                                shape = RoundedCornerShape(12.dp),
                                colors = outlinedButtonColorsDocument,
                                border = BorderStroke(1.dp, GreenQuod)

                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.mao_positivo),
                                    contentDescription = null,
                                    modifier = Modifier.size(24.dp),
                                    tint = Color.Unspecified
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(text = "OK", fontSize = 14.sp)
                            }


                            // Simular captura com falha
                            OutlinedButton(
                                enabled = documentValid,
                                onClick = {
                                    navController.navigate("biometriaFacialValidacao/false")

                                    // coroutine para atrasar a mudança de estado
                                    CoroutineScope(Dispatchers.Main).launch {
                                        // Atrasar a atualização do estado
                                        delay(2000L)

                                        // Atualizar a variável captured na viewModel
                                        documentViewModel.updateBiometricStatus(
                                            captured = true,
                                            valid = false
                                        )
                                    }

                                },
                                modifier = Modifier
                                    .padding(vertical = 4.dp)
                                    .size(120.dp, 35.dp),
                                shape = RoundedCornerShape(12.dp),
                                colors = outlinedButtonColorsDocument,
                                border = BorderStroke(1.dp, Color(0xFFFF0505))
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.mao_negativo),
                                    contentDescription = null,
                                    modifier = Modifier.size(24.dp),
                                    tint = Color.Unspecified
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(text = "Falha", fontSize = 14.sp)
                            }
                        }

                    }
                }
            }

            // Validação Final
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.BottomCenter
            ) {

                Row(
                    modifier = Modifier.offset(0.dp, -60.dp)
                ) {
                    OutlinedButton(
                        onClick = {
                            validationMessage = "Validação realizada \ncom Sucesso!"
                            showDialog = true
                            result = true
                        },
                        enabled = biometricCaptured,
                        modifier = Modifier
                            .padding(horizontal = 8.dp)
                            .width(160.dp)
                            .height(55.dp),
                        shape = RoundedCornerShape(90.dp),
                        colors = ButtonDefaults.outlinedButtonColors(
                            contentColor = PurpleQuod,
                            containerColor = Color.White
                        ),
                        border = BorderStroke(1.dp, PurpleQuod)
                    ) {
                        Text(
                            text = "Sucesso na Validação",
                            textAlign = TextAlign.Center,
                            fontSize = 16.sp
                        )
                    }

                    OutlinedButton(
                        onClick = {
                            validationMessage = "Falha na Validação. \nTente Novamente."
                            showDialog = true
                            result = false
                        },
                        enabled = biometricCaptured,
                        modifier = Modifier
                            .padding(horizontal = 8.dp)
                            .width(160.dp)
                            .height(55.dp),
                        shape = RoundedCornerShape(90.dp),
                        colors = ButtonDefaults.outlinedButtonColors(
                            contentColor = PurpleQuod,
                            containerColor = Color.White
                        ),
                        border = BorderStroke(1.dp, PurpleQuod)
                    ) {
                        Text(
                            text = "Falha na Validação",
                            textAlign = TextAlign.Center,
                            fontSize = 16.sp
                        )
                    }
                }
            }
        }

        // Mostrar diálogo com a validação
        if (showDialog) {

            CustomAlertDialog(
                showDialog = showDialog,
                onDismiss = {
                    showDialog = false
                    result = false
                    documentViewModel.updateDocumentStatus(
                        captured = false,
                        valid = false
                    )
                    documentViewModel.updateBiometricStatus(
                        captured = false,
                        valid = false
                    )
                },
                onConfirm = {
                    showDialog = false
                    result = false
                    documentViewModel.updateDocumentStatus(
                        captured = false,
                        valid = false
                    )
                    documentViewModel.updateBiometricStatus(
                        captured = false,
                        valid = false
                    )
                },
                message = validationMessage,
                isSuccess = result,
                imageResId = if (result) R.drawable.mao_positivo else R.drawable.mao_negativo
            )

        }

        IconButton(
            onClick = { navController.navigate("home") },
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(start = 22.dp, bottom = 24.dp)
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Voltar para a Home",
                tint = BlueQuod,
                modifier = Modifier.size(40.dp)
            )
        }
    }
}

