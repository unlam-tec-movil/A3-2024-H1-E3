package ar.edu.unlam.mobile.scaffolding.ui.screens.qrScanner

import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanOptions

@Composable
fun QRScannerScreen() {
    val qrResult = remember { mutableStateOf("No results yet") }
    val context = LocalContext.current
    val launcher: ActivityResultLauncher<ScanOptions> =
        rememberLauncherForActivityResult(
            contract = ScanContract(),
            onResult = {
                if (it.contents == null) {
                    Toast.makeText(context, "Cancelled", Toast.LENGTH_LONG).show()
                    qrResult.value = "No results yet"
                } else {
                    Toast.makeText(context, "Scanned: " + it.contents, Toast.LENGTH_LONG).show()
                    qrResult.value = it.contents
                }
            },
        )

    QRScanner(qrResult.value, launcher)
}

@Composable
fun QRScanner(
    result: String,
    launcher: ActivityResultLauncher<ScanOptions>,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Button(onClick = { scanQR(launcher) }) {
            Text(text = "Scan QR")
        }
        Spacer(modifier = Modifier.height(14.dp))
        Text(text = "Result:")
        Text(text = result)
    }
}

private fun scanQR(launcher: ActivityResultLauncher<ScanOptions>) {
    val options = ScanOptions()
    options.setDesiredBarcodeFormats(ScanOptions.ALL_CODE_TYPES)
    options.setPrompt("Scan a QR")
    options.setCameraId(0) // Use a specific camera of the device
    options.setBeepEnabled(false)
    options.setBarcodeImageEnabled(true)

    launcher.launch(options)
}
