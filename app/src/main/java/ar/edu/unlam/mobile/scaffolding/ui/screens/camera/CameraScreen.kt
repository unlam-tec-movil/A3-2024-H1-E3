package ar.edu.unlam.mobile.scaffolding.ui.screens.camera

import android.Manifest
import android.annotation.SuppressLint
import android.os.Environment
import android.view.ViewGroup
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.view.LifecycleCameraController
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavHostController
import ar.edu.unlam.mobile.scaffolding.ui.components.MyRational
import ar.edu.unlam.mobile.scaffolding.ui.components.usuario.viewmodel.ProductoViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.accompanist.permissions.shouldShowRationale
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.concurrent.Executor

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun CameraScreen(
    viewModel: ProductoViewModel,
    navController: NavHostController,
) {
    // val permissionState = rememberPermissionState(permission = Manifest.permission.CAMERA)
    val permissionState =
        rememberMultiplePermissionsState(permissions = listOf(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE))
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        // permissionState.launchPermissionRequest()
        permissionState.launchMultiplePermissionRequest()
    }
    if (permissionState.allPermissionsGranted) {
        CameraBody(viewModel, navController)
    } else if (permissionState.shouldShowRationale) {
        MyRational(context)
    } else {
        navController.popBackStack()
    }
    /*
    if (permissionState.status.isGranted) {
        CameraBody(viewModel, navController)
    } else if (permissionState.status.shouldShowRationale) {
        MyRational(context)
    } else {
        navController.popBackStack()
    }

     */
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CameraBody(
    viewModel: ProductoViewModel,
    navController: NavHostController,
) {
    val context = LocalContext.current
    val cameraController =
        remember {
            LifecycleCameraController(context)
        }
    val lifecycle = LocalLifecycleOwner.current

    Scaffold(modifier = Modifier.fillMaxSize(), floatingActionButton = {
        FloatingActionButton(onClick = {
            val executor = ContextCompat.getMainExecutor(context)
            takePicture(viewModel, navController, cameraController, executor)
        }) {
            Text(text = "Tomar Foto")
        }
    }) {
        Camera(
            cameraController = cameraController,
            lifecycle = lifecycle,
            modifier = Modifier.padding(it),
        )
    }
}

@Composable
fun Camera(
    cameraController: LifecycleCameraController,
    lifecycle: LifecycleOwner,
    modifier: Modifier = Modifier,
) {
    cameraController.bindToLifecycle(lifecycle)
    AndroidView(modifier = modifier, factory = { context ->
        val cameraView =
            PreviewView(context).apply {
                layoutParams =
                    ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT,
                    )
            }
        cameraView.controller = cameraController
        cameraView
    })
}

private fun takePicture(
    viewModel: ProductoViewModel,
    navController: NavHostController,
    cameraController: LifecycleCameraController,
    executor: Executor,
) {
    // TODO("La función crea un archivo temporal, hay que hacer que ese archivo se guarde
    //  y se muestre como la imagen del producto/item)
    val picturesDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
    val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(Date())
    val file = File(picturesDir, "IMG_$timeStamp.jpg")
    // val file = File.createTempFile("imagetest", ".jpg")
    val outputDirectory = ImageCapture.OutputFileOptions.Builder(file).build()
    cameraController.takePicture(
        outputDirectory,
        executor,
        object : ImageCapture.OnImageSavedCallback {
            override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                val savedUri = outputFileResults.savedUri
                viewModel.fotoUri = savedUri.toString()
                navController.popBackStack()
            }

            override fun onError(exception: ImageCaptureException) {
                println(exception)
                navController.popBackStack()
            }
        },
    )
}
