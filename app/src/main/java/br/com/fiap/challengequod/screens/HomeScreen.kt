package br.com.fiap.challengequod.screens

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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import br.com.fiap.challengequod.ui.theme.GradientColorsQuod
import br.com.fiap.challengequod.ui.theme.PurpleQuod
import br.com.fiap.challengequod.ui.theme.WhiteQuod

@Composable
fun HomeScreen(navController: NavController) {
    var showMenu by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(WhiteQuod)
    ) {
        // Cabeçalho com gradiente e imagem do usuário
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .background(GradientColorsQuod),
            contentAlignment = Alignment.Center
        ) {

            // Menu no cabeçalho
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .offset(-10.dp, -30.dp),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.Top
            ) {
                IconButton(onClick = { showMenu = !showMenu }) {
                    Icon(
                        imageVector = Icons.Default.Menu,
                        contentDescription = "Menu",
                        tint = WhiteQuod,
                        modifier = Modifier.size(35.dp)
                    )
                }

                DropdownMenu(
                    expanded = showMenu,
                    onDismissRequest = { showMenu = false },
                    modifier = Modifier.background(WhiteQuod, shape = RoundedCornerShape(8.dp))
                ) {
                    // Opções do menu
                    DropdownMenuItem(
                        onClick = {
                            showMenu = false
                        },
                        text = { Text("Sair", color = BlackQuod) },
                        modifier = Modifier.padding(8.dp)
                    )
                    DropdownMenuItem(
                        onClick = {
                            showMenu = false
                        },
                        text = { Text("Alterar Senha", color = BlackQuod) },
                        modifier = Modifier.padding(8.dp)
                    )
                    DropdownMenuItem(
                        onClick = {
                            showMenu = false
                        },
                        text = { Text("Dados do Usuário", color = BlackQuod) },
                        modifier = Modifier.padding(8.dp)
                    )
                    DropdownMenuItem(
                        onClick = {
                            showMenu = false
                        },
                        text = { Text("Configurações", color = BlackQuod) },
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                // Imagem do usuário
                Box(
                    modifier = Modifier
                        .size(80.dp)
                        .background(WhiteQuod, shape = CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.baseline_face_24),
                        contentDescription = "Imagem do Usuário",
                        modifier = Modifier.size(70.dp)
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Bem-vindo, Nome do Usuário",
                    style = MaterialTheme.typography.bodyLarge,
                    color = WhiteQuod,
                    fontSize = 18.sp
                )
            }
        }

        // Logo e slogan da empresa
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Row (
                verticalAlignment = Alignment.CenterVertically

            ){
                Image(
                    painter = painterResource(id = R.drawable.logo_quod),
                    contentDescription = "Logo da Empresa",
                    modifier = Modifier.size(120.dp)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = "Inteligência em dados \npara escolhas inteligentes",
                    style = MaterialTheme.typography.bodyMedium.copy(fontSize = 18.sp),
                    color = Color.Gray,
                    textAlign = TextAlign.Center
                )
            }

        }

        // cards estilizados
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, start = 16.dp, end = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            Text(
                text = "Selecione o Serviço",
                style = MaterialTheme.typography.titleLarge,
                color = PurpleQuod,
                textAlign = TextAlign.Center,
                fontSize = 26.sp
            )
            Spacer(modifier = Modifier.height(30.dp))


            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier.fillMaxWidth()
            ){
                CardItem(
                    title = "Biometria Facial",
                    imageResId = R.drawable.biometria_facial
                ){
                    // Ação para Biometria Facial
                    navController.navigate("biometriaFacial")
                }
                CardItem(
                    title = "Biometria Digital",
                    imageResId = R.drawable.biometria_digital
                ){
                    // Ação para Biometria Digital
                    navController.navigate("biometriaDigital")
                }
            }


            Spacer(modifier = Modifier.height(16.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier.fillMaxWidth()
            ) {
                CardItem(
                    title = "Análise de Documento",
                    imageResId = R.drawable.pesquisa_documento
                ){
                    // Ação para analise documentos
                    navController.navigate("documentos")
                }

                CardItem(
                    title = "SIM SWAP",
                    imageResId = R.drawable.chip_sim_swap
                ){
                    // Ação para SIMSwap
                    navController.navigate("SIMSwap")
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier.fillMaxWidth()
            ){
                CardItem(
                    title = "Autenticação Cadastral",
                    imageResId = R.drawable.cadastro
                ){
                    // Ação para autenticação cadastral
                    navController.navigate("autenticacaoCadastral")
                }

                CardItem(
                    title = "Score Antifraude",
                    imageResId = R.drawable.score
                ){
                    // Ação para score
                    navController.navigate("score")
                }
            }
        }

        Spacer(modifier = Modifier.weight(1f))


    }
}

@Composable
fun CardItem(title: String, imageResId: Int, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .width(170.dp)
            .height(140.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = BlueQuod),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Box(
                modifier = Modifier
                    .size(70.dp)
                    .background(WhiteQuod, shape = CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(imageResId),
                    contentDescription = "Ícone de $title",
                    modifier = Modifier.size(50.dp)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = title,
                style = MaterialTheme.typography.bodyMedium,
                color = WhiteQuod,
                textAlign = TextAlign.Center
            )
        }
    }
}
