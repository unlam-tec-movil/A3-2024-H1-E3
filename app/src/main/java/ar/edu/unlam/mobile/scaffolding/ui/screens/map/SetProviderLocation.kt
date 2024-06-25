package ar.edu.unlam.mobile.scaffolding.ui.screens.map

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavHostController
import ar.edu.unlam.mobile.scaffolding.ui.components.MyRational
import ar.edu.unlam.mobile.scaffolding.ui.components.MyTopBar
import ar.edu.unlam.mobile.scaffolding.ui.components.producto.viewmodel.ProductoViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun MapScreen(
    viewModel: ProductoViewModel,
    navController: NavHostController,
) {
    val lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current
    val context = LocalContext.current
    val permissionState =
        rememberMultiplePermissionsState(
            permissions =
                listOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                ),
        )

    LaunchedEffect(Unit) {
        permissionState.launchMultiplePermissionRequest()
    }

    val allPermissionsGranted = permissionState.allPermissionsGranted
    val shouldShowRationale = permissionState.shouldShowRationale

    when {
        allPermissionsGranted -> {
            MapBody(context, lifecycleOwner, viewModel, navController)
        }
        shouldShowRationale -> {
            MyRational(context)
        }
        else -> {
            LaunchedEffect(Unit) {
                navController.popBackStack()
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "UnrememberedMutableState")
@Composable
fun MapBody(
    context: Context,
    lifecycleOwner: LifecycleOwner,
    viewModel: ProductoViewModel,
    navigateBack: NavHostController,
) {
    val buttonEnabled = mutableStateOf(false)
    val locationState = remember { mutableStateOf<LatLng?>(null) }
    LaunchedEffect(Unit) {
        getLocation(context, lifecycleOwner) { location ->
            locationState.value = location
        }
    }
    Scaffold(
        topBar = {
            MyTopBar(
                onNavigateBack = { navigateBack.popBackStack() },
                title = "Indique la ubicación del proveedor",
            )
        },
        bottomBar = {
            MyMapBottomBar(buttonEnabled.value) {
                navigateBack.popBackStack()
            }
        },
    ) {
        locationState.value?.let { location ->
            Map(location = location, viewModel = viewModel) {
                buttonEnabled.value = it
            }
        }
    }
}

@SuppressLint("UnrememberedMutableState")
@Composable
fun Map(
    location: LatLng,
    viewModel: ProductoViewModel,
    callback: (Boolean) -> Unit,
) {
    val cameraPositionState =
        rememberCameraPositionState {
            position = CameraPosition.fromLatLngZoom(location, 15f)
        }
    val mapClicked = mutableStateOf(false)
    var providerLatLng by remember { mutableStateOf<LatLng?>(null) }

    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState,
        onMapClick = {
            mapClicked.value = true
            providerLatLng = it
            viewModel.ubicacionProveedor = providerLatLng
            callback(mapClicked.value)
        },
    ) {
        Marker(
            state = MarkerState(position = location),
        )
        providerLatLng?.let {
            Marker(
                state = MarkerState(position = it),
            )
            cameraPositionState.position = CameraPosition.fromLatLngZoom(it, 20f)
        }
    }
}

@Composable
private fun MyMapBottomBar(
    buttonEnabled: Boolean,
    onButtonClick: () -> Unit,
) {
    Box(
        contentAlignment = Alignment.BottomCenter,
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(10.dp),
    ) {
        Button(
            enabled = buttonEnabled,
            onClick = onButtonClick,
        ) {
            Text(text = "Continuar")
        }
    }
}
