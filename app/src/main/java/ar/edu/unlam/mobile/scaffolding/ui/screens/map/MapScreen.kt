package ar.edu.unlam.mobile.scaffolding.ui.screens.map

import android.annotation.SuppressLint
import android.content.Context
import android.os.Looper
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavHostController
import ar.edu.unlam.mobile.scaffolding.ui.components.MyRational
import ar.edu.unlam.mobile.scaffolding.ui.components.MyTopBar
import ar.edu.unlam.mobile.scaffolding.ui.components.usuario.viewmodel.ProductoViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
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
                    android.Manifest.permission.ACCESS_FINE_LOCATION,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION,
                ),
        )
    LaunchedEffect(Unit) {
        permissionState.launchMultiplePermissionRequest()
    }
    if (permissionState.allPermissionsGranted) {
        MapBody(context, lifecycleOwner, viewModel, navController)
    } else if (permissionState.shouldShowRationale) {
        MyRational(context)
    } else {
        // TODO("Viajar atras")
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
    val providerLocation = remember { mutableStateOf<LatLng?>(null) }
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
            MyMapBotomBar(buttonEnabled.value, navigateBack)
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
    lateinit var providerLatLng: LatLng
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
        if (mapClicked.value) {
            cameraPositionState.position = CameraPosition.fromLatLngZoom(providerLatLng, 20f)
            Marker(
                state = MarkerState(position = providerLatLng),
            )
        }
    }
}

@Composable
fun MyMapBotomBar(
    buttonEnabled: Boolean,
    navigateBack: NavHostController,
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
            onClick = {
                navigateBack.popBackStack()
            },
        ) {
            Text(text = "Continuar")
        }
    }
}

@SuppressLint("MissingPermission")
private fun getLocation(
    context: Context,
    lifecycleOwner: LifecycleOwner,
    callback: (LatLng) -> Unit,
) {
    val locationRequest =
        LocationRequest.create().apply {
            interval = 10000
            fastestInterval = 5000
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }

    val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
    fusedLocationClient.lastLocation.addOnSuccessListener {
        if (it != null) {
            Log.d("First location info", "${it.latitude}, ${it.longitude}")
            callback(LatLng(it.latitude, it.longitude))
        } else {
            callback(LatLng(1.35, 103.87))
        }
    }.addOnFailureListener {
        Log.d("Error on location", it.toString())
    }

    val locationCallBack =
        object : LocationCallback() {
            override fun onLocationResult(result: LocationResult) {
                for (it in result.locations) {
                    Log.d("Location update", "${it.latitude}, ${it.longitude}")
                    callback(LatLng(it.latitude, it.longitude))
                }
            }
        }

    fusedLocationClient.requestLocationUpdates(
        locationRequest,
        locationCallBack,
        Looper.getMainLooper(),
    )
}

private fun onMapClick() {
    // TODO("Al hacer click en una parte el mapa, crear un marcaor y obtener las coordenadas")
}
