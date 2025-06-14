package com.example.quickStock.screensUI.addProducts

import android.annotation.SuppressLint
import android.content.Context
import android.util.Size
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import android.media.Image
import androidx.annotation.OptIn
import androidx.camera.core.CameraSelector
import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.material.icons.filled.Close
import androidx.compose.ui.res.stringResource
import com.example.quickStock.R
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.common.InputImage

@Composable
fun BarcodeScannerScreen(
    onBarcodeScanned: (String?) -> Unit,
    onClose: () -> Unit
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    var scannedBarcode by remember { mutableStateOf<String?>(null) }
    var scanning by remember { mutableStateOf(true) }
    val coroutineScope = rememberCoroutineScope()

    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.Black)) {
        if (scanning) {
            CameraPreview(
                onBarcodeDetected = { code ->
                    if (code != null) {
                        scannedBarcode = code
                        scanning = false
                        onBarcodeScanned(code)
                    }
                },
                context = context,
                lifecycleOwner = lifecycleOwner
            )
        }
        IconButton(
            onClick = { onClose() },
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(16.dp)
        ) {
            Icon(
                imageVector = androidx.compose.material.icons.Icons.Default.Close,
                contentDescription = stringResource(R.string.cerrar_esc_ner),
                tint = Color.White
            )
        }
        if (!scanning && scannedBarcode == null) {
            Text(
                text = stringResource(R.string.no_se_detect_ning_n_c_digo_de_barras),
                color = Color.White,
                modifier = Modifier.align(Alignment.Center)
            )
            Button(onClick = { scanning = true }, modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(16.dp)) {
                Text(stringResource(R.string.reintentar))
            }
        }
    }
}

@SuppressLint("UnsafeOptInUsageError")
@Composable
fun CameraPreview(
    onBarcodeDetected: (String?) -> Unit,
    context: Context,
    lifecycleOwner: LifecycleOwner
) {
    val previewView = remember { PreviewView(context) }
    val cameraProviderFuture = remember { ProcessCameraProvider.getInstance(context) }
    val analyzer = remember {
        BarcodeAnalyzer { code ->
            onBarcodeDetected(code)
        }
    }
    DisposableEffect(Unit) {
        val cameraProvider = cameraProviderFuture.get()
        val preview = Preview.Builder().build().also {
            it.setSurfaceProvider(previewView.surfaceProvider)
        }
        val selector = CameraSelector.DEFAULT_BACK_CAMERA
        val imageAnalysis = ImageAnalysis.Builder()
            .setTargetResolution(Size(1280, 720))
            .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
            .build()
            .also {
                it.setAnalyzer(ContextCompat.getMainExecutor(context), analyzer)
            }
        cameraProvider.unbindAll()
        cameraProvider.bindToLifecycle(lifecycleOwner, selector, preview, imageAnalysis)
        onDispose {
            cameraProvider.unbindAll()
        }
    }
    AndroidView(factory = { previewView }, modifier = Modifier.fillMaxSize())
}

class BarcodeAnalyzer(private val onBarcodeDetected: (String?) -> Unit) : ImageAnalysis.Analyzer {
    private var found = false

    @OptIn(ExperimentalGetImage::class)
    override fun analyze(imageProxy: ImageProxy) {
        if (found) {
            imageProxy.close()
            return
        }
        val mediaImage: Image? = imageProxy.image
        if (mediaImage != null) {
            val image = InputImage.fromMediaImage(mediaImage, imageProxy.imageInfo.rotationDegrees)
            val scanner = BarcodeScanning.getClient()
            scanner.process(image)
                .addOnSuccessListener { barcodes ->
                    for (barcode in barcodes) {
                        val rawValue = barcode.rawValue
                        if (!rawValue.isNullOrEmpty()) {
                            found = true
                            onBarcodeDetected(rawValue)
                            break
                        }
                    }
                }
                .addOnFailureListener {
                    onBarcodeDetected(null)
                }
                .addOnCompleteListener {
                    imageProxy.close()
                }
        } else {
            imageProxy.close()
        }
    }
}

