package br.com.fiap.challengequod.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.fiap.challengequod.R
import br.com.fiap.challengequod.ui.theme.BlackQuod
import br.com.fiap.challengequod.ui.theme.BlueQuod
import br.com.fiap.challengequod.ui.theme.PurpleQuod
import br.com.fiap.challengequod.ui.theme.WhiteQuod


@Composable
fun FacialBiometricScreen(navController: NavController) {

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

            Spacer(modifier = Modifier.height(86.dp))

            // Imagem e cabeçalho
            Image(
                painter = painterResource(id = R.drawable.bio_facial),
                contentDescription = "Imagem de biometria facial",
                modifier = Modifier
                    .size(180.dp)
                    .padding(top = 20.dp)
            )
            Spacer(modifier = Modifier.height(22.dp))
            Text(
                text = "Biometria Facial",
                style = MaterialTheme.typography.displaySmall,
                color = BlackQuod
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Escolha uma opção para experimentar nossa tecnologia.",
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(32.dp))

            OutlinedButton(
                onClick = {navController.navigate("biometriaFacialValidacao/true") },
                modifier = Modifier
                    .padding(8.dp)
                    .width(300.dp)
                    .height(45.dp),
                shape = RoundedCornerShape(90.dp),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = PurpleQuod,
                    containerColor = Color.White
                ),
                border = BorderStroke(1.dp, PurpleQuod)
            ) {
                Text(
                    text = "Simular sucesso na autenticação",
                    textAlign = TextAlign.Center,
                    fontSize = 16.sp
                )
            }

            OutlinedButton(
                onClick = {navController.navigate("biometriaFacialValidacao/false")},
                modifier = Modifier
                    .padding(8.dp)
                    .width(300.dp)
                    .height(45.dp),
                shape = RoundedCornerShape(90.dp),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = PurpleQuod,
                    containerColor = Color.White
                ),
                border = BorderStroke(1.dp, PurpleQuod)
            ) {
                Text(
                    text = "Simular falha na autenticação",
                    textAlign = TextAlign.Center,
                    fontSize = 16.sp
                )
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

@Composable
@Preview(showBackground = true)
fun PreviewFacialBiometricScreen() {
    FacialBiometricScreen(navController = NavController(LocalContext.current))
}
