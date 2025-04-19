package com.example.quickStock.screensUI.addProducts
//
//import android.Manifest
//import android.content.pm.PackageManager
//import android.net.Uri
//import android.util.Log
//import androidx.activity.compose.rememberLauncherForActivityResult
//
//import androidx.activity.result.contract.ActivityResultContracts
//import androidx.camera.core.CameraSelector
//import androidx.camera.core.ImageCapture
//import androidx.camera.core.ImageCaptureException
//import androidx.camera.core.Preview
//import androidx.camera.lifecycle.ProcessCameraProvider
//import androidx.camera.view.PreviewView
//import androidx.compose.foundation.layout.*
//import androidx.compose.material3.Button
//import androidx.compose.material3.Text
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.platform.LocalLifecycleOwner
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.viewinterop.AndroidView
//import androidx.core.content.ContextCompat
//import java.io.File
//import java.text.SimpleDateFormat
//import java.util.Locale
//import java.util.concurrent.Executors
//import kotlin.coroutines.resume
//import kotlin.coroutines.suspendCoroutine
//
//@Composable
//fun BarcodeScannerScreen(onImageCaptured: (Uri?) -> Unit) {
//    val context = LocalContext.current
//    val lifecycleOwner = LocalLifecycleOwner.current
//    val cameraProviderFuture = remember { ProcessCameraProvider.getInstance(context) }
//    var previewView: PreviewView? by remember { mutableStateOf(null) }
//    var imageCapture: ImageCapture? by remember { mutableStateOf(null) }
//    var capturedImageUri: Uri? by remember { mutableStateOf(null) }
//
//    val executor = remember { Executors.newSingleThreadExecutor() }
//
//    val requestPermissionLauncher = rememberLauncherForActivityResult(
//        contract = ActivityResultContracts.RequestPermission(),
//        onResult = { isGranted: Boolean ->
//            if (isGranted) {
//                // Permission granted, start camera
//                startCamera(
//                    context,
//                    lifecycleOwner,
//                    cameraProviderFuture,previewView,
//                    imageCapture,
//                    executor
//                )
//            } else {
//                // Permission denied, handle accordingly (e.g., show a message)
//                Log.e("Camera", "Camera permission denied")
//                // You might want to inform the user that the feature won't work
//            }
//        }
//    )
//
//    LaunchedEffect(Unit) {
//        if (ContextCompat.checkSelfPermission(
//                context,
//                Manifest.permission.CAMERA
//            ) == PackageManager.PERMISSION_GRANTED
//        ) {
//            // Permission already granted, start camera
//            startCamera(
//                context,
//                lifecycleOwner,
//                cameraProviderFuture,
//                previewView,
//                imageCapture,
//                executor
//            )
//        } else {
//            // Request permission
//            requestPermissionLauncher.launch(Manifest.permission.CAMERA)
//        }
//    }
//
//    Column(
//        modifier = Modifier.fillMaxSize(),
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        AndroidView(
//            factory = { ctx ->
//                PreviewView(ctx).apply {
//                    previewView = this
//                }
//            },
//            modifier = Modifier
//                .fillMaxWidth()
//                .weight(1f)
//        )
//
//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(16.dp),
//            horizontalArrangement = Arrangement.SpaceAround
//        ) {
//            Button(onClick = {
//                if (capturedImageUri == null) {
//                    takePhoto(
//                        context,
//                        imageCapture,
//                        executor,
//                        onImageCaptured = { uri ->
//                            capturedImageUri = uri
//                        }
//                    )
//                } else {
//                    capturedImageUri = null
//                }
//            }) {
//                Text(if (capturedImageUri == null) "Take Photo" else "Retake Photo")
//            }
//
//            if (capturedImageUri != null) {
//                Button(onClick = {
//                    onImageCaptured(capturedImageUri)
//                }) {
//                    Text("Confirm Photo")
//                }
//            }
//        }
//    }
//}
//
//private fun startCamera(
//    context: android.content.Context,
//    lifecycleOwner: androidx.lifecycle.LifecycleOwner,
//    cameraProviderFuture: com.google.common.util.concurrent.ListenableFuture<ProcessCameraProvider>,
//    previewView: PreviewView?,
//    imageCapture: ImageCapture?,
//    executor: java.util.concurrent.Executor
//) {
//    cameraProviderFuture.addListener({
//        val cameraProvider = cameraProviderFuture.get()
//        val preview = Preview.Builder().build().also {
//            it.setSurfaceProvider(previewView?.surfaceProvider)
//        }
//
//        val camSelector = CameraSelector.DEFAULT_BACK_CAMERA
//
//        val imgCapture = ImageCapture.Builder().build()
//
//        try {
//            cameraProvider.unbindAll()
//            cameraProvider.bindToLifecycle(
//                lifecycleOwner,
//                camSelector,
//                preview,
//                imgCapture
//            )
//        } catch (e: Exception) {
//            Log.e("Camera", "Use case binding failed", e)
//        }
//    }, ContextCompat.getMainExecutor(context))
//}
//
//private fun takePhoto(
//    context: android.content.Context,
//    imageCapture: ImageCapture?,
//    executor: java.util.concurrent.Executor,
//    onImageCaptured: (Uri?) -> Unit
//) {
//    val outputDirectory = getOutputDirectory(context)
//    val photoFile = File(
//        outputDirectory,
//        SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(System.currentTimeMillis()) + ".jpg"
//    )
//
//    val outputOptions = ImageCapture.OutputFileOptions.Builder(photoFile).build()
//
//    imageCapture?.takePicture(
//        outputOptions,
//        executor,
//        object : ImageCapture.OnImageSavedCallback {
//            override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
//                val savedUri = Uri.fromFile(photoFile)
//                onImageCaptured(savedUri)
//                Log.d("Camera", "Photo captured: $savedUri")
//            }
//
//            override fun onError(exception: ImageCaptureException) {
//                Log.e("Camera", "Error capturing photo: ${exception.message}", exception)
//                onImageCaptured(null)
//            }
//        }
//    )
//}
//
//private fun getOutputDirectory(context: android.content.Context): File {
//    val mediaDir = context.externalMediaDirs.firstOrNull()?.let {
//        File(it, "QuickStock").apply { mkdirs() }
//    }
//    return if (mediaDir != null && mediaDir.exists())
//        mediaDir else context.filesDir
//}