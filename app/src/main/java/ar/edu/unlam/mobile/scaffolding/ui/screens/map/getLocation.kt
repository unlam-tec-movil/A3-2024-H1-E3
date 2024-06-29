package ar.edu.unlam.mobile.scaffolding.ui.screens.map

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.lifecycle.LifecycleOwner
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.LatLng

@SuppressLint("MissingPermission")
fun getLocation(
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

    /*
    fusedLocationClient.requestLocationUpdates(

        locationRequest,
        locationCallBack,
        Looper.getMainLooper(),
    )
     */
}
