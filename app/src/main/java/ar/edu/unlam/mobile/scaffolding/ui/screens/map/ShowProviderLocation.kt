package ar.edu.unlam.mobile.scaffolding.ui.screens.map

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
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
import ar.edu.unlam.mobile.scaffolding.ui.components.MyTopBar
import ar.edu.unlam.mobile.scaffolding.ui.components.usuario.viewmodel.ProductoViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun MapProviderScreen(
    viewModel: ProductoViewModel,
    navController: NavHostController,
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    val providerLocation = viewModel.productoDetalle()?.ubicacionProveedor

    if (providerLocation != null) {
        MapProviderBody(
            context = context,
            lifecycleOwner = lifecycleOwner,
            viewModel = viewModel,
            providerLocation = providerLocation,
            navigateBack = navController,
        )
    } else {
        Text("Ubicación del proveedor no disponible")
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "UnrememberedMutableState")
@Composable
fun MapProviderBody(
    context: Context,
    lifecycleOwner: LifecycleOwner,
    viewModel: ProductoViewModel,
    providerLocation: LatLng,
    navigateBack: NavHostController,
) {
    val buttonEnabled = remember { mutableStateOf(false) }
    val locationState = remember { mutableStateOf<LatLng?>(null) }

    // Obtener la ubicación actual del usuario
    LaunchedEffect(Unit) {
        getLocation(context, lifecycleOwner) { location ->
            locationState.value = location
            buttonEnabled.value = true // Habilitar el botón una vez que se tiene la ubicación del usuario
        }
    }

    Scaffold(
        topBar = {
            MyTopBar(
                onNavigateBack = { navigateBack.popBackStack() },
                title = "Ubicación del Proveedor",
            )
        },
        bottomBar = {
            locationState.value?.let { location ->
                MyMapBottomBar(
                    buttonEnabled.value,
                    navigateBack,
                    context,
                    location,
                    providerLocation,
                )
            }
        },
    ) {
        locationState.value?.let { location ->
            ProviderMap(location = location, providerLocation = providerLocation)
        }
    }
}

@Composable
fun ProviderMap(
    location: LatLng,
    providerLocation: LatLng,
) {
    val cameraPositionState =
        rememberCameraPositionState {
            position = CameraPosition.fromLatLngZoom(location, 15f)
        }

    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState,
    ) {
        Marker(
            state = MarkerState(position = location),
            title = "Tu ubicación",
        )
        Marker(
            state = MarkerState(position = providerLocation),
            title = "Proveedor",
        )
    }
}

@Composable
private fun MyMapBottomBar(
    buttonEnabled: Boolean,
    navigateBack: NavHostController,
    context: Context,
    origin: LatLng,
    destination: LatLng,
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
                openGoogleMaps(context, origin, destination)
                navigateBack.popBackStack()
            },
        ) {
            Text(text = "Mostrar Ruta")
        }
    }
}

fun openGoogleMaps(
    context: Context,
    origin: LatLng,
    destination: LatLng,
) {
    val uri = "http://maps.google.com/maps?saddr=${origin.latitude},${origin.longitude}&daddr=${destination.latitude},${destination.longitude}"
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
    intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity")
    context.startActivity(intent)
}
