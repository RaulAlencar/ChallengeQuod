package br.com.fiap.challengequod

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import br.com.fiap.challengequod.screens.CameraScreen
import br.com.fiap.challengequod.screens.CheckScoreScreen
import br.com.fiap.challengequod.screens.FacialBiometricScreen
import br.com.fiap.challengequod.screens.HomeScreen
import br.com.fiap.challengequod.screens.IdentityVerificationScreen
import br.com.fiap.challengequod.screens.SimSwapScreen
import br.com.fiap.challengequod.screens.FacialBiometricValidationScreen
import br.com.fiap.challengequod.ui.theme.ChallengeQuodTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ChallengeQuodTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = "home"
                    ){
                        composable(route = "home"){ HomeScreen(navController)}

                        //Biometria Facial
                        composable(route = "biometriaFacial"){ FacialBiometricScreen(navController) }
                        composable(
                            route = "biometriaFacialValidacao/{isSuccess}",
                            arguments = listOf(navArgument("isSuccess"){type = NavType.BoolType})
                        ){ backStackEntry ->
                            val isSuccess = backStackEntry.arguments?.getBoolean("isSuccess") ?: false

                            FacialBiometricValidationScreen(
                                navController = navController,
                                isSuccess = isSuccess
                            )
                        }

                        //biometria digital
                        composable(route = "camera"){ CameraScreen(navController) }
                        //documentoscopia
                        composable(route = "SIMSwap"){ SimSwapScreen(navController)}
                        composable(route = "autenticacaoCadastral"){ IdentityVerificationScreen(navController) }
                        composable(route = "score"){ CheckScoreScreen(navController) }

                    }
                }
            }
        }
    }
}
