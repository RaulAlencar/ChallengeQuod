package br.com.fiap.challengequod.screens

import android.Manifest
import android.content.pm.PackageManager
import android.util.Log
import android.widget.Toast
import androidx.camera.core.CameraSelector
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.navigation.NavController

@Composable
fun CameraScreen(navController: NavController) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val cameraProviderFuture = remember { ProcessCameraProvider.getInstance(context) }

    // Verificar permissão de câmera
    LaunchedEffect(Unit) {
        if (ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.CAMERA
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // Solicitar permissão
            ActivityCompat.requestPermissions(
                context as android.app.Activity,
                arrayOf(Manifest.permission.CAMERA),
                1001
            )
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        AndroidView(
            factory = { ctx ->
                PreviewView(ctx).apply {
                    // Configurar o Preview quando a permissão é concedida
                    cameraProviderFuture.addListener({
                        try {
                            val cameraProvider = cameraProviderFuture.get()

                            // Configurar o Preview
                            val preview = Preview.Builder().build()
                            preview.setSurfaceProvider(surfaceProvider)

                            // Selecionar a câmera traseira
                            val cameraSelector = CameraSelector.DEFAULT_FRONT_CAMERA

                            // Vincular o ciclo de vida do Preview ao lifecycleOwner
                            cameraProvider.unbindAll()
                            cameraProvider.bindToLifecycle(
                                lifecycleOwner,
                                cameraSelector,
                                preview
                            )

                        } catch (e: Exception) {
                            Log.e("CameraPreview", "Erro ao iniciar a câmera: ${e.message}")
                            Toast.makeText(ctx, "Erro ao iniciar a câmera", Toast.LENGTH_SHORT).show()
                        }
                    }, ContextCompat.getMainExecutor(ctx))
                }
            },
            modifier = Modifier
                .size(200.dp) // Define o tamanho do círculo
                .clip(CircleShape) // Aplica o recorte em forma de círculo
        )
    }
}
