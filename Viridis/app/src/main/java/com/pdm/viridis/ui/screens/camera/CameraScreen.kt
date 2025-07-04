package com.pdm.viridis.ui.screens.camera

import android.Manifest
import android.content.Context
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Log
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.core.net.toFile
import androidx.lifecycle.LifecycleOwner
import cafe.adriel.voyager.navigator.LocalNavigator
import com.pdm.viridis.utils.PlantClassifier
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun CameraCaptureScreen(gardenId: String) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val navigator = LocalNavigator.current
    var imageCapture by remember { mutableStateOf<ImageCapture?>(null) }
    var hasPermission by remember { mutableStateOf(false) }

    // Permission launcher
    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { granted ->
        hasPermission = granted
    }

    // Launch permission request once on first composition
    LaunchedEffect(Unit) {
        permissionLauncher.launch(Manifest.permission.CAMERA)
    }

    Box(modifier = Modifier.fillMaxSize()) {
        if (hasPermission) {
            CameraPreview(
                onImageCaptureReady = {
                    imageCapture = it
                }
            )

            CaptureButton(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(32.dp)
            ) {
                imageCapture?.let {
                    takePhoto(context, it) { uri ->
                        Toast.makeText(context, "Photo saved: $uri", Toast.LENGTH_SHORT).show()
                        Log.d("📸", "Image URI: $uri")
                        navigator?.pop()
                    }
                }
            }
        } else {
            // You can show a fallback UI here if needed
            Text(
                "Camera permission is required to use this feature.",
                modifier = Modifier.align(Alignment.Center),
                color = MaterialTheme.colorScheme.error
            )
        }
    }
}


@Composable
fun CameraPreview(onImageCaptureReady: (ImageCapture) -> Unit) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    AndroidView(
        factory = { ctx ->
            val previewView = PreviewView(ctx).apply {
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
            }

            val cameraProviderFuture = ProcessCameraProvider.getInstance(ctx)
            cameraProviderFuture.addListener({
                val cameraProvider = cameraProviderFuture.get()

                val preview = Preview.Builder().build().also {
                    it.setSurfaceProvider(previewView.surfaceProvider)
                }

                val imageCapture = ImageCapture.Builder().build()

                val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

                try {
                    cameraProvider.unbindAll()
                    cameraProvider.bindToLifecycle(
                        lifecycleOwner,
                        cameraSelector,
                        preview,
                        imageCapture
                    )

                    onImageCaptureReady(imageCapture)
                } catch (e: Exception) {
                    Log.e("CameraX", "Use case binding failed", e)
                }
            }, ContextCompat.getMainExecutor(ctx))

            previewView
        },
        modifier = Modifier.fillMaxSize()
    )
}







@Composable
fun CaptureButton(modifier: Modifier = Modifier, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = modifier.size(70.dp),
        shape = MaterialTheme.shapes.medium
    ) {
        Text("📸")
    }
}

fun takePhoto(
    context: Context,
    imageCapture: ImageCapture,
    onImageSaved: (Uri) -> Unit
) {
    val photoFile = File(
        context.cacheDir,
        "photo-${SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-SSS", Locale.US).format(Date())}.jpg"
    )

    val outputOptions = ImageCapture.OutputFileOptions.Builder(photoFile).build()

    imageCapture.takePicture(
        outputOptions,
        ContextCompat.getMainExecutor(context),
        object : ImageCapture.OnImageSavedCallback {
            override fun onImageSaved(output: ImageCapture.OutputFileResults) {
                val uri = Uri.fromFile(photoFile)
                val bitmap = BitmapFactory.decodeFile(uri.path)

                val classifier = PlantClassifier(context)
                val prediction = classifier.predict(bitmap)

                val labels = listOf(
                    "aloe_vera", "anthurium_andraeanum", "asplenium_nidus", "begonia_maculata",
                    "calathea_orbifolia", "chlorophytum_comosum", "codiaeum_variegatum", "cymbopogon_citratus",
                    "dracaena_marginata", "epipremnum_aureum", "ficus_lyrata", "hibiscus_rosa-sinensis",
                    "lavandula_angustifolia", "maranta_leuconeura", "mentha_spicata", "monstera_deliciosa",
                    "nephrolepis_exaltata", "ocimum_basilicum", "peperomia_obtusifolia", "philodendron_hederaceum",
                    "piper_auritum", "rosmarinus_officinalis", "sansevieria_trifasciata", "schefflera_arboricola",
                    "spathiphyllum_wallisii", "zamioculcas_zamiifolia"
                )

                // Log all label confidences
                val resultLog = prediction.mapIndexed { index, score ->
                    val label = labels.getOrNull(index) ?: "Label_$index"
                    "$label: ${(score * 100).format(2)}%"
                }.joinToString("\n")

                Log.d("🧠 Raw Prediction", resultLog)

                // Toast the top result
                val topIndex = prediction.indices.maxByOrNull { prediction[it] } ?: -1
                val topLabel = labels.getOrNull(topIndex) ?: "Unknown"
                val confidence = prediction.getOrNull(topIndex)?.times(100)?.toInt() ?: 0

                Toast.makeText(context, "Top: $topLabel ($confidence%)", Toast.LENGTH_LONG).show()

                onImageSaved(uri)
            }

            override fun onError(exception: ImageCaptureException) {
                Log.e("CameraCapture", "Photo capture failed: ${exception.message}", exception)
                Toast.makeText(context, "Capture failed", Toast.LENGTH_SHORT).show()
            }
        }
    )
}




// Helper to format float to 2 decimals
fun Float.format(decimals: Int): String = "%.${decimals}f".format(this)




